package tvestergaard.webhelpers.parameters;

/**
 * Name to value pair for instances of {@link Parameter}.
 *
 * @param <N> The type of the name of the {@link Parameter}.
 * @param <V> The type of the value in the {@link Parameter}.
 */
public interface Parameter<N, V>
{

    /**
     * Returns the name of this {@link Parameter}.
     *
     * @return The name of this {@link Parameter}.
     */
    N getName();

    /**
     * Returns the value of this {@link Parameter}.
     *
     * @return The value of this {@link Parameter}.
     */
    V getValue();

    /**
     * Returns the number of failures that occurred while performing checks on this {@link Parameter}.
     *
     * @return The number of failures.
     */
    int getFailureCount();

    /**
     * Returns {@code true} if an failure occurred while performing checks in this {@link Parameter}. This operation is
     * equivalent to {@code getFailureCount() > 0}.
     *
     * @return {@code true} if an failure occurred, {@code false} in all other cases.
     */
    default boolean hasFailures()
    {
        return getFailureCount() > 0;
    }

    /**
     * The interface contract for failure handlers.
     *
     * @param <K> The type of the name of the {@link Parameter} handled by the {@link FailureHandler}.
     * @param <V> The type of the value of the {@link Parameter} handled by the {@link FailureHandler}.
     */
    interface FailureHandler<K, V>
    {

    }
}
