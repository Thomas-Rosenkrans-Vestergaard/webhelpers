package tvestergaard.webhelpers.parameters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface Form
{

	/**
	 * The default date format used by the {@link Form}.
	 */
	String DEFAULT_DATE_FORMAT = "yyyy-mm-dd";

	/**
	 * Returns the {@link FormDataMapper} providing the form data to the {@link Form}.
	 *
	 * @return The {@link FormDataMapper}.
	 */
	FormDataMapper getDataMapper();

	/**
	 * Returns {@code true} if the form contains a mapping from the provided {@code key}.
	 *
	 * @param key The key to check for.
	 *
	 * @return {@code true} if the form contains a mapping from the provided {@code key}. Returns {@code false}
	 * in all other cases.
	 */
	boolean has(String key);

	/**
	 * Checks if the value associated with the provided {@code key} in the {@link FormDataMapper} can safely be
	 * converted to an {@link InputText} using the {@link Form#getText(String)} method.
	 * <p>
	 * You can be sure that {@code Form#getText(String)} will never throw an {@link InputConversionException}
	 * when the result of {@code isText(String)} on the same {@code key} is {@code true}.
	 *
	 * @param key The key of the value to check.
	 *
	 * @return {@code true} if the mapping can safely be converted to an instance of {@link InputText}.
	 * @see Form#getText(String)
	 */
	boolean isText(String key);

	/**
	 * Converts and returns the mapping with the provided {@code key} into an instance of {@link InputText}.
	 * <p>
	 * No {@code Exception} will be called if the {@link Form#isDate(String)} method returns {@code true} for the
	 * same {@code key}.
	 *
	 * @param key The {@code key} of the mapping to convert.
	 *
	 * @return The converted instance of {@link InputText}.
	 * @throws InputConversionException When the mapping with the provided {@code key} cannot be converted.
	 */
	InputText getText(String key) throws InputConversionException;

	/**
	 * Checks if the value associated with the provided {@code key} in the {@link FormDataMapper} can safely be
	 * converted to an {@link InputDate} using the {@link Form#getDate(String)} method.
	 * <p>
	 * The value can be safely converted when the value of the input matches the {@link Form#DEFAULT_DATE_FORMAT}.
	 * <p>
	 * You can be sure that {@link Form#getDate(String)} will never throw an {@link InputConversionException}
	 * when the result of {@code isDate(String)} on the same {@code key} is {@code true}.
	 *
	 * @param key The key of the value to check.
	 * @param key The format to check the value against.
	 *
	 * @return {@code true} if the mapping can safely be converted to an instance of {@link InputDate}.
	 * @see Form#getDate(String)
	 */
	default boolean isDate(String key)
	{
		return isDate(key, new SimpleDateFormat(DEFAULT_DATE_FORMAT));
	}

	/**
	 * Checks if the value associated with the provided {@code key} in the {@link FormDataMapper} can safely be
	 * converted to an {@link InputDate} using the {@link Form#getDate(String)} method.
	 * <p>
	 * The value can be safely converted when the value of the input matches the provided {@code DateFormat}.
	 * <p>
	 * You can be sure that {@link Form#getDate(String)} will never throw an {@link InputConversionException}
	 * when the result of {@code isDate(String)} on the same {@code key} is {@code true}.
	 *
	 * @param key The key of the value to check.
	 * @param key The format to check the value against.
	 *
	 * @return {@code true} if the mapping can safely be converted to an instance of {@link InputDate}.
	 * @see Form#getDate(String)
	 */
	boolean isDate(String key, DateFormat format);

	/**
	 * Converts and returns the mapping with the provided {@code key} into an instance of {@link InputDate}.
	 * <p>
	 * The default date format of {@link Form#DEFAULT_DATE_FORMAT} is used during this operation.
	 * <p>
	 * No {@code Exception} will be called if the {@link Form#isDate(String)} method returns {@code true} for the
	 * same {@code key}.
	 *
	 * @param key The key of the mapping to convert.
	 *
	 * @return The converted instance of {@link InputDate}.
	 * @throws InputConversionException When the mapping with the provided {@code key} cannot be converted.
	 */
	default InputDate getDate(String key) throws InputConversionException
	{
		return getDate(key, new SimpleDateFormat(DEFAULT_DATE_FORMAT));
	}

	/**
	 * Converts and returns the mapping with the provided {@code key} into an instance of {@link InputDate} using the
	 * provided {@code format}.
	 * <p>
	 * No {@code Exception} will be called if the {@link Form#isDate(String, DateFormat)} method returns {@code true} for the
	 * same {@code key} and {@code format}.
	 *
	 * @param key    The key of the mapping to convert.
	 * @param format The format of the date value to convert.
	 *
	 * @return The converted instance of {@link InputDate}.
	 * @throws InputConversionException When the mapping with the provided {@code key} cannot be converted.
	 */
	InputDate getDate(String key, DateFormat format) throws InputConversionException;

	/**
	 * Checks if the value associated with the provided {@code key} in the {@link FormDataMapper} can safely be
	 * converted to an {@link InputEmail} using the {@link Form#getEmail(String)} method. When
	 * {@link Form#isEmail(String)} returns {@code true}, {@link Form#getEmail(String)} will never throw an
	 * {@link InputConversionException}.
	 *
	 * @param key The key of the value to check.
	 *
	 * @return {@code true} if the mapping can safely be converted to an instance of {@link InputEmail}.
	 * @see Form#getEmail(String)
	 */
	boolean isEmail(String key);

	/**
	 * Converts and returns the mapping with the provided {@code key} into an instance of {@link InputEmail}.
	 *
	 * @param key The {@code key} of the mapping to convert.
	 *
	 * @return The converted instance of {@link InputEmail}.
	 * @throws InputConversionException When the mapping with the provided {@code key} cannot be converted.
	 */
	InputEmail getEmail(String key) throws InputConversionException;

	/**
	 * Checks if the value associated with the provided {@code key} in the {@link FormDataMapper} can safely be
	 * converted to an {@link InputNumber} using the {@link Form#getNumber(String)} method. When
	 * {@link Form#isNumber(String)} returns {@code true}, {@link Form#getNumber(String)} will never throw an
	 * {@link InputConversionException}.
	 *
	 * @param key The key of the value to check.
	 *
	 * @return {@code true} if the mapping can safely be converted to an instance of {@link InputNumber}.
	 * @see Form#getNumber(String)
	 */
	boolean isNumber(String key);

	/**
	 * Converts and returns the mapping with the provided {@code key} into an instance of {@link InputNumber}.
	 *
	 * @param key The {@code key} of the mapping to convert.
	 *
	 * @return The converted instance of {@link InputNumber}.
	 * @throws InputConversionException When the mapping with the provided {@code key} cannot be converted.
	 */
	InputNumber getNumber(String key) throws InputConversionException;

	/**
	 * Checks if the value associated with the provided {@code key} in the {@link FormDataMapper} can safely be
	 * converted to an {@link InputTime} using the {@link Form#getTime(String)} method. When
	 * {@link Form#isTime(String)} returns {@code true}, {@link Form#getTime(String)} will never throw an
	 * {@link InputConversionException}.
	 *
	 * @param key The key of the value to check.
	 *
	 * @return {@code true} if the mapping can safely be converted to an instance of {@link InputTime}.
	 * @see Form#getTime(String)
	 */
	boolean isTime(String key);

	/**
	 * Converts and returns the mapping with the provided {@code key} into an instance of {@link InputTime}.
	 *
	 * @param key The {@code key} of the mapping to convert.
	 *
	 * @return The converted instance of {@link InputTime}.
	 * @throws InputConversionException When the mapping with the provided {@code key} cannot be converted.
	 */
	InputTime getTime(String key) throws InputConversionException;


	/**
	 * Checks if the value associated with the provided {@code key} in the {@link FormDataMapper} can safely be
	 * converted to an {@link InputRange} using the {@link Form#getRange(String)} method. When
	 * {@link Form#isRange(String)} returns {@code true}, {@link Form#getRange(String)} will never throw an
	 * {@link InputConversionException}.
	 *
	 * @param key The key of the value to check.
	 *
	 * @return {@code true} if the mapping can safely be converted to an instance of {@link InputRange}.
	 * @see Form#getRange(String)
	 */
	boolean isRange(String key);

	/**
	 * Converts and returns the mapping with the provided {@code key} into an instance of {@link InputRange}.
	 *
	 * @param key The {@code key} of the mapping to convert.
	 *
	 * @return The converted instance of {@link InputRange}.
	 * @throws InputConversionException When the mapping with the provided {@code key} cannot be converted.
	 */
	InputRange getRange(String key) throws InputConversionException;

	/**
	 * Checks if the value associated with the provided {@code key} in the {@link FormDataMapper} can safely be
	 * converted to an {@link InputUrl} using the {@link Form#getUrl(String)} method. When
	 * {@link Form#isUrl(String)} returns {@code true}, {@link Form#getUrl(String)} will never throw an
	 * {@link InputConversionException}.
	 *
	 * @param key The key of the value to check.
	 *
	 * @return {@code true} if the mapping can safely be converted to an instance of {@link InputUrl}.
	 * @see Form#getUrl(String)
	 */
	boolean isUrl(String key);

	/**
	 * Converts and returns the mapping with the provided {@code key} into an instance of {@link InputUrl}.
	 *
	 * @param key The {@code key} of the mapping to convert.
	 *
	 * @return The converted instance of {@link InputUrl}.
	 * @throws InputConversionException When the mapping with the provided {@code key} cannot be converted.
	 */
	InputUrl getUrl(String key) throws InputConversionException;
}
