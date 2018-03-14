package tvestergaard.webhelpers.parameters;

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
    public FloatParameter(N name, Float value, Iterable<? extends NumberParameter.FailureHandler<N, Float>> onFailures)
    {
        super(name, value, 0f, onFailures);
    }
}