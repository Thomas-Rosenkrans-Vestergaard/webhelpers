package tvestergaard.webhelpers.parameters.mappers;

import tvestergaard.webhelpers.parameters.ParameterConversionException;
import tvestergaard.webhelpers.parameters.ParameterMapper;
import tvestergaard.webhelpers.parameters.Parameters;
import tvestergaard.webhelpers.parameters.TextParameter;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Consumer;

/**
 * Implementation of the {@link ParameterMapper} interface to wrap instances of {@link HttpServletRequest} for use in
 * {@link Parameters} implementations.
 */
public class HttpServletRequestParameters implements Parameters<String>
{

    private final HttpServletRequest request;

    public HttpServletRequestParameters(HttpServletRequest request)
    {
        this.request = request;
    }

    @Override public boolean isText(String name)
    {
        return true;
    }

    @Override public TextParameter getText(String name) throws ParameterConversionException
    {
        return new TextParameter(name, request.getParameter(name));
    }

    @Override public boolean onText(String name, Consumer<TextParameter> consumer) throws ParameterConversionException
    {
        TextParameter<String> parameter = new TextParameter<>(name, request.getParameter(name));
        consumer.accept(parameter);
        return !parameter.hasFailures();
    }
}
