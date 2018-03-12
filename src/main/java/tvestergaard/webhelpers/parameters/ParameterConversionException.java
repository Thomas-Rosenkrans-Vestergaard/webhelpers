package tvestergaard.webhelpers.parameters;

/**
 * Thrown when a factory method cannot create a {@link Parameter} from a provided key value pair.
 */
public class ParameterConversionException extends RuntimeException
{

    /**
     * The name of the {@link Parameter} that could not be created.
     */
    private final String parameterName;

    /**
     * The value of the {@link Parameter} that could not be created.
     */
    private final String parameterValue;

    /**
     * The type of the {@link Parameter} that could not be created.
     */
    private final Class<? extends Parameter> parameterType;

    /**
     * The exception message.
     */
    private final String message;

    /**
     * Creates a new {@link ParameterConversionException}.
     *
     * @param parameterType
     */
    public ParameterConversionException(String parameterName, String parameterValue, Class<? extends Parameter> parameterType)
    {
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
        this.parameterType = parameterType;
        this.message = String.format(
                "Could not create %s from key '%s' and value '%s'.",
                parameterType.getSimpleName(),
                parameterName,
                parameterValue);
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override public String getMessage()
    {
        return message;
    }

    /**
     * Returns the name of the {@link Parameter} that could not be created.
     *
     * @return The name of the {@link Parameter} that could not be created.
     */
    public String getParameterName()
    {
        return this.parameterName;
    }

    /**
     * Returns the value of the {@link Parameter} that could not be created.
     *
     * @return The value of the {@link Parameter} that could not be created.
     */
    public String getParameterValue()
    {
        return this.parameterValue;
    }

    /**
     * Returns the type of the {@link Parameter} that could not be created.
     *
     * @return The type of the {@link Parameter} that could not be created.
     */
    public Class<? extends Parameter> getParameterType()
    {
        return this.parameterType;
    }
}
