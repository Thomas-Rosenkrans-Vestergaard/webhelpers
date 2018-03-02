package tvestergaard.webhelpers.parameters;

import tvestergaard.webhelpers.parameters.servlet.HttpServletRequestDataMapper;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;

public class FormHelper implements Form
{

	/**
	 * The {@link FormDataMapper} providing the data in the form.
	 */
	private FormDataMapper mapper;

	/**
	 * Creates a new {@link FormHelper}.
	 *
	 * @param dataMapper The {@link FormDataMapper} providing the data in the form.
	 */
	public FormHelper(FormDataMapper dataMapper)
	{
		this.mapper = dataMapper;
	}

	/**
	 * Creates a new {@link FormHelper} for an incoming {@code HttpServletRequest}.
	 *
	 * @param request The
	 */
	public FormHelper(HttpServletRequest request)
	{
		this(new HttpServletRequestDataMapper(request));
	}

	/**
	 * Returns {@code true} if the form contains a mapping from the provided {@code key}.
	 *
	 * @param key The key to check for.
	 *
	 * @return {@code true} if the form contains a mapping from the provided {@code key}. Returns {@code false}
	 * in all other cases.
	 */
	@Override public boolean has(String key)
	{
		return mapper.has(key);
	}

	/**
	 * Returns the {@link FormDataMapper} providing the form data to the {@link Form}.
	 *
	 * @return The {@link FormDataMapper}.
	 */
	@Override public FormDataMapper getDataMapper()
	{
		return mapper;
	}

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
	@Override public boolean isText(String key)
	{
		return mapper.has(key);
	}

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
	@Override public InputText getText(String key) throws InputConversionException
	{
		if (mapper.has(key)) {
			return new InputText(key, mapper.get(key));
		}

		throw new InputConversionException(this, InputText.class);
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
	 * @param key    The key of the value to check.
	 * @param format
	 *
	 * @return {@code true} if the mapping can safely be converted to an instance of {@link InputDate}.
	 * @see Form#getDate(String)
	 */
	@Override public boolean isDate(String key, DateFormat format)
	{
		try {
			format.parse(mapper.get(key));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Converts and returns the mapping with the provided {@code key} into an instance of {@link InputDate} using the
	 * provided {@code format}.
	 * <p>
	 * No {@code Exception} will be called if the {@link Form#isDate(String, DateFormat)} method returns {@code true} for the
	 * same {@code key} and {@code format}.
	 *
	 * @param key    The key of the mapping to convert.
	 * @param format The format of the date in the string.
	 *
	 * @return The converted instance of {@link InputDate}.
	 * @throws InputConversionException When the mapping with the provided {@code key} cannot be converted.
	 */
	@Override public InputDate getDate(String key, DateFormat format) throws InputConversionException
	{
		try {
			String value = mapper.get(key);
			return new InputDate(key, value, format.parse(value), format);
		} catch (Exception e) {
			throw new InputConversionException(this, InputDate.class);
		}
	}

	@Override public boolean isEmail(String key)
	{
		return false;
	}

	@Override public InputEmail getEmail(String key) throws InputConversionException
	{
		return null;
	}

	@Override public boolean isNumber(String key)
	{
		return false;
	}

	@Override public InputNumber getNumber(String key) throws InputConversionException
	{
		return null;
	}

	@Override public boolean isTime(String key)
	{
		return false;
	}

	@Override public InputTime getTime(String key) throws InputConversionException
	{
		return null;
	}

	@Override public boolean isRange(String key)
	{
		return false;
	}

	@Override public InputRange getRange(String key) throws InputConversionException
	{
		return null;
	}

	@Override public boolean isUrl(String key)
	{
		return false;
	}

	@Override public InputUrl getUrl(String key) throws InputConversionException
	{
		return null;
	}
}
