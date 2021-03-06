package tvestergaard.webhelpers.parameters;

import java.util.function.Consumer;

public interface Parameters<N>
{

    /**
     * Checks that the mapping associated with the provided {@code name} can safely be converted to an instance of
     * {@link TextParameter} using the {@link Parameters#getText(Object)} method. When this method returns {@code true},
     * the {@link Parameters#getText(N)} method must not throw an exception when provided the same {@code name}.
     *
     * @param name The name of the mapping to check.
     *
     * @return {@code true} if the mapping with the provided {@code name} can safely be converted to the instance of {@link TextParameter}.
     */
    boolean isText(N name);

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
    TextParameter<N> getText(N name) throws ParameterConversionException;

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
    boolean onText(N name, Consumer<TextParameter<N>> consumer) throws ParameterConversionException;

    /**
     * Checks that the mapping associated with the provided {@code name} can safely be converted to an instance of
     * {@link IntParameter} using the {@link Parameters#getInt(Object)} method. When this method returns {@code true},
     * the {@link Parameters#getInt(N)} method must not throw an exception when provided the same {@code name}.
     *
     * @param name The name of the mapping to check.
     *
     * @return {@code true} if the mapping with the provided {@code name} can safely be converted to the instance of {@link IntParameter}.
     */
    boolean isInt(N name);

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
    IntParameter<N> getInt(N name) throws ParameterConversionException;

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
    boolean onInt(N name, Consumer<IntParameter<N>> consumer) throws ParameterConversionException;

    /**
     * Checks that the mapping associated with the provided {@code name} can safely be converted to an instance of
     * {@link LongParameter} using the {@link Parameters#getLong(Object)} method. When this method returns {@code true},
     * the {@link Parameters#getLong(N)} method must not throw an exception when provided the same {@code name}.
     *
     * @param name The name of the mapping to check.
     *
     * @return {@code true} if the mapping with the provided {@code name} can safely be converted to the instance of {@link LongParameter}.
     */
    boolean isLong(N name);

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
    LongParameter<N> getLong(N name) throws ParameterConversionException;

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
    boolean onLong(N name, Consumer<LongParameter<N>> consumer) throws ParameterConversionException;

    /**
     * Checks that the mapping associated with the provided {@code name} can safely be converted to an instance of
     * {@link FloatParameter} using the {@link Parameters#getFloat(Object)} method. When this method returns {@code true},
     * the {@link Parameters#getFloat(N)} method must not throw an exception when provided the same {@code name}.
     *
     * @param name The name of the mapping to check.
     *
     * @return {@code true} if the mapping with the provided {@code name} can safely be converted to the instance of {@link FloatParameter}.
     */
    boolean isFloat(N name);

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
    FloatParameter<N> getFloat(N name) throws ParameterConversionException;

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
    boolean onFloat(N name, Consumer<FloatParameter<N>> consumer) throws ParameterConversionException;

    /**
     * Checks that the mapping associated with the provided {@code name} can safely be converted to an instance of
     * {@link DoubleParameter} using the {@link Parameters#getDouble(Object)} method. When this method returns {@code true},
     * the {@link Parameters#getDouble(N)} method must not throw an exception when provided the same {@code name}.
     *
     * @param name The name of the mapping to check.
     *
     * @return {@code true} if the mapping with the provided {@code name} can safely be converted to the instance of {@link DoubleParameter}.
     */
    boolean isDouble(N name);

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
    DoubleParameter<N> getDouble(N name) throws ParameterConversionException;

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
    boolean onDouble(N name, Consumer<DoubleParameter<N>> consumer) throws ParameterConversionException;

    /**
     * Adds an object to handle failure raised by checks performed on instances of {@link TextParameter} created by this
     * object.
     *
     * @param onFailure The failure handler to add.
     */
    void addTextFailureHandler(TextParameter.FailureHandler<N> onFailure);

    /**
     * Adds an object to handle failure raised by checks performed on instances of {@link IntParameter} created by this
     * object.
     *
     * @param onFailure The failure handler to add.
     */
    void addIntFailureHandler(IntParameter.FailureHandler<N, Integer> onFailure);

    /**
     * Adds an object to handle failure raised by checks performed on instances of {@link LongParameter} created by this
     * object.
     *
     * @param onFailure The failure handler to add.
     */
    void addLongFailureHandler(LongParameter.FailureHandler<N, Long> onFailure);

    /**
     * Adds an object to handle failure raised by checks performed on instances of {@link FloatParameter} created by this
     * object.
     *
     * @param onFailure The failure handler to add.
     */
    void addFloatFailureHandler(FloatParameter.FailureHandler<N, Float> onFailure);

    /**
     * Adds an object to handle failure raised by checks performed on instances of {@link DoubleParameter} created by this
     * object.
     *
     * @param onFailure The failure handler to add.
     */
    void addDoubleFailureHandler(DoubleParameter.FailureHandler<N, Double> onFailure);
}
