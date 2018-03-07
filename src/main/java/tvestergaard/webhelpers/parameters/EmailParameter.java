package tvestergaard.webhelpers.parameters;

/**
 * An {@link Parameter} where the value matches a valid email.
 */
public class EmailParameter extends TextParameter
{

    /**
     * Creates a new {@link EmailParameter}.
     *
     * @param name  The name of the {@link Parameter}.
     * @param value The value of the {@link Parameter}.
     */
    public EmailParameter(String name, String value)
    {
        super(name, value);
    }
}
