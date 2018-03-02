package tvestergaard.webhelpers.parameters;

public interface FormDataMapper
{

	/**
	 * Returns the value associated with the provided {@code key}.
	 *
	 * @param key The name of the {@code key} to return the associated value of.
	 *
	 * @return The value associated with the provided {@code key}. Returns {@code null} if the {@code
	 * FormDataMapper} contains no mapping for the provided {@code key}.
	 */
	String get(String key);

	/**
	 * Returns {@code true} if the {@link FormDataMapper} contains the provided a value for the provided {@code
	 * key}.
	 *
	 * @param key The key to check for in the {@link FormDataMapper}.
	 *
	 * @return {@code true} if the {@link FormDataMapper} contains the provided a value for the provided {@code
	 * key}.
	 */
	boolean has(String key);
}
