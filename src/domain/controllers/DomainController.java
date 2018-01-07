package domain.controllers;

import domain.classes.*;
import enums.*;
import exceptions.*;
import javafx.application.Platform;
import persistence.GamePersistence;
import persistence.PlayerPersistence;
import persistence.RankingPersistence;
import presentation.controllers.PresentationController;
import presentation.runnables.*;
import util.Constants;
import util.Pair;
import util.Translate;
import util.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Domain Controller
 *
 * @author Alejandro de Haro, Rafael Ramírez, Alex Sánchez
 */

public class DomainController
{
    private State state;
    private Set<String> savedGames;

    //private final OldPresentationController oldPresentationController;
    private PresentationController presentationController;

    private BoardController boardController;
    private GameController gameController;
    private PlayerController loggedPlayerController;
    private PlayerController codeMakerController;
    private PlayerController codeBreakerController;
    private Ranking ranking;

    private GamePersistence gamePersistence;
    private PlayerPersistence playerPersistence;
    private RankingPersistence rankingPersistence;

    /**
     * Constructor de controlador de dominio.
     *
     * Instancia un controlador de dominio
     *
     */

    public DomainController()
    {
        state = State.INIT_PROGRAM;
        savedGames = new HashSet<>();

        //oldPresentationController = new OldPresentationController();
        this.presentationController = null;

        boardController = new BoardController();
        gameController = new GameController();
        loggedPlayerController = new PlayerController();
        ranking = new Ranking();

        gamePersistence = new GamePersistence();
        playerPersistence = new PlayerPersistence();
        rankingPersistence = new RankingPersistence();
    }

    /**
     * Setter del controlador de presentación.
     *
     * Establece el controlador de presentación
     * al que se debe enviar toda la información
     * para que se muestre por pantalla lo que
     * queremos.
     *
     * @param presentationController Controlador de presentación.
     */

    public void setPresentationController(final PresentationController presentationController)
    {
        this.presentationController = presentationController;
    }

    /* EXECUTE */

    /**
     * Bloquear ejecucion
     *
     * Informa si se debe bloquear la ejecucion segun el rol
     * de la persona que esta jugando.
     *
     * @param role rol que se debe comparar con el rol de jugador actual.
     * @return true si en la partida solo juegan humanos o
     *         el rol pasado por parametro es el mismo que el del jugador.
     */

    private boolean hasToBlock(final Role role)
    {
        return gameController.getMode() == Mode.HUMAN_VS_HUMAN || loggedPlayerController.getRole() == role;
    }

    /**
     * Logueo de usuario.
     *
     * Se identifica en el programa como el
     * usuario que tiene nombre y contraseña
     * iguales a los parametros introducidos.
     *
     * @param username Nombre del usuario.
     * @param password Contraseña del usuario.
     * @exception IOException Entrada de parametros incorrecta.
     * @exception WrongPasswordException La contraseña no coincide con el nombre de usuario.
     * @exception ClassNotFoundException Error en la creacion del objeto player.
     */

    private void logInUser(String username, String password) throws IOException, WrongPasswordException, ClassNotFoundException
    {
        Player player = playerPersistence.load(username);
        PlayerController playerController = new PlayerController(player);

        boolean b = playerController.checkPassword(password);
        if (!b) throw new WrongPasswordException();

        loggedPlayerController = playerController;
    }

    /**
     * Registro de usuario
     *
     * Crea y guarda un usuario con nombre
     * y contraseña como los introducidos.
     *
     * @param username Nombre de nuevo usuario.
     * @param password Contraseña de nuevo usuario.
     * @param confirmPassword Contraseña de validacion.
     * @exception IOException Entrada de parametros incorrecta.
     * @exception PasswordMismatchException password y confirmPassword son diferentes
     */

    private void registerUser(final String username, final String password, final String confirmPassword) throws IOException, PasswordMismatchException
    {
        boolean b = password.equals(confirmPassword);
        if(!b) throw new PasswordMismatchException();

        b = playerPersistence.exists(username);
        if(b) throw new FileAlreadyExistsException("");

        Player player = loggedPlayerController.newHuman(username, password);

        playerPersistence.save(player);
    }

    /**
     * Crear nueva partida.
     *
     * Crear una nueva partida con los parametros
     * introducidos y asociarla a los controladores
     * correspondientes.
     *
     * @param mode Modo de juego de la nueva partida.
     * @param role Rol del jugador logueado en la nueva partida.
     * @param difficulty Dificultad de la nueva partida.
     */

    private void newGame(final Mode mode, Role role, final Difficulty difficulty)
    {
        loggedPlayerController.restart();
        presentationController.clear();

        List<Player> players = new ArrayList<>();

        PlayerController playerController1 = null;
        PlayerController playerController2 = null;

        switch(mode)
        {
            case HUMAN_VS_HUMAN:
                playerController1 = loggedPlayerController;
                playerController2 = new PlayerController();

                playerController2.newHuman(Utils.autoID());
                break;

            case HUMAN_VS_CPU:
                playerController1 = loggedPlayerController;
                playerController2 = new PlayerController();

                playerController2.newCPU(Utils.autoID());
                break;

            case CPU_VS_CPU:
                role = Role.autoRole();

                playerController1 = new PlayerController();
                playerController2 = new PlayerController();

                playerController1.newCPU(Utils.autoID());
                playerController2.newCPU(Utils.autoID());

                loggedPlayerController.setRole(Role.WATCHER);
                break;

            default:
                break;
        }

        playerController1.setRole(role);
        playerController2.setRole(Role.complementaryRole(role));

        switch (role)
        {
            case CODE_MAKER:
                codeMakerController = playerController1;
                codeBreakerController = playerController2;
                break;
            case CODE_BREAKER:
                codeMakerController = playerController2;
                codeBreakerController = playerController1;
                break;
            default:
                throw new IllegalArgumentException();
        }

        players.add(playerController1.getPlayer());
        players.add(playerController2.getPlayer());

        Board board = boardController.newBoard(difficulty);

        gameController.newGame(Utils.autoID(), difficulty, mode, board, players);
    }

    /**
     * Cargar lista de partidas guardadas
     *
     * Carga en un parametro del controlador, una
     * lista con todas las partidas guardadas por
     * el jugador actual.
     *
     * @exception IOException La lista de partidas no se ha cargado correctamente.
     */

    private void loadSavedGamesList() throws IOException
    {
        savedGames.clear();
        Player loggedPlayer = loggedPlayerController.getPlayer();
        savedGames = playerPersistence.loadSavedGames(loggedPlayer.getId());
    }

    /**
     * Cargar partida
     *
     * Carga la partida con nombre id y la asocia
     * con los controladores necesarios.
     *
     * @param id Nombre de la partida que se quiere cargar.
     * @throws IOException En caso que la partida no pueda cargarse por algún motivo.
     * @throws ClassNotFoundException En caso que el archivo cargado no contuviese una partida.
     */

    private void loadGame(String id) throws IOException, ClassNotFoundException
    {
        Player loggedPlayer = loggedPlayerController.getPlayer();
        String player = loggedPlayer.getId();
        Game game = gamePersistence.load(id, player);
        gameController.setGameByReference(game);
        boardController.setBoardByReference(game.getBoard());

        List<Player> players = game.getPlayers();

        for(int i = 0; i < players.size(); ++i)
        {
            PlayerController playerController;

            String playerId = players.get(i).getId();

            if(playerId.equals(loggedPlayerController.getId()))
            {
                loggedPlayerController.setRole(players.get(i).getRole());
                players.set(i, loggedPlayerController.getPlayer());
                playerController = loggedPlayerController;
            }
            else
            {
                playerController = new PlayerController(players.get(i));
            }

            Role role = playerController.getRole();

            switch (role)
            {
                case CODE_MAKER:
                    codeMakerController = playerController;
                    break;
                case CODE_BREAKER:
                    codeBreakerController = playerController;
                    break;
                case WATCHER:
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        presentationController.clear();

        List<Turn> turnSet = boardController.getTurnSet();
        List<List<Color>> codes = new ArrayList<>(turnSet.size());
        List<List<Color>> corrections = new ArrayList<>(turnSet.size());

        for(final Turn turn : turnSet)
        {
            List<Color> code = turn.getCodePins();
            List<Color> correction = turn.getCorrectionPins();

            codes.add(code);

            if(!correction.isEmpty())
            {
                corrections.add(correction);
            }
        }

        if(boardController.getSolution() != null)
        {
            presentationController.setSolution(boardController.getSolution().getCodePins());
        }
        presentationController.setCodes(codes);
        presentationController.setCorrections(corrections);
    }

    /**
     * Guardar Partida
     *
     * Guarda la partida actual en un archivo
     * binario.
     *
     * @exception IOException La partida no se ha guardado correctamente.
     */

    private void saveGame() throws IOException
    {
        Game game = gameController.getGame();
        String gameId = gameController.getId();
        String playerId = loggedPlayerController.getId();

        gamePersistence.save(game, playerId);
        playerPersistence.savePlayerGame(gameId, playerId);
    }

    /**
     * Renombrar partida
     *
     * Cambia el nombre de la partida actual
     * por el nombre del parametro introducido
     *
     * @param gameId Nombre de la partida
     */

    private void renameGame(final String gameId)
    {
        Game game = gameController.getGame();
        game.setId(gameId);
    }

    /**
     * Eliminar partida.
     *
     * Envia la orden a persistencia de
     * eliminar una partida con nombre gameId
     *
     * @param gameId Nombre de la partida.
     * @throws IOException nombre introducido incorrecto
     */

    public void deleteGame(final String gameId) throws IOException
    {
        String playerId = loggedPlayerController.getId();
        gamePersistence.setPlayerPath(playerId + "/");
        gamePersistence.delete(gameId);

        playerPersistence.deletePlayerGame(gameId, playerId);
    }

    /**
     * Cambio de nombre de usuario
     *
     * Cambia el nombre del usuario actual
     * al nombre que se ha introducido
     *
     * @param username Nombre de usuario nuevo.
     * @throws IOException Nombre introducido incorrecto
     */

    public void renameUsername(final String username) throws IOException
    {
        Player loggedPlayer = loggedPlayerController.getPlayer();
        String player = loggedPlayer.getId();

        playerPersistence.renamePlayer(player, username);
        loggedPlayer.setId(username);
        playerPersistence.delete(username);
        playerPersistence.save(loggedPlayer);
    }

    /**
     * Cambio de contraseña
     *
     * Cambia la contraseña del usuario actual
     * por una nueva pasada por parametro.
     *
     * @param oldPassword Contraseña actual
     * @param newPassword Contraseña nueva
     * @param confirmNewPassword Comprobacion de la nueva contraseña
     * @exception IOException Error al introducir los parametros.
     * @exception WrongPasswordException Contraseña actual no coincide con la pasada por parametro oldPassword.
     * @exception PasswordMismatchException newPassword y confirmNewPassword no coinciden
     */

    private void changePassword(final String oldPassword, final String newPassword, final String confirmNewPassword) throws IOException, WrongPasswordException, PasswordMismatchException
    {
        boolean b = loggedPlayerController.checkPassword(oldPassword);
        if(!b) throw new WrongPasswordException();

        b = newPassword.equals(confirmNewPassword);
        if(!b) throw new PasswordMismatchException();

        Player loggedPlayer = loggedPlayerController.getPlayer();
        loggedPlayer.setPassword(newPassword);
        String username = loggedPlayerController.getId();
        deleteUser(username);

        Player player = loggedPlayerController.newHuman(username, newPassword);

        playerPersistence.save(player);
    }

    /**
     * Borrar usuario
     *
     * Borra el usuario con el mismo nombre
     * que el parametro introducido.
     *
     * @param username Nombre de usuario que se va a borrar.
     * @exception IOException Error al introducir el parametro.
     */

    private void deleteUser(final String username) throws IOException
    {
        playerPersistence.delete(username);
    }

    /**
     * Borrar ficheros de configuracion
     *
     * Borra el fichero de configuracion del
     * usuario pasado por parametro.
     *
     * @param username Nombre de usuario cuyos ficheros se van a borrar
     */
    private void deleteConfigFile(final String username)
    {
        playerPersistence.deleteConfigFile(username);
    }

    /**
     * Jugar
     *
     * Se ejecuta la accion que corresponde
     * al jugador del controlador que se pasa
     * como parametro
     *
     * @param playerController Controlador de jugador con los datos del jugador actual cargados
     * @exception IllegalArgumentException Parametro no valido
     * @exception ReservedKeywordException Se introduce un valor que indica un retroceso en los menus
     * @exception IllegalActionException Accion no valida
     * @exception InterruptedException Threat que se esta ejecutando es interrumpido
     */

    private void play(PlayerController playerController) throws IllegalArgumentException, ReservedKeywordException, IllegalActionException, InterruptedException
    {
        Action action;

        Code code = boardController.getSolution();
        Turn lastTurn = boardController.getLastTurn();
        Difficulty difficulty = boardController.getDifficulty();

        action = playerController.play(difficulty, lastTurn, code, boardController.isFirstTurn());

        if(action != null)
        {
            boardController.checkAction(action);
            boardController.addAction(action);

            updateBoard();
        }
    }

    /**
     * Jugar como CodeBreaker
     *
     * Se ejecuta un turno como
     * CodeBreaker.
     *
     * @exception IllegalArgumentException Algun parametro no es valido.
     * @exception ReservedKeywordException Se ha introducido un valor que se usa para moverse atras en los menus.
     * @exception IllegalActionException Accion no valida.
     * @exception InterruptedException El threat que se esta ejecutando ha sido interrumpido.
     */

    private void playCodeBreaker() throws IllegalArgumentException, ReservedKeywordException, IllegalActionException, InterruptedException
    {
        play(codeBreakerController);
    }

    /**
     * Jugar como Corrector
     *
     * Se ejecuta un turno como
     * Corrector de codigo.
     *
     * @exception IllegalArgumentException Algun parametro no es valido.
     * @exception ReservedKeywordException Se ha introducido un valor que se usa para moverse atras en los menus.
     * @exception IllegalActionException Accion no valida.
     * @exception InterruptedException El threat que se esta ejecutando ha sido interrumpido.
     */

    private void playCodeCorrecter() throws IllegalArgumentException, ReservedKeywordException, IllegalActionException, InterruptedException
    {
        play(codeMakerController);
        if(!boardController.isFirstTurn()) gameController.pointsEndTurn();
    }

    /**
     * Jugar como CodeMaker
     *
     * Se ejecuta un turno como
     * CodeMaker.
     *
     * @exception IllegalArgumentException Algun parametro no es valido.
     * @exception ReservedKeywordException Se ha introducido un valor que se usa para moverse atras en los menus.
     * @exception IllegalActionException Accion no valida.
     * @exception InterruptedException El threat que se esta ejecutando ha sido interrumpido.
     */

    private void playCodeMaker() throws IllegalArgumentException, ReservedKeywordException, IllegalActionException, InterruptedException
    {
        play(codeMakerController);
        if(!boardController.isFirstTurn()) gameController.pointsEndTurn();
    }

    /**Generar pista
     *
     * Se crea una pista segun el
     * codigo solucion de la partida
     * actual.
     *
     * @return String de la pista.
     * @exception GameNotStartedException La partida no ha comenzado o no tiene solucion.
     */

    private String generateHint() throws GameNotStartedException
    {
        boolean b = gameController.hasStarted();
        if(!b) throw new GameNotStartedException();

        int number = 0;
        int type = ThreadLocalRandom.current().nextInt(0, 2);

        Color color;
        String message;
        Code solution = boardController.getSolution();
        List<Color> solutionPins = solution.getCodePins();

        switch(type)
        {
            case 0:
                number = (int) (Math.random() * solution.size());
                color = solutionPins.get(number);

                message = "Token in position " + (number + 1) + " is " + color.getStrDescription().toLowerCase();
                break;

            case 1:
                Difficulty difficulty = boardController.getDifficulty();
                int numColors = Constants.getNumColorsByDifficulty(difficulty);
                color = Color.getRandomColor(numColors);

                for(final Color pin : solutionPins)
                {
                    if(pin == color)
                    {
                        ++number;
                    }
                }

                message = "There " + ((number == 1) ? "is" : "are") + " " + number + " " + color.getStrDescription().toLowerCase() + " token" + ((number == 1) ? "" : "s");
                break;

            default:
                throw new IllegalArgumentException();
        }

        return message;
    }

    /* USER INTERACTION METHODS */

    /**
     * Actualizar Tablero
     *
     * Se actualizan los valores
     * del tablero de la partida actual.
     */

    private void updateBoard()
    {
        Turn lastTurn = boardController.getLastTurn();

        if(lastTurn == null)
        {
            Code solution = boardController.getSolution();
            presentationController.setSolution(solution.getCodePins());
        }
        else
        {
            Code code = lastTurn.getCode();
            Code correction = lastTurn.getCorrectionCode();

            if(correction.getCodePins().isEmpty())
            {
                presentationController.addCode(code.getCodePins());
            }
            else
            {
                presentationController.addCorrection(correction.getCodePins());
            }
        }
    }

    /**
     * Cargar Ranking de puntuaciones.
     *
     * Obtiene de los controladores de
     * persistencia la lista de mejores
     * puntuaciones.
     *
     * @exception IOException Error en entrada de parametros.
     * @exception ClassNotFoundException Un objeto no se ha creado correctamente.
     */

    private void loadRanking() throws IOException, ClassNotFoundException
    {
        if(rankingPersistence.exists())
        {
            ranking = rankingPersistence.load();
        }
    }

    /**
     * Guardar Ranking de puntuaciones.
     *
     * Borra la antigua lista de mejores
     * puntuaciones y guarda una nueva.
     *
     * @exception IOException Error en entrada de parametros.
     */

    private void saveRanking() throws IOException
    {
        if(rankingPersistence.exists())
        {
            rankingPersistence.delete();
        }
        rankingPersistence.save(ranking);
    }

    /**
     * Actualizar Ranking de puntuaciones.
     *
     * Compara una puntuacion con el resto
     * de puntuaciones del ranking y la
     * coloca en la posicion correcta si es
     * necesario.
     */

    private void updateRanking()
    {
        String playerId = loggedPlayerController.getId();
        int points = gameController.getPoints();

        if(ranking.toTopTen(points))
        {
            ranking.addToTopTen(playerId, points);
        }
    }

    /**
     * runOnGUIThreadAndWait
     *
     * Carga un threat y manda la orden
     * de esperar.
     *
     * @param runnable Código a ejecutar en el thread.
     * @exception InterruptedException El threat que se estaba ejecutando ha sido interrumpido.
     */

    private void runOnGUIThreadAndWait(final Runnable runnable) throws InterruptedException
    {
        Platform.runLater(runnable);

        while(!PresentationController.threadHasFinished())
        {
            wait();
        }

        PresentationController.clearThreadHasFinished();
    }

    /**
     * Actualizar vista
     *
     * Manda al threat la vista que
     * debe cargar.
     *
     * @param view Vista para cargar.
     * @exception InterruptedException El threat que se estaba ejecutando ha sido interrumpido.
     */

    private void updateView(final View view) throws InterruptedException
    {
        runOnGUIThreadAndWait(new UpdateViewRunnable(presentationController, view.getViewFile()));
    }

    /**
     * Ejecuta un pop up
     *
     * Manda al threat la informacion
     * necesaria para cargar el pop up
     * correspondiente.
     *
     * @param popUpWindowStyle Estilo del popUp.
     * @param view Vista para cargar.
     * @exception InterruptedException El threat que se estaba ejecutando ha sido interrumpido.
     */

    private void popUpView(final PopUpWindowStyle popUpWindowStyle, final View view) throws InterruptedException
    {
        runOnGUIThreadAndWait(new PopUpViewRunnable(presentationController, popUpWindowStyle, view.getViewFile()));
    }

    /**
     * Muestra mensaje de error.
     *
     * Manda el mensaje al threat.
     *
     * @param message Mensaje para mostrar.
     */

    private void errorMessage(final String message)
    {
        Platform.runLater(new ProcessInfoRunnable(presentationController, message));
    }

    /**
     * Mensaje y espera
     *
     * Manda el mensaje pasado como
     * parametro al threat y manda la orden de esperar.
     *
     * @param message Mensaje para mostrar.
     * @exception InterruptedException El threat que se estaba ejecutando ha sido interrumpido.
     */

    private void showMessageAndWait(final String message) throws InterruptedException
    {
        runOnGUIThreadAndWait(new ProcessInfoRunnable(presentationController, message));
    }

    /**
     * Muestra pista.
     *
     * Manda la pista al threat.
     *
     * @param message Mensaje pista para mostrar.
     * @exception InterruptedException El threat que se estaba ejecutando ha sido interrumpido.
     */

    private void showHint(final String message) throws InterruptedException
    {
        runOnGUIThreadAndWait(new ProcessInfoRunnable(presentationController, message));
    }

    /**
     * Muestra la puntuacion final
     *
     * Manda la puntuacion final al threat.
     *
     * @param message Mensaje puntuacion para mostrar.
     * @exception InterruptedException El threat que se estaba ejecutando ha sido interrumpido.
     */

    private void showScore(final String message) throws InterruptedException
    {
        runOnGUIThreadAndWait(new ProcessInfoRunnable(presentationController, message));
    }

    /**
     * Muestra informacion.
     *
     * Pasa el texto de informacion al threat.
     *
     * @exception InterruptedException El threat que se estaba ejecutando ha sido interrumpido.
     */

    private void showInfo() throws InterruptedException
    {
        runOnGUIThreadAndWait(new ProcessInfoRunnable(presentationController, Constants.INFO_MESSAGE));
    }

    /**
     * Muestra partidas guardadas
     *
     * Manda al threat la lista de
     * partidas guardadas que se pueden
     * cargar.
     *
     * @param savedGames Lista de partidas para mostrar.
     * @exception InterruptedException El threat que se estaba ejecutando ha sido interrumpido.
     */

    private void showLoadedGames(final Set<String> savedGames) throws InterruptedException
    {
        runOnGUIThreadAndWait(new ShowLoadedGamesRunnable(presentationController, savedGames));
    }

    /**
     * Mostrar nombre de usuario.
     *
     * Manda al threat el nombre del usuario
     * actual.
     *
     * @exception InterruptedException El threat que se estaba ejecutando ha sido interrumpido.
     */

    private void showUsername() throws InterruptedException
    {
        runOnGUIThreadAndWait(new ProcessInfoRunnable(presentationController, loggedPlayerController.getId()));
    }

    /**
     * Mostrar campo de texto de cambio de nombre.
     *
     * Manda al threat el campo de
     * cambio de nombre de usuario.
     *
     * @exception InterruptedException El threat que se estaba ejecutando ha sido interrumpido.
     */

    private void showRenameUsernameTextField() throws InterruptedException
    {
        runOnGUIThreadAndWait(new ProcessInfoRunnable(presentationController, Constants.EDIT_USERNAME));
    }

    /**
     * Mostrar ranking.
     *
     * Manda al threat la lista de nombres
     * y puntuaciones que forman el ranking.
     *
     * @param topTen Lista de jugadores y puntuaciones para mostrar.
     * @exception InterruptedException El threat que se estaba ejecutando ha sido interrumpido.
     */

    private void showRanking(List<Pair<String, Integer>> topTen) throws InterruptedException
    {
        runOnGUIThreadAndWait(new ProcessInfoRunnable(presentationController, topTen));
    }

    /**
     * Renderizar tablero
     *
     * Manda al threat la dificultad de
     * la partida para establecer el tamaño
     * de tablero.
     *
     * @param difficulty Dificultad de la partida.
     */

    private void renderBoard(final Difficulty difficulty)
    {
        Platform.runLater(new RenderBoardRunnable(presentationController, difficulty));
    }

    /**
     * Renderizar ultimo turno
     *
     * Manda al threat el ultimo turno
     * jugado.
     */

    private void renderLastTurn()
    {
        Platform.runLater(new RenderLastTurnRunnable(presentationController, false));
    }

    /**
     * Renderizar ultimo turno y esperar.
     *
     * Manda al threat el ultimo turno jugado
     * y manda la orden de esperar.
     *
     * @exception InterruptedException El threat que se estaba ejecutando ha sido interrumpido.
     */

    private void renderLastTurnBlocking() throws InterruptedException
    {
        runOnGUIThreadAndWait(new RenderLastTurnRunnable(presentationController, true));
    }

    /**
     * Actualizar runnable del tablero.
     *
     * Actualiza el ejecutable segun la accion
     * que se vaya a hacer.
     *
     * @param playingAction Accion del jugador que va a jugar.
     * @return runnable del tablero.
     */

    private Runnable getUpdateBoardRunnable(final PlayingAction playingAction)
    {
        Runnable runnable;

        switch (playingAction)
        {
            case CODE_BREAK:
                runnable = () -> presentationController.updateToCodeBreakerBoard();
                break;
            case CODE_CORRECT:
                runnable = () -> presentationController.updateToCodeCorrecterBoard();
                break;
            case CODE_MAKE:
                runnable = () -> presentationController.updateToCodeMakerBoard();
                break;
            default:
                throw new IllegalArgumentException();
        }

        return runnable;
    }

    /**
     * Actualizar tablero
     *
     * Actualiza el tablero segun la
     * proxima accion que se vaya a hacer.
     *
     * @param playingAction Proxima accion del jugador.
     */

    private void updateBoardTo(final PlayingAction playingAction)
    {
        Runnable runnable = getUpdateBoardRunnable(playingAction);

        Platform.runLater(runnable);
    }

    /* MAIN STATE MACHINE */

    /**
     * Máquina de estados
     *
     * Ejecuta las funciones necesarias segun el
     * estado en el que se encuentra y cambia el
     * estado segun el resultado de las funciones
     * ejecutadas.
     *
     * @throws InterruptedException el threat actual ha sido interrumpido
     */

    public synchronized void exe() throws InterruptedException
    {
        int returnState;
        String returnString = null;
        String gameId = null;
        String username;
        String password;
        String confirmPassword;
        String currentPassword;

        Mode mode = null;
        Role role = null;
        Difficulty difficulty = null;

        while(!state.equals(State.CLOSE_PROGRAM))
        {
            switch(state)
            {
                case CHECK_GAME_HAS_FINISHED:
                    boolean hasFinished = gameController.hasFinished();

                    if(hasFinished)
                    {
                        try
                        {
                            renderLastTurn();

                            if(gameController.getMode() != Mode.CPU_VS_CPU)
                            {
                                updateRanking();
                                saveRanking();
                            }
                        }
                        catch (IOException e)
                        {
                            errorMessage(Constants.RANKING_LOAD_ERROR);
                        }
                    }

                    state = Translate.booleanModeToStateCheckGameHasFinished(hasFinished);

                    break;

                case CLOSE_PROGRAM:
                    // This state is never reached since it is the loop exit condition.
                    //
                    // Its presence here is only for completeness purposes.
                    break;

                case CLOSE_PROGRAM_WARNING:
                    popUpView(PopUpWindowStyle.WARNING, View.CLOSE_PROGRAM_WARNING_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateCloseProgramWarning(returnState);

                    break;

                case CONTINUE_GAME:
                    state = State.RENDER_BOARD;
                    break;

                case DELETE_GAME:
                    try
                    {
                        deleteGame(gameId);
                    }
                    catch (IOException e)
                    {
                        errorMessage(Constants.GAME_DELETING_ERROR);
                    }

                    state = State.LOAD_GAME_MENU;
                    break;

                case EDIT_USER_MENU:
                    updateView(View.EDIT_USER_VIEW);
                    showUsername();

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateEditUserMenu(returnState);

                    break;

                case EDIT_USERNAME:
                    showRenameUsernameTextField();
                    username = PresentationController.getUsername();

                    try
                    {
                        renameUsername(username);
                    }
                    catch(IOException e)
                    {
                        errorMessage(Constants.USERNAME_ALREADY_EXISTS);
                    }
                    state = State.EDIT_USER_MENU;
                    break;

                case EDIT_PASSWORD:
                    currentPassword = PresentationController.getCurrentPassword();
                    password = PresentationController.getNewPassword();
                    confirmPassword = PresentationController.getConfirmPassword();

                    try
                    {
                        changePassword(currentPassword, password, confirmPassword);
                        errorMessage(Constants.NEW_PASSWORD_SAVED);
                    }
                    catch(WrongPasswordException e)
                    {
                        errorMessage(Constants.WRONG_PASSWORD);
                    }
                    catch (PasswordMismatchException pme)
                    {
                        errorMessage(Constants.PASSWORDS_MUST_MATCH);
                    }
                    catch (IOException ioe)
                    {
                        errorMessage(Constants.PASSWORD_CHANGE_ERROR);
                    }
                    finally
                    {
                        state = State.EDIT_USER_MENU;
                    }

                    break;

                case EXIT_CURRENT_GAME:
                    state = State.MAIN_MENU;
                    break;

                case EXIT_CURRENT_GAME_WARNING:
                    popUpView(PopUpWindowStyle.WARNING, View.EXIT_CURRENT_GAME_WARNING_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateExitCurrentGameWarning(returnState);

                    break;

                case GAME_OVER_MENU:
                    popUpView(PopUpWindowStyle.GAME_OVER, View.GAME_OVER_VIEW);
                    showScore(String.valueOf(gameController.getGame().getPoints()));
                    /*gameController.pointsEndGame();*/

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateGameOverMenu(returnState);


                    break;

                case GAME_PAUSE_MENU:
                    updateView(View.PAUSE_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateGamePauseMenu(returnState);

                    break;

                case HINT_MENU:
                    popUpView(PopUpWindowStyle.INTERACTION, View.HINT_VIEW);
                    try
                    {
                        returnString = generateHint();
                        gameController.pointsClue();
                    }
                    catch (GameNotStartedException e)
                    {
                        returnString = Constants.GAME_NOT_STARTED_ERROR;
                    }
                    finally
                    {
                        showHint(returnString);

                        returnState = PresentationController.getReturnState();
                        state = Translate.intToStateHintMenu(returnState);
                    }
                    break;

                case INFO_MENU:
                    updateView(View.INFO_VIEW);
                    showInfo();

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateInfoMenu(returnState);

                    break;

                case INIT_PROGRAM:
                    state = State.LOAD_RANKING;
                    
                    break;

                case INIT_SESSION_MENU:
                    updateView(View.INIT_SESSION_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateInitSessionMenu(returnState);

                    break;

                case LAST_ACTIONS:
                    Platform.exit();
                    state = State.CLOSE_PROGRAM;
                    break;

                case LOAD_GAME:
                    try
                    {
                        loadGame(gameId);
                        state = State.RENDER_BOARD;
                    }
                    catch(FileNotFoundException e)
                    {
                        errorMessage(Constants.GAME_DOESNT_EXISTS);
                        state = State.LOAD_GAME_MENU;
                    }
                    catch (IOException | ClassNotFoundException e)
                    {
                        errorMessage(Constants.GAME_LOADING_ERROR);
                        state = State.LOAD_GAME_MENU;
                    }
                    break;

                case LOAD_GAME_MENU:
                    updateView(View.LOAD_GAME_VIEW);

                    try
                    {
                        loadSavedGamesList();
                    }
                    catch (FileNotFoundException e)
                    {
                        errorMessage(Constants.PLAYER_HAS_NO_SAVED_GAMES);
                    }
                    catch (IOException e)
                    {
                        errorMessage(Constants.SAVED_GAMES_LIST_LOAD_ERROR);
                    }
                    finally
                    {
                        showLoadedGames(savedGames);

                        returnState = PresentationController.getReturnState();
                        gameId = PresentationController.getGameId();
                        state = Translate.int2StateLoadGameMenu(returnState);
                    }

                    break;

                case LOAD_RANKING:
                    try
                    {
                        loadRanking();
                    }
                    catch (IOException | ClassNotFoundException e)
                    {
                        errorMessage(Constants.RANKING_LOADING_ERROR);
                    }
                    finally
                    {
                        Thread.sleep(Constants.LOADING_TIME_MS);
                        state = State.INIT_SESSION_MENU;
                    }
                    break;

                case LOG_IN_MENU:
                    updateView(View.LOG_IN_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.intToStateLogInMenu(returnState);

                    break;

                case LOG_IN_USER:
                    try
                    {
                        username = PresentationController.getUsername();
                        password = PresentationController.getPassword();

                        logInUser(username, password);
                        state = State.MAIN_MENU;
                    }
                    catch(FileNotFoundException | WrongPasswordException e)
                    {
                        errorMessage(Constants.WRONG_USERNAME_OR_PASSWORD);
                        state = State.LOG_IN_MENU;
                    }
                    catch(IOException | ClassNotFoundException e)
                    {
                        errorMessage(Constants.USER_LOADING_ERROR);
                        state = State.LOG_IN_MENU;
                    }
                    break;

                case LOG_OUT_WARNING:
                    popUpView(PopUpWindowStyle.WARNING, View.LOG_OUT_WARNING_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateLogOutWarning(returnState);

                    break;

                case MAIN_MENU:
                    updateView(View.MAIN_MENU_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateMainMenu(returnState);

                    break;

                case NEW_GAME:
                    newGame(mode, role, difficulty);
                    state = State.RENDER_BOARD;
                    break;

                case NEW_GAME_MENU:
                    updateView(View.NEW_GAME_VIEW);

                    returnState = PresentationController.getMode();
                    mode = Translate.int2Mode(returnState);

                    returnState = PresentationController.getRole();
                    role = Translate.int2Role(returnState);

                    returnState = PresentationController.getDifficulty();
                    difficulty = Translate.int2Difficulty(returnState);

                    returnState = PresentationController.getReturnState();
                    state = Translate.int2StateGameSettingsMenu(returnState);

                    break;

                case PLAY_CODE_BREAKER:
                    try
                    {
                        if(hasToBlock(Role.CODE_BREAKER))
                        {
                            updateBoardTo(PlayingAction.CODE_BREAK);
                            renderLastTurnBlocking();
                        }
                        else
                        {
                            renderLastTurn();
                        }

                        playCodeBreaker();

                        state = State.PLAY_CODE_CORRECTER;
                    }
                    catch (IllegalActionException e)
                    {
                        errorMessage(e.getMessage());
                    }
                    catch (ReservedKeywordException e)
                    {
                        returnState = PresentationController.getReturnState();
                        state = Translate.intToStatePlay(returnState);
                    }
                    break;

                case PLAY_CODE_CORRECTER:
                    try
                    {

                        if(hasToBlock(Role.CODE_MAKER))
                        {
                            updateBoardTo(PlayingAction.CODE_CORRECT);
                            renderLastTurnBlocking();
                        }
                        else
                        {
                            renderLastTurn();
                        }

                        playCodeCorrecter();

                        state = State.CHECK_GAME_HAS_FINISHED;
                    }
                    catch (IllegalActionException e)
                    {
                        errorMessage(e.getMessage());
                    }
                    catch (ReservedKeywordException e)
                    {
                        returnState = PresentationController.getReturnState();
                        state = Translate.intToStatePlay(returnState);
                    }
                    break;

                case PLAY_CODE_MAKER:
                    try
                    {

                        if(hasToBlock(Role.CODE_MAKER))
                        {
                            updateBoardTo(PlayingAction.CODE_MAKE);
                            renderLastTurnBlocking();
                        }
                        else
                        {
                            renderLastTurn();
                        }

                        playCodeMaker();

                        state = State.CHECK_GAME_HAS_FINISHED;
                    }
                    catch (IllegalActionException e)
                    {
                        errorMessage(e.getMessage());
                    }
                    catch (ReservedKeywordException e)
                    {
                        returnState = PresentationController.getReturnState();
                        state = Translate.intToStatePlay(returnState);
                    }
                    break;

                case PLAY_TURN:
                    boolean hasStarted = gameController.hasStarted();
                    boolean hasCodeToCorrect = boardController.hasCodeToCorrect();

                    state = Translate.booleanToStatePlayTurn(hasStarted, hasCodeToCorrect);

                    break;

                case RANKING_MENU:
                    updateView(View.RANKING_VIEW);
                    showRanking(ranking.getTopTen());

                    returnState = PresentationController.getReturnState();
                    state = Translate.intToStateRankingMenu(returnState);

                    break;

                default:
                    break;

                case REGISTER_MENU:
                    updateView(View.REGISTER_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.intToStateRegisterMenu(returnState);

                    break;

                case REGISTER_USER:
                    try
                    {
                        username = PresentationController.getUsername();
                        password = PresentationController.getPassword();
                        confirmPassword = PresentationController.getConfirmPassword();

                        registerUser(username, password, confirmPassword);
                        state = State.MAIN_MENU;
                    }
                    catch (PasswordMismatchException e)
                    {
                        errorMessage(Constants.PASSWORDS_MUST_MATCH);
                        state = State.REGISTER_MENU;
                    }
                    catch(FileAlreadyExistsException e)
                    {
                        errorMessage(Constants.USERNAME_ALREADY_EXISTS);
                        state = State.REGISTER_MENU;
                    }
                    catch (IllegalArgumentException iae)
                    {
                        errorMessage(Constants.EMPTY_PASSWORD_ERROR);
                        state = State.REGISTER_MENU;
                    }
                    catch(IOException e)
                    {
                        errorMessage(Constants.USER_REGISTERING_ERROR);
                        state = State.REGISTER_MENU;
                    }
                    break;

                case RENDER_BOARD:
                    updateView(View.GAME_IN_PROGRESS_VIEW);
                    renderBoard(boardController.getDifficulty());

                    state = State.CHECK_GAME_HAS_FINISHED;
                    break;

                case RESTART_GAME:
                    mode = gameController.getMode();
                    role = loggedPlayerController.getRole();
                    difficulty = boardController.getDifficulty();

                    state = State.NEW_GAME;
                    break;

                case SAVE_GAME:
                    try
                    {
                        renameGame(PresentationController.getGameId());
                        saveGame();
                        state = State.GAME_PAUSE_MENU;
                    }
                    catch (FileAlreadyExistsException e)
                    {
                        state = State.SAVE_GAME_OVERWRITE_MENU;
                    }
                    catch(IOException e)
                    {
                        popUpView(PopUpWindowStyle.WARNING, View.ERROR_MESSAGE_WARNING_VIEW);
                        showMessageAndWait(Constants.GAME_SAVING_ERROR);

                        returnState = PresentationController.getReturnState();
                        state = Translate.intToStateErrorMessageWarning(returnState);
                    }
                    break;

                case SAVE_GAME_MENU:
                    popUpView(PopUpWindowStyle.INTERACTION, View.SAVE_GAME_VIEW);

                    returnState = PresentationController.getReturnState();
                    state = Translate.intToStateSaveGameMenu(returnState);

                    break;

                case SAVE_GAME_OVERWRITE_MENU:
                    popUpView(PopUpWindowStyle.INTERACTION, View.SAVE_GAME_OVERWRITE_VIEW);

                    returnState = PresentationController.getReturnState();

                    try
                    {
                        if(returnState == 1)
                        {
                            gamePersistence.delete(gameController.getId());
                        }

                        state = Translate.intToStateSaveGameOverwrite(returnState);
                    }
                    catch (IOException ei)
                    {
                        popUpView(PopUpWindowStyle.WARNING, View.ERROR_MESSAGE_WARNING_VIEW);
                        showMessageAndWait(Constants.GAME_DELETING_ERROR);

                        returnState = PresentationController.getReturnState();
                        state = Translate.intToStateErrorMessageWarning(returnState);
                    }
                    break;
            }
        }
    }
}
