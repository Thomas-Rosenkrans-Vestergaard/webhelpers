package tvestergaard.webhelpers.parameters;

/**
 * {@link Parameter} implementations for performing checks upon {@code Float} values.
 *
 * @param <K> The type of the name of the {@link FloatParameter}.
 */
public class FloatParameter<K> extends NumberParameter<K, Float>
{

    /**
     * Creates a new {@link FloatParameter}.
     *
     * @param name            The name of the {@link FloatParameter}.
     * @param value           The value of the {@link FloatParameter}.
     * @param failureHandlers The failure handlers to register in the {@link FloatParameter}.
     */
    public FloatParameter(K name, Float value, Iterable<FailureHandler<K>> failureHandlers)
    {
        super(name, value, 0f, failureHandlers);
    }

    /**
     * Contains the failure handler callbacks for checks in the {@link FloatParameter} class.
     *
     * @param <K> The type of the name of the {@link FloatParameter}.
     */
    interface FailureHandler<K> extends NumberParameter.FailureHandler<K, Float>
    {

    }
}
