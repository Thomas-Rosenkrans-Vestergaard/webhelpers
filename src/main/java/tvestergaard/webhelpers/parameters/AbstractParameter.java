package tvestergaard.webhelpers.parameters;

/**
 * An abstract implementation of the {@link Parameter} interface.
 *
 * @param <N> The type of the name of the {@link AbstractParameter}.
 * @param <V> The type of the value in the {@link AbstractParameter}.
 */
public abstract class AbstractParameter<N, V> implements Parameter<N, V>
{

    /**
     * The name of the {@link AbstractParameter}.
     */
    protected final N name;

    /**
     * The value of the {@link AbstractParameter}.
     */
    protected final V value;

    /**
     * The number of errors that occurred during checks on this {@link AbstractParameter}.
     */
    private int failureCount = 0;

    /**
     * Creates a new {@link AbstractParameter}.
     *
     * @param name  The name of the {@link AbstractParameter}.
     * @param value The value of the {@link AbstractParameter}.
     */
    public AbstractParameter(N name, V value)
    {
        this.name = name;
        this.value = value;
    }

    /**
     * Increments the counter controlling the number of checks that have failed on this parameter.
     *
     * @return The number of checks that have failed on this parameter.
     */
    protected int incrementFailureCount()
    {
        return ++failureCount;
    }

    /**
     * Returns the name of the {@code parameter}.
     *
     * @return The name of the {@code parameter}.
     */
    @Override public final N getName()
    {
        return this.name;
    }

    /**
     * Returns the value of the {@code parameter}.
     *
     * @return The value of the {@code parameter}.
     */
    @Override public V getValue()
    {
        return this.value;
    }

    /**
     * Returns the number of failures that occurred while performing checks on this {@code parameter}.
     *
     * @return The number of failures that occurred while performing checks on this {@code parameter}.
     */
    @Override public int getFailureCount()
    {
        return failureCount;
    }
}
