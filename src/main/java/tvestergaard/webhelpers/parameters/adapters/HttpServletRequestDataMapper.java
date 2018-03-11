package tvestergaard.webhelpers.parameters.adapters;

import tvestergaard.webhelpers.parameters.ParameterFormatException;
import tvestergaard.webhelpers.parameters.ParameterMapper;
import tvestergaard.webhelpers.parameters.Parameters;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of the {@link ParameterMapper} interface to wrap instances of {@link HttpServletRequest} for use in
 * {@link Parameters} implementations.
 */
public class HttpServletRequestDataMapper implements ParameterMapper
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
    @Override public boolean has(String parameterName)
    {
        return request.getParameter(parameterName) != null;
    }

    /**
     * Returns the value associated with the provided {@code parameterName} converted to a {@code String}.
     *
     * @param parameterName The {@code parameterName} of the associated value to be formatted as a {@code String}.
     *
     * @return The formatted value associated with the provided {@code parameterName}.
     * @throws ParameterFormatException If the value associated with the provided {@code parameterName} cannot be
     *                                  converted to a {@code String}.
     */
    @Override public String getText(String parameterName) throws ParameterFormatException
    {
        return null;
    }

    /**
     * Returns the value associated with the provided {@code parameterName} converted to an {@code Integer}.
     *
     * @param parameterName The {@code parameterName} of the associated value to be formatted as a {@code Integer}.
     *
     * @return The formatted value associated with the provided {@code parameterName}.
     * @throws ParameterFormatException If the value associated with the provided {@code parameterName} cannot be
     *                                  converted to a {@code Integer}.
     */
    @Override public Integer getInt(String parameterName) throws ParameterFormatException
    {
        return null;
    }

    /**
     * Returns the value associated with the provided {@code parameterName} converted to an {@code Float}.
     *
     * @param parameterName The {@code parameterName} of the associated value to be formatted as a {@code Float}.
     *
     * @return The formatted value associated with the provided {@code parameterName}.
     * @throws ParameterFormatException If the value associated with the provided {@code parameterName} cannot be
     *                                  converted to a {@code Float}.
     */
    @Override public Float getFloat(String parameterName) throws ParameterFormatException
    {
        return null;
    }
}
