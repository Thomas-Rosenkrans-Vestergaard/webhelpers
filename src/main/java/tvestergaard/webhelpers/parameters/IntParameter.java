package tvestergaard.webhelpers.parameters;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * {@link NumberParameter} implementation using {@link Integer} values.
 *
 * @param <N> The type of the name of the {@link IntParameter}.
 */
public class IntParameter<N> extends NumberParameter<N, Integer>
{

    /**
     * Creates a new {@link IntParameter} using the provided {@code name} and {@code value}.
     *
     * @param name  The name of the {@link IntParameter}.
     * @param value The value of the {@link IntParameter}.
     */
    public IntParameter(N name, Integer value)
    {
        super(name, value, 0, new LinkedList<>());
    }

    /**
     * Creates a new {@link IntParameter} using the provided {@code name}, {@code value} and {@code failureHandler}.
     *
     * @param name           The name of the {@link IntParameter}.
     * @param value          The value of the {@link IntParameter}.
     * @param failureHandler The failure handler to register with the {@link IntParameter}.
     */
    public IntParameter(N name, Integer value, FailureHandler<N, Integer> failureHandler)
    {
        super(name, value, 0, Arrays.asList(failureHandler));
    }

    /**
     * Creates a new {@link IntParameter} using the provided {@code name}, {@code value} and {@code failureHandlers}.
     *
     * @param name            The name of the {@link IntParameter}.
     * @param value           The value of the {@link IntParameter}.
     * @param failureHandlers The failure handlers to register with the {@link IntParameter}.
     */
    public IntParameter(N name, Integer value, Iterable<? extends FailureHandler<N, Integer>> failureHandlers)
    {
        super(name, value, 0, failureHandlers);
    }
}
