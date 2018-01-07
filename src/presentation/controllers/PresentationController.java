package presentation.controllers;

import domain.controllers.DomainController;
import enums.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Alejandro de Haro
 */

public abstract class PresentationController
{
    /* ATTRIBUTES */

    private String currentViewFile;

    protected static Stage mainStage;
    protected static Stage popUpStage;

    protected static Stage currentStage;

    private static final DomainController DOMAIN_CONTROLLER = new DomainController();

    private static volatile boolean threadFinished = false;

    private static volatile int returnState;
    protected static volatile int mode;
    protected static volatile int difficulty;
    protected static volatile int role;
    protected static volatile String gameId;
    protected static volatile String username;
    protected static volatile String password;
    protected static volatile String confirmPassword;
    protected static volatile String newPassword;
    protected static volatile String currentPassword;

    protected static Difficulty boardDifficulty;
    protected static List<Color> solution = new ArrayList<>();
    protected static List<List<Color>> codes = new ArrayList<>();
    protected static List<List<Color>> corrections = new ArrayList<>();
    protected static List<Color> currentTurn = new ArrayList<>();

    /* PROTECTED METHODS */

    /**
     * Sincronización con controlador de dominio.
     *
     * Sincroniza el controlador de presentación con el de dominio
     * y notifica a este último de que presentación ha terminado de realizar
     * las tareas para las que había sido bloqueado.
     */
    protected void endAction()
    {
        PresentationController.threadFinished = true;

        synchronized(DOMAIN_CONTROLLER)
        {
            DOMAIN_CONTROLLER.notify();
        }
    }

    /**
     * Método plantilla de pulsado de botón.
     *
     * Método plantilla que ejecuta su código cuando cualquier botón de la vista
     * del controlador se pulsa.
     */
    protected void pressButtonTemplateAction()
    {

    }

    /**
     * Método de gestión de botón genérico.
     *
     * Método de gestión de las acciones a llevar a cabo al pulsar un botón genérico.
     *
     * @param value Valor del botón pulsado.
     */
    protected void pressButtonAction(final int value)
    {
        PresentationController.returnState = value;
        pressButtonTemplateAction();
        endAction();
    }

    /**
     * Registro en controlador de dominio.
     *
     * Registra el controlador de presentación actual en el controlador de dominio
     * como controlador de presentación vigente con el que éste puede interactuar.
     */
    protected void registerToDomainController()
    {
        DOMAIN_CONTROLLER.setPresentationController(this);
    }

    /* GUI ELEMENT METHODS */

    /**
     * Generación de pin de color.
     *
     * Genera un pin del color indicado con su estilo CSS y
     * un tamaño por defecto.
     *
     * @param color Color del pin a generar.
     * @return Pin correctamente configurado.
     */
    protected Circle getNewPin(final Color color)
    {
        Circle pin = new Circle();

        pin.setRadius(25);
        pin.getStyleClass().add(color.getCssStyleClass());

        return pin;
    }

    /* PRIVATE METHODS */

    /**
     * Setter de archivo de vista actual.
     *
     * Permite modificar el archivo de vista que hay cargado en un momento dado
     * en la ventana gestionada por el controlador de presentación.
     *
     * @param currentViewFile Archivo fxml de vista.
     */
    private void setCurrentViewFile(final String currentViewFile)
    {
        this.currentViewFile = currentViewFile;
    }

    /**
     * Gestor de carga de vista.
     *
     * Carga la vista indicada por parámetro y configura su controlador.
     *
     * @param viewFile Archivo fxml de vista a cargar.
     * @return Raíz de la estructura que contiene todos los elementos de la vista.
     * @throws IOException En caso que la vista no pueda ser cargada por algún motivo.
     */
    private Parent loadView(final String viewFile) throws IOException
    {
        this.currentViewFile = viewFile;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.VIEWS_PATH + viewFile));

        Parent root = fxmlLoader.load();

        PresentationController presentationController = fxmlLoader.getController();

        presentationController.setCurrentViewFile(this.currentViewFile);

        return root;
    }

    /* SET METHODS */

    /**
     * Setter de stage principal.
     *
     * Modifica el stage mostrado en la ventana principal.
     *
     * @param mainStage Stage a mostrar en la ventana principal.
     */
    public void setMainStage(final Stage mainStage)
    {
        PresentationController.mainStage = mainStage;
    }

    /**
     * Limpiado de flag de sincronización con controlador de dominio.
     *
     * Limpia el flag consultado por el controlador de dominio para determinar si
     * una notificación desbloqueante ha sido producida por el controlador de
     * presentación o no.
     */
    public static void clearThreadHasFinished()
    {
        PresentationController.threadFinished = false;
    }

    /* GET METHODS */

    /**
     * Getter de estado de retorno.
     *
     * Retorna el estado de retorno indicado por los diferentes controladores de presentación.
     *
     * @return Valor del estado de retorno.
     */
    public static int getReturnState()
    {
        return PresentationController.returnState;
    }

    /**
     * Getter de modo de juego.
     *
     * Retorna el modo de juego indicado por los controladores de presentación correspondientes.
     *
     * @return Valor del modo de juego.
     */
    public static int getMode()
    {
        return PresentationController.mode;
    }

    /**
     * Getter de dificultad de juego.
     *
     * Retorna la dificultad de juego indicado por los controladores de presentación correspondientes.
     *
     * @return Valor de la dificultad de juego.
     */
    public static int getDifficulty()
    {
        return PresentationController.difficulty;
    }

    /**
     * Getter de rol de juego.
     *
     * Retorna el rol de juego indicado por los controladores de presentación correspondientes.
     *
     * @return Valor del rol de juego.
     */
    public static int getRole()
    {
        return PresentationController.role;
    }

    /**
     * Getter de nombre de usuario.
     *
     * Retorna el nombre de usuario indicado por los controladores de presentación correspondientes.
     *
     * @return Nombre de usuario.
     */
    public static String getUsername()
    {
        return PresentationController.username;
    }

    /**
     * Getter de contraseña.
     *
     * Retorna la contraseña indicada por los controladores de presentación correspondientes.
     *
     * @return Contraseña.
     */
    public static String getPassword()
    {
        return PresentationController.password;
    }

    /**
     * Getter de contraseña de confirmación.
     *
     * Retorna la contraseña de confirmación indicada por los controladores de presentación correspondientes.
     *
     * @return Contraseña de confirmación.
     */
    public static String getConfirmPassword()
    {
        return PresentationController.confirmPassword;
    }

    /**
     * Getter de nueva contraseña.
     *
     * Retorna la nueva contraseña indicada por los controladores de presentación correspondientes.
     *
     * @return Nueva contraseña.
     */
    public static String getNewPassword()
    {
        return PresentationController.newPassword;
    }

    /**
     * Getter de contraseña actual.
     *
     * Retorna la contraseña actual indicada por los controladores de presentación correspondientes.
     *
     * @return Contraseña actual.
     */
    public static String getCurrentPassword()
    {
        return PresentationController.currentPassword;
    }

    /**
     * Getter de ID de juego.
     *
     * Retorna el ID de juego indicado por los controladores de presentación correspondientes.
     *
     * @return Valor del ID de juego.
     */
    public static String getGameId()
    {
        return PresentationController.gameId;
    }

    /**
     * Getter de turno actual.
     *
     * Retorna el turno actual de juego para poder ser usado por controladores
     * de jugadores.
     *
     * @return Último turno jugado.
     */
    public static List<Color> getCurrentTurn()
    {
        return PresentationController.currentTurn;
    }

    /**
     * Getter de controlador de dominio.
     *
     * Retorna el controlador de dominio instanciado para la ejecución actual.
     *
     * @return Controlador de dominio.
     */
    public DomainController getDomainController()
    {
        return PresentationController.DOMAIN_CONTROLLER;
    }

    /**
     * Getter de flag de sincronización con controlador de dominio.
     *
     * Retorna el valor del flag de sincronización con el controlador de dominio.
     *
     * @return Flag de sincronización.
     */
    public static boolean threadHasFinished()
    {
        return PresentationController.threadFinished;
    }

    /* GUI INTERACTION */

    /**
     * Método de captura de inicialización de JavaFX.
     *
     * Método a ejecutar cuando todos los campos fxml han sido construidos
     * e inicializados, implementado con la finalidad de no realizar ninguna acción por defecto.
     */
    public void initialize()
    {

    }

    /**
     * Inicializado de ventana.
     *
     * Inicializa la ventana de juego al iniciar la ejecución del programa.
     *
     * @throws IOException En caso que la vista inicial no pueda ser cargada.
     * @throws NullPointerException En caso que el stage de la ventana no haya sido correctamente cargado.
     */
    public void initView() throws IOException, NullPointerException
    {
        Parent root = loadView(View.LOADING_VIEW.getViewFile());

        mainStage.setTitle("Mastermind");
        mainStage.getIcons().add(new Image(getClass().getResourceAsStream(Constants.RESOURCES_PATH + Constants.ICON_FILE)));

        mainStage.setScene(new Scene(root));
        mainStage.setOnCloseRequest((WindowEvent event) ->
            {
                if(popUpStage != null)
                {
                    popUpStage.close();
                }
            }
        );

        mainStage.show();
    }

    /**
     * Actualización de vista.
     *
     * Actualiza la vista a mostrar en la ventana gestionada por el controlador de presentación.
     *
     * @param viewFile Archivo .fxml de vista a cargar.
     * @throws IOException En caso que la vista no pueda ser cargada por algún motivo.
     */
    public void updateView(final String viewFile) throws IOException
    {
        if(currentViewFile == null || !currentViewFile.equals(viewFile))
        {
            Parent root = loadView(viewFile);

            mainStage.getScene().setRoot(root);
        }
        else
        {
            initialize();
        }
    }

    /**
     * Generación de stage de popup.
     *
     * Genera un stage de popup adecuadamente configurado según los parámetros
     * de entrada para ser mostrado un la ventana gestionada por el controlador
     * de presentación.
     *
     * @param viewFile Archivo .fxml de vista a ser cargada en el popup.
     * @param width Ancho de ventana de popup.
     * @param height Alto de ventana de popup.
     * @param title Título de la ventana de popup.
     * @param iconPath Favicon de la ventana de popup.
     * @param modality Ventana bloqueante o no bloqueante de otras ventanas.
     * @param closingEventHandler Gestor de eventos al cerrar la ventana de popup.
     * @throws IOException En caso que la vista no pueda ser cargada por algún motivo.
     */
    private void newPopUpStage(final String viewFile, final int width, final int height, final String title, final String iconPath, final Modality modality, final EventHandler<WindowEvent> closingEventHandler) throws IOException
    {
        popUpStage = new Stage();

        Parent root = loadView(viewFile);

        popUpStage.setTitle(title);
        popUpStage.getIcons().add(new Image(getClass().getResourceAsStream(iconPath)));

        popUpStage.initModality(modality);
        popUpStage.setResizable(false);

        popUpStage.setOnCloseRequest(closingEventHandler);

        popUpStage.setScene(new Scene(root, width, height));

        popUpStage.show();
    }

    /**
     * Generación de ventana de popup.
     *
     * Genera una ventana de popup adecuadamente configurada según los parámetros de entrada
     * de configuraciones predefinidas.
     *
     * @param popUpWindowStyle Configuración predefinida a cargar.
     * @param viewFile Archivo .fxml de vista a cargar.
     * @throws IOException En caso que la vista no pueda ser cargada por algún motivo.
     */
    public void popUpWindow(final PopUpWindowStyle popUpWindowStyle, final String viewFile) throws IOException
    {
        String title = popUpWindowStyle.getTitle();
        String iconPath = Constants.RESOURCES_PATH + popUpWindowStyle.getIconFile();
        int width = popUpWindowStyle.getWidth();
        int height = popUpWindowStyle.getHeight();
        Modality modality = popUpWindowStyle.getModality();
        EventHandler<WindowEvent> closingEventHandler;

        switch (popUpWindowStyle)
        {
            case INFO:
            case INFO_HARD:
                closingEventHandler = (WindowEvent event) -> {};
                break;
            default:
                closingEventHandler = (WindowEvent event) -> pressButtonAction(0);
                break;
        }

        newPopUpStage(viewFile, width, height, title, iconPath, modality, closingEventHandler);
    }

    /* TEMPLATE PATTERN */

    /**
     * Método de procesado de información proveniente del controlador de dominio.
     *
     * Método encargado de recibir información genérica proveniente del controlador
     * de dominio, concretamente de no realizar ninguna acción por defecto.
     *
     * @param info Información recibida desde el controlador de dominio.
     */
    public void processInfo(final Object info)
    {

    }

    /**
     * Método plantilla de visualización de partidas guardadas.
     *
     * Plantilla para visualización del listado de partidas guardadas.
     * Por defecto, no realiza ninguna acción.
     *
     * @param savedGames Lista de partidas guardadas.
     */
    public void showLoadedGames(final Set<String> savedGames)
    {

    }

    /* BOARD METHODS */

    /**
     * Setter de código de solución.
     *
     * Guarda el código de solución indicado como parámetro en el controlador
     * de presentación para su posterior consulta.
     *
     * @param solution Código de solución.
     */
    public void setSolution(List<Color> solution)
    {
        PresentationController.solution = solution;
    }

    /**
     * Setter de códigos de turnos.
     *
     * Guarda los códigos de los turnos indicados como parámetro en el
     * controlador de presentación para su posterior consulta.
     *
     * @param codes Códigos de turnos jugados.
     */
    public void setCodes(List<List<Color>> codes)
    {
        PresentationController.codes = codes;
    }

    /**
     * Setter de códigos de correcciones de turnos.
     *
     * Guarda los códigos de las correcciones de turnos indicadas como
     * parámetro en el controlador de presentación para su posterior
     * consulta.
     *
     * @param corrections Códigos de correcciones de turnos jugados.
     */
    public void setCorrections(List<List<Color>> corrections)
    {
        PresentationController.corrections = corrections;
    }

    /**
     * Añadir código.
     *
     * Añade un código a la lista de códigos jugados.
     *
     * @param code Código a añadir.
     */
    public void addCode(List<Color> code)
    {
        PresentationController.codes.add(code);
    }

    /**
     * Añadir corrección.
     *
     * Añade una corrección a la lista de correciones de turnos jugados.
     *
     * @param correction Corrección a añadir.
     */
    public void addCorrection(List<Color> correction)
    {
        PresentationController.corrections.add(correction);
    }

    /**
     * Limpiado de estructuras de estado de juego.
     *
     * Borra y reinicializa todas las estructuras de almacenamiento
     * del estado actual del juego (solución, turnos y correcciones).
     */
    public void clear()
    {
        PresentationController.solution.clear();
        PresentationController.codes.clear();
        PresentationController.corrections.clear();
    }

    /**
     * Renderizado de tablero vacío.
     *
     * Interfaz para interactuar con las subclases adecuadas.
     *
     * @param difficulty Dificultad del tablero a visualizar.
     */
    public void renderBoard(final Difficulty difficulty)
    {
        throw new RuntimeException("You should not be executing this method from here");
    }

    /**
     * Renderizado de último turno.
     *
     * Interfaz para interactuar con las subclases adecuadas.
     *
     * @param renderFinishTurnButton Booleano de renderizado del botón de finalización
     *                               de turno.
     */
    public void renderLastTurn(final boolean renderFinishTurnButton)
    {
        throw new RuntimeException("You should not be executing this method from here");
    }

    /**
     * Actualización de tablero a jugador code breaker.
     *
     * Interfaz para interactuar con las subclases adecuadas.
     */
    public void updateToCodeBreakerBoard()
    {
        throw new RuntimeException("You should not be executing this method from here");
    }

    /**
     * Actualización de tablero a jugador code maker en modo corrección.
     *
     * Interfaz para interactuar con las subclases adecuadas.
     */
    public void updateToCodeCorrecterBoard()
    {
        throw new RuntimeException("You should not be executing this method from here");
    }

    /**
     * Actualización de tablero a jugador code maker en modo generación.
     *
     * Interfaz para interactuar con las subclases adecuadas.
     */
    public void updateToCodeMakerBoard()
    {
        throw new RuntimeException("You should not be executing this method from here");
    }
}
