package tvestergaard.webhelpers.parameters;

import java.util.List;

public class IntParameter extends AbstractParameter
{

    /**
     * The internal value of the {@link IntParameter}.
     */
    private final int value;

    /**
     * A list containing the registered instances of {@link IntParameterErrorHandler}. These failure handlers are
     * notified when checks performed on this instance of {@link IntParameter} fails.
     */
    private final List<IntParameterErrorHandler> errorHandlers;

    /**
     * An array containing the registered instances of {@link IntParameterErrorHandler}. These failure handlers are
     * notified when checks performed on this instance of {@link IntParameter} fails.
     */
    private final IntParameterErrorHandler[] errorHandlersArray;

    /**
     * The number of failures that have occurred during checks.
     */
    private int failureCount = 0;

    /**
     * Creates a new {@link IntParameter}.
     *
     * @param name            The name of the {@link IntParameter}.
     * @param value           The value of the {@link IntParameter}.
     * @param failureHandlers The failure handlers that are notified when checks performed on this instance of
     *                        {@link IntParameter} fails.
     */
    public IntParameter(String name, int value, List<IntParameterErrorHandler> failureHandlers)
    {
        super(name);
        this.value = value;
        this.errorHandlers = failureHandlers;
        this.errorHandlersArray = failureHandlers.toArray(new IntParameterErrorHandler[failureHandlers.size()]);
    }

    /**
     * The functional interface defining the routine notified when the {@code is()} check fails.
     *
     * @see IntParameter#is(int)
     * @see IntParameter#is(int, IsFailureCallback...)
     */
    @FunctionalInterface public interface IsFailureCallback
    {

        /**
         * Notifies the {@link IsFailureCallback} when the {@code is} check fails.
         *
         * @param parameter The {@link IntParameter} instance on which the {@code is} check failed.
         * @param other     The {@code int} parameter passed to the {@code is} check that failed.
         */
        void isFailure(IntParameter parameter, int other);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} equals the provided {@code other}.
     *
     * @param other          The value the internal value of the {@link IntParameter} must equal for the check to pass.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean is(int other, IsFailureCallback... errorCallbacks)
    {
        boolean result = value == other;
        if (!result) {
            failureCount++;
            for (IsFailureCallback errorCallback : errorCallbacks)
                errorCallback.isFailure(this, other);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link IntParameter} equals the provided {@code other}.
     * <p>
     * Notifies the {@link IsFailureCallback#isFailure(IntParameter, int)} method on the error handlers provided to this
     * instance of {@link IntParameter} if the check fails.
     *
     * @param other The value the internal value of the {@link IntParameter} must equal for the check to pass.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean is(int other)
    {
        return is(other, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link {@code is()} check fails.
     *
     * @see IntParameter#not(int)
     * @see IntParameter#not(int, IsFailureCallback...)
     */
    @FunctionalInterface public interface NotFailureCallback
    {

        /**
         * Notifies the {@link NotFailureCallback} when the {@code not} check fails.
         *
         * @param parameter The {@link IntParameter} instance on which the {@code not} check failed.
         * @param other     The {@code int} parameter passed to the {@code not} check that failed.
         */
        void notFailure(IntParameter parameter, int other);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} not not equal the provided {@code other}.
     *
     * @param other          The value the internal value of the {@link IntParameter} must not equal for the check to pass.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean not(int other, NotFailureCallback... errorCallbacks)
    {
        boolean result = value != other;
        if (!result) {
            failureCount++;
            for (NotFailureCallback errorCallback : errorCallbacks)
                errorCallback.notFailure(this, other);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link IntParameter} does not equal the provided {@code other}.
     * <p>
     * Notifies the {@link IsFailureCallback#isFailure(IntParameter, int)} method on the error handlers provided to this
     * instance of {@link IntParameter} if the check fails.
     *
     * @param other The value the internal value of the {@link IntParameter} must not equal for the check to pass.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean not(int other)
    {
        return not(other, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@code isPositive} check fails.
     *
     * @see IntParameter#isPositive()
     * @see IntParameter#isPositive(IsPositiveFailureCallback...)
     */
    @FunctionalInterface public interface IsPositiveFailureCallback
    {

        /**
         * Notifies the {@link IsPositiveFailureCallback} when the {@code isPositive} check fails.
         *
         * @param parameter The {@link IntParameter} instance on which the {@code isPositive} check failed.
         */
        boolean isPositiveFailure(IntParameter parameter);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is positive.
     *
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isPositive(IsPositiveFailureCallback... errorCallbacks)
    {
        boolean result = value > 0;
        if (!result) {
            failureCount++;
            for (IsPositiveFailureCallback errorCallback : errorCallbacks)
                errorCallback.isPositiveFailure(this);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is positive.
     * <p>
     * Notifies the {@link IsPositiveFailureCallback#isPositiveFailure(IntParameter)} method on the error handlers provided to
     * this instance of {@link IntParameter} if the check fails.
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
     * @see IntParameter#notPositive()
     * @see IntParameter#notPositive(NotPositiveFailureCallback...)
     */
    @FunctionalInterface public interface NotPositiveFailureCallback
    {

        /**
         * Notifies the {@link IsPositiveFailureCallback} when the {@code notPositive} check fails.
         *
         * @param parameter The {@link IntParameter} instance on which the {@code notPositive} check failed.
         */
        boolean notPositiveError(IntParameter parameter);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is not positive.
     *
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notPositive(NotPositiveFailureCallback... errorCallbacks)
    {
        boolean result = value <= 0;
        if (!result) {
            failureCount++;
            for (NotPositiveFailureCallback errorCallback : errorCallbacks)
                errorCallback.notPositiveError(this);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is not positive.
     * <p>
     * Notifies the {@link NotPositiveFailureCallback#notPositiveError(IntParameter)} method on the error handlers provided to
     * this instance of {@link IntParameter} if the check fails.
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
     * @see IntParameter#isNegative()
     * @see IntParameter#isNegative(IsNegativeFailureCallback...)
     */
    @FunctionalInterface public interface IsNegativeFailureCallback
    {

        /**
         * Notifies the {@link IsPositiveFailureCallback} when the {@code isNegative} check fails.
         *
         * @param parameter The {@link IntParameter} instance on which the {@code isNegative} check failed.
         */
        boolean isNegativeFailure(IntParameter parameter);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is negative.
     *
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isNegative(IsNegativeFailureCallback... errorCallbacks)
    {
        boolean result = value < 0;
        if (!result) {
            failureCount++;
            for (IsNegativeFailureCallback errorCallback : errorCallbacks)
                errorCallback.isNegativeFailure(this);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is negative.
     * <p>
     * Notifies the {@link IsNegativeFailureCallback#isNegativeFailure(IntParameter)} method on the error handlers provided to
     * this instance of {@link IntParameter} if the check fails.
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
    @FunctionalInterface public interface NotNegativeFailureCallback
    {

        /**
         * Notifies the {@link NotNegativeFailureCallback} when the {@code notNegative} check fails.
         *
         * @param parameter The {@link IntParameter} instance on which the {@code notNegative} check failed.
         * @see IntParameter#notNegative()
         * @see IntParameter#notNegative(NotNegativeFailureCallback...)
         */
        boolean notNegativeFailure(IntParameter parameter);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is not negative.
     *
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notNegative(NotNegativeFailureCallback... errorCallbacks)
    {
        boolean result = value >= 0;
        if (!result) {
            failureCount++;
            for (NotNegativeFailureCallback errorCallback : errorCallbacks)
                errorCallback.notNegativeFailure(this);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is not negative.
     * <p>
     * Notifies the {@link NotNegativeFailureCallback#notNegativeFailure(IntParameter)} method on the error handlers provided to
     * this instance of {@link IntParameter} if the check fails.
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
     * @see IntParameter#isGreaterThan(int)
     * @see IntParameter#isGreaterThan(int, IsGreaterThanFailureCallback...)
     */
    @FunctionalInterface public interface IsGreaterThanFailureCallback
    {

        /**
         * Notifies the {@link IsGreaterThanFailureCallback} when the {@code isGreaterThan} check fails.
         *
         * @param parameter The {@link IntParameter} instance on which the {@code isGreaterThan} check failed.
         * @param lower     The lower bound parameter provided to the {@code isGreaterThan} check that failed.
         */
        boolean isGreaterThanFailure(IntParameter parameter, int lower);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is greater than the provided lower bound.
     *
     * @param lower          The lower bound that the internal value of the {@link IntParameter} must exceed.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isGreaterThan(int lower, IsGreaterThanFailureCallback... errorCallbacks)
    {
        boolean result = value > lower;
        if (!result) {
            failureCount++;
            for (IsGreaterThanFailureCallback errorCallback : errorCallbacks)
                errorCallback.isGreaterThanFailure(this, lower);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is greater than the provided lower bound.
     * <p>
     * Notifies the {@link IsGreaterThanFailureCallback#isGreaterThanFailure(IntParameter, int)} method on the error
     * handlers provided to this instance of {@link IntParameter} if the check fails.
     *
     * @param lower The lower bound that the internal value of the {@link IntParameter} must exceed.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isGreaterThan(int lower)
    {
        return isGreaterThan(lower, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@code notGreaterThan} check fails.
     *
     * @see IntParameter#notGreaterThan(int)
     * @see IntParameter#notGreaterThan(int, NotGreaterThanFailureCallback...)
     */
    @FunctionalInterface public interface NotGreaterThanFailureCallback
    {

        /**
         * Notifies the {@link IsGreaterThanFailureCallback} when the {@code notGreaterThan} check fails.
         *
         * @param parameter The {@link IntParameter} instance on which the {@code notGreaterThan} check failed.
         * @param upper     The upper bound parameter provided to the {@code notGreaterThan} check that failed.
         */
        boolean notGreaterThanFailure(IntParameter parameter, int upper);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is not greater than the provided upper bound.
     *
     * @param upper          The upper bound which the internal value of the {@link IntParameter} must not exceed.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notGreaterThan(int upper, NotGreaterThanFailureCallback... errorCallbacks)
    {
        boolean result = value <= upper;
        if (!result) {
            failureCount++;
            for (NotGreaterThanFailureCallback errorCallback : errorCallbacks)
                errorCallback.notGreaterThanFailure(this, upper);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is not greater than the provided upper bound.
     * <p>
     * Notifies the {@link NotGreaterThanFailureCallback#notGreaterThanFailure(IntParameter, int)} method on the error
     * handlers provided to this instance of {@link IntParameter} if the check fails.
     *
     * @param upper The upper bound which the internal value of the {@link IntParameter} must not exceed.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notGreaterThan(int upper)
    {
        return notGreaterThan(upper, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@code isLessThan} check fails.
     */
    @FunctionalInterface public interface IsLessThanFailureCallback
    {

        /**
         * Notifies the {@link IsLessThanFailureCallback} when the {@code isLessThan} check fails.
         *
         * @param parameter The {@link IntParameter} instance on which the {@code isLessThan} check failed.
         * @param upper     The upper bound parameter provided to the {@code isLessThan} check that failed.
         * @see IntParameter#isLessThan(int)
         * @see IntParameter#isLessThan(int, IsLessThanFailureCallback...)
         */
        boolean isLessThanFailure(IntParameter parameter, int upper);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is less than the provided upper bound.
     *
     * @param upper          The upper bound which the internal value of the {@link IntParameter} must not exceed.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isLessThan(int upper, IsLessThanFailureCallback... errorCallbacks)
    {
        boolean result = value < upper;
        if (!result) {
            failureCount++;
            for (IsLessThanFailureCallback errorCallback : errorCallbacks)
                errorCallback.isLessThanFailure(this, upper);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is less than the provided upper bound.
     * <p>
     * Notifies the {@link IsLessThanFailureCallback#isLessThan(int)} method on the error
     * handlers provided to this instance of {@link IntParameter} if the check fails.
     *
     * @param upper The upper bound which the internal value of the {@link IntParameter} must not exceed.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isLessThan(int upper)
    {
        return isLessThan(upper, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@code notLessThan} check fails.
     *
     * @see IntParameter#notLessThan(int)
     * @see IntParameter#notLessThan(int, NotLessThanFailureCallback...)
     */
    @FunctionalInterface public interface NotLessThanFailureCallback
    {

        /**
         * Notifies the {@link NotLessThanFailureCallback} when the {@code notLessThan} check fails.
         *
         * @param parameter The {@link IntParameter} instance on which the {@code notLessThan} check failed.
         * @param lower     The lower bound parameter provided to the {@code notLessThan} check that failed.
         */
        boolean notLessThanFailure(IntParameter parameter, int lower);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is not less than the provided lower bound.
     *
     * @param lower          The lower bound which the internal value of the {@link IntParameter} must not exceed not equal.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notLessThan(int lower, NotLessThanFailureCallback... errorCallbacks)
    {
        boolean result = value >= lower;
        if (!result) {
            failureCount++;
            for (NotLessThanFailureCallback errorCallback : errorCallbacks)
                errorCallback.notLessThanFailure(this, lower);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is not less than the provided lower bound.
     * <p>
     * Notifies the {@link NotLessThanFailureCallback#notLessThan(int)} method on the error
     * handlers provided to this instance of {@link IntParameter} if the check fails.
     *
     * @param lower The lower bound which the internal value of the {@link IntParameter} must not exceed not equal.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notLessThan(int lower)
    {
        return notLessThan(lower, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@code isBetween} check fails.
     *
     * @see IntParameter#isBetween(int, int)
     * @see IntParameter#isBetween(int, int, boolean)
     * @see IntParameter#isBetween(int, int, IsBetweenFailureCallback...)
     * @see IntParameter#isBetween(int, int, boolean, IsBetweenFailureCallback...)
     */
    @FunctionalInterface public interface IsBetweenFailureCallback
    {

        /**
         * Notifies the {@link IsBetweenFailureCallback} when the {@code isBetween} check fails.
         *
         * @param parameter The {@link IntParameter} instance on which the {@code isBetween} check failed.
         * @param lower     The lower bound parameter provided to the {@code isBetween} check that failed.
         */
        boolean isBetweenError(IntParameter parameter, int lower, int upper, boolean inclusive);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is between the provided lower and upper bound. Whether
     * or not the bounds are included or excluded in the interval, can be controlled using the {@code inclusive}
     * parameter. When {@code inclusive} is {@code true} the internal value of the {@link IntParameter} must conform
     * to {@code value >= lower && value <= upper} for the check to pass. When {@code inclusive} is {@code false} the
     * internal value of the {@link IntParameter} must conform to {@code value > lower && value < upper} for the check
     * to pass.
     *
     * @param lower          The lower bound which the internal value of the {@link IntParameter} must exceed, and may
     *                       equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param upper          The upper bound which the internal value of the {@link IntParameter} must not exceed, and may
     *                       equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param inclusive      Whether or not the provided bounds should be included or excluded by the check.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean isBetween(int lower, int upper, boolean inclusive, IsBetweenFailureCallback... errorCallbacks)
    {
        boolean result;

        if (inclusive)
            result = value >= lower && value <= upper;
        else
            result = value > lower && value < upper;

        if (!result) {
            failureCount++;
            for (IsBetweenFailureCallback errorCallback : errorCallbacks)
                errorCallback.isBetweenError(this, lower, upper, inclusive);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is between the provided lower and upper bound. The
     * internal value of the {@link IntParameter} must conform to {@code value >= lower && value <= upper} for the check
     * to pass. If the provided bounds should not be valid, use the
     * {@link IntParameter#isBetween(int, int, boolean, IsBetweenFailureCallback...)} method, where the inclusivity setting
     * can be provided as a parameter.
     *
     * @param lower          The lower bound which the internal value of the {@link IntParameter} must exceed or equal
     *                       for the check to pass.
     * @param upper          The upper bound which the internal value of the {@link IntParameter} must not exceed or equal
     *                       for the check to pass.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see IntParameter#isBetween(int, int, boolean, IsBetweenFailureCallback...)
     */
    public boolean isBetween(int lower, int upper, IsBetweenFailureCallback... errorCallbacks)
    {
        return isBetween(lower, upper, true, errorCallbacks);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is between the provided lower and upper bound. Whether
     * or not the bounds are included or excluded in the interval, can be controlled using the {@code inclusive}
     * parameter. When {@code inclusive} is {@code true} the internal value of the {@link IntParameter} must conform
     * to {@code value >= lower && value <= upper} for the check to pass. When {@code inclusive} is {@code false} the
     * internal value of the {@link IntParameter} must conform to {@code value > lower && value < upper} for the check
     * to pass.
     * <p>
     * Notifies the {@link NotLessThanFailureCallback#isBetween(int, int, boolean)} method on the error
     * handlers provided to this instance of {@link IntParameter} if the check fails.
     *
     * @param lower     The lower bound which the internal value of the {@link IntParameter} must exceed or equal
     *                  for the check to pass.
     * @param upper     The upper bound which the internal value of the {@link IntParameter} must not exceed or equal
     *                  for the check to pass.
     * @param inclusive Whether or not the provided bounds should be included or excluded by the check.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see IntParameter#isBetween(int, int, boolean, IsBetweenFailureCallback...)
     */
    public boolean isBetween(int lower, int upper, boolean inclusive)
    {
        return isBetween(lower, upper, inclusive, errorHandlersArray);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is between the provided lower and upper bound. The
     * internal value of the {@link IntParameter} must conform to {@code value >= lower && value <= upper} for the check
     * to pass. If the provided bounds should not be valid, use the
     * {@link IntParameter#isBetween(int, int, boolean, IsBetweenFailureCallback...)} method, where the inclusivity setting
     * can be provided as a parameter.
     * <p>
     * Notifies the {@link NotLessThanFailureCallback#isBetween(int, int, boolean)} method on the error
     * handlers provided to this instance of {@link IntParameter} if the check fails.
     *
     * @param lower The lower bound which the internal value of the {@link IntParameter} must exceed or equal
     *              for the check to pass.
     * @param upper The upper bound which the internal value of the {@link IntParameter} must not exceed or equal
     *              for the check to pass.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see IntParameter#isBetween(int, int, boolean, IsBetweenFailureCallback...)
     */
    public boolean isBetween(int lower, int upper)
    {
        return isBetween(lower, upper, true);
    }

    /**
     * The functional interface defining the routine notified when the {@code notBetween} check fails.
     *
     * @see IntParameter#notBetween(int, int)
     * @see IntParameter#notBetween(int, int, boolean)
     * @see IntParameter#notBetween(int, int, NotBetweenFailureCallback...)
     * @see IntParameter#notBetween(int, int, boolean, NotBetweenFailureCallback...)
     */
    @FunctionalInterface public interface NotBetweenFailureCallback
    {

        /**
         * Notifies the {@link NotBetweenFailureCallback} when the {@code notBetween} check fails.
         *
         * @param parameter The {@link IntParameter} instance on which the {@code notBetween} check failed.
         * @param lower     The lower bound parameter provided to the {@code notBetween} check that failed.
         * @param upper     The upper bound parameter provided to the {@code notBetween} check that failed.
         * @param inclusive The inclusive parameter provided to the {@code notBetween} check that failed.
         */
        boolean notBetweenError(IntParameter parameter, int lower, int upper, boolean inclusive);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is not between the provided lower and upper bound. Whether
     * or not the bounds are included or excluded in the interval, can be controlled using the {@code inclusive}
     * parameter. When {@code inclusive} is {@code true} the internal value of the {@link IntParameter} must conform
     * to {@code value <= lower || value >= upper} for the check to pass. When {@code inclusive} is {@code false} the
     * internal value of the {@link IntParameter} must conform to {@code value < lower || value > upper} for the check
     * to pass.
     *
     * @param lower          The lower bound which the internal value of the {@link IntParameter} must not exceed, and may
     *                       not equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param upper          The upper bound which the internal value of the {@link IntParameter} must exceed, and may
     *                       not equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param inclusive      Whether or not the provided bounds should be included or excluded by the check.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notBetween(int lower, int upper, boolean inclusive, NotBetweenFailureCallback... errorCallbacks)
    {
        boolean result;

        if (inclusive)
            result = value <= lower || value >= upper;
        else
            result = value < lower || value > upper;

        if (!result) {
            failureCount++;
            for (NotBetweenFailureCallback errorCallback : errorCallbacks)
                errorCallback.notBetweenError(this, lower, upper, inclusive);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is not between the provided lower and upper bound. The
     * internal value of the {@link IntParameter} must conform to {@code value <= lower || value >= upper} for the check
     * to pass. If the provided bounds should not be valid, use the
     * {@link IntParameter#notBetween(int, int, boolean, NotBetweenFailureCallback...)}  method, where the inclusivity setting
     * can be provided as a parameter.
     *
     * @param lower          The lower bound which the internal value of the {@link IntParameter} must not exceed nor equal
     *                       for the check to pass.
     * @param upper          The upper bound which the internal value of the {@link IntParameter} must exceed nor equal
     *                       for the check to pass.
     * @param errorCallbacks The error handlers that are notified in case the check fails.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see IntParameter#isBetween(int, int, boolean, IsBetweenFailureCallback...)
     */
    public boolean notBetween(int lower, int upper, NotBetweenFailureCallback... errorCallbacks)
    {
        return notBetween(lower, upper, true, errorCallbacks);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is not between the provided lower and upper bound. Whether
     * or not the bounds are included or excluded in the interval, can be controlled using the {@code inclusive}
     * parameter. When {@code inclusive} is {@code true} the internal value of the {@link IntParameter} must conform
     * to {@code value <= lower || value >= upper} for the check to pass. When {@code inclusive} is {@code false} the
     * internal value of the {@link IntParameter} must conform to {@code value < lower || value > upper} for the check
     * to pass.
     * <p>
     * Notifies the {@link NotBetweenFailureCallback#notBetween(int, int, boolean)} method on the error
     * handlers provided to this instance of {@link IntParameter} if the check fails.
     *
     * @param lower     The lower bound which the internal value of the {@link IntParameter} must not exceed, and may
     *                  not equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param upper     The upper bound which the internal value of the {@link IntParameter} must exceed, and may
     *                  not equal depending on the flag provided to the {@code inclusive} parameter, for the check to pass.
     * @param inclusive Whether or not the provided bounds should be included or excluded by the check.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     */
    public boolean notBetween(int lower, int upper, boolean inclusive)
    {
        return notBetween(lower, upper, inclusive, errorHandlersArray);
    }

    /**
     * Checks that the internal value of the {@link IntParameter} is not between the provided lower and upper bound. The
     * internal value of the {@link IntParameter} must conform to {@code value <= lower || value >= upper} for the check
     * to pass. If the provided bounds should not be valid, use the
     * {@link IntParameter#notBetween(int, int, boolean, NotBetweenFailureCallback...)}  method, where the inclusivity setting
     * can be provided as a parameter.
     * <p>
     * Notifies the {@link NotBetweenFailureCallback#notBetween(int, int, boolean)} method on the error
     * handlers provided to this instance of {@link IntParameter} if the check fails.
     *
     * @param lower The lower bound which the internal value of the {@link IntParameter} must not exceed nor equal
     *              for the check to pass.
     * @param upper The upper bound which the internal value of the {@link IntParameter} must exceed nor equal
     *              for the check to pass.
     * @return {@code true} if the check passes, {@code false} is all other cases.
     * @see IntParameter#isBetween(int, int, boolean, IsBetweenFailureCallback...)
     */
    public boolean notBetween(int lower, int upper)
    {
        return notBetween(lower, upper, true);
    }

    /**
     * Returns the value of this parameter.
     *
     * @return The value of this parameter. This method never return {@code null} values.
     */
    @Override public Integer getValue()
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
