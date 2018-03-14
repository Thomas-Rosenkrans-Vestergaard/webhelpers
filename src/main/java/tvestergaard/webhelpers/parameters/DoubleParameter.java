package tvestergaard.webhelpers.parameters;

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
    public DoubleParameter(N name, Double value,  Iterable<? extends NumberParameter.FailureHandler<N, Double>> onFailures)
    {
        super(name, value, 0d, onFailures);
    }
}
