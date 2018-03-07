package tvestergaard.webhelpers.parameters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface Parameters
{

    /**
     * The default date format used by the {@link Parameters}.
     */
    String DEFAULT_DATE_FORMAT = "yyyy-mm-dd";

    /**
     * Returns the {@link ParameterDataMapper} providing the form data to the {@link Parameters}.
     *
     * @return The {@link ParameterDataMapper}.
     */
    ParameterDataMapper getDataMapper();

    /**
     * Returns {@code true} if the form contains a mapping from the provided {@code key}.
     *
     * @param key The key to check for.
     * @return {@code true} if the form contains a mapping from the provided {@code key}. Returns {@code false}
     * in all other cases.
     */
    boolean has(String key);

    /**
     * Checks if the value associated with the provided {@code key} in the {@link ParameterDataMapper} can safely be
     * converted to an {@link TextParameter} using the {@link Parameters#getText(String)} method.
     * <p>
     * You can be sure that {@code Form#getText(String)} will never throw an {@link InputConversionException}
     * when the result of {@code isText(String)} on the same {@code key} is {@code true}.
     *
     * @param key The key of the value to check.
     * @return {@code true} if the mapping can safely be converted to an instance of {@link TextParameter}.
     * @see Parameters#getText(String)
     */
    boolean isText(String key);

    /**
     * Creates and returns an instance of {@link TextParameter} representing the value associated with the provided {@code key}.
     * <p>
     * No {@code Exception} will be called if the {@link Parameters#isDate(String)} method returns {@code true} for the
     * same {@code key}.
     *
     * @param key The {@code key} of the mapping to convert.
     * @return The converted instance of {@link TextParameter}.
     * @throws InputConversionException When the mapping with the provided {@code key} cannot be converted.
     */
    TextParameter getText(String key) throws InputConversionException;

    /**
     * Checks if the value associated with the provided {@code key} in the {@link ParameterDataMapper} can safely be
     * converted to an {@link DateParameter} using the {@link Parameters#getDate(String)} method.
     * <p>
     * The value can be safely converted when the value of the parameters matches the {@link Parameters#DEFAULT_DATE_FORMAT}.
     * <p>
     * You can be sure that {@link Parameters#getDate(String)} will never throw an {@link InputConversionException}
     * when the result of {@code isDate(String)} on the same {@code key} is {@code true}.
     *
     * @param key The key of the value to check.
     * @param key The format to check the value against.
     * @return {@code true} if the mapping can safely be converted to an instance of {@link DateParameter}.
     * @see Parameters#getDate(String)
     */
    default boolean isDate(String key)
    {
        return isDate(key, new SimpleDateFormat(DEFAULT_DATE_FORMAT));
    }

    /**
     * Checks if the value associated with the provided {@code key} in the {@link ParameterDataMapper} can safely be
     * converted to an {@link DateParameter} using the {@link Parameters#getDate(String)} method.
     * <p>
     * The value can be safely converted when the value of the parameters matches the provided {@code DateFormat}.
     * <p>
     * You can be sure that {@link Parameters#getDate(String)} will never throw an {@link InputConversionException}
     * when the result of {@code isDate(String)} on the same {@code key} is {@code true}.
     *
     * @param key The key of the value to check.
     * @param key The format to check the value against.
     * @return {@code true} if the mapping can safely be converted to an instance of {@link DateParameter}.
     * @see Parameters#getDate(String)
     */
    boolean isDate(String key, DateFormat format);

    /**
     * Creates and returns an instance of {@link DateParameter} representing the value associated with the provided {@code key}.
     * <p>
     * The default date format of {@link Parameters#DEFAULT_DATE_FORMAT} is used during this operation.
     * <p>
     * No {@code Exception} will be called if the {@link Parameters#isDate(String)} method returns {@code true} for the
     * same {@code key}.
     *
     * @param key The key of the mapping to convert.
     * @return The converted instance of {@link DateParameter}.
     * @throws InputConversionException When the mapping with the provided {@code key} cannot be converted.
     */
    default DateParameter getDate(String key) throws InputConversionException
    {
        return getDate(key, new SimpleDateFormat(DEFAULT_DATE_FORMAT));
    }

    /**
     * Creates and returns an instance of {@link DateParameter} representing the value associated with the provided {@code key}.
     * <p>
     * No {@code Exception} will be called if the {@link Parameters#isDate(String, DateFormat)} method returns {@code true} for the
     * same {@code key} and {@code format}.
     *
     * @param key    The key of the mapping to convert.
     * @param format The format of the date value to convert.
     * @return The converted instance of {@link DateParameter}.
     * @throws InputConversionException When the mapping with the provided {@code key} cannot be converted.
     */
    DateParameter getDate(String key, DateFormat format) throws InputConversionException;

    /**
     * Checks if the value associated with the provided {@code key} in the {@link ParameterDataMapper} can safely be
     * converted to an {@link DateParameter} using the {@link Parameters#getEmail(String)} method.
     * <p>
     * The value can be safely converted when the value of the parameters is a valid email address.
     * <p>
     * You can be sure that {@link Parameters#getEmail(String)} will never throw an {@link InputConversionException}
     * when the result of {@code isDate(String)} on the same {@code key} is {@code true}.
     *
     * @param key The key of the value to check.
     * @param key The format to check the value against.
     * @return {@code true} if the mapping can safely be converted to an instance of {@link DateParameter}.
     * @see Parameters#getEmail(String)
     */
    boolean isEmail(String key);

    /**
     * Creates and returns an instance of {@link EmailParameter} representing the value associated with the provided {@code key}.
     * <p>
     * No {@code Exception} will be called if the {@link Parameters#isEmail(String)} method returns {@code true} for the
     * same {@code key}.
     *
     * @param key The {@code key} of the mapping to convert.
     * @return The converted instance of {@link TextParameter}.
     * @throws InputConversionException When the mapping with the provided {@code key} cannot be converted.
     */
    EmailParameter getEmail(String key) throws InputConversionException;

    boolean isNumber(String key);

    FloatParameter getNumber(String key) throws InputConversionException;

    boolean isTime(String key);

    TimeParameter getTime(String key) throws InputConversionException;

    boolean isRange(String key);

    RangeParameter getRange(String key) throws InputConversionException;

    boolean isUrl(String key);

    UrlParameter getUrl(String key) throws InputConversionException;
}
