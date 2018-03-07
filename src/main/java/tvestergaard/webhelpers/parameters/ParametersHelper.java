package tvestergaard.webhelpers.parameters;

import org.apache.commons.validator.routines.EmailValidator;
import tvestergaard.webhelpers.parameters.tomcat.HttpServletRequestParameterDataMapper;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;

public class ParametersHelper implements Parameters
{

    /**
     * The {@link ParameterDataMapper} providing the data in the form.
     */
    private ParameterDataMapper mapper;

    /**
     * Creates a new {@link ParametersHelper}.
     *
     * @param dataMapper The {@link ParameterDataMapper} providing the data in the form.
     */
    public ParametersHelper(ParameterDataMapper dataMapper)
    {
        this.mapper = dataMapper;
    }

    /**
     * Creates a new {@link ParametersHelper} for an incoming {@code HttpServletRequest}.
     *
     * @param request The
     */
    public ParametersHelper(HttpServletRequest request)
    {
        this(new HttpServletRequestParameterDataMapper(request));
    }

    /**
     * Returns {@code true} if the form contains a mapping from the provided {@code key}.
     *
     * @param key The key to check for.
     * @return {@code true} if the form contains a mapping from the provided {@code key}. Returns {@code false}
     * in all other cases.
     */
    @Override public boolean has(String key)
    {
        return mapper.has(key);
    }

    /**
     * Returns the {@link ParameterDataMapper} providing the form data to the {@link Parameters}.
     *
     * @return The {@link ParameterDataMapper}.
     */
    @Override public ParameterDataMapper getDataMapper()
    {
        return mapper;
    }

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
    @Override public boolean isText(String key)
    {
        return mapper.has(key);
    }

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
    @Override public TextParameter getText(String key) throws InputConversionException
    {
        if (mapper.has(key)) {
            return new TextParameter(key, mapper.get(key));
        }

        throw new InputConversionException(this, TextParameter.class);
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
     * @param key    The key of the value to check.
     * @param format
     * @return {@code true} if the mapping can safely be converted to an instance of {@link DateParameter}.
     * @see Parameters#getDate(String)
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
    @Override public DateParameter getDate(String key, DateFormat format) throws InputConversionException
    {
        try {
            String value = mapper.get(key);
            return new DateParameter(key, value, format.parse(value), format);
        } catch (Exception e) {
            throw new InputConversionException(this, DateParameter.class);
        }
    }

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
     * @return {@code true} if the mapping can safely be converted to an instance of {@link DateParameter}.
     * @see Parameters#getEmail(String)
     */
    @Override public boolean isEmail(String key)
    {
        return EmailValidator.getInstance().isValid(mapper.get(key));
    }

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
    @Override public EmailParameter getEmail(String key) throws InputConversionException
    {
        if (!isEmail(key)) {
            throw new InputConversionException(this, EmailParameter.class);
        }

        return new EmailParameter(key, mapper.get(key));
    }

    @Override public boolean isNumber(String key)
    {
        return false;
    }

    @Override public FloatParameter getNumber(String key) throws InputConversionException
    {
        return null;
    }

    @Override public boolean isTime(String key)
    {
        return false;
    }

    @Override public TimeParameter getTime(String key) throws InputConversionException
    {
        return null;
    }

    @Override public boolean isRange(String key)
    {
        return false;
    }

    @Override public RangeParameter getRange(String key) throws InputConversionException
    {
        return null;
    }

    @Override public boolean isUrl(String key)
    {
        return false;
    }

    @Override public UrlParameter getUrl(String key) throws InputConversionException
    {
        return null;
    }
}
