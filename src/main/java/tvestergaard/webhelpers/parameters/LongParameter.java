package tvestergaard.webhelpers.parameters;

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
    public LongParameter(N name, Long value, Iterable<? extends NumberParameter.FailureHandler<N, Long>> onFailures)
    {
        super(name, value, 0l, onFailures);
    }
}
