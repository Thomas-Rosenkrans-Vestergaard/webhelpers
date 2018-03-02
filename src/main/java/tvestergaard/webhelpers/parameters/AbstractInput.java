package tvestergaard.webhelpers.parameters;

public abstract class AbstractInput implements Input
{

	/**
	 * The name of this input.
	 */
	private final String name;

	/**
	 * Creates a new {@link AbstractInput}.
	 *
	 * @param name The name of this input.
	 */
	public AbstractInput(String name)
	{
		this.name = name;
	}

	/**
	 * Returns the name of this input.
	 *
	 * @return The name of this input.
	 */
	@Override public final String getName()
	{
		return this.name;
	}
}
