package tvestergaard.webhelpers.parameters;

public interface ParametersFailureHandler
{

    ParametersFailureHandler EMPTY_FAILURE_HANDLER = new ParametersFailureHandler()
    {

    };

    /**
     * Returns the instance of {@link TextParameterFailureHandler} provided as a default error handler for instances of
     * {@link TextParameter}.
     *
     * @return The error handler.
     */
    default TextParameterFailureHandler getTextFailureHandler()
    {
        return TextParameterFailureHandler.EMPTY_FAILURE_HANDLER;
    }
}
