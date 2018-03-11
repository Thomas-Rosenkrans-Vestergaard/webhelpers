package tvestergaard.webhelpers.parameters;

import static tvestergaard.webhelpers.parameters.Parameters.iterable;

/**
 * An implementation of the {@link Parameter} interface for use on {@code Comparable} value types.
 *
 * @param <N> The type of the name of the {@link ComparableParameter}.
 * @param <V> The type of the value in the {@link ComparableParameter}.
 */
public abstract class ComparableParameter<N, V extends Comparable<V>> extends GenericParameter<N, V>
{

    /**
     * The failure handlers registered with the {@link ComparableParameter}.
     */
    private final Iterable<? extends FailureHandler<N, V>> failureHandlers;

    /**
     * Creates a new {@link ComparableParameter}.
     *
     * @param name            The name of the {@link ComparableParameter}.
     * @param value           The value of the {@link ComparableParameter}.
     * @param failureHandlers The {@link FailureHandler}s to register with the {@link ComparableParameter}.
     */
    public ComparableParameter(N name, V value, Iterable<? extends FailureHandler<N, V>> failureHandlers)
    {
        super(name, value, failureHandlers);

        this.failureHandlers = failureHandlers;
    }

    /**
     * The functional interface defining the routine notified when the {@code isGreaterThan} check fails.
     *
     * @see ComparableParameter#isGreaterThan(Comparable)
     * @see ComparableParameter#isGreaterThan(Comparable, IsGreaterThanFailureCallback)
     * @see ComparableParameter#isGreaterThan(Comparable, Iterable)
     */
    @FunctionalInterface public interface IsGreaterThanFailureCallback<K, V extends Comparable<V>>
    {

        /**
         * Notifies the {@link IsGreaterThanFailureCallback} when the {@code isGreaterThan} check fails.
         *
         * @param parameter The {@link ComparableParameter} instance on which the {@code isGreaterThan} check failed.
         * @param lower     The lower bound parameter provided to the {@code isGreaterThan} check that failed.
         *
         * @see ComparableParameter#isGreaterThan(Comparable)
         * @see ComparableParameter#isGreaterThan(Comparable, IsGreaterThanFailureCallback)
         * @see ComparableParameter#isGreaterThan(Comparable, Iterable)
         */
        void isGreaterThanFailure(ComparableParameter<K, V> parameter, V lower);
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is greater than the provided lower bound.
     *
     * @param lower            The lower bound that the internal value of the {@link ComparableParameter} must exceed.
     * @param failureCallbacks The failure handlers that are notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isGreaterThan(V lower, Iterable<? extends IsGreaterThanFailureCallback<N, V>> failureCallbacks)
    {
        boolean result = value.compareTo(lower) > 0;
        if (!result) {
            incrementFailureCount();
            for (IsGreaterThanFailureCallback<N, V> failureCallback : failureCallbacks)
                failureCallback.isGreaterThanFailure(this, lower);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is greater than the provided lower bound.
     *
     * @param lower           The lower bound that the internal value of the {@link ComparableParameter} must exceed.
     * @param failureCallback The failure handler that is notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isGreaterThan(V lower, IsGreaterThanFailureCallback<N, V> failureCallback)
    {
        return isGreaterThan(lower, iterable(failureCallback));
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is greater than the provided lower bound.
     * <p>
     * Notifies the {@link IsGreaterThanFailureCallback#isGreaterThanFailure(ComparableParameter, Comparable)} method on the
     * failure handlers provided to this instance of {@link ComparableParameter} if the check fails.
     *
     * @param lower The lower bound that the internal value of the {@link ComparableParameter} must exceed.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isGreaterThan(V lower)
    {
        return isGreaterThan(lower, failureHandlers);
    }

    /**
     * The functional interface defining the routine notified when the {@code notGreaterThan} check fails.
     *
     * @see ComparableParameter#notGreaterThan(Comparable)
     * @see ComparableParameter#notGreaterThan(Comparable, NotGreaterThanFailureCallback)
     * @see ComparableParameter#notGreaterThan(Comparable, Iterable)
     */
    @FunctionalInterface public interface NotGreaterThanFailureCallback<K, V extends Comparable<V>>
    {

        /**
         * Notifies the {@link IsGreaterThanFailureCallback} when the {@code notGreaterThan} check fails.
         *
         * @param parameter The {@link ComparableParameter} instance on which the {@code notGreaterThan} check failed.
         * @param upper     The upper bound parameter provided to the {@code notGreaterThan} check that failed.
         *
         * @see ComparableParameter#notGreaterThan(Comparable)
         * @see ComparableParameter#notGreaterThan(Comparable, NotGreaterThanFailureCallback)
         * @see ComparableParameter#notGreaterThan(Comparable, Iterable)
         */
        void notGreaterThanFailure(ComparableParameter<K, V> parameter, V upper);
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is not greater than the provided upper bound.
     *
     * @param upper            The upper bound which the internal value of the {@link ComparableParameter} must not exceed.
     * @param failureCallbacks The failure handlers that are notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notGreaterThan(V upper, Iterable<? extends NotGreaterThanFailureCallback<N, V>> failureCallbacks)
    {
        boolean result = value.compareTo(upper) <= 0;
        if (!result) {
            incrementFailureCount();
            for (NotGreaterThanFailureCallback<N, V> failureCallback : failureCallbacks)
                failureCallback.notGreaterThanFailure(this, upper);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is not greater than the provided upper bound.
     *
     * @param upper           The upper bound which the internal value of the {@link ComparableParameter} must not exceed.
     * @param failureCallback The failure handler that is notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notGreaterThan(V upper, NotGreaterThanFailureCallback<N, V> failureCallback)
    {
        return notGreaterThan(upper, iterable(failureCallback));
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is not greater than the provided upper bound.
     * <p>
     * Notifies the {@link NotGreaterThanFailureCallback#notGreaterThanFailure(ComparableParameter, Comparable)} method on the
     * failure handlers provided to this instance of {@link ComparableParameter} if the check fails.
     *
     * @param upper The upper bound which the internal value of the {@link ComparableParameter} must not exceed.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notGreaterThan(V upper)
    {
        return notGreaterThan(upper, failureHandlers);
    }

    /**
     * The functional interface defining the routine notified when the {@code isLessThan} check fails.
     *
     * @see ComparableParameter#isLessThan(Comparable)
     * @see ComparableParameter#isLessThan(Comparable, IsLessThanFailureCallback)
     * @see ComparableParameter#isLessThan(Comparable, Iterable)
     */
    @FunctionalInterface public interface IsLessThanFailureCallback<K, V extends Comparable<V>>
    {

        /**
         * Notifies the {@link IsLessThanFailureCallback} when the {@code isLessThan} check fails.
         *
         * @param parameter The {@link ComparableParameter} instance on which the {@code isLessThan} check failed.
         * @param upper     The upper bound parameter provided to the {@code isLessThan} check that failed.
         *
         * @see ComparableParameter#isLessThan(Comparable)
         * @see ComparableParameter#isLessThan(Comparable, IsLessThanFailureCallback)
         * @see ComparableParameter#isLessThan(Comparable, Iterable)
         */
        void isLessThanFailure(ComparableParameter<K, V> parameter, V upper);
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is less than the provided upper bound.
     *
     * @param upper            The upper bound which the internal value of the {@link ComparableParameter} must not exceed.
     * @param failureCallbacks The failure handlers that are notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isLessThan(V upper, Iterable<? extends IsLessThanFailureCallback<N, V>> failureCallbacks)
    {
        boolean result = value.compareTo(upper) < 0;
        if (!result) {
            incrementFailureCount();
            for (IsLessThanFailureCallback<N, V> failureCallback : failureCallbacks)
                failureCallback.isLessThanFailure(this, upper);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is less than the provided upper bound.
     *
     * @param upper           The upper bound which the internal value of the {@link ComparableParameter} must not exceed.
     * @param failureCallback The failure handler that is notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isLessThan(V upper, IsLessThanFailureCallback<N, V> failureCallback)
    {
        return isLessThan(upper, iterable(failureCallback));
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is less than the provided upper bound.
     * <p>
     * Notifies the {@link IsLessThanFailureCallback#isLessThanFailure(ComparableParameter, Comparable)} method on the error
     * handlers provided to this instance of {@link ComparableParameter} if the check fails.
     *
     * @param upper The upper bound which the internal value of the {@link ComparableParameter} must not exceed.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isLessThan(V upper)
    {
        return isLessThan(upper, failureHandlers);
    }

    /**
     * The functional interface defining the routine notified when the {@code notLessThan} check fails.
     *
     * @see ComparableParameter#notLessThan(Comparable)
     * @see ComparableParameter#notLessThan(Comparable, NotLessThanFailureCallback)
     * @see ComparableParameter#notLessThan(Comparable, Iterable)
     */
    @FunctionalInterface public interface NotLessThanFailureCallback<K, V extends Comparable<V>>
    {

        /**
         * Notifies the {@link NotLessThanFailureCallback} when the {@code notLessThan} check fails.
         *
         * @param parameter The {@link ComparableParameter} instance on which the {@code notLessThan} check failed.
         * @param lower     The lower bound parameter provided to the {@code notLessThan} check that failed.
         *
         * @see ComparableParameter#notLessThan(Comparable)
         * @see ComparableParameter#notLessThan(Comparable, NotLessThanFailureCallback)
         * @see ComparableParameter#notLessThan(Comparable, Iterable)
         */
        void notLessThanFailure(ComparableParameter<K, V> parameter, V lower);
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is not less than the provided lower bound.
     *
     * @param lower            The lower bound which the internal value of the {@link ComparableParameter} must not exceed not equal.
     * @param failureCallbacks The failure handlers that are notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notLessThan(V lower, Iterable<? extends NotLessThanFailureCallback<N, V>> failureCallbacks)
    {
        boolean result = value.compareTo(lower) >= 0;
        if (!result) {
            incrementFailureCount();
            for (NotLessThanFailureCallback<N, V> failureCallback : failureCallbacks)
                failureCallback.notLessThanFailure(this, lower);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is not less than the provided lower bound.
     *
     * @param lower           The lower bound which the internal value of the {@link ComparableParameter} must not exceed not equal.
     * @param failureCallback The failure handler that is notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notLessThan(V lower, NotLessThanFailureCallback<N, V> failureCallback)
    {
        return notLessThan(lower, iterable(failureCallback));
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is not less than the provided lower bound.
     * <p>
     * Notifies the {@link NotLessThanFailureCallback#notLessThanFailure(ComparableParameter, Comparable)} method on the error
     * handlers provided to this instance of {@link ComparableParameter} if the check fails.
     *
     * @param lower The lower bound which the internal value of the {@link ComparableParameter} must not exceed not equal.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notLessThan(V lower)
    {
        return notLessThan(lower, failureHandlers);
    }

    /**
     * The functional interface defining the routine notified when the {@code isBetween} check fails.
     *
     * @see ComparableParameter#isBetween(Comparable, Comparable)
     * @see ComparableParameter#isBetween(Comparable, Comparable, Iterable)
     * @see ComparableParameter#isBetween(Comparable, Comparable, IsBetweenFailureCallback)
     * @see ComparableParameter#isBetween(Comparable, Comparable, boolean)
     * @see ComparableParameter#isBetween(Comparable, Comparable, boolean, Iterable)
     * @see ComparableParameter#isBetween(Comparable, Comparable, boolean, IsBetweenFailureCallback)
     */
    @FunctionalInterface public interface IsBetweenFailureCallback<K, V extends Comparable<V>>
    {

        /**
         * Notifies the {@link IsBetweenFailureCallback} when the {@code isBetween} check fails.
         *
         * @param parameter The {@link ComparableParameter} instance on which the {@code isBetween} check failed.
         * @param lower     The lower bound parameter provided to the {@code isBetween} check that failed.
         * @param upper     The upper bound provided to the {@code isBetween} check that failed.
         * @param inclusive The inclusive setting provided to the {@code isBetween} check that failed.
         *
         * @see ComparableParameter#isBetween(Comparable, Comparable)
         * @see ComparableParameter#isBetween(Comparable, Comparable, Iterable)
         * @see ComparableParameter#isBetween(Comparable, Comparable, IsBetweenFailureCallback)
         * @see ComparableParameter#isBetween(Comparable, Comparable, boolean)
         * @see ComparableParameter#isBetween(Comparable, Comparable, boolean, Iterable)
         * @see ComparableParameter#isBetween(Comparable, Comparable, boolean, IsBetweenFailureCallback)
         */
        void isBetweenFailure(ComparableParameter<K, V> parameter, V lower, V upper, boolean inclusive);
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is between the provided lower and upper bound. Whether
     * or not the bounds are included or excluded in the interval, can be controlled using the {@code inclusive}
     * parameter. When {@code inclusive} is {@code true} the internal value of the {@link ComparableParameter} must conform
     * to {@code value >= lower && value <= upper} for the check to pass. When {@code inclusive} is {@code false} the
     * internal value of the {@link ComparableParameter} must conform to {@code value > lower && value < upper} for the check
     * to pass.
     *
     * @param lower            The lower bound which the internal value of the {@link ComparableParameter} must exceed, and may
     *                         equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param upper            The upper bound which the internal value of the {@link ComparableParameter} must not exceed, and may
     *                         equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param inclusive        Whether or not the provided bounds should be included or excluded by the check.
     * @param failureCallbacks The failure handlers that are notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isBetween(V lower, V upper, boolean inclusive, Iterable<? extends IsBetweenFailureCallback<N, V>> failureCallbacks)
    {
        boolean result;

        if (inclusive)
            result = value.compareTo(lower) >= 0 && value.compareTo(upper) <= 0;
        else
            result = value.compareTo(lower) > 0 && value.compareTo(upper) < 0;

        if (!result) {
            incrementFailureCount();
            for (IsBetweenFailureCallback<N, V> failureCallback : failureCallbacks)
                failureCallback.isBetweenFailure(this, lower, upper, inclusive);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is between the provided lower and upper bound. Whether
     * or not the bounds are included or excluded in the interval, can be controlled using the {@code inclusive}
     * parameter. When {@code inclusive} is {@code true} the internal value of the {@link ComparableParameter} must conform
     * to {@code value >= lower && value <= upper} for the check to pass. When {@code inclusive} is {@code false} the
     * internal value of the {@link ComparableParameter} must conform to {@code value > lower && value < upper} for the check
     * to pass.
     *
     * @param lower           The lower bound which the internal value of the {@link ComparableParameter} must exceed, and may
     *                        equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param upper           The upper bound which the internal value of the {@link ComparableParameter} must not exceed, and may
     *                        equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param inclusive       Whether or not the provided bounds should be included or excluded by the check.
     * @param failureCallback The failure handler that is notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isBetween(V lower, V upper, boolean inclusive, IsBetweenFailureCallback<N, V> failureCallback)
    {
        return isBetween(lower, upper, inclusive, iterable(failureCallback));
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is between the provided lower and upper bound. The
     * internal value of the {@link ComparableParameter} must conform to {@code value >= lower && value <= upper} for the check
     * to pass. If the provided bounds should not be valid, use the
     * {@link ComparableParameter#isBetween(Comparable, Comparable, boolean)} method, where the inclusivity setting can be provided
     * as a parameter.
     *
     * @param lower            The lower bound which the internal value of the {@link ComparableParameter} must exceed or equal
     *                         for the check to pass.
     * @param upper            The upper bound which the internal value of the {@link ComparableParameter} must not exceed or equal
     *                         for the check to pass.
     * @param failureCallbacks The failure handlers that are notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see ComparableParameter#isBetween(Comparable, Comparable, boolean, Iterable)
     */
    public boolean isBetween(V lower, V upper, Iterable<? extends IsBetweenFailureCallback<N, V>> failureCallbacks)
    {
        return isBetween(lower, upper, true, failureCallbacks);
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is between the provided lower and upper bound. The
     * internal value of the {@link ComparableParameter} must conform to {@code value >= lower && value <= upper} for the check
     * to pass. If the provided bounds should not be valid, use the
     * {@link ComparableParameter#isBetween(Comparable, Comparable, boolean)} method, where the inclusivity setting can be provided
     * as a parameter.
     *
     * @param lower           The lower bound which the internal value of the {@link ComparableParameter} must exceed or equal
     *                        for the check to pass.
     * @param upper           The upper bound which the internal value of the {@link ComparableParameter} must not exceed or equal
     *                        for the check to pass.
     * @param failureCallback The failure handler that is notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see ComparableParameter#isBetween(Comparable, Comparable, boolean, Iterable)
     */
    public boolean isBetween(V lower, V upper, IsBetweenFailureCallback<N, V> failureCallback)
    {
        return isBetween(lower, upper, true, iterable(failureCallback));
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is between the provided lower and upper bound. Whether
     * or not the bounds are included or excluded in the interval, can be controlled using the {@code inclusive}
     * parameter. When {@code inclusive} is {@code true} the internal value of the {@link ComparableParameter} must conform
     * to {@code value >= lower && value <= upper} for the check to pass. When {@code inclusive} is {@code false} the
     * internal value of the {@link ComparableParameter} must conform to {@code value > lower && value < upper} for the check
     * to pass.
     * <p>
     * Notifies the {@link IsBetweenFailureCallback#isBetweenFailure(ComparableParameter, Comparable, Comparable, boolean)} method
     * on the failure handlers provided to this instance of {@link ComparableParameter} if the check fails.
     *
     * @param lower     The lower bound which the internal value of the {@link ComparableParameter} must exceed or equal
     *                  for the check to pass.
     * @param upper     The upper bound which the internal value of the {@link ComparableParameter} must not exceed or equal
     *                  for the check to pass.
     * @param inclusive Whether or not the provided bounds should be included or excluded by the check.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see ComparableParameter#isBetween(Comparable, Comparable, boolean, Iterable)
     */
    public boolean isBetween(V lower, V upper, boolean inclusive)
    {
        return isBetween(lower, upper, inclusive, failureHandlers);
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is between the provided lower and upper bound. The
     * internal value of the {@link ComparableParameter} must conform to {@code value >= lower && value <= upper} for the check
     * to pass. If the provided bounds should not be valid, use the
     * {@link ComparableParameter#isBetween(Comparable, Comparable, boolean, Iterable)} method, where the inclusivity setting can be
     * provided as a parameter.
     * <p>
     * Notifies the {@link IsBetweenFailureCallback#isBetweenFailure(ComparableParameter, Comparable, Comparable, boolean)} method
     * on the failure handlers provided to this instance of {@link ComparableParameter} if the check fails.
     *
     * @param lower The lower bound which the internal value of the {@link ComparableParameter} must exceed or equal
     *              for the check to pass.
     * @param upper The upper bound which the internal value of the {@link ComparableParameter} must not exceed or equal
     *              for the check to pass.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see ComparableParameter#isBetween(Comparable, Comparable, Iterable)
     */
    public boolean isBetween(V lower, V upper)
    {
        return isBetween(lower, upper, true);
    }

    /**
     * The functional interface defining the routine notified when the {@code notBetween} check fails.
     *
     * @see ComparableParameter#notBetween(Comparable, Comparable)
     * @see ComparableParameter#notBetween(Comparable, Comparable, Iterable)
     * @see ComparableParameter#notBetween(Comparable, Comparable, NotBetweenFailureCallback)
     * @see ComparableParameter#notBetween(Comparable, Comparable, boolean)
     * @see ComparableParameter#notBetween(Comparable, Comparable, boolean, Iterable)
     * @see ComparableParameter#notBetween(Comparable, Comparable, boolean, NotBetweenFailureCallback)
     */
    @FunctionalInterface public interface NotBetweenFailureCallback<K, V extends Comparable<V>>
    {

        /**
         * Notifies the {@link NotBetweenFailureCallback} when the {@code notBetween} check fails.
         *
         * @param parameter The {@link ComparableParameter} instance on which the {@code notBetween} check failed.
         * @param lower     The lower bound parameter provided to the {@code notBetween} check that failed.
         * @param upper     The upper bound parameter provided to the {@code notBetween} check that failed.
         * @param inclusive The inclusive parameter provided to the {@code notBetween} check that failed.
         *
         * @see ComparableParameter#notBetween(Comparable, Comparable)
         * @see ComparableParameter#notBetween(Comparable, Comparable, Iterable)
         * @see ComparableParameter#notBetween(Comparable, Comparable, NotBetweenFailureCallback)
         * @see ComparableParameter#notBetween(Comparable, Comparable, boolean)
         * @see ComparableParameter#notBetween(Comparable, Comparable, boolean, Iterable)
         * @see ComparableParameter#notBetween(Comparable, Comparable, boolean, NotBetweenFailureCallback)
         */
        void notBetweenFailure(ComparableParameter<K, V> parameter, V lower, V upper, boolean inclusive);
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is not between the provided lower and upper bound. Whether
     * or not the bounds are included or excluded in the interval, can be controlled using the {@code inclusive}
     * parameter. When {@code inclusive} is {@code true} the internal value of the {@link ComparableParameter} must conform
     * to {@code value <= lower || value >= upper} for the check to pass. When {@code inclusive} is {@code false} the
     * internal value of the {@link ComparableParameter} must conform to {@code value < lower || value > upper} for the check
     * to pass.
     *
     * @param lower            The lower bound which the internal value of the {@link ComparableParameter} must not exceed, and may
     *                         not equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param upper            The upper bound which the internal value of the {@link ComparableParameter} must exceed, and may
     *                         not equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param inclusive        Whether or not the provided bounds should be included or excluded by the check.
     * @param failureCallbacks The failure handlers that are notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notBetween(V lower, V upper, boolean inclusive, Iterable<? extends NotBetweenFailureCallback<N, V>> failureCallbacks)
    {
        boolean result;

        if (inclusive)
            result = value.compareTo(lower) <= 0 || value.compareTo(upper) >= 0;
        else
            result = value.compareTo(lower) < 0 || value.compareTo(upper) > 0;

        if (!result) {
            incrementFailureCount();
            for (NotBetweenFailureCallback<N, V> failureCallback : failureCallbacks)
                failureCallback.notBetweenFailure(this, lower, upper, inclusive);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is not between the provided lower and upper bound. Whether
     * or not the bounds are included or excluded in the interval, can be controlled using the {@code inclusive}
     * parameter. When {@code inclusive} is {@code true} the internal value of the {@link ComparableParameter} must conform
     * to {@code value <= lower || value >= upper} for the check to pass. When {@code inclusive} is {@code false} the
     * internal value of the {@link ComparableParameter} must conform to {@code value < lower || value > upper} for the check
     * to pass.
     *
     * @param lower           The lower bound which the internal value of the {@link ComparableParameter} must not exceed, and may
     *                        not equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param upper           The upper bound which the internal value of the {@link ComparableParameter} must exceed, and may
     *                        not equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param inclusive       Whether or not the provided bounds should be included or excluded by the check.
     * @param failureCallback The failure handler that is notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notBetween(V lower, V upper, boolean inclusive, NotBetweenFailureCallback<N, V> failureCallback)
    {
        return notBetween(lower, upper, inclusive, iterable(failureCallback));
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is not between the provided lower and upper bound. The
     * internal value of the {@link ComparableParameter} must conform to {@code value <= lower || value >= upper} for the check
     * to pass. If the provided bounds should not be valid, use the
     * {@link ComparableParameter#notBetween(Comparable, Comparable, boolean, Iterable)} method, where the inclusivity setting can
     * be provided as a parameter.
     *
     * @param lower            The lower bound which the internal value of the {@link ComparableParameter} must not exceed nor equal
     *                         for the check to pass.
     * @param upper            The upper bound which the internal value of the {@link ComparableParameter} must exceed nor equal
     *                         for the check to pass.
     * @param failureCallbacks The failure handlers that are notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see ComparableParameter#isBetween(Comparable, Comparable, Iterable)
     */
    public boolean notBetween(V lower, V upper, Iterable<? extends NotBetweenFailureCallback<N, V>> failureCallbacks)
    {
        return notBetween(lower, upper, true, failureCallbacks);
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is not between the provided lower and upper bound. The
     * internal value of the {@link ComparableParameter} must conform to {@code value <= lower || value >= upper} for the check
     * to pass. If the provided bounds should not be valid, use the
     * {@link ComparableParameter#notBetween(Comparable, Comparable, boolean, Iterable)} method, where the inclusivity setting can
     * be provided as a parameter.
     *
     * @param lower           The lower bound which the internal value of the {@link ComparableParameter} must not exceed nor equal
     *                        for the check to pass.
     * @param upper           The upper bound which the internal value of the {@link ComparableParameter} must exceed nor equal
     *                        for the check to pass.
     * @param failureCallback The failure handler that is notified in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see ComparableParameter#isBetween(Comparable, Comparable, Iterable)
     */
    public boolean notBetween(V lower, V upper, NotBetweenFailureCallback<N, V> failureCallback)
    {
        return notBetween(lower, upper, iterable(failureCallback));
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is not between the provided lower and upper bound. Whether
     * or not the bounds are included or excluded in the interval, can be controlled using the {@code inclusive}
     * parameter. When {@code inclusive} is {@code true} the internal value of the {@link ComparableParameter} must conform
     * to {@code value <= lower || value >= upper} for the check to pass. When {@code inclusive} is {@code false} the
     * internal value of the {@link ComparableParameter} must conform to {@code value < lower || value > upper} for the check
     * to pass.
     * <p>
     * Notifies the {@link NotBetweenFailureCallback#notBetweenFailure(ComparableParameter, Comparable, Comparable, boolean)} method
     * on the failure handlers provided to this instance of {@link ComparableParameter} if the check fails.
     *
     * @param lower     The lower bound which the internal value of the {@link ComparableParameter} must not exceed, and may
     *                  not equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param upper     The upper bound which the internal value of the {@link ComparableParameter} must exceed, and may
     *                  not equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param inclusive Whether or not the provided bounds should be included or excluded by the check.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notBetween(V lower, V upper, boolean inclusive)
    {
        return notBetween(lower, upper, inclusive, failureHandlers);
    }

    /**
     * Checks that the internal value of the {@link ComparableParameter} is not between the provided lower and upper bound. The
     * internal value of the {@link ComparableParameter} must conform to {@code value <= lower || value >= upper} for the check
     * to pass. If the provided bounds should not be valid, use the
     * {@link ComparableParameter#notBetween(Comparable, Comparable, Iterable)} method, where the inclusivity setting can be provided
     * as a parameter.
     * <p>
     * Notifies the {@link NotBetweenFailureCallback#notBetweenFailure(ComparableParameter, Comparable, Comparable, boolean)} method
     * on the failure handlers provided to this instance of {@link ComparableParameter} if the check fails.
     *
     * @param lower The lower bound which the internal value of the {@link ComparableParameter} must not exceed nor equal
     *              for the check to pass.
     * @param upper The upper bound which the internal value of the {@link ComparableParameter} must exceed nor equal
     *              for the check to pass.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see ComparableParameter#isBetween(Comparable, Comparable, Iterable)
     */
    public boolean notBetween(V lower, V upper)
    {
        return notBetween(lower, upper, true);
    }

    public interface FailureHandler<N, V extends Comparable<V>>
            extends GenericParameter.FailureHandler<N, V>,
                    IsGreaterThanFailureCallback<N, V>,
                    NotGreaterThanFailureCallback<N, V>,
                    IsLessThanFailureCallback<N, V>,
                    NotLessThanFailureCallback<N, V>,
                    IsBetweenFailureCallback<N, V>,
                    NotBetweenFailureCallback<N, V>
    {

        /**
         * Notifies the {@link IsGreaterThanFailureCallback} when the {@code isGreaterThan} check fails.
         *
         * @param parameter The {@link ComparableParameter} instance on which the {@code isGreaterThan} check failed.
         * @param lower     The lower bound parameter provided to the {@code isGreaterThan} check that failed.
         *
         * @see ComparableParameter#isGreaterThan(Comparable)
         * @see ComparableParameter#isGreaterThan(Comparable, IsGreaterThanFailureCallback)
         * @see ComparableParameter#isGreaterThan(Comparable, Iterable)
         */
        @Override default void isGreaterThanFailure(ComparableParameter<N, V> parameter, V lower)
        {

        }

        /**
         * Notifies the {@link IsGreaterThanFailureCallback} when the {@code notGreaterThan} check fails.
         *
         * @param parameter The {@link ComparableParameter} instance on which the {@code notGreaterThan} check failed.
         * @param upper     The upper bound parameter provided to the {@code notGreaterThan} check that failed.
         *
         * @see ComparableParameter#notGreaterThan(Comparable)
         * @see ComparableParameter#notGreaterThan(Comparable, NotGreaterThanFailureCallback)
         * @see ComparableParameter#notGreaterThan(Comparable, Iterable)
         */
        @Override default void notGreaterThanFailure(ComparableParameter<N, V> parameter, V upper)
        {

        }

        /**
         * Notifies the {@link IsLessThanFailureCallback} when the {@code isLessThan} check fails.
         *
         * @param parameter The {@link ComparableParameter} instance on which the {@code isLessThan} check failed.
         * @param upper     The upper bound parameter provided to the {@code isLessThan} check that failed.
         *
         * @see ComparableParameter#isLessThan(Comparable)
         * @see ComparableParameter#isLessThan(Comparable, IsLessThanFailureCallback)
         * @see ComparableParameter#isLessThan(Comparable, Iterable)
         */
        @Override default void isLessThanFailure(ComparableParameter<N, V> parameter, V upper)
        {

        }

        /**
         * Notifies the {@link NotLessThanFailureCallback} when the {@code notLessThan} check fails.
         *
         * @param parameter The {@link ComparableParameter} instance on which the {@code notLessThan} check failed.
         * @param lower     The lower bound parameter provided to the {@code notLessThan} check that failed.
         *
         * @see ComparableParameter#notLessThan(Comparable)
         * @see ComparableParameter#notLessThan(Comparable, NotLessThanFailureCallback)
         * @see ComparableParameter#notLessThan(Comparable, Iterable)
         */
        @Override default void notLessThanFailure(ComparableParameter<N, V> parameter, V lower)
        {

        }

        /**
         * Notifies the {@link IsBetweenFailureCallback} when the {@code isBetween} check fails.
         *
         * @param parameter The {@link ComparableParameter} instance on which the {@code isBetween} check failed.
         * @param lower     The lower bound parameter provided to the {@code isBetween} check that failed.
         * @param upper     The upper bound provided to the {@code isBetween} check that failed.
         * @param inclusive The inclusive setting provided to the {@code isBetween} check that failed.
         *
         * @see ComparableParameter#isBetween(Comparable, Comparable)
         * @see ComparableParameter#isBetween(Comparable, Comparable, Iterable)
         * @see ComparableParameter#isBetween(Comparable, Comparable, IsBetweenFailureCallback)
         * @see ComparableParameter#isBetween(Comparable, Comparable, boolean)
         * @see ComparableParameter#isBetween(Comparable, Comparable, boolean, Iterable)
         * @see ComparableParameter#isBetween(Comparable, Comparable, boolean, IsBetweenFailureCallback)
         */
        @Override default void isBetweenFailure(ComparableParameter<N, V> parameter, V lower, V upper, boolean inclusive)
        {

        }

        /**
         * Notifies the {@link NotBetweenFailureCallback} when the {@code notBetween} check fails.
         *
         * @param parameter The {@link ComparableParameter} instance on which the {@code notBetween} check failed.
         * @param lower     The lower bound parameter provided to the {@code notBetween} check that failed.
         * @param upper     The upper bound parameter provided to the {@code notBetween} check that failed.
         * @param inclusive The inclusive parameter provided to the {@code notBetween} check that failed.
         *
         * @see ComparableParameter#notBetween(Comparable, Comparable)
         * @see ComparableParameter#notBetween(Comparable, Comparable, Iterable)
         * @see ComparableParameter#notBetween(Comparable, Comparable, NotBetweenFailureCallback)
         * @see ComparableParameter#notBetween(Comparable, Comparable, boolean)
         * @see ComparableParameter#notBetween(Comparable, Comparable, boolean, Iterable)
         * @see ComparableParameter#notBetween(Comparable, Comparable, boolean, NotBetweenFailureCallback)
         */
        @Override default void notBetweenFailure(ComparableParameter<N, V> parameter, V lower, V upper, boolean inclusive)
        {

        }
    }
}
