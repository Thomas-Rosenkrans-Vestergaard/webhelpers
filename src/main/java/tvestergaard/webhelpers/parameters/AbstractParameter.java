package tvestergaard.webhelpers.parameters;

public abstract class AbstractParameter implements Parameter
{

    /**
     * The name of this {@link Parameter}.
     */
    protected final String name;

    /**
     * Creates a new {@link AbstractParameter}.
     *
     * @param name The name of this {@link Parameter}.
     */
    public AbstractParameter(String name)
    {
        this.name = name;
    }

    /**
     * Returns the name of this {@link Parameter}.
     *
     * @return The name of this {@link Parameter}.
     */
    @Override public final String getName()
    {
        return this.name;
    }
}
