package tvestergaard.webhelpers.parameters;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static tvestergaard.webhelpers.parameters.TextParameterFailureHandler.EMPTY_FAILURE_HANDLER;

public class TextParameter extends AbstractParameter
{

    /**
     * The value of the {@link Parameter}.
     */
    private final String value;

    /**
     * The isLength of the value in the {@link Parameter}.
     */
    private final int length;

    /**
     * An list containing the {@link TextParameterFailureHandler} instances that must be notified of
     * validation failures.
     */
    private final List<TextParameterFailureHandler> failureHandlers;

    /**
     * An array containing the {@link TextParameterFailureHandler} instances that must be notified of validation failures.
     */
    private final TextParameterFailureHandler[] failureHandlerArray;

    /**
     * The number of failures that occurred while performing checks.
     */
    private int failureCount = 0;

    /**
     * Creates a new {@link TextParameter}.
     *
     * @param name           The name of the {@link TextParameter}.
     * @param value          The value of the {@link TextParameter}.
     * @param failureHandler The {@link TextParameterFailureHandler} instances used as callbacks for when checks fails.
     */
    public TextParameter(String name, String value, List<TextParameterFailureHandler> failureHandler)
    {
        super(name);

        this.value = value;
        this.length = value.length();
        this.failureHandlers = failureHandler;
        this.failureHandlerArray = this.failureHandlers.toArray(new TextParameterFailureHandler[this.failureHandlers.size()]);
    }

    /**
     * Creates a new {@link TextParameter}.
     *
     * @param name           The name of the {@link TextParameter}.
     * @param value          The value of the {@link TextParameter}.
     * @param failureHandler The {@link TextParameterFailureHandler} instances used as callbacks for when checks fails.
     */
    public TextParameter(String name, String value, TextParameterFailureHandler... failureHandler)
    {
        this(name, value, Arrays.asList(failureHandler));
    }

    /**
     * Creates a new {@link TextParameter} without any failureHandler for check onFailures.
     *
     * @param name  The name of the {@link TextParameter}.
     * @param value The value of the {@link TextParameter}.
     */
    public TextParameter(String name, String value)
    {
        this(name, value, EMPTY_FAILURE_HANDLER);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#is(CharSequence)} method
     * check fails.
     */
    @FunctionalInterface public interface IsFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#is(CharSequence)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#is(CharSequence)} check method failed.
         * @param other     The {@code CharSequence} provided to the {@link TextParameter#is(CharSequence)}.
         */
        void isFailure(TextParameter parameter, CharSequence other);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals the provided {@code CharSequence}.
     * <p>
     * The {@link IsFailureCallback#isFailure(TextParameter, CharSequence)} method on all the provided {@link IsFailureCallback}
     * instances will be called if the check fails. The {@link TextParameterFailureHandler#isFailure(TextParameter, CharSequence)}
     * methods on the instances of {@link TextParameterFailureHandler} provided to this object will not be called.
     *
     * @param other            The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @param failureCallbacks The {@link IsFailureCallback}s called in case the check fails.
     * @return {@code true} if the value equals the provided {code CharSequence}, {@code false} in all other cases.
     */
    public boolean is(CharSequence other, IsFailureCallback... failureCallbacks)
    {
        boolean result = other.equals(value);
        if (!result) {
            failureCount++;
            for (IsFailureCallback failureCallback : failureCallbacks) {
                failureCallback.isFailure(this, other);
            }
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals the provided {@code CharSequence}.
     * <p>
     * The {@link TextParameterFailureHandler#isFailure(TextParameter, CharSequence)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @param other The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @return {@code true} if the value equals the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean is(CharSequence other)
    {
        return is(other, failureHandlerArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#not(CharSequence)} method
     * check fails.
     */
    @FunctionalInterface public interface NotFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#not(CharSequence)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#not(CharSequence)} check method failed.
         * @param other     The {@code CharSequence} provided to the {@link TextParameter#not(CharSequence)}.
         */
        void notFailure(TextParameter parameter, CharSequence other);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} does not equal the provided {@code CharSequence}.
     * <p>
     * The {@link NotFailureCallback#notFailure(TextParameter, CharSequence)} method on all the provided {@link NotFailureCallback}
     * instances will be called if the check fails.
     *
     * @param other            The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @param failureCallbacks The {@link NotFailureCallback}s called in case the check fails.
     * @return {@code true} if the value does not equal the provided {code CharSequence}, {@code false} in all other cases.
     */
    public boolean not(CharSequence other, NotFailureCallback... failureCallbacks)
    {
        boolean result = !other.equals(this.value);
        if (!result) {
            failureCount++;
            for (NotFailureCallback failureCallback : failureCallbacks)
                failureCallback.notFailure(this, other);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} does not equal the provided {@code CharSequence}.
     * <p>
     * The {@link TextParameterFailureHandler#notFailure(TextParameter, CharSequence)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @param other The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @return {@code true} if the value does not equal the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean not(CharSequence other)
    {
        return not(other, failureHandlerArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isEmpty()} method
     * check fails.
     */
    @FunctionalInterface public interface IsEmptyFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#isEmpty()} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#isEmpty()} check method failed.
         */
        void isEmptyFailure(TextParameter parameter);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is empty.
     * <p>
     * The {@link IsEmptyFailureCallback#isEmptyFailure(TextParameter)} method on all the provided {@link IsEmptyFailureCallback}
     * instances will be called if the check fails.
     *
     * @param failureCallbacks The {@link IsEmptyFailureCallback}s called in case the check fails.
     * @return {@code true} if the value is empty, {@code false} in all other cases.
     */
    public boolean isEmpty(IsEmptyFailureCallback... failureCallbacks)
    {
        boolean result = value.isEmpty();
        if (!result) {
            failureCount++;
            for (IsEmptyFailureCallback failureCallback : failureCallbacks)
                failureCallback.isEmptyFailure(this);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is empty.
     * <p>
     * The {@link TextParameterFailureHandler#isEmptyFailure(TextParameter)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @return {@code true} if the value is empty, {@code false} in all other cases.
     */
    public boolean isEmpty()
    {
        return isEmpty(failureHandlerArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#notEmpty()} method
     * check fails.
     */
    @FunctionalInterface public interface NotEmptyFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#notEmpty()} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#notEmpty()} check method failed.
         */
        void notEmptyFailure(TextParameter parameter);
    }


    /**
     * Checks that the internal value of the {@link TextParameter} is not empty.
     * <p>
     * The {@link NotEmptyFailureCallback#notEmptyFailure(TextParameter)} method on all the provided {@link NotEmptyFailureCallback}
     * instances will be called if the check fails.
     *
     * @param failureCallbacks The {@link NotEmptyFailureCallback}s called in case the check fails.
     * @return {@code true} if the value is not empty, {@code false} in all other cases.
     */
    public boolean notEmpty(NotEmptyFailureCallback... failureCallbacks)
    {
        boolean result = !value.isEmpty();
        if (!result) {
            failureCount++;
            for (NotEmptyFailureCallback failureCallback : failureCallbacks)
                failureCallback.notEmptyFailure(this);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not empty.
     * <p>
     * The {@link TextParameterFailureHandler#notEmptyFailure(TextParameter)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @return {@code true} if the value is not empty, {@code false} in all other cases.
     */
    public boolean notEmpty()
    {
        return notEmpty(failureHandlerArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isLength(int)} method
     * check fails.
     */
    @FunctionalInterface public interface IsLengthFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#isLength(int)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#isLength(int)} check method failed.
         * @param check     The length provided to the {@link TextParameter#isLength(int)} check that failed.
         */
        void isLengthFailure(TextParameter parameter, int check);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is empty.
     * <p>
     * The {@link IsLengthFailureCallback#isLengthFailure(TextParameter, int)} method on all the provided {@link IsLengthFailureCallback}
     * instances will be called if the check fails.
     *
     * @param n                The length the internal value of the {@link TextParameter} must be for the check to pass.
     * @param failureCallbacks The {@link IsLengthFailureCallback}s called in case the check fails.
     * @return {@code true} if the value is empty, {@code false} in all other cases.
     */
    public boolean isLength(int n, IsLengthFailureCallback... failureCallbacks)
    {
        boolean result = length == n;
        if (!result) {
            failureCount++;
            for (IsLengthFailureCallback failureCallback : failureCallbacks)
                failureCallback.isLengthFailure(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is of the provided length.
     * <p>
     * The {@link TextParameterFailureHandler#isLengthFailure(TextParameter, int)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must be for the check to pass.
     * @return {@code true} if the value is of the provided length, {@code false} in all other cases.
     */
    public boolean isLength(int n)
    {
        return isLength(n, failureHandlerArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#notLength(int)} method
     * check fails.
     */
    @FunctionalInterface public interface NotLengthFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#notLength(int)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#notLength(int)} check method failed.
         * @param check     The length provided to the {@link TextParameter#notLength(int)} check that failed.
         */
        void notLengthFailure(TextParameter parameter, int check);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not of the provided length.
     * <p>
     * The {@link NotLengthFailureCallback#notLengthFailure(TextParameter, int)} method on all the provided {@link NotLengthFailureCallback}
     * instances will be called if the check fails.
     *
     * @param n                The length the internal value of the {@link TextParameter} must not be for the check to pass.
     * @param failureCallbacks The {@link NotLengthFailureCallback}s called in case the check fails.
     * @return {@code true} if the value is not of the provided length, {@code false} in all other cases.
     */
    public boolean notLength(int n, NotLengthFailureCallback... failureCallbacks)
    {
        boolean result = length != n;
        if (!result) {
            failureCount++;
            for (NotLengthFailureCallback failureCallback : failureCallbacks)
                failureCallback.notLengthFailure(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not of the provided length.
     * <p>
     * The {@link TextParameterFailureHandler#notLengthFailure(TextParameter, int)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must not be for the check to pass.
     * @return {@code true} if the value is not of the provided length, {@code false} in all other cases.
     */
    public boolean notLength(int n)
    {
        return notLength(n, failureHandlerArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isShorterThan(int)} method
     * check fails.
     */
    @FunctionalInterface public interface IsShorterThanFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#isShorterThan(int)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#isShorterThan(int)} check method failed.
         * @param check     The length provided to the {@link TextParameter#isShorterThan(int)} check that failed.
         */
        void isShorterThanFailure(TextParameter parameter, int check);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is shorter than the provided length.
     * <p>
     * The {@link IsShorterThanFailureCallback#isShorterThanFailure(TextParameter, int)} method on all the provided
     * {@link IsShorterThanFailureCallback} instances will be called if the check fails.
     *
     * @param n                The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @param failureCallbacks The {@link IsShorterThanFailureCallback}s called in case the check fails.
     * @return {@code true} if the value is shorter than the provided length, {@code false} in all other cases.
     */
    public boolean isShorterThan(int n, IsShorterThanFailureCallback... failureCallbacks)
    {
        boolean result = length < n;
        if (!result) {
            failureCount++;
            for (IsShorterThanFailureCallback failureCallback : failureCallbacks)
                failureCallback.isShorterThanFailure(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is shorter than the provided length.
     * <p>
     * The {@link TextParameterFailureHandler#isShorterThanFailure(TextParameter, int)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @return {@code true} if the value is shorter than the provided length, {@code false} in all other cases.
     */
    public boolean isShorterThan(int n)
    {
        return isShorterThan(n, failureHandlerArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isLongerThan(int)} method
     * check fails.
     */
    @FunctionalInterface public interface NotShorterThanFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#notShorterThan(int)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#notShorterThan(int)} check method failed.
         * @param check     The length provided to the {@link TextParameter#notShorterThan(int)} check that failed.
         */
        void notShorterThanFailure(TextParameter parameter, int check);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not shorter than the provided length.
     * <p>
     * The {@link NotShorterThanFailureCallback#notShorterThanFailure(TextParameter, int)} method on all the provided
     * {@link NotShorterThanFailureCallback} instances will be called if the check fails.
     *
     * @param n                The length the internal value of the {@link TextParameter} must not be shorter than for the check to pass.
     * @param failureCallbacks The {@link NotShorterThanFailureCallback}s called in case the check fails.
     * @return {@code true} if the value is not shorter than the provided length, {@code false} in all other cases.
     */
    public boolean notShorterThan(int n, NotShorterThanFailureCallback... failureCallbacks)
    {
        boolean result = value.length() >= n;
        if (!result) {
            failureCount++;
            for (NotShorterThanFailureCallback failureCallback : failureCallbacks)
                failureCallback.notShorterThanFailure(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not shorter than the provided length.
     * <p>
     * The {@link TextParameterFailureHandler#notShorterThanFailure(TextParameter, int)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must not be shorter than for the check to pass.
     * @return {@code true} if the value is not shorter than the provided length, {@code false} in all other cases.
     */
    public boolean notShorterThan(int n)
    {
        return notShorterThan(n, failureHandlerArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isLongerThan(int)} method
     * check fails.
     */
    @FunctionalInterface public interface IsLongerThanFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#isLongerThan(int)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#isLongerThan(int)} check method failed.
         * @param check     The length provided to the {@link TextParameter#isLongerThan(int)} check that failed.
         */
        void isLongerThanFailure(TextParameter parameter, int check);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is longer than the provided length.
     * <p>
     * The {@link IsLongerThanFailureCallback#isLongerThanFailure(TextParameter, int)} method on all the provided
     * {@link IsLongerThanFailureCallback} instances will be called if the check fails.
     *
     * @param n                The length the internal value of the {@link TextParameter} must be longer than for the check to pass.
     * @param failureCallbacks The {@link IsLongerThanFailureCallback}s called in case the check fails.
     * @return {@code true} if the length of the value is longer than the provided length, {@code false} in all other cases.
     */
    public boolean isLongerThan(int n, IsLongerThanFailureCallback... failureCallbacks)
    {
        boolean result = value.length() > n;
        if (!result) {
            failureCount++;
            for (IsLongerThanFailureCallback failureCallback : failureCallbacks)
                failureCallback.isLongerThanFailure(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is longer than the provided length.
     * <p>
     * The {@link TextParameterFailureHandler#isLongerThanFailure(TextParameter, int)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @return {@code true} if the value is longer than the provided length, {@code false} in all other cases.
     */
    public boolean isLongerThan(int n)
    {
        return isLongerThan(n, failureHandlerArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#notLongerThan(int)} method
     * check fails.
     */
    @FunctionalInterface public interface NotLongerThanFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#notLongerThan(int)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#notLongerThan(int)} check method failed.
         * @param check     The length provided to the {@link TextParameter#notLongerThan(int)} check that failed.
         */
        void notLongerThanFailure(TextParameter parameter, int check);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not longer than the provided length.
     * <p>
     * The {@link NotLongerThanFailureCallback#notLongerThanFailure(TextParameter, int)} method on all the provided
     * {@link NotLongerThanFailureCallback} instances will be called if the check fails.
     *
     * @param n                The length the internal value of the {@link TextParameter} must not be shorter than for the check to pass.
     * @param failureCallbacks The {@link NotLongerThanFailureCallback}s called in case the check fails.
     * @return {@code true} if the value is not longer than the provided length, {@code false} in all other cases.
     */
    public boolean notLongerThan(int n, NotLongerThanFailureCallback... failureCallbacks)
    {
        boolean result = value.length() <= n;
        if (!result) {
            failureCount++;
            for (NotLongerThanFailureCallback failureCallback : failureCallbacks)
                failureCallback.notLongerThanFailure(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not longer than the provided length.
     * <p>
     * The {@link TextParameterFailureHandler#notLongerThanFailure(TextParameter, int)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @return {@code true} if the value is not longer than the provided length, {@code false} in all other cases.
     */
    public boolean notLongerThan(int n)
    {
        return notLongerThan(n, failureHandlerArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isMatch(Pattern)} method
     * check fails.
     */
    @FunctionalInterface public interface IsMatchFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#isMatch(Pattern)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#isMatch(Pattern)} check method failed.
         * @param pattern   The {@code Pattern} provided to the {@link TextParameter#isMatch(Pattern)} check that failed.
         */
        void isMatchFailure(TextParameter parameter, Pattern pattern);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} matches that of the provided {@code Pattern}.
     * <p>
     * The {@link IsMatchFailureCallback#isMatchFailure(TextParameter, Pattern)} method on all the provided
     * {@link IsMatchFailureCallback} instances will be called if the check fails.
     *
     * @param pattern          The {@code Pattern} the internal value of the {@link TextParameter} must match for the check to pass.
     * @param failureCallbacks The {@link IsMatchFailureCallback}s called in case the check fails.
     * @return {@code true} if the value matches that of the provided {@code Pattern}, {@code false} in all other cases.
     */
    public boolean isMatch(Pattern pattern, IsMatchFailureCallback... failureCallbacks)
    {
        boolean result = pattern.matcher(value).find();
        if (!result) {
            failureCount++;
            for (IsMatchFailureCallback failureCallback : failureCallbacks)
                failureCallback.isMatchFailure(this, pattern);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} matches that of the provided {@code Pattern}.
     * <p>
     * The {@link TextParameterFailureHandler#isMatchFailure(TextParameter, Pattern)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @param pattern The {@code Pattern} the internal value of the {@link TextParameter} must match for the check to pass.
     * @return {@code true} if the value matches that of the provided {@code Pattern}, {@code false} in all other cases.
     */
    public boolean isMatch(Pattern pattern)
    {
        return isMatch(pattern, failureHandlerArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#notMatch(Pattern)} method
     * check fails.
     */
    @FunctionalInterface public interface NotMatchFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#notMatch(Pattern)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#notMatch(Pattern)} check method failed.
         * @param pattern   The {@code Pattern} provided to the {@link TextParameter#notMatch(Pattern)} check that failed.
         */
        void notMatchFailure(TextParameter parameter, Pattern pattern);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} doesn't match that of the provided {@code Pattern}.
     * <p>
     * The {@link NotMatchFailureCallback#notMatchFailure(TextParameter, Pattern)} method on all the provided {@link NotMatchFailureCallback}
     * instances will be called if the check fails.
     *
     * @param pattern          The {@code Pattern} the internal value of the {@link TextParameter} must match for the check to pass.
     * @param failureCallbacks The {@link NotMatchFailureCallback}s called in case the check fails.
     * @return {@code true} if the value doesn't match that of the provided {@code Pattern}, {@code false} in all other cases.
     */
    public boolean notMatch(Pattern pattern, NotMatchFailureCallback... failureCallbacks)
    {
        boolean result = !pattern.matcher(value).find();
        if (!result) {
            failureCount++;
            for (NotMatchFailureCallback failureCallback : failureCallbacks)
                failureCallback.notMatchFailure(this, pattern);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} doesn't match that of the provided {@code Pattern}.
     * <p>
     * The {@link TextParameterFailureHandler#notMatchFailure(TextParameter, Pattern)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @param pattern The {@code Pattern} the internal value of the {@link TextParameter} must match for the check to pass.
     * @return {@code true} if the value doesn't match that of the provided {@code Pattern}, {@code false} in all other cases.
     */
    public boolean notMatch(Pattern pattern)
    {
        return notMatch(pattern, failureHandlerArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isIn(List)} method
     * check fails.
     */
    @FunctionalInterface public interface IsInFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#isIn(List)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#isIn(List)} check method failed.
         * @param patterns  The list of {@code CharSequence} instances provided to the {@link TextParameter#isIn(List)} check that failed.
         */
        void isInFailure(TextParameter parameter, List<? extends CharSequence> patterns);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals one of the provided instances of {@code CharSequence}.
     * <p>
     * The {@link IsInFailureCallback#isInFailure(TextParameter, List)} method on all the provided {@link IsInFailureCallback}
     * instances will be called if the check fails.
     *
     * @param patterns         The list of {@code CharSequence} instances the internal value of the {@link TextParameter} must match for the check to pass.
     * @param failureCallbacks The {@link IsInFailureCallback}s called in case the check fails.
     * @return {@code true} if the value equals at least one of the provided instances of {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean isIn(List<? extends CharSequence> patterns, IsInFailureCallback... failureCallbacks)
    {
        for (CharSequence pattern : patterns) {
            if (value.equals(pattern)) {
                return true;
            }
        }

        failureCount++;
        for (IsInFailureCallback failureCallback : failureCallbacks)
            failureCallback.isInFailure(this, patterns);
        return false;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals one of the provided instances of {@code CharSequence}.
     * <p>
     * The {@link TextParameterFailureHandler#isInFailure(TextParameter, List)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @param patterns The list of {@code CharSequence} instances the internal value of the {@link TextParameter} must match for the check to pass.
     * @return {@code true} if the value equals at least one of the provided instances of {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean isIn(List<? extends CharSequence> patterns)
    {
        return isIn(patterns, failureHandlerArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#notIn(List)} method
     * check fails.
     */
    @FunctionalInterface public interface NotInFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#notIn(List)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#notIn(List)} check method failed.
         * @param patterns  The list of {@code CharSequence} instances provided to the {@link TextParameter#notIn(List)} check that failed.
         * @param collision The index of the {@code CharSequence} in the provided list that caused the check to fail.
         */
        void notInFailure(TextParameter parameter, List<? extends CharSequence> patterns, int collision);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals none of the provided instances of {@code CharSequence}.
     * <p>
     * The {@link NotInFailureCallback#notInFailure(TextParameter, List, int)} method on all the provided {@link NotInFailureCallback}
     * instances will be called if the check fails.
     *
     * @param patterns         The list of {@code CharSequence} instances the internal value of the {@link TextParameter} must not match for the check to pass.
     * @param failureCallbacks The {@link NotInFailureCallback}s called in case the check fails.
     * @return {@code true} if the value equals none of the provided instances of {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean notIn(List<? extends CharSequence> patterns, NotInFailureCallback... failureCallbacks)
    {
        int size = patterns.size();
        for (int x = 0; x < size; x++) {
            if (patterns.get(x).equals(value)) {
                failureCount++;
                for (NotInFailureCallback failureCallback : failureCallbacks)
                    failureCallback.notInFailure(this, patterns, x);
                return false;
            }
        }

        return true;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals none of the provided instances of {@code CharSequence}.
     * <p>
     * The {@link TextParameterFailureHandler#notInFailure(TextParameter, List, int)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @param patterns The list of {@code CharSequence} instances the internal value of the {@link TextParameter} must not match for the check to pass.
     * @return {@code true} if the value equals none of the provided instances of {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean notIn(List<? extends CharSequence> patterns)
    {
        return notIn(patterns, failureHandlerArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isContained(CharSequence)} method
     * check fails.
     */
    @FunctionalInterface public interface IsContainedFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#isContained(CharSequence)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#isContained(CharSequence)} check method failed.
         * @param other     The {@code CharSequence} provided to the {@link TextParameter#isContained(CharSequence)} check that failed.
         */
        void isContainedFailure(TextParameter parameter, CharSequence other);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} isContained the provided {@code CharSequence}.
     * <p>
     * The {@link IsContainedFailureCallback#isContainedFailure(TextParameter, CharSequence)} method on all the provided
     * {@link IsContainedFailureCallback} instances will be called if the check fails.
     *
     * @param other            The other {@code CharSequence} instance the internal value of the {@link TextParameter} must contain for the check to pass.
     * @param failureCallbacks The {@link IsContainedFailureCallback}s called in case the check fails.
     * @return {@code true} if the value isContained the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean isContained(CharSequence other, IsContainedFailureCallback... failureCallbacks)
    {
        boolean result = this.value.contains(other);
        if (!result) {
            failureCount++;
            for (IsContainedFailureCallback failureCallback : failureCallbacks)
                failureCallback.isContainedFailure(this, other);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} isContained the provided {@code CharSequence}.
     * <p>
     * The {@link TextParameterFailureHandler#isContainedFailure(TextParameter, CharSequence)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @param other The other {@code CharSequence} instance the internal value of the {@link TextParameter} must contain for the check to pass.
     * @return {@code true} if the value isContained the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean isContained(CharSequence other)
    {
        return isContained(other, failureHandlerArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#notContained(CharSequence)} method
     * check fails.
     */
    @FunctionalInterface public interface NotContainedFailureCallback
    {

        /**
         * The method called when the {@link TextParameter#notContained(CharSequence)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#notContained(CharSequence)} check method failed.
         * @param other     The {@code CharSequence} provided to the {@link TextParameter#notContained(CharSequence)} check that failed.
         */
        void notContainedFailure(TextParameter parameter, CharSequence other);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} doesn't contain the provided {@code CharSequence}.
     * <p>
     * The {@link NotContainedFailureCallback#notContainedFailure(TextParameter, CharSequence)} method on all the provided
     * {@link NotContainedFailureCallback} instances will be called if the check fails.
     *
     * @param other            The other {@code CharSequence} instance the internal value of the {@link TextParameter} must not contain for the check to pass.
     * @param failureCallbacks The {@link NotContainedFailureCallback}s called in case the check fails.
     * @return {@code true} if the value doesn't contain the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean notContained(CharSequence other, NotContainedFailureCallback... failureCallbacks)
    {
        boolean result = !value.contains(other);
        if (!result) {
            failureCount++;
            for (NotContainedFailureCallback failureCallback : failureCallbacks)
                failureCallback.notContainedFailure(this, other);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} does not contain of the provided instances of {@code CharSequence}.
     * <p>
     * The {@link TextParameterFailureHandler#notContainedFailure(TextParameter, CharSequence)} methods on the instances of
     * {@link TextParameterFailureHandler} registered with this object will be called if the check fails.
     *
     * @param other The other {@code CharSequence} instance the internal value of the {@link TextParameter} must not contain for the check to pass.
     * @return {@code true} if the value doesn't contain the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean notContained(CharSequence other)
    {
        return notContained(other, failureHandlerArray);
    }

    /**
     * Returns the internal value of the {@link TextParameter}.
     *
     * @return The internal value of the {@link TextParameter}.
     */
    public String getValue()
    {
        return this.value;
    }

    /**
     * Returns the number of failures that occurred while performing checks on this {@link Parameter}.
     *
     * @return The number of failures.
     */
    @Override public int getFailureCount()
    {
        return failureCount;
    }
}
