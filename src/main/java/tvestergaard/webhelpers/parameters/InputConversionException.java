package tvestergaard.webhelpers.parameters;

public class InputConversionException extends RuntimeException
{

	private final Form                   form;
	private final Class<? extends Input> type;

	/**
	 * Constructs a new runtime exception with {@code null} as its
	 * detail message.  The cause is not initialized, and may subsequently be
	 * initialized by a call to {@link #initCause}.
	 */
	public InputConversionException(Form form, Class<? extends Input> type)
	{
		this.form = form;
		this.type = type;
	}

	public Form getForm()
	{
		return this.form;
	}

	public Class<? extends Input> getType()
	{
		return this.type;
	}
}
