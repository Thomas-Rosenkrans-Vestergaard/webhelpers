package tvestergaard.webhelpers.parameters;

import java.util.LinkedList;
import java.util.List;

/**
 * An implementation of the {@link Parameter} interface for use on generic values.
 *
 * @param <N> The type of the name of the {@link GenericParameter}.
 * @param <V> The type of the value in the {@link GenericParameter}.
 */
public class GenericParameter<N, V> extends AbstractParameter<N, V>
{

    /**
     * The failure handlers registered with the {@link GenericParameter}.
     */
    private Iterable<? extends FailureHandler<N, V>> failureHandlers;

    /**
     * Creates a new {@link GenericParameter}.
     *
     * @param name            The name of the {@link GenericParameter}.
     * @param value           The value of the {@link GenericParameter}.
     * @param failureHandlers The failure handlers to register with the {@link GenericParameter}.
     */
    public GenericParameter(N name, V value, Iterable<? extends FailureHandler<N, V>> failureHandlers)
    {
        super(name, value);

        this.failureHandlers = failureHandlers;
    }

    /**
     * Creates a new {@link GenericParameter}.
     *
     * @param name           The name of the {@link GenericParameter}.
     * @param value          The value of the {@link GenericParameter}.
     * @param failureHandler The failure handler to register with the {@link GenericParameter}.
     */
    public GenericParameter(N name, V value, FailureHandler<N, V> failureHandler)
    {
        this(name, value, Parameters.iterable(failureHandler));
    }

    /**
     * Creates a new {@link GenericParameter}.
     *
     * @param name  The name of the {@link GenericParameter}.
     * @param value The value of the {@link GenericParameter}.
     */
    public GenericParameter(N name, V value)
    {
        this(name, value, new LinkedList<>());
    }

    /**
     * Functional interface for {@code isPresent} check failures.
     *
     * @param <N> The type of the name of the {@link GenericParameter} on which the {@code isPresent} check failed.
     * @param <V> The type of the value of the {@link GenericParameter} on which the {@code isPresent} check failed.
     *
     * @see GenericParameter#isPresent(IsPresentFailureCallback)
     * @see GenericParameter#isPresent(Iterable)
     * @see GenericParameter#isPresent()
     */
    @FunctionalInterface interface IsPresentFailureCallback<N, V>
    {

        /**
         * Notifies the {@link IsPresentFailureCallback} that an {@code isPresent} check failed.
         *
         * @param parameter The {@link GenericParameter} on which the {@code IsPresent} check failed.
         *
         * @see GenericParameter#isPresent(IsPresentFailureCallback)
         * @see GenericParameter#isPresent(Iterable)
         * @see GenericParameter#isPresent()
         */
        void isPresentFailure(GenericParameter<N, V> parameter);
    }

    /**
     * Checks that the value in the {@link GenericParameter} is not {@code null}.
     *
     * @param failureCallbacks The callbacks used in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isPresent(Iterable<? extends IsPresentFailureCallback<N, V>> failureCallbacks)
    {
        boolean check = value != null;
        if (!check) {
            incrementFailureCount();
            for (IsPresentFailureCallback<N, V> failureCallback : failureCallbacks)
                failureCallback.isPresentFailure(this);
        }

        return check;
    }

    /**
     * Checks that the value in the {@link GenericParameter} is not {@code null}.
     *
     * @param failureCallback The callback used in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isPresent(IsPresentFailureCallback<N, V> failureCallback)
    {
        boolean check = value != null;
        if (!check) {
            incrementFailureCount();
            failureCallback.isPresentFailure(this);
        }

        return check;
    }

    /**
     * Checks that the value in the {@link GenericParameter} is not {@code null}.
     * Notifies the failure handlers provided to the {@link GenericParameter} in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isPresent()
    {
        return isPresent(failureHandlers);
    }

    /**
     * Functional interface for {@code notPresent} check failures.
     *
     * @param <N> The type of the name of the {@link GenericParameter} on which the {@code notPresent} check failed.
     * @param <V> The type of the value of the {@link GenericParameter} on which the {@code notPresent} check failed.
     *
     * @see GenericParameter#notPresent(NotPresentFailureCallback)
     * @see GenericParameter#notPresent(Iterable)
     * @see GenericParameter#notPresent()
     */
    @FunctionalInterface interface NotPresentFailureCallback<N, V>
    {

        /**
         * Notifies the {@link NotPresentFailureCallback} that an {@code notPresent} check failed.
         *
         * @param parameter The {@link GenericParameter} on which the {@code notPresent} check failed.
         *
         * @see GenericParameter#notPresent(NotPresentFailureCallback)
         * @see GenericParameter#notPresent(Iterable)
         * @see GenericParameter#notPresent()
         */
        void notPresentFailure(GenericParameter<N, V> parameter);
    }

    /**
     * Checks that the value in the {@link GenericParameter} is {@code null}.
     *
     * @param failureCallbacks The callbacks notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean notPresent(Iterable<? extends NotPresentFailureCallback<N, V>> failureCallbacks)
    {
        boolean check = value == null;
        if (!check) {
            incrementFailureCount();
            for (NotPresentFailureCallback<N, V> failureCallback : failureCallbacks)
                failureCallback.notPresentFailure(this);
        }

        return check;
    }

    /**
     * Checks that the value in the {@link GenericParameter} is {@code null}.
     *
     * @param failureCallback The callback notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean notPresent(NotPresentFailureCallback<N, V> failureCallback)
    {
        boolean check = value == null;
        if (!check) {
            incrementFailureCount();
            failureCallback.notPresentFailure(this);
        }

        return check;
    }

    /**
     * Checks that the value in the {@link GenericParameter} is {@code null}.
     * Notifies the failure handlers provided to the {@link GenericParameter} in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean notPresent()
    {
        return notPresent(failureHandlers);
    }

    /**
     * Functional interface for {@code isEqual} check failures.
     *
     * @param <N> The type of the name of the {@link GenericParameter} on which the {@code isEqual} check failed.
     * @param <V> The type of the value of the {@link GenericParameter} on which the {@code isEqual} check failed.
     *
     * @see GenericParameter#isEqual(V, IsEqualFailureCallback)
     * @see GenericParameter#isEqual(V, Iterable)
     * @see GenericParameter#isEqual(V)
     */
    @FunctionalInterface interface IsEqualFailureCallback<N, V>
    {

        /**
         * Notifies the {@link IsEqualFailureCallback} that an {@code isEqual} check failed.
         *
         * @param parameter The {@link GenericParameter} on which the {@code isEqual} check failed.
         * @param other     The {@code other} value passed to the {@code isEqual} check that failed.
         *
         * @see GenericParameter#isEqual(V, IsEqualFailureCallback)
         * @see GenericParameter#isEqual(V, Iterable)
         * @see GenericParameter#isEqual(Object)
         */
        void isEqualFailure(GenericParameter<N, V> parameter, V other);
    }

    /**
     * Checks that the value in the {@link GenericParameter} is equal to the provided {@code other}.
     *
     * @param other            The value the value in the {@link GenericParameter} must equal.
     * @param failureCallbacks The callbacks used in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isEqual(V other, Iterable<? extends IsEqualFailureCallback<N, V>> failureCallbacks) throws NullValueException
    {
        nullCheck();

        boolean check = value.equals(other);
        if (!check) {
            incrementFailureCount();
            for (IsEqualFailureCallback<N, V> failureCallback : failureCallbacks)
                failureCallback.isEqualFailure(this, other);
        }

        return check;
    }

    /**
     * Checks that the value in the {@link GenericParameter} is equal to the provided {@code other}.
     *
     * @param other           The value the value in the {@link GenericParameter} must equal.
     * @param failureCallback The callback used in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isEqual(V other, IsEqualFailureCallback<N, V> failureCallback) throws NullValueException
    {
        nullCheck();

        boolean check = value.equals(other);
        if (!check) {
            incrementFailureCount();
            failureCallback.isEqualFailure(this, other);
        }

        return check;
    }

    /**
     * Checks that the value in the {@link GenericParameter} is equal to the provided {@code other}.
     * Notifies the failure handlers provided to the {@link GenericParameter} in case the check fails.
     *
     * @param other The value the value in the {@link GenericParameter} must equal.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isEqual(V other) throws NullValueException
    {
        return isEqual(other, failureHandlers);
    }

    /**
     * Functional interface for {@code notEqual} check failures.
     *
     * @param <N> The type of the name of the {@link GenericParameter} on which the {@code notEqual} check failed.
     * @param <V> The type of the value of the {@link GenericParameter} on which the {@code notEqual} check failed.
     *
     * @see GenericParameter#notEqual(V, NotEqualFailureCallback)
     * @see GenericParameter#notEqual(V, Iterable)
     * @see GenericParameter#notEqual(V)
     */
    @FunctionalInterface interface NotEqualFailureCallback<N, V>
    {

        /**
         * Notifies the {@link NotEqualFailureCallback} that an {@code notEqual} check failed.
         *
         * @param parameter The {@link GenericParameter} on which the {@code notEqual} check failed.
         * @param other     The {@code other} value passed to the {@code notEqual} check that failed.
         *
         * @see GenericParameter#notEqual(V, NotEqualFailureCallback)
         * @see GenericParameter#notEqual(V, Iterable)
         * @see GenericParameter#notEqual(V)
         */
        void notEqualFailure(GenericParameter<N, V> parameter, V other) throws NullValueException;
    }

    /**
     * Checks that the value in the {@link GenericParameter} is not equal to the provided {@code other}.
     *
     * @param other            The value the value in the {@link GenericParameter} must equal.
     * @param failureCallbacks The callbacks used in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean notEqual(V other, Iterable<? extends NotEqualFailureCallback<N, V>> failureCallbacks)
    {
        nullCheck();

        boolean check = !value.equals(other);
        if (!check) {
            incrementFailureCount();
            for (NotEqualFailureCallback<N, V> failureCallback : failureCallbacks)
                failureCallback.notEqualFailure(this, other);
        }

        return check;
    }

    /**
     * Checks that the value in the {@link GenericParameter} is not equal to the provided {@code other}.
     *
     * @param other           The value the value in the {@link GenericParameter} must equal.
     * @param failureCallback The callback used in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean notEqual(V other, NotEqualFailureCallback<N, V> failureCallback)
    {
        nullCheck();

        boolean check = !value.equals(other);
        if (!check) {
            incrementFailureCount();
            failureCallback.notEqualFailure(this, other);
        }

        return check;
    }

    /**
     * Checks that the value in the {@link GenericParameter} is not equal to the provided {@code other}.
     * Notifies the failure handlers provided to the {@link GenericParameter} in case the check fails.
     *
     * @param other The value the value in the {@link GenericParameter} must equal.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean notEqual(V other)
    {
        return notEqual(other, failureHandlers);
    }

    /**
     * Functional interface for {@code isIn} check failures.
     *
     * @param <N> The type of the name of the {@link GenericParameter} on which the {@code isIn} check failed.
     * @param <V> The type of the value of the {@link GenericParameter} on which the {@code isIn} check failed.
     *
     * @see GenericParameter#isIn(List)
     * @see GenericParameter#isIn(List, Iterable)
     * @see GenericParameter#isIn(List, IsInFailureCallback)
     * @see GenericParameter#isIn(V[])
     */
    @FunctionalInterface public interface IsInFailureCallback<N, V>
    {

        /**
         * Notifies the {@link IsInFailureCallback} that an {@code isIn} check failed.
         *
         * @param parameter The {@link GenericParameter} on which the {@code isIn} check failed.
         * @param others    The {@code others} passed to the {@code isIn} check that failed.
         *
         * @see GenericParameter#isIn(List)
         * @see GenericParameter#isIn(List, Iterable)
         * @see GenericParameter#isIn(List, IsInFailureCallback)
         * @see GenericParameter#isIn(V[])
         */
        void isInFailure(GenericParameter<N, V> parameter, Iterable<? extends V> others);
    }

    /**
     * Checks that the value of the {@link GenericParameter} equals one of the provided values.
     *
     * @param others           The values to compare the value of the {@link GenericParameter} to.
     * @param failureCallbacks The callbacks used in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isIn(List<? extends V> others, Iterable<? extends IsInFailureCallback<N, V>> failureCallbacks)
    {
        nullCheck();

        for (V other : others) {
            if (value.equals(other)) {
                return true;
            }
        }

        incrementFailureCount();
        for (IsInFailureCallback<N, V> failureCallback : failureCallbacks)
            failureCallback.isInFailure(this, others);
        return false;
    }

    /**
     * Checks that the value of the {@link GenericParameter} equals one of the provided values.
     *
     * @param others          The values to compare the value of the {@link GenericParameter} to.
     * @param failureCallback The callback used in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isIn(List<? extends V> others, IsInFailureCallback<N, V> failureCallback)
    {
        nullCheck();

        for (V other : others) {
            if (value.equals(other)) {
                return true;
            }
        }

        incrementFailureCount();
        failureCallback.isInFailure(this, others);
        return false;
    }

    /**
     * Checks that the value of the {@link GenericParameter} equals one of the provided values.
     * Notifies the failure handlers provided to the {@link GenericParameter} in case the check fails.
     *
     * @param others The values to compare the value of the {@link GenericParameter} to.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isIn(List<? extends V> others)
    {
        return isIn(others, failureHandlers);
    }

    /**
     * Checks that the value of the {@link GenericParameter} equals one of the provided values.
     * Notifies the failure handlers provided to the {@link GenericParameter} in case the check fails.
     *
     * @param others The values to compare the value of the {@link GenericParameter} to.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isIn(V... others)
    {
        return isIn(Parameters.list(others), failureHandlers);
    }

    /**
     * Functional interface for {@code notIn} check failures.
     *
     * @param <N> The type of the name of the {@link GenericParameter} on which the {@code notIn} check failed.
     * @param <V> The type of the value of the {@link GenericParameter} on which the {@code notIn} check failed.
     *
     * @see GenericParameter#notIn(List)
     * @see GenericParameter#notIn(List, NotInFailureCallback)
     * @see GenericParameter#notIn(List, Iterable)
     * @see GenericParameter#notIn(V[])
     */
    @FunctionalInterface public interface NotInFailureCallback<N, V>
    {

        /**
         * Notifies the {@link NotInFailureCallback} that an {@code notIn} check failed.
         *
         * @param parameter The {@link GenericParameter} on which the {@code notIn} check failed.
         * @param others    The {@code others} passed to the {@code notIn} check that failed.
         * @param collision The index where the collision occurred.
         *
         * @see GenericParameter#notIn(List)
         * @see GenericParameter#notIn(List, NotInFailureCallback)
         * @see GenericParameter#notIn(List, Iterable)
         */
        void notInFailure(GenericParameter<N, V> parameter, List<? extends V> others, int collision);
    }

    /**
     * Checks that the value of the {@link GenericParameter} does not equal one of the provided {@code others}.
     *
     * @param others           The values to compare the value of the {@link GenericParameter} to.
     * @param failureCallbacks The callbacks used in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean notIn(List<? extends V> others, Iterable<? extends NotInFailureCallback<N, V>> failureCallbacks)
    {
        nullCheck();

        int size = others.size();
        for (int x = 0; x < size; x++) {
            if (value.equals(others.get(x))) {
                incrementFailureCount();
                for (NotInFailureCallback failureCallback : failureCallbacks)
                    failureCallback.notInFailure(this, others, x);
                return false;
            }
        }

        return true;
    }

    /**
     * Checks that the value of the {@link GenericParameter} does not equal one of the provided {@code others}.
     *
     * @param others          The values to compare the value of the {@link GenericParameter} to.
     * @param failureCallback The callback used in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean notIn(List<? extends V> others, NotInFailureCallback<N, V> failureCallback)
    {
        nullCheck();

        int size = others.size();
        for (int x = 0; x < size; x++) {
            if (value.equals(others.get(x))) {
                incrementFailureCount();
                failureCallback.notInFailure(this, others, x);
                return false;
            }
        }

        return true;
    }

    /**
     * Checks that the value of the {@link GenericParameter} does not equal one of the provided {@code others}.
     * Notifies the failure handlers provided to the {@link GenericParameter} in case the check fails.
     *
     * @param others The values to compare the value of the {@link GenericParameter} to.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean notIn(List<? extends V> others)
    {
        return notIn(others, failureHandlers);
    }

    /**
     * Checks that the value of the {@link GenericParameter} does not equal one of the provided {@code others}.
     * Notifies the failure handlers provided to the {@link GenericParameter} in case the check fails.
     *
     * @param others The values to compare the value of the {@link GenericParameter} to.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean notIn(V... others)
    {
        return notIn(Parameters.list(others), failureHandlers);
    }

    /**
     * Checks that the value in the {@link GenericParameter} is not {@code null}.
     *
     * @throws NullValueException When the value in the {@link GenericParameter} is {@code null}.
     */
    private void nullCheck() throws NullValueException
    {
        if (value == null)
            throw new NullValueException();
    }

    /**
     * The interface contract for failure handlers for {@link GenericParameter}s.
     *
     * @param <N> The type of the name of the {@link Parameter} handled by the {@link FailureHandler}.
     * @param <V> The type of the value of the {@link Parameter} handled by the {@link FailureHandler}.
     */
    interface FailureHandler<N, V> extends Parameter.FailureHandler<N, V>,
                                           IsPresentFailureCallback<N, V>,
                                           NotPresentFailureCallback<N, V>,
                                           IsEqualFailureCallback<N, V>,
                                           NotEqualFailureCallback<N, V>,
                                           IsInFailureCallback<N, V>,
                                           NotInFailureCallback<N, V>
    {

        /**
         * Notifies the {@link IsPresentFailureCallback} that an {@code isPresent} check failed.
         *
         * @param parameter The {@link GenericParameter} on which the {@code IsPresent} check failed.
         *
         * @see GenericParameter#isPresent(IsPresentFailureCallback)
         * @see GenericParameter#isPresent(Iterable)
         * @see GenericParameter#isPresent()
         */
        @Override default void isPresentFailure(GenericParameter<N, V> parameter)
        {

        }

        /**
         * Notifies the {@link NotPresentFailureCallback} that an {@code notPresent} check failed.
         *
         * @param parameter The {@link GenericParameter} on which the {@code notPresent} check failed.
         *
         * @see GenericParameter#notPresent(NotPresentFailureCallback)
         * @see GenericParameter#notPresent(Iterable)
         * @see GenericParameter#notPresent()
         */
        @Override default void notPresentFailure(GenericParameter<N, V> parameter)
        {

        }

        /**
         * Notifies the {@link IsEqualFailureCallback} that an {@code isEqual} check failed.
         *
         * @param parameter The {@link GenericParameter} on which the {@code isEqual} check failed.
         * @param other     The {@code other} value passed to the {@code isEqual} check that failed.
         *
         * @see GenericParameter#isEqual(V, IsEqualFailureCallback)
         * @see GenericParameter#isEqual(V, Iterable)
         * @see GenericParameter#isEqual(Object)
         */
        @Override default void isEqualFailure(GenericParameter<N, V> parameter, V other)
        {

        }

        /**
         * Notifies the {@link IsEqualFailureCallback} that an {@code notEqual} check failed.
         *
         * @param parameter The {@link GenericParameter} on which the {@code notEqual} check failed.
         * @param other     The {@code other} value passed to the {@code notEqual} check that failed.
         *
         * @see GenericParameter#notEqual(V, NotEqualFailureCallback)
         * @see GenericParameter#notEqual(V, Iterable)
         * @see GenericParameter#notEqual(V)
         */
        @Override default void notEqualFailure(GenericParameter<N, V> parameter, V other) throws NullValueException
        {

        }

        /**
         * Notifies the {@link IsEqualFailureCallback} that an {@code isIn} check failed.
         *
         * @param parameter The {@link GenericParameter} on which the {@code isIn} check failed.
         * @param others    The {@code others} passed to the {@code isIn} check that failed.
         *
         * @see GenericParameter#isIn(List)
         * @see GenericParameter#isIn(List, Iterable)
         * @see GenericParameter#isIn(List, IsInFailureCallback)
         */
        @Override default void isInFailure(GenericParameter<N, V> parameter, Iterable<? extends V> others)
        {

        }

        /**
         * Notifies the {@link NotInFailureCallback} that an {@code notIn} check failed.
         *
         * @param parameter The {@link GenericParameter} on which the {@code notIn} check failed.
         * @param others    The {@code others} passed to the {@code notIn} check that failed.
         * @param collision The index where the collision occurred.
         *
         * @see GenericParameter#notIn(List)
         * @see GenericParameter#notIn(List, NotInFailureCallback)
         * @see GenericParameter#notIn(List, Iterable)
         */
        @Override default void notInFailure(GenericParameter<N, V> parameter, List<? extends V> others, int collision)
        {

        }
    }
}
