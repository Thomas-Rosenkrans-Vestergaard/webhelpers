package tvestergaard.webhelpers.parameters;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateParameter extends TextParameter
{

    /**
     * The internal {@code Date} of the {@link DateParameter}.
     */
    private final Date date;

    /**
     * The format of the {@code Date} in the {@link DateParameter}.
     */
    private final DateFormat format;

    /**
     * The {@code Calender} instance created from the {@code Date} instance.
     */
    private final Calendar calendar;

    /**
     * Creates a new {@link DateParameter}.
     *
     * @param name  The name of the {@link Parameter}.
     * @param value The text based value of the {@link Parameter}.
     * @param date  The date value of the {@link Parameter}.
     */
    public DateParameter(String name, String value, Date date, DateFormat format)
    {
        super(name, value);
        this.date = date;
        this.format = format;
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(date);
    }

    /**
     * Tests if this date is before the specified date.
     *
     * @param when a date.
     * @return <code>true</code> if and only if the instant of time
     * represented by this <tt>Date</tt> object is strictly
     * earlier than the instant represented by <tt>when</tt>;
     * <code>false</code> otherwise.
     * @throws NullPointerException if <code>when</code> is null.
     */
    public boolean before(Date when)
    {
        return date.before(when);
    }

    /**
     * Tests if this date is after the specified date.
     *
     * @param when a date.
     * @return <code>true</code> if and only if the instant represented
     * by this <tt>Date</tt> object is strictly later than the
     * instant represented by <tt>when</tt>;
     * <code>false</code> otherwise.
     * @throws NullPointerException if <code>when</code> is null.
     */
    public boolean after(Date when)
    {
        return date.after(when);
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
