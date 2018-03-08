package tvestergaard.webhelpers.parameters.tomcat;

import tvestergaard.webhelpers.parameters.ParameterDataMapper;
import tvestergaard.webhelpers.parameters.Parameters;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of the {@link ParameterDataMapper} interface to wrap instances of {@link HttpServletRequest} for use in
 * {@link Parameters} implementations.
 */
public class HttpServletRequestDataMapper implements ParameterDataMapper
{

    /**
     * The internal {@code HttpServletRequest} that methods are delegated to.
     */
    private final HttpServletRequest request;

    /**
     * Creates a new {@link HttpServletRequestDataMapper}.
     *
     * @param request The internal {@code HttpServletRequest} that methods are delegated to.
     */
    public HttpServletRequestDataMapper(HttpServletRequest request)
    {
        this.request = request;
    }

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
    @Override public String get(String parameterName)
    {
        String value = request.getParameter(parameterName);

        return value == null ? "" : value;
    }

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
    @Override public boolean has(String parameterName)
    {
        return request.getParameter(parameterName) != null;
    }
}
