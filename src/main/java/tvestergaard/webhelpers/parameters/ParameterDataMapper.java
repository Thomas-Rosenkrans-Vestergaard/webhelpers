package tvestergaard.webhelpers.parameters;

public interface ParameterDataMapper
{

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
     * Returns {@code true} if the {@link ParameterDataMapper} isContained the provided a value for the provided {@code
     * parameterName}.
     * <p>
     * The method must not return {@code true} if the value associated with the provided {@code parameterName} equals
     * {@code null}.
     *
     * @param parameterName The parameterName to check for in the {@link ParameterDataMapper}.
     * @return {@code true} if the {@link ParameterDataMapper} isContained the provided a value for the provided
     * {@code parameterName}.
     */
    boolean has(String parameterName);
}
