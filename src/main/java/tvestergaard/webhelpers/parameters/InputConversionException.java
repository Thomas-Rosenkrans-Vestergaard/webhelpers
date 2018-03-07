package tvestergaard.webhelpers.parameters;

public class InputConversionException extends RuntimeException
{

	private final Parameters                 form;
	private final Class<? extends Parameter> type;

	/**
	 * Constructs a new runtime exception with {@code null} as its
	 * detail message.  The cause is not initialized, and may subsequently be
	 * initialized by a call to {@link #initCause}.
	 */
	public InputConversionException(Parameters form, Class<? extends Parameter> type)
	{
		this.form = form;
		this.type = type;
	}

	public Parameters getForm()
	{
		return this.form;
	}

	public Class<? extends Parameter> getType()
	{
		return this.type;
	}
}
