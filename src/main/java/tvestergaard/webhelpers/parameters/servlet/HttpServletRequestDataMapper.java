package tvestergaard.webhelpers.parameters.servlet;

import tvestergaard.webhelpers.parameters.Form;
import tvestergaard.webhelpers.parameters.FormDataMapper;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of the {@link FormDateMapper} interface to wrap instances of {@link HttpServletRequest} for use in
 * {@link Form} implementations.
 */
public class HttpServletRequestDataMapper implements FormDataMapper
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
	 * Returns the value associated with the provided {@code key}.
	 *
	 * @param key The name of the {@code key} to return the associated value of.
	 *
	 * @return The value associated with the provided {@code key}. Returns {@code null} if the {@code
	 * HttpServletRequest} contains no mapping for the provided {@code key}.
	 */
	@Override public String get(String key)
	{
		return request.getParameter(key);
	}

	/**
	 * Returns {@code true} if the {@code HttpServletRequest} contains the provided a value for the provided {@code
	 * key}.
	 *
	 * @param key The key to check for in the {@code HttpServletRequest}.
	 *
	 * @return {@code true} if the {@code HttpServletRequest} contains the provided a value for the provided {@code
	 * key}.
	 */
	@Override public boolean has(String key)
	{
		return request.getParameter(key) != null;
	}
}
