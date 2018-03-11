package tvestergaard.webhelpers.parameters;

/**
 * {@link Parameter} implementations for performing checks upon {@code Integer} values.
 *
 * @param <K> The type of the name of the {@link IntegerParameter}.
 */
public class IntegerParameter<K> extends NumberParameter<K, Integer>
{

    /**
     * Creates a new {@link IntegerParameter}.
     *
     * @param name            The name of the {@link IntegerParameter}.
     * @param value           The value of the {@link IntegerParameter}.
     * @param failureHandlers The failure handlers used by the {@link IntegerParameter}.
     */
    public IntegerParameter(K name, Integer value, Iterable<FailureHandler<K>> failureHandlers)
    {
        super(name, value, 0, failureHandlers);
    }

    /**
     * Contains the failure handler callbacks for checks in the {@link IntegerParameter} class.
     *
     * @param <K> The type of the name of the {@link IntegerParameter}.
     */
    public interface FailureHandler<K> extends NumberParameter.FailureHandler<K, Integer>
    {

    }
}
