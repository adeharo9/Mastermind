package domain.classes;

import util.Constants;
import util.Pair;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Clase Ranking.
 *
 * Esta clase representa un ranking formado por
 * las 10 mejores puntuaciones o menos (en caso que no se
 * hayan jugado 10 partidas) con el id del jugador que la ha conseguido.
 *
 * @author Alex Sánchez
 */
public class Ranking implements Serializable
{
    /* ATTRIBUTES */

    private LinkedList<Pair<String, Integer>> topTen;

    /* CONSTRUCTION METHODS */

    /**
     * Constructora de Ranking.
     *
     * Instancia un ranking vacío pero evitando NullPointerException.
     */
    public Ranking()
    {
        topTen = new LinkedList<>();
    }

    /**
     * Constructora de ranking por lista de jugadores y puntuación.
     *
     * @param topTen Lista de los 10 o menos (en caso que no se hayan jugado todavía 10 partidas)
     *              mejores jugadores y puntos respectivos ordenados por este último.
     */
    public Ranking(final LinkedList<Pair<String, Integer>> topTen)
    {
        this.topTen = topTen;
    }

    /* SET METHODS */

    /* GET METHODS */

    /**
     * Getter del top ten
     *
     * Devuelve la lista de jugadores y puntuaciones
     * respectivas que conforman las mejores 10 partidas.
     *
     * @return Lista de los 10 o menos (en caso que no se hayan jugado todavía 10 partidas)
     *              mejores jugadores y puntos respectivos ordenados por este último.
     */
    public LinkedList<Pair<String, Integer>> getTopTen()
    {
        return topTen;
    }

    /* TESTING METHODS */

    /**
     * Consultar si está en el top ten.
     *
     * Consulta si el jugador identificado por idPlayer junto con la
     * puntuación que ha conseguido, están en el top ten.
     *
     * @param points Puntuación que el jugador ha conseguido.
     * @return True si debe estar en el top ten, en cualquier otro caso false.
     */
    public boolean toTopTen(final Integer points)
    {
        return (topTen.size() < Constants.RANKING_SIZE) || (points > topTen.getLast().second);
    }

    /**
     * Añadir jugador y puntuación al top ten.
     *
     * La pareja formada por el jugador identificado por idPlayer y
     * la puntuación que ha conseguido, siempre previamente habiendo sido comprobado
     * si está en el top Ten, se añade en el top ten en la posición que le toca.
     *
     * @param idPlayer Id del jugador que realiza la partida.
     * @param points Puntuación que el jugador ha conseguido y que está entre las 10 mejores.
     */
    public void addToTopTen(final String idPlayer, final Integer points)
    {
        int i = correctPosition(points);

        Pair<String, Integer> toAdd = new Pair<>(idPlayer, points);
        topTen.add(i, toAdd);

        if(topTen.size() > Constants.RANKING_SIZE) { topTen.removeLast(); }
    }

    private int correctPosition(final Integer points)
    {
        int left = 0;
        int right = topTen.size() - 1;
        int mid, cmp;

        while (left <= right)
        {
            mid = (left + right) / 2;
            Integer midVal = topTen.get(mid).second;
            cmp = midVal.compareTo(points);

            if (cmp > 0)
            {
                left = mid + 1;
            }

            else if (cmp < 0)
            {
                right = mid - 1;
            }

            else
            {
                return mid;
            }
        }

        return left;
    }
}