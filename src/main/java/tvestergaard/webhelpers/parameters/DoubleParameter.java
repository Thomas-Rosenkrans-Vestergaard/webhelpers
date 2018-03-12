package tvestergaard.webhelpers.parameters;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * {@link NumberParameter} implementation using {@link Double} values.
 *
 * @param <N> The type of the name of the {@link DoubleParameter}.
 */
public class DoubleParameter<N> extends NumberParameter<N, Double>
{

    /**
     * Creates a new {@link DoubleParameter} using the provided {@code name} and {@code value}.
     *
     * @param name  The name of the {@link DoubleParameter}.
     * @param value The value of the {@link DoubleParameter}.
     */
    public DoubleParameter(N name, Double value)
    {
        super(name, value, 0d, new LinkedList<>());
    }

    /**
     * Creates a new {@link DoubleParameter} using the provided {@code name}, {@code value} and {@code failureHandler}.
     *
     * @param name           The name of the {@link DoubleParameter}.
     * @param value          The value of the {@link DoubleParameter}.
     * @param failureHandler The failure handler to register with the {@link DoubleParameter}.
     */
    public DoubleParameter(N name, Double value, FailureHandler<N, Double> failureHandler)
    {
        super(name, value, 0d, Arrays.asList(failureHandler));
    }

    /**
     * Creates a new {@link DoubleParameter} using the provided {@code name}, {@code value} and {@code failureHandlers}.
     *
     * @param name            The name of the {@link DoubleParameter}.
     * @param value           The value of the {@link DoubleParameter}.
     * @param failureHandlers The failure handlers to register with the {@link DoubleParameter}.
     */
    public DoubleParameter(N name, Double value, Iterable<? extends FailureHandler<N, Double>> failureHandlers)
    {
        super(name, value, 0d, failureHandlers);
    }
}
