package tvestergaard.webhelpers.parameters;

import java.text.DateFormat;
import java.util.Date;

public class InputDate extends InputText
{

	/**
	 * The internal {@code Date} of the {@link InputDate}.
	 */
	private final Date date;

	/**
	 * The format of the {@code Date}.
	 */
	private final DateFormat format;

	/**
	 * Creates a new {@link InputDate}.
	 *
	 * @param name  The name of the input.
	 * @param value The text based value of the input.
	 * @param date  The date value of the input.
	 */
	public InputDate(String name, String value, Date date, DateFormat format)
	{
		super(name, value);
		this.date = date;
		this.format = format;
	}

	/**
	 * Returns the format of the {@code Date}.
	 *
	 * @return The format of the {@code Date}.
	 */
	public DateFormat getFormat()
	{
		return this.format;
	}
}
