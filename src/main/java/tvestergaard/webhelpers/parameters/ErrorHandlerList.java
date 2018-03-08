package tvestergaard.webhelpers.parameters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ErrorHandlerList<T> implements Iterable<T>
{

    private final List<T> errorHandlers = new ArrayList<>();

    public ErrorHandlerList(T... errorHandlers)
    {
        for (T errorHandler : errorHandlers)
            this.errorHandlers.add(errorHandler);
    }

    public int size()
    {
        return errorHandlers.size();
    }

    public boolean isEmpty()
    {
        return errorHandlers.isEmpty();
    }

    public boolean add(T t)
    {
        return errorHandlers.add(t);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override public Iterator<T> iterator()
    {
        return errorHandlers.iterator();
    }
}
