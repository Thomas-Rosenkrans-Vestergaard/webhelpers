package tvestergaard.webhelpers.parameters;

public interface Parameter
{

    /**
     * Returns the name of this parameter.
     *
     * @return The name of this parameter.
     */
    String getName();

    /**
     * Returns the value of this parameter.
     *
     * @return The value of this parameter. This method never return {@code null} values.
     */
    Object getValue();
}
