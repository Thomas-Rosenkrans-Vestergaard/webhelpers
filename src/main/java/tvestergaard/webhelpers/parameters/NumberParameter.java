package tvestergaard.webhelpers.parameters;

import java.util.List;

public class NumberParameter<T extends Number & Comparable> extends AbstractParameter
{
    /**
     * The internal value of the {@link NumberParameter}.
     */
    private final T value;

    /**
     * A list containing the registered instances of {@link NumberParameterFailureHandler}. These failure handlers are
     * notified when checks performed on this instance of {@link NumberParameter} fails.
     */
    private final List<NumberParameterFailureHandler> errorHandlers;

    /**
     * An array containing the registered instances of {@link NumberParameterFailureHandler}. These failure handlers are
     * notified when checks performed on this instance of {@link NumberParameter} fails.
     */
    private final NumberParameterFailureHandler[] errorHandlersArray;

    /**
     * The number of failures that have occurred during checks.
     */
    private int failureCount = 0;

    /**
     * Creates a new {@link NumberParameter}.
     *
     * @param name            The name of the {@link NumberParameter}.
     * @param value           The value of the {@link NumberParameter}.
     * @param failureHandlers The failure handlers that are notified when checks performed on this instance of
     *                        {@link NumberParameter} fails.
     */
    public NumberParameter(String name, T value, List<NumberParameterFailureHandler> failureHandlers)
    {
        super(name);
        this.value = value;
        this.errorHandlers = failureHandlers;
        this.errorHandlersArray = failureHandlers.toArray(new NumberParameterFailureHandler[failureHandlers.size()]);
    }

    /**
     * The functional interface defining the routine notified when the {@code is()} check fails.
     *
     * @see NumberParameter#is(Number)
     * @see NumberParameter#is(Number, IsFailureCallback...)
     */
    @FunctionalInterface public interface IsFailureCallback<T extends Number & Comparable>
    {

        /**
         * Notifies the {@link IsFailureCallback} when the {@code is} check fails.
         *
         * @param parameter The {@link NumberParameter} instance on which the {@code is} check failed.
         * @param other     The {@code int} parameter passed to the {@code is} check that failed.
         */
        void isFailure(NumberParameter<T> parameter, T other);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} equals the provided {@code other}.
     *
     * @param other          The value the internal value of the {@link NumberParameter} must equal for the check to pass.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean is(T other, IsFailureCallback<T>... errorCallbacks)
    {
        boolean result = value.compareTo(other) == 0;
        if (!result) {
            failureCount++;
            for (IsFailureCallback<T> errorCallback : errorCallbacks)
                errorCallback.isFailure(this, other);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} equals the provided {@code other}.
     * <p>
     * Notifies the {@link IsFailureCallback#isFailure(NumberParameter, Number)} method on the error
     * handlers provided to this instance of {@link NumberParameter} if the check fails.
     *
     * @param other The value the internal value of the {@link NumberParameter} must equal for the check to pass.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean is(T other)
    {
        return is(other, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link {@code is()} check fails.
     *
     * @see NumberParameter#not(int)
     * @see NumberParameter#not(int, IsFailureCallback...)
     */
    @FunctionalInterface public interface NotFailureCallback<T extends Number & Comparable>
    {

        /**
         * Notifies the {@link NotFailureCallback} when the {@code not} check fails.
         *
         * @param parameter The {@link NumberParameter} instance on which the {@code not} check failed.
         * @param other     The {@code int} parameter passed to the {@code not} check that failed.
         */
        void notFailure(NumberParameter<T> parameter, T other);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} not not equal the provided {@code other}.
     *
     * @param other          The value the internal value of the {@link NumberParameter} must not equal for the check to pass.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean not(T other, NotFailureCallback<T>... errorCallbacks)
    {
        boolean result = value.compareTo(other) != 0;
        if (!result) {
            failureCount++;
            for (NotFailureCallback<T> errorCallback : errorCallbacks)
                errorCallback.notFailure(this, other);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} does not equal the provided {@code other}.
     * <p>
     * Notifies the {@link IsFailureCallback#isFailure(NumberParameter, Number)} method on the error
     * handlers provided to this instance of {@link NumberParameter} if the check fails.
     *
     * @param other The value the internal value of the {@link NumberParameter} must not equal for the check to pass.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean not(T other)
    {
        return not(other, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@code isPositive} check fails.
     *
     * @see NumberParameter#isPositive()
     * @see NumberParameter#isPositive(IsPositiveFailureCallback...)
     */
    @FunctionalInterface public interface IsPositiveFailureCallback<T extends Number & Comparable>
    {

        /**
         * Notifies the {@link IsPositiveFailureCallback} when the {@code isPositive} check fails.
         *
         * @param parameter The {@link NumberParameter} instance on which the {@code isPositive} check failed.
         */
        void isPositiveFailure(NumberParameter<T> parameter);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is positive.
     *
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isPositive(IsPositiveFailureCallback<T>... errorCallbacks)
    {
        boolean result = value.compareTo(0) < 0;
        if (!result) {
            failureCount++;
            for (IsPositiveFailureCallback<T> errorCallback : errorCallbacks)
                errorCallback.isPositiveFailure(this);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is positive.
     * <p>
     * Notifies the {@link IsPositiveFailureCallback#isPositiveFailure(NumberParameter)} method on the error handlers provided to
     * this instance of {@link NumberParameter} if the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isPositive()
    {
        return isPositive(errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@code notPositive} check fails.
     *
     * @see NumberParameter#notPositive()
     * @see NumberParameter#notPositive(NotPositiveFailureCallback...)
     */
    @FunctionalInterface public interface NotPositiveFailureCallback<T extends Number & Comparable>
    {

        /**
         * Notifies the {@link IsPositiveFailureCallback} when the {@code notPositive} check fails.
         *
         * @param parameter The {@link NumberParameter} instance on which the {@code notPositive} check failed.
         */
        void notPositiveFailure(NumberParameter<T> parameter);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is not positive.
     *
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notPositive(NotPositiveFailureCallback<T>... errorCallbacks)
    {
        boolean result = value.compareTo(0) <= 0;
        if (!result) {
            failureCount++;
            for (NotPositiveFailureCallback<T> errorCallback : errorCallbacks)
                errorCallback.notPositiveFailure(this);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is not positive.
     * <p>
     * Notifies the {@link NotPositiveFailureCallback#notPositiveFailure(NumberParameter)} method on the error handlers provided to
     * this instance of {@link NumberParameter} if the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notPositive()
    {
        return notPositive(errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@code isNegative} check fails.
     *
     * @see NumberParameter#isNegative()
     * @see NumberParameter#isNegative(IsNegativeFailureCallback...)
     */
    @FunctionalInterface public interface IsNegativeFailureCallback<T extends Number & Comparable>
    {

        /**
         * Notifies the {@link IsPositiveFailureCallback} when the {@code isNegative} check fails.
         *
         * @param parameter The {@link NumberParameter} instance on which the {@code isNegative} check failed.
         */
        void isNegativeFailure(NumberParameter<T> parameter);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is negative.
     *
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isNegative(IsNegativeFailureCallback<T>... errorCallbacks)
    {
        boolean result = value.compareTo(0) < 0;
        if (!result) {
            failureCount++;
            for (IsNegativeFailureCallback<T> errorCallback : errorCallbacks)
                errorCallback.isNegativeFailure(this);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is negative.
     * <p>
     * Notifies the {@link IsNegativeFailureCallback#isNegativeFailure(NumberParameter)} method on the error handlers provided to
     * this instance of {@link NumberParameter} if the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isNegative()
    {
        return isNegative(errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@code notNegative} check fails.
     */
    @FunctionalInterface public interface NotNegativeFailureCallback<T extends Number & Comparable>
    {

        /**
         * Notifies the {@link NotNegativeFailureCallback} when the {@code notNegative} check fails.
         *
         * @param parameter The {@link NumberParameter} instance on which the {@code notNegative} check failed.
         * @see NumberParameter#notNegative()
         * @see NumberParameter#notNegative(NotNegativeFailureCallback...)
         */
        void notNegativeFailure(NumberParameter<T> parameter);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is not negative.
     *
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notNegative(NotNegativeFailureCallback<T>... errorCallbacks)
    {
        boolean result = value.compareTo(0) >= 0;
        if (!result) {
            failureCount++;
            for (NotNegativeFailureCallback<T> errorCallback : errorCallbacks)
                errorCallback.notNegativeFailure(this);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is not negative.
     * <p>
     * Notifies the {@link NotNegativeFailureCallback#notNegativeFailure(NumberParameter)} method on the error handlers provided to
     * this instance of {@link NumberParameter} if the check fails.
     *
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notNegative()
    {
        return notNegative(errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@code isGreaterThan} check fails.
     *
     * @see NumberParameter#isGreaterThan(Number)
     * @see NumberParameter#isGreaterThan(Number, IsGreaterThanFailureCallback[])
     */
    @FunctionalInterface public interface IsGreaterThanFailureCallback<T extends Number & Comparable>
    {

        /**
         * Notifies the {@link IsGreaterThanFailureCallback} when the {@code isGreaterThan} check fails.
         *
         * @param parameter The {@link NumberParameter} instance on which the {@code isGreaterThan} check failed.
         * @param lower     The lower bound parameter provided to the {@code isGreaterThan} check that failed.
         */
        void isGreaterThanFailure(NumberParameter<T> parameter, T lower);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is greater than the provided lower bound.
     *
     * @param lower          The lower bound that the internal value of the {@link NumberParameter} must exceed.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isGreaterThan(T lower, IsGreaterThanFailureCallback<T>... errorCallbacks)
    {
        boolean result = value.compareTo(lower) > 0;
        if (!result) {
            failureCount++;
            for (IsGreaterThanFailureCallback<T> errorCallback : errorCallbacks)
                errorCallback.isGreaterThanFailure(this, lower);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is greater than the provided lower bound.
     * <p>
     * Notifies the {@link IsGreaterThanFailureCallback#isGreaterThanFailure(NumberParameter, Number)} method on the
     * error handlers provided to this instance of {@link NumberParameter} if the check fails.
     *
     * @param lower The lower bound that the internal value of the {@link NumberParameter} must exceed.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isGreaterThan(T lower)
    {
        return isGreaterThan(lower, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@code notGreaterThan} check fails.
     *
     * @see NumberParameter#notGreaterThan(Number)
     * @see NumberParameter#notGreaterThan(Number, NotGreaterThanFailureCallback...)
     */
    @FunctionalInterface public interface NotGreaterThanFailureCallback<T extends Number & Comparable>
    {

        /**
         * Notifies the {@link IsGreaterThanFailureCallback} when the {@code notGreaterThan} check fails.
         *
         * @param parameter The {@link NumberParameter} instance on which the {@code notGreaterThan} check failed.
         * @param upper     The upper bound parameter provided to the {@code notGreaterThan} check that failed.
         */
        void notGreaterThanFailure(NumberParameter<T> parameter, T upper);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is not greater than the provided upper bound.
     *
     * @param upper          The upper bound which the internal value of the {@link NumberParameter} must not exceed.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notGreaterThan(T upper, NotGreaterThanFailureCallback<T>... errorCallbacks)
    {
        boolean result = value.compareTo(upper) <= 0;
        if (!result) {
            failureCount++;
            for (NotGreaterThanFailureCallback<T> errorCallback : errorCallbacks)
                errorCallback.notGreaterThanFailure(this, upper);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is not greater than the provided upper bound.
     * <p>
     * Notifies the {@link NotGreaterThanFailureCallback#notGreaterThanFailure(NumberParameter, Number)} method on the
     * error handlers provided to this instance of {@link NumberParameter} if the check fails.
     *
     * @param upper The upper bound which the internal value of the {@link NumberParameter} must not exceed.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notGreaterThan(T upper)
    {
        return notGreaterThan(upper, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@code isLessThan} check fails.
     *
     * @see NumberParameter#isLessThan(Number)
     * @see NumberParameter#isLessThan(Number, IsLessThanFailureCallback...)
     */
    @FunctionalInterface public interface IsLessThanFailureCallback<T extends Number & Comparable>
    {

        /**
         * Notifies the {@link IsLessThanFailureCallback} when the {@code isLessThan} check fails.
         *
         * @param parameter The {@link NumberParameter} instance on which the {@code isLessThan} check failed.
         * @param upper     The upper bound parameter provided to the {@code isLessThan} check that failed.
         */
        void isLessThanFailure(NumberParameter<T> parameter, T upper);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is less than the provided upper bound.
     *
     * @param upper          The upper bound which the internal value of the {@link NumberParameter} must not exceed.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isLessThan(T upper, IsLessThanFailureCallback<T>... errorCallbacks)
    {
        boolean result = value.compareTo(upper) < 0;
        if (!result) {
            failureCount++;
            for (IsLessThanFailureCallback<T> errorCallback : errorCallbacks)
                errorCallback.isLessThanFailure(this, upper);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is less than the provided upper bound.
     * <p>
     * Notifies the {@link IsLessThanFailureCallback#isLessThanFailure(NumberParameter, Number)} method on the error
     * handlers provided to this instance of {@link NumberParameter} if the check fails.
     *
     * @param upper The upper bound which the internal value of the {@link NumberParameter} must not exceed.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isLessThan(T upper)
    {
        return isLessThan(upper, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@code notLessThan} check fails.
     *
     * @see NumberParameter#notLessThan(Number)
     * @see NumberParameter#notLessThan(Number)
     */
    @FunctionalInterface public interface NotLessThanFailureCallback<T extends Number & Comparable>
    {

        /**
         * Notifies the {@link NotLessThanFailureCallback} when the {@code notLessThan} check fails.
         *
         * @param parameter The {@link NumberParameter} instance on which the {@code notLessThan} check failed.
         * @param lower     The lower bound parameter provided to the {@code notLessThan} check that failed.
         */
        void notLessThanFailure(NumberParameter<T> parameter, T lower);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is not less than the provided lower bound.
     *
     * @param lower          The lower bound which the internal value of the {@link NumberParameter} must not exceed not equal.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notLessThan(T lower, NotLessThanFailureCallback<T>... errorCallbacks)
    {
        boolean result = value.compareTo(lower) >= 0;
        if (!result) {
            failureCount++;
            for (NotLessThanFailureCallback<T> errorCallback : errorCallbacks)
                errorCallback.notLessThanFailure(this, lower);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is not less than the provided lower bound.
     * <p>
     * Notifies the {@link NotLessThanFailureCallback#notLessThanFailure(NumberParameter, Number)} method on the error
     * handlers provided to this instance of {@link NumberParameter} if the check fails.
     *
     * @param lower The lower bound which the internal value of the {@link NumberParameter} must not exceed not equal.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notLessThan(T lower)
    {
        return notLessThan(lower, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@code isBetween} check fails.
     *
     * @see NumberParameter#isBetween(Number, Number)
     * @see NumberParameter#isBetween(Number, Number, boolean)
     * @see NumberParameter#isBetween(Number, Number, IsBetweenFailureCallback...)
     * @see NumberParameter#isBetween(Number, Number, boolean, IsBetweenFailureCallback...)
     */
    @FunctionalInterface public interface IsBetweenFailureCallback<T extends Number & Comparable>
    {

        /**
         * Notifies the {@link IsBetweenFailureCallback} when the {@code isBetween} check fails.
         *
         * @param parameter The {@link NumberParameter} instance on which the {@code isBetween} check failed.
         * @param lower     The lower bound parameter provided to the {@code isBetween} check that failed.
         * @param upper     The upper bound provided to the {@code isBetween} check that failed.
         * @param inclusive The inclusive setting provided to the {@code isBetween} check that failed.
         */
        void isBetweenFailure(NumberParameter<T> parameter, T lower, T upper, boolean inclusive);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is between the provided lower and upper bound. Whether
     * or not the bounds are included or excluded in the interval, can be controlled using the {@code inclusive}
     * parameter. When {@code inclusive} is {@code true} the internal value of the {@link NumberParameter} must conform
     * to {@code value >= lower && value <= upper} for the check to pass. When {@code inclusive} is {@code false} the
     * internal value of the {@link NumberParameter} must conform to {@code value > lower && value < upper} for the check
     * to pass.
     *
     * @param lower          The lower bound which the internal value of the {@link NumberParameter} must exceed, and may
     *                       equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param upper          The upper bound which the internal value of the {@link NumberParameter} must not exceed, and may
     *                       equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param inclusive      Whether or not the provided bounds should be included or excluded by the check.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isBetween(T lower, T upper, boolean inclusive, IsBetweenFailureCallback<T>... errorCallbacks)
    {
        boolean result;

        if (inclusive)
            result = value.compareTo(lower) >= 0 && value.compareTo(upper) <= 0;
        else
            result = value.compareTo(lower) > 0 && value.compareTo(upper) < 0;

        if (!result) {
            failureCount++;
            for (IsBetweenFailureCallback<T> errorCallback : errorCallbacks)
                errorCallback.isBetweenFailure(this, lower, upper, inclusive);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is between the provided lower and upper bound. The
     * internal value of the {@link NumberParameter} must conform to {@code value >= lower && value <= upper} for the check
     * to pass. If the provided bounds should not be valid, use the
     * {@link NumberParameter#isBetween(Number, Number, boolean)} method, where the inclusivity setting can be provided
     * as a parameter.
     *
     * @param lower          The lower bound which the internal value of the {@link NumberParameter} must exceed or equal
     *                       for the check to pass.
     * @param upper          The upper bound which the internal value of the {@link NumberParameter} must not exceed or equal
     *                       for the check to pass.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see NumberParameter#isBetween(Number, Number, boolean, IsBetweenFailureCallback[])
     */
    public boolean isBetween(T lower, T upper, IsBetweenFailureCallback... errorCallbacks)
    {
        return isBetween(lower, upper, true, errorCallbacks);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is between the provided lower and upper bound. Whether
     * or not the bounds are included or excluded in the interval, can be controlled using the {@code inclusive}
     * parameter. When {@code inclusive} is {@code true} the internal value of the {@link NumberParameter} must conform
     * to {@code value >= lower && value <= upper} for the check to pass. When {@code inclusive} is {@code false} the
     * internal value of the {@link NumberParameter} must conform to {@code value > lower && value < upper} for the check
     * to pass.
     * <p>
     * Notifies the {@link IsBetweenFailureCallback#isBetweenFailure(NumberParameter, Number, Number, boolean)} method
     * on the error handlers provided to this instance of {@link NumberParameter} if the check fails.
     *
     * @param lower     The lower bound which the internal value of the {@link NumberParameter} must exceed or equal
     *                  for the check to pass.
     * @param upper     The upper bound which the internal value of the {@link NumberParameter} must not exceed or equal
     *                  for the check to pass.
     * @param inclusive Whether or not the provided bounds should be included or excluded by the check.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see NumberParameter#isBetween(Number, Number, boolean, IsBetweenFailureCallback[])
     */
    public boolean isBetween(T lower, T upper, boolean inclusive)
    {
        return isBetween(lower, upper, inclusive, errorHandlersArray);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is between the provided lower and upper bound. The
     * internal value of the {@link NumberParameter} must conform to {@code value >= lower && value <= upper} for the check
     * to pass. If the provided bounds should not be valid, use the
     * {@link NumberParameter#isBetween(Number, Number, boolean, IsBetweenFailureCallback[])} method, where the
     * inclusivity setting can be provided as a parameter.
     * <p>
     * Notifies the {@link IsBetweenFailureCallback#isBetweenFailure(NumberParameter, Number, Number, boolean)} method
     * on the error handlers provided to this instance of {@link NumberParameter} if the check fails.
     *
     * @param lower The lower bound which the internal value of the {@link NumberParameter} must exceed or equal
     *              for the check to pass.
     * @param upper The upper bound which the internal value of the {@link NumberParameter} must not exceed or equal
     *              for the check to pass.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see NumberParameter#isBetween(Number, Number, IsBetweenFailureCallback[])
     */
    public boolean isBetween(T lower, T upper)
    {
        return isBetween(lower, upper, true);
    }

    /**
     * The functional interface defining the routine notified when the {@code notBetween} check fails.
     *
     * @see NumberParameter#notBetween(Number, Number)
     * @see NumberParameter#notBetween(Number, Number, boolean)
     * @see NumberParameter#notBetween(Number, Number, NotBetweenFailureCallback...)
     * @see NumberParameter#notBetween(Number, Number, boolean, NotBetweenFailureCallback...)
     */
    @FunctionalInterface public interface NotBetweenFailureCallback<T extends Number & Comparable>
    {

        /**
         * Notifies the {@link NotBetweenFailureCallback} when the {@code notBetween} check fails.
         *
         * @param parameter The {@link NumberParameter} instance on which the {@code notBetween} check failed.
         * @param lower     The lower bound parameter provided to the {@code notBetween} check that failed.
         * @param upper     The upper bound parameter provided to the {@code notBetween} check that failed.
         * @param inclusive The inclusive parameter provided to the {@code notBetween} check that failed.
         */
        void notBetweenFailure(NumberParameter<T> parameter, T lower, T upper, boolean inclusive);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is not between the provided lower and upper bound. Whether
     * or not the bounds are included or excluded in the interval, can be controlled using the {@code inclusive}
     * parameter. When {@code inclusive} is {@code true} the internal value of the {@link NumberParameter} must conform
     * to {@code value <= lower || value >= upper} for the check to pass. When {@code inclusive} is {@code false} the
     * internal value of the {@link NumberParameter} must conform to {@code value < lower || value > upper} for the check
     * to pass.
     *
     * @param lower          The lower bound which the internal value of the {@link NumberParameter} must not exceed, and may
     *                       not equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param upper          The upper bound which the internal value of the {@link NumberParameter} must exceed, and may
     *                       not equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param inclusive      Whether or not the provided bounds should be included or excluded by the check.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notBetween(T lower, T upper, boolean inclusive, NotBetweenFailureCallback<T>... errorCallbacks)
    {
        boolean result;

        if (inclusive)
            result = value.compareTo(lower) <= 0 || value.compareTo(upper) >= 0;
        else
            result = value.compareTo(lower) < 0 || value.compareTo(upper) > 0;

        if (!result) {
            failureCount++;
            for (NotBetweenFailureCallback<T> errorCallback : errorCallbacks)
                errorCallback.notBetweenFailure(this, lower, upper, inclusive);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is not between the provided lower and upper bound. The
     * internal value of the {@link NumberParameter} must conform to {@code value <= lower || value >= upper} for the check
     * to pass. If the provided bounds should not be valid, use the
     * {@link NumberParameter#notBetween(Number, Number, boolean, NotBetweenFailureCallback[])} method, where the
     * inclusivity setting can be provided as a parameter.
     *
     * @param lower          The lower bound which the internal value of the {@link NumberParameter} must not exceed nor equal
     *                       for the check to pass.
     * @param upper          The upper bound which the internal value of the {@link NumberParameter} must exceed nor equal
     *                       for the check to pass.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see NumberParameter#isBetween(Number, Number, IsBetweenFailureCallback[])
     */
    public boolean notBetween(T lower, T upper, NotBetweenFailureCallback... errorCallbacks)
    {
        return notBetween(lower, upper, true, errorCallbacks);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is not between the provided lower and upper bound. Whether
     * or not the bounds are included or excluded in the interval, can be controlled using the {@code inclusive}
     * parameter. When {@code inclusive} is {@code true} the internal value of the {@link NumberParameter} must conform
     * to {@code value <= lower || value >= upper} for the check to pass. When {@code inclusive} is {@code false} the
     * internal value of the {@link NumberParameter} must conform to {@code value < lower || value > upper} for the check
     * to pass.
     * <p>
     * Notifies the {@link NotBetweenFailureCallback#notBetweenFailure(NumberParameter, Number, Number, boolean)} method
     * on the error handlers provided to this instance of {@link NumberParameter} if the check fails.
     *
     * @param lower     The lower bound which the internal value of the {@link NumberParameter} must not exceed, and may
     *                  not equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param upper     The upper bound which the internal value of the {@link NumberParameter} must exceed, and may
     *                  not equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param inclusive Whether or not the provided bounds should be included or excluded by the check.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notBetween(T lower, T upper, boolean inclusive)
    {
        return notBetween(lower, upper, inclusive, errorHandlersArray);
    }

    /**
     * Checks that the internal value of the {@link NumberParameter} is not between the provided lower and upper bound. The
     * internal value of the {@link NumberParameter} must conform to {@code value <= lower || value >= upper} for the check
     * to pass. If the provided bounds should not be valid, use the
     * {@link NumberParameter#notBetween(Number, Number, NotBetweenFailureCallback[])} method, where the inclusivity setting
     * can be provided as a parameter.
     * <p>
     * Notifies the {@link NotBetweenFailureCallback#notBetweenFailure(NumberParameter, Number, Number, boolean)} method
     * on the error handlers provided to this instance of {@link NumberParameter} if the check fails.
     *
     * @param lower The lower bound which the internal value of the {@link NumberParameter} must not exceed nor equal
     *              for the check to pass.
     * @param upper The upper bound which the internal value of the {@link NumberParameter} must exceed nor equal
     *              for the check to pass.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see NumberParameter#isBetween(Number, Number, IsBetweenFailureCallback[])
     */
    public boolean notBetween(T lower, T upper)
    {
        return notBetween(lower, upper, true);
    }

    /**
     * Returns the value of this parameter.
     *
     * @return The value of this parameter. This method never return {@code null} values.
     */
    @Override public T getValue()
    {
        return value;
    }

    /**
     * Returns the number of errors that occurred while performing checks on this {@link Parameter}.
     *
     * @return The number of errors.
     */
    @Override public int getFailureCount()
    {
        return failureCount;
    }
}
