package tvestergaard.webhelpers.parameters;

/**
 * An abstract implementation of the {@link Parameter} interface.
 *
 * @param <N> The type of the name of the {@link Parameter}.
 * @param <V> The type of the value in the {@link Parameter}.
 */
public abstract class AbstractParameter<N, V> implements Parameter<N, V>
{

    /**
     * The name of the {@link Parameter}.
     */
    protected final N name;

    /**
     * The value of the {@link Parameter}.
     */
    protected final V value;

    /**
     * The number of failures that have occurred on checks performed on this instance of {@link Parameter}.
     *
     * @see AbstractParameter#getFailureCount()
     * @see AbstractParameter#hasFailures()
     */
    private int failureCount = 0;

    /**
     * Creates a new {@link AbstractParameter}.
     *
     * @param name  The name of the {@link Parameter}.
     * @param value The value of the {@link Parameter}.
     */
    public AbstractParameter(N name, V value)
    {
        this.name = name;
        this.value = value;
    }

    /**
     * Increments the counter recording the number of checks that have failed on this instance of {@link Parameter}.
     *
     * @return The number of checks that have failed on this instance of {@link Parameter}.
     */
    protected int incrementFailureCount()
    {
        return ++failureCount;
    }

    /**
     * Returns the name of the {@link Parameter}.
     *
     * @return The name of the {@link Parameter}.
     */
    @Override public final N getName()
    {
        return this.name;
    }

    /**
     * Returns the value of the {@link Parameter}.
     *
     * @return The value of the {@link Parameter}.
     */
    @Override public final V getValue()
    {
        return this.value;
    }

    /**
     * Returns the number of failures that have occurred on checks performed on this instance of {@link Parameter}.
     *
     * @return The number of failures that have occurred on checks performed on this instance of {@link Parameter}.
     */
    @Override public int getFailureCount()
    {
        return failureCount;
    }
}
