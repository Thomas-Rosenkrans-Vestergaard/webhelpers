package tvestergaard.webhelpers.parameters;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * {@link NumberParameter} implementation using {@link Float} values.
 *
 * @param <N> The type of the name of the {@link FloatParameter}.
 */
public class FloatParameter<N> extends NumberParameter<N, Float>
{

    /**
     * Creates a new {@link FloatParameter} using the provided {@code name} and {@code value}.
     *
     * @param name  The name of the {@link FloatParameter}.
     * @param value The value of the {@link FloatParameter}.
     */
    public FloatParameter(N name, Float value)
    {
        super(name, value, 0f, new LinkedList<>());
    }

    /**
     * Creates a new {@link FloatParameter} using the provided {@code name}, {@code value} and {@code failureHandler}.
     *
     * @param name           The name of the {@link FloatParameter}.
     * @param value          The value of the {@link FloatParameter}.
     * @param failureHandler The failure handler to register with the {@link FloatParameter}.
     */
    public FloatParameter(N name, Float value, FailureHandler<N, Float> failureHandler)
    {
        super(name, value, 0f, Arrays.asList(failureHandler));
    }

    /**
     * Creates a new {@link FloatParameter} using the provided {@code name}, {@code value} and {@code failureHandlers}.
     *
     * @param name            The name of the {@link FloatParameter}.
     * @param value           The value of the {@link FloatParameter}.
     * @param failureHandlers The failure handlers to register with the {@link FloatParameter}.
     */
    public FloatParameter(N name, Float value, Iterable<? extends FailureHandler<N, Float>> failureHandlers)
    {
        super(name, value, 0f, failureHandlers);
    }
}