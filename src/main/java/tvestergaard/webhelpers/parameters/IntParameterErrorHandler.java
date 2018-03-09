package tvestergaard.webhelpers.parameters;

import static tvestergaard.webhelpers.parameters.IntParameter.*;

public interface IntParameterErrorHandler extends IsFailureCallback,
                                                  NotFailureCallback,
                                                  IsPositiveFailureCallback,
                                                  NotPositiveFailureCallback,
                                                  IsNegativeFailureCallback,
                                                  NotNegativeFailureCallback,
                                                  IsGreaterThanFailureCallback,
                                                  NotGreaterThanFailureCallback,
                                                  IsLessThanFailureCallback,
                                                  NotLessThanFailureCallback,
                                                  IsBetweenFailureCallback,
                                                  NotBetweenFailureCallback
{

    /**
     * Notifies the {@link IsFailureCallback} when the {@code is} check fails.
     *
     * @param parameter The {@link IntParameter} instance on which the {@code is} check failed.
     * @param other     The {@code int} parameter passed to the {@code is} check that failed.
     */
    @Override default void isFailure(IntParameter parameter, int other)
    {

    }

    /**
     * Notifies the {@link NotFailureCallback} when the {@code not} check fails.
     *
     * @param parameter The {@link IntParameter} instance on which the {@code not} check failed.
     * @param other     The {@code int} parameter passed to the {@code not} check that failed.
     */
    @Override default void notFailure(IntParameter parameter, int other)
    {

    }

    /**
     * Notifies the {@link IsPositiveFailureCallback} when the {@code isPositive} check fails.
     *
     * @param parameter The {@link IntParameter} instance on which the {@code isPositive} check failed.
     */
    @Override default void isPositiveFailure(IntParameter parameter)
    {

    }

    /**
     * Notifies the {@link IsPositiveFailureCallback} when the {@code notPositive} check fails.
     *
     * @param parameter The {@link IntParameter} instance on which the {@code notPositive} check failed.
     */
    @Override default void notPositiveFailure(IntParameter parameter)
    {

    }

    /**
     * Notifies the {@link IsPositiveFailureCallback} when the {@code isNegative} check fails.
     *
     * @param parameter The {@link IntParameter} instance on which the {@code isNegative} check failed.
     */
    @Override default void isNegativeFailure(IntParameter parameter)
    {

    }

    /**
     * Notifies the {@link NotNegativeFailureCallback} when the {@code notNegative} check fails.
     *
     * @param parameter The {@link IntParameter} instance on which the {@code notNegative} check failed.
     * @see IntParameter#notNegative()
     * @see IntParameter#notNegative(NotNegativeFailureCallback...)
     */
    @Override default void notNegativeFailure(IntParameter parameter)
    {

    }

    /**
     * Notifies the {@link IsGreaterThanFailureCallback} when the {@code isGreaterThan} check fails.
     *
     * @param parameter The {@link IntParameter} instance on which the {@code isGreaterThan} check failed.
     * @param lower     The lower bound parameter provided to the {@code isGreaterThan} check that failed.
     */
    @Override default void isGreaterThanFailure(IntParameter parameter, int lower)
    {

    }

    /**
     * Notifies the {@link IsGreaterThanFailureCallback} when the {@code notGreaterThan} check fails.
     *
     * @param parameter The {@link IntParameter} instance on which the {@code notGreaterThan} check failed.
     * @param upper     The upper bound parameter provided to the {@code notGreaterThan} check that failed.
     */
    @Override default void notGreaterThanFailure(IntParameter parameter, int upper)
    {

    }

    /**
     * Notifies the {@link IsLessThanFailureCallback} when the {@code isLessThan} check fails.
     *
     * @param parameter The {@link IntParameter} instance on which the {@code isLessThan} check failed.
     * @param upper     The upper bound parameter provided to the {@code isLessThan} check that failed.
     * @see IntParameter#isLessThan(int)
     * @see IntParameter#isLessThan(int, IsLessThanFailureCallback...)
     */
    @Override default void isLessThanFailure(IntParameter parameter, int upper)
    {

    }

    /**
     * Notifies the {@link NotLessThanFailureCallback} when the {@code notLessThan} check fails.
     *
     * @param parameter The {@link IntParameter} instance on which the {@code notLessThan} check failed.
     * @param lower     The lower bound parameter provided to the {@code notLessThan} check that failed.
     */
    @Override default void notLessThanFailure(IntParameter parameter, int lower)
    {

    }

    /**
     * Notifies the {@link IsBetweenFailureCallback} when the {@code isBetween} check fails.
     *
     * @param parameter The {@link IntParameter} instance on which the {@code isBetween} check failed.
     * @param lower     The lower bound parameter provided to the {@code isBetween} check that failed.
     * @param upper     The upper bound provided to the {@code isBetween} check that failed.
     * @param inclusive The inclusive setting provided to the {@code isBetween} check that failed.
     */
    @Override default void isBetweenFailure(IntParameter parameter, int lower, int upper, boolean inclusive)
    {

    }

    /**
     * Notifies the {@link NotBetweenFailureCallback} when the {@code notBetween} check fails.
     *
     * @param parameter The {@link IntParameter} instance on which the {@code notBetween} check failed.
     * @param lower     The lower bound parameter provided to the {@code notBetween} check that failed.
     * @param upper     The upper bound parameter provided to the {@code notBetween} check that failed.
     * @param inclusive The inclusive parameter provided to the {@code notBetween} check that failed.
     */
    @Override default void notBetweenFailure(IntParameter parameter, int lower, int upper, boolean inclusive)
    {

    }
}
