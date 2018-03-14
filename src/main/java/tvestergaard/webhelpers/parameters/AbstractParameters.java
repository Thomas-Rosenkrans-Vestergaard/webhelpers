package tvestergaard.webhelpers.parameters;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParameters<N> implements Parameters<N>
{

    /**
     * The failure handlers to provide to new instances of {@link TextParameter}.
     */
    protected final List<TextParameter.FailureHandler<N>> textParameterFailureHandlers = new ArrayList<>();

    /**
     * The failure handlers to provide to new instances of {@link IntParameter}.
     */
    protected final List<IntParameter.FailureHandler<N, Integer>> intParameterFailureHandlers = new ArrayList<>();

    /**
     * The failure handlers to provide to new instances of {@link LongParameter}.
     */
    protected final List<LongParameter.FailureHandler<N, Long>> longParameterFailureHandlers = new ArrayList<>();

    /**
     * The failure handlers to provide to new instances of {@link FloatParameter}.
     */
    protected final List<FloatParameter.FailureHandler<N, Float>> floatParameterFailureHandlers = new ArrayList<>();

    /**
     * The failure handlers to provide to new instance of {@link DoubleParameter}.
     */
    protected final List<DoubleParameter.FailureHandler<N, Double>> doubleParameterFailureHandlers = new ArrayList<>();

    /**
     * Adds an object to handle failure raised by checks performed on instances of {@link TextParameter} created by this
     * object.
     *
     * @param onFailure The failure handler to add.
     */
    @Override public void addTextFailureHandler(TextParameter.FailureHandler<N> onFailure)
    {
        textParameterFailureHandlers.add(onFailure);
    }

    /**
     * Adds an object to handle failure raised by checks performed on instances of {@link IntParameter} created by this
     * object.
     *
     * @param onFailure The failure handler to add.
     */
    @Override public void addIntFailureHandler(NumberParameter.FailureHandler<N, Integer> onFailure)
    {
        intParameterFailureHandlers.add(onFailure);
    }

    /**
     * Adds an object to handle failure raised by checks performed on instances of {@link LongParameter} created by this
     * object.
     *
     * @param onFailure The failure handler to add.
     */
    @Override public void addLongFailureHandler(NumberParameter.FailureHandler<N, Long> onFailure)
    {
        longParameterFailureHandlers.add(onFailure);
    }

    /**
     * Adds an object to handle failure raised by checks performed on instances of {@link FloatParameter} created by this
     * object.
     *
     * @param onFailure The failure handler to add.
     */
    @Override public void addFloatFailureHandler(NumberParameter.FailureHandler<N, Float> onFailure)
    {
        floatParameterFailureHandlers.add(onFailure);
    }

    /**
     * Adds an object to handle failure raised by checks performed on instances of {@link DoubleParameter} created by this
     * object.
     *
     * @param onFailure The failure handler to add.
     */
    @Override public void addDoubleFailureHandler(NumberParameter.FailureHandler<N, Double> onFailure)
    {
        doubleParameterFailureHandlers.add(onFailure);
    }
}
