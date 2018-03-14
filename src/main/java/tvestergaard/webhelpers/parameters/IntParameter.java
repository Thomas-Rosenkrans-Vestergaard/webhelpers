package tvestergaard.webhelpers.parameters;

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
    public IntParameter(N name, Integer value, Iterable<? extends NumberParameter.FailureHandler<N, Integer>> onFailures)
    {
        super(name, value, 0, onFailures);
    }
}
