package tvestergaard.webhelpers.parameters;

public interface Parameter
{

    /**
     * Returns the name of this {@link Parameter}.
     *
     * @return The name of this {@link Parameter}.
     */
    String getName();

    /**
     * Returns the value of this {@link Parameter}.
     *
     * @return The value of this {@link Parameter}.
     */
    Object getValue();

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
}
