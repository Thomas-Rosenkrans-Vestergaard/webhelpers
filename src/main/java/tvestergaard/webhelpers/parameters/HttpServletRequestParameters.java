package tvestergaard.webhelpers.parameters;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Consumer;

/**
 * {@link Parameters} implementation allowing for validation of parameters provided to instances of {@link HttpServletRequest}.
 */
public class HttpServletRequestParameters extends AbstractParameters<String>
{

    /**
     * The {@code HttpServletRequest} to validate the parameters of.
     */
    private final HttpServletRequest request;

    /**
     * Creates a new {@link HttpServletRequestParameters}.
     *
     * @param request The {@code HttpServletRequest} to validate the parameters of.
     */
    public HttpServletRequestParameters(HttpServletRequest request)
    {
        this.request = request;
    }

    /**
     * Checks that the mapping associated with the provided {@code name} can safely be converted to an instance of
     * {@link TextParameter} using the {@link Parameters#getText(Object)} method. When this method returns {@code true},
     * the {@link Parameters#getText(Object)} method must not throw an exception when provided the same {@code name}.
     *
     * @param name The name of the mapping to check.
     *
     * @return {@code true} if the mapping with the provided {@code name} can safely be converted to the instance of {@link TextParameter}.
     */
    @Override public boolean isText(String name)
    {
        return true;
    }

    /**
     * Returns an instance of {@link TextParameter} from the mapping of the provided {@code name}. When no mapping
     * with the provided {@code name} exists, the method must return a new instance of {@link TextParameter} with the
     * provided {@code name} and {@code value = null}.
     *
     * @param name The name of the mapping from which to create the new {@link TextParameter}.
     *
     * @return The newly created {@link TextParameter}.
     * @throws ParameterConversionException When the mapping associated with the provided {@code name} cannot be converted
     *                                      to the instance of {@link TextParameter}.
     */
    @Override public TextParameter<String> getText(String name) throws ParameterConversionException
    {
        return new TextParameter(name, request.getParameter(name), textParameterFailureHandlers);
    }

    /**
     * Creates and provides to the {@code consumer} an instance of {@link TextParameter} from the mapping of the provided
     * {@code name}. When no mapping with the provided {@code name} exists, the new instance of {@link TextParameter}
     * with be created using the provided {@code name} and {@code value = null}.
     *
     * @param name     The name of the mapping from which to create an instance of {@link TextParameter}.
     * @param consumer The consumer that is provided the newly created instance of {@link TextParameter}.
     *
     * @return {@code true} when the checks performed on the instance of {@link TextParameter} during the execution of
     * the provided {@code Consumer} passed.
     */
    @Override public boolean onText(String name, Consumer<TextParameter<String>> consumer) throws ParameterConversionException
    {
        TextParameter<String> parameter = new TextParameter<>(name, request.getParameter(name), textParameterFailureHandlers);
        consumer.accept(parameter);
        return !parameter.hasFailures();
    }

    /**
     * Checks that the mapping associated with the provided {@code name} can safely be converted to an instance of
     * {@link IntParameter} using the {@link Parameters#getInt(Object)} method. When this method returns {@code true},
     * the {@link Parameters#getInt(Object)} method must not throw an exception when provided the same {@code name}.
     *
     * @param name The name of the mapping to check.
     *
     * @return {@code true} if the mapping with the provided {@code name} can safely be converted to the instance of {@link IntParameter}.
     */
    @Override public boolean isInt(String name)
    {
        String value = request.getParameter(name);

        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns an instance of {@link IntParameter} from the mapping of the provided {@code name}. When no mapping
     * with the provided {@code name} exists, the method must return a new instance of {@link IntParameter} with the
     * provided {@code name} and {@code value = null}.
     *
     * @param name The name of the mapping from which to create the new {@link IntParameter}.
     *
     * @return The newly created {@link IntParameter}.
     * @throws ParameterConversionException When the mapping associated with the provided {@code name} cannot be converted
     *                                      to the instance of {@link IntParameter}.
     */
    @Override public IntParameter<String> getInt(String name) throws ParameterConversionException
    {
        try {
            int value = Integer.parseInt(request.getParameter(name));
            return new IntParameter(name, value, intParameterFailureHandlers);
        } catch (NumberFormatException e) {
            throw new ParameterConversionException(e, IntParameter.class);
        }
    }

    /**
     * Creates and provides to the {@code consumer} an instance of {@link IntParameter} from the mapping of the provided
     * {@code name}. When no mapping with the provided {@code name} exists, the new instance of {@link IntParameter}
     * with be created using the provided {@code name} and {@code value = null}.
     *
     * @param name     The name of the mapping from which to create an instance of {@link IntParameter}.
     * @param consumer The consumer that is provided the newly created instance of {@link IntParameter}.
     *
     * @return {@code true} when the checks performed on the instance of {@link IntParameter} during the execution of
     * the provided {@code Consumer} passed.
     */
    @Override public boolean onInt(String name, Consumer<IntParameter<String>> consumer) throws ParameterConversionException
    {
        IntParameter<String> parameter = getInt(name);
        consumer.accept(parameter);
        return !parameter.hasFailures();
    }

    /**
     * Checks that the mapping associated with the provided {@code name} can safely be converted to an instance of
     * {@link LongParameter} using the {@link Parameters#getLong(Object)} method. When this method returns {@code true},
     * the {@link Parameters#getLong(Object)} method must not throw an exception when provided the same {@code name}.
     *
     * @param name The name of the mapping to check.
     *
     * @return {@code true} if the mapping with the provided {@code name} can safely be converted to the instance of {@link LongParameter}.
     */
    @Override public boolean isLong(String name)
    {
        try {
            Long.parseLong(request.getParameter(name));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns an instance of {@link LongParameter} from the mapping of the provided {@code name}. When no mapping
     * with the provided {@code name} exists, the method must return a new instance of {@link LongParameter} with the
     * provided {@code name} and {@code value = null}.
     *
     * @param name The name of the mapping from which to create the new {@link LongParameter}.
     *
     * @return The newly created {@link LongParameter}.
     * @throws ParameterConversionException When the mapping associated with the provided {@code name} cannot be converted
     *                                      to the instance of {@link LongParameter}.
     */
    @Override public LongParameter<String> getLong(String name) throws ParameterConversionException
    {
        try {
            return new LongParameter<>(name, Long.parseLong(request.getParameter(name)), longParameterFailureHandlers);
        } catch (NumberFormatException e) {
            throw new ParameterConversionException(e, LongParameter.class);
        }
    }

    /**
     * Creates and provides to the {@code consumer} an instance of {@link LongParameter} from the mapping of the provided
     * {@code name}. When no mapping with the provided {@code name} exists, the new instance of {@link LongParameter}
     * with be created using the provided {@code name} and {@code value = null}.
     *
     * @param name     The name of the mapping from which to create an instance of {@link LongParameter}.
     * @param consumer The consumer that is provided the newly created instance of {@link LongParameter}.
     *
     * @return {@code true} when the checks performed on the instance of {@link LongParameter} during the execution of
     * the provided {@code Consumer} passed.
     */
    @Override public boolean onLong(String name, Consumer<LongParameter<String>> consumer) throws ParameterConversionException
    {
        LongParameter<String> parameter = getLong(name);
        consumer.accept(parameter);
        return !parameter.hasFailures();
    }

    /**
     * Checks that the mapping associated with the provided {@code name} can safely be converted to an instance of
     * {@link FloatParameter} using the {@link Parameters#getFloat(Object)} method. When this method returns {@code true},
     * the {@link Parameters#getFloat(Object)} method must not throw an exception when provided the same {@code name}.
     *
     * @param name The name of the mapping to check.
     *
     * @return {@code true} if the mapping with the provided {@code name} can safely be converted to the instance of {@link FloatParameter}.
     */
    @Override public boolean isFloat(String name)
    {
        try {
            Float.parseFloat(request.getParameter(name));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns an instance of {@link FloatParameter} from the mapping of the provided {@code name}. When no mapping
     * with the provided {@code name} exists, the method must return a new instance of {@link FloatParameter} with the
     * provided {@code name} and {@code value = null}.
     *
     * @param name The name of the mapping from which to create the new {@link FloatParameter}.
     *
     * @return The newly created {@link FloatParameter}.
     * @throws ParameterConversionException When the mapping associated with the provided {@code name} cannot be converted
     *                                      to the instance of {@link FloatParameter}.
     */
    @Override public FloatParameter<String> getFloat(String name) throws ParameterConversionException
    {
        try {
            return new FloatParameter<>(name, Float.parseFloat(request.getParameter(name)), floatParameterFailureHandlers);
        } catch (NumberFormatException e) {
            throw new ParameterConversionException(e, FloatParameter.class);
        }
    }

    /**
     * Creates and provides to the {@code consumer} an instance of {@link FloatParameter} from the mapping of the provided
     * {@code name}. When no mapping with the provided {@code name} exists, the new instance of {@link FloatParameter}
     * with be created using the provided {@code name} and {@code value = null}.
     *
     * @param name     The name of the mapping from which to create an instance of {@link FloatParameter}.
     * @param consumer The consumer that is provided the newly created instance of {@link FloatParameter}.
     *
     * @return {@code true} when the checks performed on the instance of {@link FloatParameter} during the execution of
     * the provided {@code Consumer} passed.
     */
    @Override public boolean onFloat(String name, Consumer<FloatParameter<String>> consumer) throws ParameterConversionException
    {
        FloatParameter<String> parameter = getFloat(name);
        consumer.accept(parameter);
        return !parameter.hasFailures();
    }

    /**
     * Checks that the mapping associated with the provided {@code name} can safely be converted to an instance of
     * {@link DoubleParameter} using the {@link Parameters#getDouble(Object)} method. When this method returns {@code true},
     * the {@link Parameters#getDouble(Object)} method must not throw an exception when provided the same {@code name}.
     *
     * @param name The name of the mapping to check.
     *
     * @return {@code true} if the mapping with the provided {@code name} can safely be converted to the instance of {@link DoubleParameter}.
     */
    @Override public boolean isDouble(String name)
    {
        String value = request.getParameter(name);

        if (value == null)
            return true;

        try {

            Double.parseDouble(request.getParameter(name));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns an instance of {@link DoubleParameter} from the mapping of the provided {@code name}. When no mapping
     * with the provided {@code name} exists, the method must return a new instance of {@link DoubleParameter} with the
     * provided {@code name} and {@code value = null}.
     *
     * @param name The name of the mapping from which to create the new {@link DoubleParameter}.
     *
     * @return The newly created {@link DoubleParameter}.
     * @throws ParameterConversionException When the mapping associated with the provided {@code name} cannot be converted
     *                                      to the instance of {@link DoubleParameter}.
     */
    @Override public DoubleParameter<String> getDouble(String name) throws ParameterConversionException
    {
        return null;
    }

    /**
     * Creates and provides to the {@code consumer} an instance of {@link DoubleParameter} from the mapping of the provided
     * {@code name}. When no mapping with the provided {@code name} exists, the new instance of {@link DoubleParameter}
     * with be created using the provided {@code name} and {@code value = null}.
     *
     * @param name     The name of the mapping from which to create an instance of {@link DoubleParameter}.
     * @param consumer The consumer that is provided the newly created instance of {@link DoubleParameter}.
     *
     * @return {@code true} when the checks performed on the instance of {@link DoubleParameter} during the execution of
     * the provided {@code Consumer} passed.
     */
    @Override public boolean onDouble(String name, Consumer<DoubleParameter<String>> consumer) throws ParameterConversionException
    {
        return false;
    }
}
