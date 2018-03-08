package tvestergaard.webhelpers.parameters;

public interface ParametersErrorHandler
{

    /**
     * Returns the instance of {@link TextParameterErrorHandler} provided as a default error handler for instances of
     * {@link TextParameter}.
     *
     * @return The error handler.
     */
    default TextParameterErrorHandler getTextErrorHandler()
    {
        return null;
    }
}
