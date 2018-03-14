package tvestergaard.webhelpers.parameters;

/**
 * Thrown when a factory method cannot create a {@link Parameter} from a provided key value pair.
 */
public class ParameterConversionException extends RuntimeException
{

    /**
     * The type of the {@link Parameter} that could not be created.
     */
    private final Class<? extends Parameter> parameterType;

    public ParameterConversionException(Throwable cause, Class<? extends Parameter> parameterType)
    {
        super(cause);

        this.parameterType = parameterType;
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
