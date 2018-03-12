package tvestergaard.webhelpers.parameters;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * {@link NumberParameter} implementation using {@link Long} values.
 *
 * @param <N> The type of the name of the {@link LongParameter}.
 */
public class LongParameter<N> extends NumberParameter<N, Long>
{

    /**
     * Creates a new {@link LongParameter} using the provided {@code name} and {@code value}.
     *
     * @param name  The name of the {@link LongParameter}.
     * @param value The value of the {@link LongParameter}.
     */
    public LongParameter(N name, Long value)
    {
        super(name, value, 0l, new LinkedList<>());
    }

    /**
     * Creates a new {@link LongParameter} using the provided {@code name}, {@code value} and {@code failureHandler}.
     *
     * @param name           The name of the {@link LongParameter}.
     * @param value          The value of the {@link LongParameter}.
     * @param failureHandler The failure handler to register with the {@link LongParameter}.
     */
    public LongParameter(N name, Long value, FailureHandler<N, Long> failureHandler)
    {
        super(name, value, 0l, Arrays.asList(failureHandler));
    }

    /**
     * Creates a new {@link LongParameter} using the provided {@code name}, {@code value} and {@code failureHandlers}.
     *
     * @param name            The name of the {@link LongParameter}.
     * @param value           The value of the {@link LongParameter}.
     * @param failureHandlers The failure handlers to register with the {@link LongParameter}.
     */
    public LongParameter(N name, Long value, Iterable<? extends FailureHandler<N, Long>> failureHandlers)
    {
        super(name, value, 0l, failureHandlers);
    }
}
