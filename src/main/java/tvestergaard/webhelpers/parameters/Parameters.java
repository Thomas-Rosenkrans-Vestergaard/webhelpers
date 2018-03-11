package tvestergaard.webhelpers.parameters;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Parameters
{

    /**
     * The {@link ParameterMapper} providing the data in the form.
     */
    private ParameterMapper mapper;

    /**
     * Creates a new {@link Parameters}.
     *
     * @param dataMapper The {@link ParameterMapper} providing the data in the form.
     */
    public Parameters(ParameterMapper dataMapper)
    {
        this.mapper = dataMapper;
    }

    /**
     * Returns {@code true} if the form isContained a mapping from the provided {@code key}.
     *
     * @param key The key to check for.
     *
     * @return {@code true} if the {@link Parameters} isContained a mapping from the provided {@code key}, {@code false} in all other cases.
     */
    public boolean has(String key)
    {
        return mapper.has(key);
    }

    public static <T> Iterable<T> iterable(T... values)
    {
        return new ArrayIterable<>(values);
    }

    public static <T> List<T> list(T... values)
    {
        return Arrays.asList(values);
    }

    private static class ArrayIterable<T> implements Iterable<T>
    {
        private final T[] array;

        public ArrayIterable(T[] array)
        {
            this.array = array;
        }

        /**
         * Returns an iterator over elements of type {@code T}.
         *
         * @return an Iterator.
         */
        @Override public Iterator<T> iterator()
        {
            return new ArrayIterator();
        }

        private class ArrayIterator implements Iterator<T>
        {

            private int index = 0;

            /**
             * Returns {@code true} if the iteration has more elements.
             * (In other words, returns {@code true} if {@link #next} would
             * return an element rather than throwing an exception.)
             *
             * @return {@code true} if the iteration has more elements
             */
            @Override public boolean hasNext()
            {
                return index < array.length;
            }

            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration
             * @throws NoSuchElementException if the iteration has no more elements
             */
            @Override public T next()
            {
                if (index >= array.length)
                    throw new NoSuchElementException();

                return array[index++];
            }
        }
    }
}
