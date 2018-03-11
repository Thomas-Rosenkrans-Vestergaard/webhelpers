package tvestergaard.webhelpers.parameters;

/**
 * An interface
 */
public interface ParameterMapper
{

    /**
     * Returns the value associated with the provided {@code parameterName} converted to a {@code String}.
     *
     * @param parameterName The {@code parameterName} of the associated value to be formatted as a {@code String}.
     * @return The formatted value associated with the provided {@code parameterName}.
     * @throws ParameterFormatException If the value associated with the provided {@code parameterName} cannot be
     *                                  converted to a {@code String}.
     */
    String getText(String parameterName) throws ParameterFormatException;

    /**
     * Returns the value associated with the provided {@code parameterName} converted to an {@code Integer}.
     *
     * @param parameterName The {@code parameterName} of the associated value to be formatted as a {@code Integer}.
     * @return The formatted value associated with the provided {@code parameterName}.
     * @throws ParameterFormatException If the value associated with the provided {@code parameterName} cannot be
     *                                  converted to a {@code Integer}.
     */
    Integer getInt(String parameterName) throws ParameterFormatException;

    /**
     * Returns the value associated with the provided {@code parameterName} converted to an {@code Float}.
     *
     * @param parameterName The {@code parameterName} of the associated value to be formatted as a {@code Float}.
     * @return The formatted value associated with the provided {@code parameterName}.
     * @throws ParameterFormatException If the value associated with the provided {@code parameterName} cannot be
     *                                  converted to a {@code Float}.
     */
    Float getFloat(String parameterName) throws ParameterFormatException;

    /**
     * Returns the value associated with the provided {@code parameterName}.
     * <p>
     * The method must not return {@code null} when a provided {@code parameterName} exists in the
     * {@code FormDataMapper}.
     *
     * @param parameterName The name of the parameter to return the associated value of.
     * @return The value associated with the provided {@code parameterName}. Returns {@code null} if the {@code
     * FormDataMapper} isContained no mapping for the provided {@code parameterName}.
     */
    String get(String parameterName);

    /**
     * Returns {@code true} if the {@link ParameterMapper} isContained the provided a value for the provided {@code
     * parameterName}.
     * <p>
     * The method must not return {@code true} if the value associated with the provided {@code parameterName} equals
     * {@code null}.
     *
     * @param parameterName The parameterName to check for in the {@link ParameterMapper}.
     * @return {@code true} if the {@link ParameterMapper} isContained the provided a value for the provided
     * {@code parameterName}.
     */
    boolean has(String parameterName);
}
