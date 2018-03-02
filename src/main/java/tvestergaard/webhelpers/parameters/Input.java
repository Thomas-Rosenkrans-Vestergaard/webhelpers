package tvestergaard.webhelpers.parameters;

public interface Input
{

	/**
	 * Returns the name of this input.
	 *
	 * @return The name of this input.
	 */
	String getName();

	/**
	 * Returns the value of this input.
	 *
	 * @return The value of this input.
	 */
	Object getValue();
}
