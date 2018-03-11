package tvestergaard.webhelpers.parameters;

import static tvestergaard.webhelpers.parameters.Parameters.iterable;

/**
 * {@link Parameter} implementation for values of type {@code Number}.
 *
 * @param <K> The type of the key of the {@link NumberParameter}.
 * @param <V> The type of the value of the {@link NumberParameter}.
 */
public abstract class NumberParameter<K, V extends Number & Comparable<V>> extends ComparableParameter<K, V>
{

    /**
     * The value representing zero in the type {@code V}.
     */
    private final V zero;

    /**
     * The failure handlers registered with the {@link NumberParameter}.
     */
    private final Iterable<? extends FailureHandler<K, V>> failureHandlers;

    /**
     * Creates a new {@link NumberParameter}.
     *
     * @param name            The name of the {@link NumberParameter}.
     * @param value           The value of the {@link NumberParameter}.
     * @param zero            The value representing zero in the type of {@code V}.
     * @param failureHandlers The failure handlers that are notified when checks performed on this instance of
     *                        {@link NumberParameter} fails.
     */
    public NumberParameter(K name, V value, V zero, Iterable<? extends FailureHandler<K, V>> failureHandlers)
    {
        super(name, value, failureHandlers);
        this.zero = zero;

        this.failureHandlers = failureHandlers;
    }

    /**
     * Functional interface for {@code isPositive} check failures.
     *
     * @param <K> The type of the name of the {@link NumberParameter} on which the {@code isPositive} check failed.
     * @param <V> The type of the value of the {@link NumberParameter} on which the {@code isPositive} check failed.
     *
     * @see NumberParameter#isPositive()
     * @see NumberParameter#isPositive(IsPositiveFailureCallback)
     * @see NumberParameter#isPositive(Iterable)
     */
    @FunctionalInterface public interface IsPositiveFailureCallback<K, V extends Number & Comparable<V>>
    {

        /**
         * Notifies the {@link IsPositiveFailureCallback} that the {@code isPositive} check failed.
         *
         * @param parameter The {@link NumberParameter} on which the {@code isPositive} check failed.
         *
         * @see NumberParameter#isPositive()
         * @see NumberParameter#isPositive(IsPositiveFailureCallback)
         * @see NumberParameter#isPositive(Iterable)
         */
        void isPositiveFailure(NumberParameter<K, V> parameter);
    }

    /**
     * Checks that the value of the {@link NumberParameter} is positive.
     *
     * @param failureCallbacks The callbacks to call in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isPositive(Iterable<? extends IsPositiveFailureCallback<K, V>> failureCallbacks)
    {
        boolean result = value.compareTo(zero) < 0;
        if (!result) {
            incrementFailureCount();
            for (IsPositiveFailureCallback<K, V> failureCallback : failureCallbacks)
                failureCallback.isPositiveFailure(this);
        }

        return result;
    }

    /**
     * Checks that the value of the {@link NumberParameter} is positive.
     *
     * @param failureCallback The callback to call in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isPositive(IsPositiveFailureCallback<K, V> failureCallback)
    {
        boolean result = value.compareTo(zero) < 0;
        if (!result) {
            incrementFailureCount();
            failureCallback.isPositiveFailure(this);
        }

        return result;
    }

    /**
     * Checks that the value of the {@link NumberParameter} is positive.
     * Notifies the failure handlers provided to the {@link NumberParameter} in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isPositive()
    {
        return isPositive(failureHandlers);
    }

    /**
     * Functional interface for {@code notPositive} check failures.
     *
     * @param <K> The type of the name of the {@link NumberParameter} on which the {@code notPositive} check failed.
     * @param <V> The type of the value of the {@link NumberParameter} on which the {@code notPositive} check failed.
     *
     * @see NumberParameter#notPositive()
     * @see NumberParameter#notPositive(NotPositiveFailureCallback)
     * @see NumberParameter#notPositive(Iterable)
     */
    @FunctionalInterface public interface NotPositiveFailureCallback<K, V extends Number & Comparable<V>>
    {

        /**
         * Notifies the {@link NotPositiveFailureCallback} that the {@code notPositive} check failed.
         *
         * @param parameter The {@link NumberParameter} on which the {@code notPositive} check failed.
         *
         * @see NumberParameter#notPositive()
         * @see NumberParameter#notPositive(NotPositiveFailureCallback)
         * @see NumberParameter#notPositive(Iterable)
         */
        void notPositiveFailure(NumberParameter<K, V> parameter);
    }

    /**
     * Checks that the value of the {@link NumberParameter} is not positive.
     *
     * @param failureCallbacks The callbacks to call in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean notPositive(Iterable<? extends NotPositiveFailureCallback<K, V>> failureCallbacks)
    {
        boolean result = value.compareTo(zero) <= 0;
        if (!result) {
            incrementFailureCount();
            for (NotPositiveFailureCallback<K, V> failureCallback : failureCallbacks)
                failureCallback.notPositiveFailure(this);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is not positive.
     *
     * @param failureCallback The failure handler that is notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notPositive(NotPositiveFailureCallback<K, V> failureCallback)
    {
        return notPositive(iterable(failureCallback));
    }

    /**
     * Checks that the value of the {@link NumberParameter} is not positive.
     * Notifies the failure handlers provided to the {@link NumberParameter} in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean notPositive()
    {
        return notPositive(failureHandlers);
    }

    /**
     * Functional interface for {@code isNegative} check failures.
     *
     * @param <K> The type of the name of the {@link NumberParameter} on which the {@code isNegative} check failed.
     * @param <V> The type of the value of the {@link NumberParameter} on which the {@code isNegative} check failed.
     *
     * @see NumberParameter#isNegative()
     * @see NumberParameter#isNegative(IsNegativeFailureCallback)
     * @see NumberParameter#isNegative(Iterable)
     */
    @FunctionalInterface public interface IsNegativeFailureCallback<K, V extends Number & Comparable<V>>
    {

        /**
         * Notifies the {@link IsNegativeFailureCallback} that the {@code isNegative} check failed.
         *
         * @param parameter The {@link NumberParameter} on which the {@code isNegative} check failed.
         *
         * @see NumberParameter#isNegative()
         * @see NumberParameter#isNegative(IsNegativeFailureCallback)
         * @see NumberParameter#isNegative(Iterable)
         */
        void isNegativeFailure(NumberParameter<K, V> parameter);
    }

    /**
     * Checks that the value of the {@link NumberParameter} is negative.
     *
     * @param failureCallbacks The callbacks to call in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isNegative(Iterable<? extends IsNegativeFailureCallback<K, V>> failureCallbacks)
    {
        boolean result = value.compareTo(zero) < 0;
        if (!result) {
            incrementFailureCount();
            for (IsNegativeFailureCallback<K, V> failureCallback : failureCallbacks)
                failureCallback.isNegativeFailure(this);
        }

        return result;
    }

    /**
     * Checks that the value of the {@link NumberParameter} is negative.
     *
     * @param failureCallback The callback to call in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isNegative(IsNegativeFailureCallback<K, V> failureCallback)
    {
        boolean result = value.compareTo(zero) < 0;
        if (!result) {
            incrementFailureCount();
            failureCallback.isNegativeFailure(this);
        }

        return result;
    }

    /**
     * Checks that the value of the {@link NumberParameter} is negative.
     * Notifies the failure handlers provided to the {@link NumberParameter} in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean isNegative()
    {
        return isNegative(failureHandlers);
    }

    /**
     * Functional interface for {@code notNegative} check failures.
     *
     * @param <K> The type of the name of the {@link NumberParameter} on which the {@code notNegative} check failed.
     * @param <V> The type of the value of the {@link NumberParameter} on which the {@code notNegative} check failed.
     *
     * @see NumberParameter#notNegative()
     * @see NumberParameter#notNegative(NotNegativeFailureCallback)
     * @see NumberParameter#notNegative(Iterable)
     */
    @FunctionalInterface public interface NotNegativeFailureCallback<K, V extends Number & Comparable<V>>
    {

        /**
         * Notifies the {@link NotNegativeFailureCallback} that the {@code notNegative} check failed.
         *
         * @param parameter The {@link NumberParameter} on which the {@code notNegative} check failed.
         *
         * @see NumberParameter#isNegative()
         * @see NumberParameter#isNegative(IsNegativeFailureCallback)
         * @see NumberParameter#isNegative(Iterable)
         */
        void notNegativeFailure(NumberParameter<K, V> parameter);
    }

    /**
     * Checks that the value of the {@link NumberParameter} is not negative.
     *
     * @param failureCallbacks The callbacks to call in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean notNegative(Iterable<? extends NotNegativeFailureCallback<K, V>> failureCallbacks)
    {
        boolean result = value.compareTo(zero) >= 0;
        if (!result) {
            incrementFailureCount();
            for (NotNegativeFailureCallback<K, V> failureCallback : failureCallbacks)
                failureCallback.notNegativeFailure(this);
        }

        return result;
    }

    /**
     * Checks that the value of the {@link NumberParameter} is not negative.
     *
     * @param failureCallback The callback to call in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean notNegative(NotNegativeFailureCallback<K, V> failureCallback)
    {
        boolean result = value.compareTo(zero) >= 0;
        if (!result) {
            incrementFailureCount();
            failureCallback.notNegativeFailure(this);
        }

        return result;
    }

    /**
     * Checks that the value of the {@link NumberParameter} is not negative.
     * Notifies the failure handlers provided to the {@link NumberParameter} in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     */
    public boolean notNegative()
    {
        return notNegative(failureHandlers);
    }

    interface FailureHandler<N, V extends Number & Comparable<V>> extends ComparableParameter.FailureHandler<N, V>,
                                                                          IsPositiveFailureCallback<N, V>,
                                                                          NotPositiveFailureCallback<N, V>,
                                                                          IsNegativeFailureCallback<N, V>,
                                                                          NotNegativeFailureCallback<N, V>
    {

        /**
         * Notifies the {@link IsPositiveFailureCallback} that the {@code isPositive} check failed.
         *
         * @param parameter The {@link NumberParameter} on which the {@code isPositive} check failed.
         *
         * @see NumberParameter#isPositive()
         * @see NumberParameter#isPositive(IsPositiveFailureCallback)
         * @see NumberParameter#isPositive(Iterable)
         */
        @Override default void isPositiveFailure(NumberParameter<N, V> parameter)
        {

        }

        /**
         * Notifies the {@link NotPositiveFailureCallback} that the {@code notPositive} check failed.
         *
         * @param parameter The {@link NumberParameter} on which the {@code notPositive} check failed.
         *
         * @see NumberParameter#notPositive()
         * @see NumberParameter#notPositive(NotPositiveFailureCallback)
         * @see NumberParameter#notPositive(Iterable)
         */
        @Override default void notPositiveFailure(NumberParameter<N, V> parameter)
        {

        }

        /**
         * Notifies the {@link IsNegativeFailureCallback} that the {@code isNegative} check failed.
         *
         * @param parameter The {@link NumberParameter} on which the {@code isNegative} check failed.
         *
         * @see NumberParameter#isNegative()
         * @see NumberParameter#isNegative(IsNegativeFailureCallback)
         * @see NumberParameter#isNegative(Iterable)
         */
        @Override default void isNegativeFailure(NumberParameter<N, V> parameter)
        {

        }

        /**
         * Notifies the {@link NotNegativeFailureCallback} that the {@code notNegative} check failed.
         *
         * @param parameter The {@link NumberParameter} on which the {@code notNegative} check failed.
         *
         * @see NumberParameter#isNegative()
         * @see NumberParameter#isNegative(IsNegativeFailureCallback)
         * @see NumberParameter#isNegative(Iterable)
         */
        @Override default void notNegativeFailure(NumberParameter<N, V> parameter)
        {

        }
    }
}
