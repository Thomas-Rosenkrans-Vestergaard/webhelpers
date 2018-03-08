package tvestergaard.webhelpers.parameters;

import tvestergaard.webhelpers.parameters.tomcat.HttpServletRequestDataMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Consumer;

public class Parameters
{

    /**
     * The {@link ParameterDataMapper} providing the data in the form.
     */
    private ParameterDataMapper mapper;

    /**
     * The {@link ParametersErrorHandler} that provides error handlers for the {@link Parameter} instances created by this.
     */
    private ParametersErrorHandler errorHandler;

    /**
     * Creates a new {@link Parameters}.
     *
     * @param dataMapper The {@link ParameterDataMapper} providing the data in the form.
     */
    public Parameters(ParameterDataMapper dataMapper)
    {
        this.mapper = dataMapper;
    }

    /**
     * Creates a new {@link Parameters} for an incoming {@code HttpServletRequest}.
     *
     * @param request The {@code HttpServletRequest} to create an instance of {@link Parameters} for.
     */
    public Parameters(HttpServletRequest request)
    {
        this(new HttpServletRequestDataMapper(request));
    }

    /**
     * Returns {@code true} if the form isContained a mapping from the provided {@code key}.
     *
     * @param key The key to check for.
     * @return {@code true} if the {@link Parameters} isContained a mapping from the provided {@code key}, {@code false} in all other cases.
     */
    public boolean has(String key)
    {
        return mapper.has(key);
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
    public boolean isText(String key)
    {
        return mapper.has(key);
    }

    public TextParameter getText(String key, ErrorHandlerList<TextParameterErrorHandler> errorHandler)
    {
        if (mapper.has(key))
            return new TextParameter(key, mapper.get(key), errorHandler);

        throw new InputConversionException(this, TextParameter.class);
    }

    /**
     * Creates and returns an instance of {@link TextParameter} representing the value associated with the provided {@code key}.
     * <p>
     * No {@code Exception} will be called if the {@link Parameters#isText(String)} method returns {@code true} for the
     * same {@code key}.
     *
     * @param key The {@code key} of the mapping to convert.
     * @return The converted instance of {@link TextParameter}.
     * @throws InputConversionException When the mapping with the provided {@code key} cannot be converted.
     */
    public TextParameter getText(String key) throws InputConversionException
    {
        return getText(key, new ErrorHandlerList<>(errorHandler.getTextErrorHandler()));
    }

    public boolean checkText(String key, ErrorHandlerList<TextParameterErrorHandler> errorHandlers, Consumer<TextParameter> consumer)
    {
        TextParameter textParameter = getText(key, errorHandlers);
        consumer.accept(textParameter);

        return !textParameter.hasErrors();
    }

    public boolean checkText(String key, TextParameterErrorHandler errorHandler, Consumer<TextParameter> consumer)
    {
        return checkText(key, new ErrorHandlerList<>(errorHandler), consumer);
    }

    public boolean checkText(String key, Consumer<TextParameter> consumer)
    {
        return checkText(key, errorHandler.getTextErrorHandler(), consumer);
    }
}
