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
     * Returns the number of errors that occurred while performing checks on this {@link Parameter}.
     *
     * @return The number of errors.
     */
    int getErrorCount();

    /**
     * Returns {@code true} if an error occurred while performing checks in this {@link Parameter}. This operation is
     * equivalent to {@code getErrorCount() > 0}.
     *
     * @return {@code true} if an error occurred, {@code false} in all other cases.
     */
    default boolean hasErrors()
    {
        return getErrorCount() > 0;
    }
}
