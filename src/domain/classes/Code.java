package domain.classes;

import enums.Color;
import enums.Difficulty;
import util.Constants;
import util.DeepCopyable;
import util.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Clase Code.
 *
 * Representa un código de colores con su respectivo tamaño.
 *
 * @author Alejandro de Haro
 */

public class Code implements DeepCopyable, Serializable
{
        /* ATTRIBUTES */

        protected final long orderedHash;
        protected final long unorderedHash;
        protected final int size;
        protected ArrayList<Color> codePins;

        /**
         * Calcular hash ordenado.
         *
         * Calcula el código hash.
         *
         * @param code Código de colores.
         */
        private static long calcOrderedHash(List<Color> code)
        {
            int k = Color.SIZE;
            long orderedHash = 0;

            for(int i = 0; i < code.size(); ++i)
            {
                orderedHash += code.get(i).getId() * Math.pow((k), i);
            }

            return orderedHash;
        }

    /**
     * Calcular hash desordenado.
     *
     * Calcula el código hash.
     *
     * @param code Código de colores.
     */
        private static long calcUnorderedHash(Collection<Color> code)
        {
            int k;
            int n = code.size();
            long unorderedHash = 0;

            for(Color color : code)
            {
                k = color.getId();
                unorderedHash += (Math.pow(n, k) - 1) / (n - 1);
            }

            return unorderedHash;
        }

        /* CONSTRUCTION METHODS */

        public Code(final List<Color> codePins)
        {
            this.orderedHash = calcOrderedHash(codePins);
            this.unorderedHash = calcUnorderedHash(codePins);
            this.size = codePins.size();
            setCodePins(codePins);
        }

        public Code(final Code code) throws IllegalArgumentException, NullPointerException
        {
            this.orderedHash = code.orderedHash;
            this.unorderedHash = code.unorderedHash;
            size = code.size();
            setCodePins(code.getCodePins());
        }

        /* SET METHODS */

    /**
     * Setter código.
     *
     * Indica el código de colores.
     *
     * @param codePins Código de colores.
     * @throws IllegalArgumentException Si el código de colores no es válido.
     * @throws NullPointerException Si el código de colores está vacío.
     */
        protected final <C extends  Collection<Color>> void setCodePins(final C codePins) throws IllegalArgumentException, NullPointerException
        {
            boolean b = Utils.isValidCollection(codePins);
            if(!b) throw new IllegalArgumentException();

            this.codePins = new ArrayList<>(codePins.size());
            this.codePins.addAll(codePins);
        }

        /* GET METHODS */

    /**
     * Getter código.
     *
     * Devuelve el código de colores.
     *
     * @return List<Color> codePins.
     */
        public final List<Color> getCodePins()
        {
            return codePins;
        }

        /* CONSULTING METHODS */
    /**
     * Getter medida código.
     *
     * Devuelve la medida del código de colores.
     *
     * @return int size.
     */
        public final int size()
        {
            return size;
        }

        /* VALIDITY METHODS */
    /**
     * Validar código.
     *
     * Comprueba si el código de colores es válido.
     *
     * @return true si el código de colores, en cualquier otro caso false.
     */
        public boolean isValid()
        {
            return codePins != null;
        }

        /* CLONING METHODS */

        @Override
        public Code deepCopy() throws IllegalArgumentException, NullPointerException
        {
            return new Code(this);
        }

        /* HASHING METHODS */

    /**
     * Igualdad de códigos.
     *
     * Comprueba si los códigos son iguales tanto en colores como en posición de colores.
     *
     * @return true si son iguales, en cualquier otro caso falso.
     */
        public boolean orderedEquals(final Object o)
        {
            if (o == null || getClass() != o.getClass()) return false;

            final Code code = (Code) o;

            return this.orderedHash == code.orderedHash;
        }

    /**
     * Igualdad de códigos.
     *
     * Comprueba si los códigos son iguales solo en posición.
     *
     * @return true si son iguales, en cualquier otro caso falso.
     */
        public boolean unorderedEquals(final Object o)
        {
            if (o == null || getClass() != o.getClass()) return false;

            final Code code = (Code) o;

            return this.unorderedHash == code.unorderedHash;
        }

    /**
     * Igualdad de códigos.
     *
     * Comprueba si los códigos son iguales solo en colores.
     *
     * @return true si son iguales, en cualquier otro caso falso.
     */
        @Override
        public boolean equals(final Object o)
        {
            return unorderedEquals(o);
        }

    /**
     * Igualdad de códigos.
     *
     * Comprueba si los códigos son iguales.
     *
     * @return true si son iguales, en cualquier otro caso falso.
     */
        public boolean hardEquals(final Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final Code code = (Code) o;

            if (this.size != code.size) return false;
            return getCodePins() != null ? getCodePins().equals(code.getCodePins()) : code.getCodePins() == null;
        }

    /**
     * Código hash.
     *
     * Devuelve el código hash del código de colores correspondiente.
     *
     * @return código hash.
     */
        @Override
        public int hashCode()
        {
            int result = size;
            result = 31 * result + (getCodePins() != null ? getCodePins().hashCode() : 0);
            return result;
        }
}