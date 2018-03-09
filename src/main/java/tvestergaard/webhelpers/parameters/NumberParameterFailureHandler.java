package tvestergaard.webhelpers.parameters;

import static tvestergaard.webhelpers.parameters.NumberParameter.*;

public interface NumberParameterFailureHandler<T extends Number & Comparable> extends IsFailureCallback<T>,
                                                                                      NotFailureCallback<T>,
                                                                                      IsPositiveFailureCallback<T>,
                                                                                      NotPositiveFailureCallback<T>,
                                                                                      IsNegativeFailureCallback<T>,
                                                                                      NotNegativeFailureCallback<T>,
                                                                                      IsGreaterThanFailureCallback<T>,
                                                                                      NotGreaterThanFailureCallback<T>,
                                                                                      IsLessThanFailureCallback<T>,
                                                                                      NotLessThanFailureCallback<T>,
                                                                                      IsBetweenFailureCallback<T>,
                                                                                      NotBetweenFailureCallback<T>
{

    /**
     * Notifies the {@link IsFailureCallback} when the {@code is} check fails.
     *
     * @param parameter The {@link NumberParameter} instance on which the {@code is} check failed.
     * @param other     The {@code int} parameter passed to the {@code is} check that failed.
     */
    @Override default void isFailure(NumberParameter<T> parameter, T other)
    {

    }

    /**
     * Notifies the {@link NotFailureCallback} when the {@code not} check fails.
     *
     * @param parameter The {@link NumberParameter} instance on which the {@code not} check failed.
     * @param other     The {@code int} parameter passed to the {@code not} check that failed.
     */
    @Override default void notFailure(NumberParameter<T> parameter, T other)
    {

    }

    /**
     * Notifies the {@link IsPositiveFailureCallback} when the {@code isPositive} check fails.
     *
     * @param parameter The {@link NumberParameter} instance on which the {@code isPositive} check failed.
     */
    @Override default void isPositiveFailure(NumberParameter<T> parameter)
    {

    }

    /**
     * Notifies the {@link IsPositiveFailureCallback} when the {@code notPositive} check fails.
     *
     * @param parameter The {@link NumberParameter} instance on which the {@code notPositive} check failed.
     */
    @Override default void notPositiveFailure(NumberParameter<T> parameter)
    {

    }

    /**
     * Notifies the {@link IsPositiveFailureCallback} when the {@code isNegative} check fails.
     *
     * @param parameter The {@link NumberParameter} instance on which the {@code isNegative} check failed.
     */
    @Override default void isNegativeFailure(NumberParameter<T> parameter)
    {

    }

    /**
     * Notifies the {@link NotNegativeFailureCallback} when the {@code notNegative} check fails.
     *
     * @param parameter The {@link NumberParameter} instance on which the {@code notNegative} check failed.
     * @see NumberParameter#notNegative()
     * @see NumberParameter#notNegative(NotNegativeFailureCallback...)
     */
    @Override default void notNegativeFailure(NumberParameter<T> parameter)
    {

    }

    /**
     * Notifies the {@link IsGreaterThanFailureCallback} when the {@code isGreaterThan} check fails.
     *
     * @param parameter The {@link NumberParameter} instance on which the {@code isGreaterThan} check failed.
     * @param lower     The lower bound parameter provided to the {@code isGreaterThan} check that failed.
     */
    @Override default void isGreaterThanFailure(NumberParameter<T> parameter, T lower)
    {

    }

    /**
     * Notifies the {@link IsGreaterThanFailureCallback} when the {@code notGreaterThan} check fails.
     *
     * @param parameter The {@link NumberParameter} instance on which the {@code notGreaterThan} check failed.
     * @param upper     The upper bound parameter provided to the {@code notGreaterThan} check that failed.
     */
    @Override default void notGreaterThanFailure(NumberParameter<T> parameter, T upper)
    {

    }

    /**
     * Notifies the {@link IsLessThanFailureCallback} when the {@code isLessThan} check fails.
     *
     * @param parameter The {@link NumberParameter} instance on which the {@code isLessThan} check failed.
     * @param upper     The upper bound parameter provided to the {@code isLessThan} check that failed.
     */
    @Override default void isLessThanFailure(NumberParameter<T> parameter, T upper)
    {

    }

    /**
     * Notifies the {@link NotLessThanFailureCallback} when the {@code notLessThan} check fails.
     *
     * @param parameter The {@link NumberParameter} instance on which the {@code notLessThan} check failed.
     * @param lower     The lower bound parameter provided to the {@code notLessThan} check that failed.
     */
    @Override default void notLessThanFailure(NumberParameter<T> parameter, T lower)
    {

    }

    /**
     * Notifies the {@link IsBetweenFailureCallback} when the {@code isBetween} check fails.
     *
     * @param parameter The {@link NumberParameter} instance on which the {@code isBetween} check failed.
     * @param lower     The lower bound parameter provided to the {@code isBetween} check that failed.
     * @param upper     The upper bound provided to the {@code isBetween} check that failed.
     * @param inclusive The inclusive setting provided to the {@code isBetween} check that failed.
     */
    @Override default void isBetweenFailure(NumberParameter<T> parameter, T lower, T upper, boolean inclusive)
    {

    }

    /**
     * Notifies the {@link NotBetweenFailureCallback} when the {@code notBetween} check fails.
     *
     * @param parameter The {@link NumberParameter} instance on which the {@code notBetween} check failed.
     * @param lower     The lower bound parameter provided to the {@code notBetween} check that failed.
     * @param upper     The upper bound parameter provided to the {@code notBetween} check that failed.
     * @param inclusive The inclusive parameter provided to the {@code notBetween} check that failed.
     */
    @Override default void notBetweenFailure(NumberParameter<T> parameter, T lower, T upper, boolean inclusive)
    {

    }
}
