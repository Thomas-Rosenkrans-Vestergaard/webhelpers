package tvestergaard.webhelpers.parameters;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class TextParameter extends AbstractParameter
{

    /**
     * The value of the {@link Parameter}.
     */
    private final String value;

    /**
     * The isLength of the value in the {@link Parameter}.
     */
    protected final int length;

    /**
     * An {@link ErrorHandlerList} containing the {@link TextParameterErrorHandler} instances that must be notified of
     * validation errors.
     */
    private final List<TextParameterErrorHandler> errorHandlers;

    /**
     * An array containing the {@link TextParameterErrorHandler} instances that must be notified of validation errors.
     */
    private final TextParameterErrorHandler[] errorHandlersArray;

    /**
     * The number of errors that occurred while performing checks.
     */
    private int errorCount = 0;

    /**
     * Creates a new {@link TextParameter}.
     *
     * @param name         The name of the {@link TextParameter}.
     * @param value        The value of the {@link TextParameter}.
     * @param errorHandler The {@link TextParameterErrorHandler} instances used as callbacks for when checks fails.
     */
    public TextParameter(String name, String value, List<TextParameterErrorHandler> errorHandler)
    {
        super(name);

        this.value = value;
        this.length = value.length();
        this.errorHandlers = errorHandler;
        this.errorHandlersArray = this.errorHandlers.toArray(new TextParameterErrorHandler[this.errorHandlers.size()]);
    }

    /**
     * Creates a new {@link TextParameter}.
     *
     * @param name
     * @param value
     * @param errorHandler
     */
    public TextParameter(String name, String value, TextParameterErrorHandler... errorHandler)
    {
        this(name, value, Arrays.asList(errorHandler));
    }

    /**
     * Creates a new {@link TextParameter} without any errorHandler for check onErrors.
     *
     * @param name  The name of the {@link TextParameter}.
     * @param value The value of the {@link TextParameter}.
     */
    public TextParameter(String name, String value)
    {
        this(name, value, new TextParameterErrorHandler()
        {

        });
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#is(CharSequence)} method
     * check fails.
     */
    @FunctionalInterface public interface IsErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#is(CharSequence)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#is(CharSequence)} check method failed.
         * @param other     The {@code CharSequence} provided to the {@link TextParameter#is(CharSequence)}.
         */
        void isError(TextParameter parameter, CharSequence other);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals the provided {@code CharSequence}.
     * <p>
     * The {@link IsErrorCallback#isError(TextParameter, CharSequence)} method on all the provided {@link IsErrorCallback}
     * instances will be called if the check fails. The {@link TextParameterErrorHandler#isError(TextParameter, CharSequence)}
     * methods on the instances of {@link TextParameterErrorHandler} provided to this object will not be called.
     *
     * @param other          The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @param errorCallbacks The {@link IsErrorCallback}s called in case the check fails.
     * @return {@code true} if the value equals the provided {code CharSequence}, {@code false} in all other cases.
     */
    public boolean is(CharSequence other, IsErrorCallback... errorCallbacks)
    {
        boolean result = other.equals(value);
        if (!result) {
            errorCount++;
            for (IsErrorCallback errorCallback : errorCallbacks) {
                errorCallback.isError(this, other);
            }
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals the provided {@code CharSequence}.
     * <p>
     * The {@link TextParameterErrorHandler#isError(TextParameter, CharSequence)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @param other The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @return {@code true} if the value equals the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean is(CharSequence other)
    {
        return is(other, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#not(CharSequence)} method
     * check fails.
     */
    @FunctionalInterface public interface NotErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#not(CharSequence)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#not(CharSequence)} check method failed.
         * @param other     The {@code CharSequence} provided to the {@link TextParameter#not(CharSequence)}.
         */
        void notError(TextParameter parameter, CharSequence other);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} does not equal the provided {@code CharSequence}.
     * <p>
     * The {@link NotErrorCallback#notError(TextParameter, CharSequence)} method on all the provided {@link NotErrorCallback}
     * instances will be called if the check fails.
     *
     * @param other          The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @param errorCallbacks The {@link NotErrorCallback}s called in case the check fails.
     * @return {@code true} if the value does not equal the provided {code CharSequence}, {@code false} in all other cases.
     */
    public boolean not(CharSequence other, NotErrorCallback... errorCallbacks)
    {
        boolean result = !other.equals(this.value);
        if (!result) {
            errorCount++;
            for (NotErrorCallback errorCallback : errorCallbacks)
                errorCallback.notError(this, other);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} does not equal the provided {@code CharSequence}.
     * <p>
     * The {@link TextParameterErrorHandler#notError(TextParameter, CharSequence)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @param other The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @return {@code true} if the value does not equal the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean not(CharSequence other)
    {
        return not(other, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isEmpty()} method
     * check fails.
     */
    @FunctionalInterface public interface IsEmptyErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#isEmpty()} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#isEmpty()} check method failed.
         */
        void isEmptyError(TextParameter parameter);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is empty.
     * <p>
     * The {@link IsEmptyErrorCallback#isEmptyError(TextParameter)} method on all the provided {@link IsEmptyErrorCallback}
     * instances will be called if the check fails.
     *
     * @param errorCallbacks The {@link IsEmptyErrorCallback}s called in case the check fails.
     * @return {@code true} if the value is empty, {@code false} in all other cases.
     */
    public boolean isEmpty(IsEmptyErrorCallback... errorCallbacks)
    {
        boolean result = value.isEmpty();
        if (!result) {
            errorCount++;
            for (IsEmptyErrorCallback errorCallback : errorCallbacks)
                errorCallback.isEmptyError(this);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is empty.
     * <p>
     * The {@link TextParameterErrorHandler#isEmptyError(TextParameter)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @return {@code true} if the value is empty, {@code false} in all other cases.
     */
    public boolean isEmpty()
    {
        return isEmpty(errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#notEmpty()} method
     * check fails.
     */
    @FunctionalInterface public interface NotEmptyErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#notEmpty()} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#notEmpty()} check method failed.
         */
        void notEmptyError(TextParameter parameter);
    }


    /**
     * Checks that the internal value of the {@link TextParameter} is not empty.
     * <p>
     * The {@link NotEmptyErrorCallback#notEmptyError(TextParameter)} method on all the provided {@link NotEmptyErrorCallback}
     * instances will be called if the check fails.
     *
     * @param errorCallbacks The {@link NotEmptyErrorCallback}s called in case the check fails.
     * @return {@code true} if the value is not empty, {@code false} in all other cases.
     */
    public boolean notEmpty(NotEmptyErrorCallback... errorCallbacks)
    {
        boolean result = !value.isEmpty();
        if (!result) {
            errorCount++;
            for (NotEmptyErrorCallback errorCallback : errorCallbacks)
                errorCallback.notEmptyError(this);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not empty.
     * <p>
     * The {@link TextParameterErrorHandler#notEmptyError(TextParameter)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @return {@code true} if the value is not empty, {@code false} in all other cases.
     */
    public boolean notEmpty()
    {
        return notEmpty(errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isLength(int)} method
     * check fails.
     */
    @FunctionalInterface public interface IsLengthErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#isLength(int)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#isLength(int)} check method failed.
         * @param check     The length provided to the {@link TextParameter#isLength(int)} check that failed.
         */
        void isLengthError(TextParameter parameter, int check);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is empty.
     * <p>
     * The {@link IsLengthErrorCallback#isLengthError(TextParameter, int)} method on all the provided {@link IsLengthErrorCallback}
     * instances will be called if the check fails.
     *
     * @param n              The length the internal value of the {@link TextParameter} must be for the check to pass.
     * @param errorCallbacks The {@link IsLengthErrorCallback}s called in case the check fails.
     * @return {@code true} if the value is empty, {@code false} in all other cases.
     */
    public boolean isLength(int n, IsLengthErrorCallback... errorCallbacks)
    {
        boolean result = length == n;
        if (!result) {
            errorCount++;
            for (IsLengthErrorCallback errorCallback : errorCallbacks)
                errorCallback.isLengthError(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is of the provided length.
     * <p>
     * The {@link TextParameterErrorHandler#isLengthError(TextParameter, int)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must be for the check to pass.
     * @return {@code true} if the value is of the provided length, {@code false} in all other cases.
     */
    public boolean isLength(int n)
    {
        return isLength(n, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#notLength(int)} method
     * check fails.
     */
    @FunctionalInterface public interface NotLengthErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#notLength(int)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#notLength(int)} check method failed.
         * @param check     The length provided to the {@link TextParameter#notLength(int)} check that failed.
         */
        void notLengthError(TextParameter parameter, int check);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not of the provided length.
     * <p>
     * The {@link NotLengthErrorCallback#notLengthError(TextParameter, int)} method on all the provided {@link NotLengthErrorCallback}
     * instances will be called if the check fails.
     *
     * @param n              The length the internal value of the {@link TextParameter} must not be for the check to pass.
     * @param errorCallbacks The {@link NotLengthErrorCallback}s called in case the check fails.
     * @return {@code true} if the value is not of the provided length, {@code false} in all other cases.
     */
    public boolean notLength(int n, NotLengthErrorCallback... errorCallbacks)
    {
        boolean result = length != n;
        if (!result) {
            errorCount++;
            for (NotLengthErrorCallback errorCallback : errorCallbacks)
                errorCallback.notLengthError(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not of the provided length.
     * <p>
     * The {@link TextParameterErrorHandler#notLengthError(TextParameter, int)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must not be for the check to pass.
     * @return {@code true} if the value is not of the provided length, {@code false} in all other cases.
     */
    public boolean notLength(int n)
    {
        return notLength(n, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isShorterThan(int)} method
     * check fails.
     */
    @FunctionalInterface public interface IsShorterThanErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#isShorterThan(int)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#isShorterThan(int)} check method failed.
         * @param check     The length provided to the {@link TextParameter#isShorterThan(int)} check that failed.
         */
        void isShorterThanError(TextParameter parameter, int check);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is shorter than the provided length.
     * <p>
     * The {@link IsShorterThanErrorCallback#isShorterThanError(TextParameter, int)} method on all the provided
     * {@link IsShorterThanErrorCallback} instances will be called if the check fails.
     *
     * @param n              The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @param errorCallbacks The {@link IsShorterThanErrorCallback}s called in case the check fails.
     * @return {@code true} if the value is shorter than the provided length, {@code false} in all other cases.
     */
    public boolean isShorterThan(int n, IsShorterThanErrorCallback... errorCallbacks)
    {
        boolean result = length < n;
        if (!result) {
            errorCount++;
            for (IsShorterThanErrorCallback errorCallback : errorCallbacks)
                errorCallback.isShorterThanError(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is shorter than the provided length.
     * <p>
     * The {@link TextParameterErrorHandler#isShorterThanError(TextParameter, int)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @return {@code true} if the value is shorter than the provided length, {@code false} in all other cases.
     */
    public boolean isShorterThan(int n)
    {
        return isShorterThan(n, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isLongerThan(int)} method
     * check fails.
     */
    @FunctionalInterface public interface NotShorterThanErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#notShorterThan(int)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#notShorterThan(int)} check method failed.
         * @param check     The length provided to the {@link TextParameter#notShorterThan(int)} check that failed.
         */
        void notShorterThanError(TextParameter parameter, int check);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not shorter than the provided length.
     * <p>
     * The {@link NotShorterThanErrorCallback#notShorterThanError(TextParameter, int)} method on all the provided
     * {@link NotShorterThanErrorCallback} instances will be called if the check fails.
     *
     * @param n              The length the internal value of the {@link TextParameter} must not be shorter than for the check to pass.
     * @param errorCallbacks The {@link NotShorterThanErrorCallback}s called in case the check fails.
     * @return {@code true} if the value is not shorter than the provided length, {@code false} in all other cases.
     */
    public boolean notShorterThan(int n, NotShorterThanErrorCallback... errorCallbacks)
    {
        boolean result = value.length() >= n;
        if (!result) {
            errorCount++;
            for (NotShorterThanErrorCallback errorCallback : errorCallbacks)
                errorCallback.notShorterThanError(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not shorter than the provided length.
     * <p>
     * The {@link TextParameterErrorHandler#notShorterThanError(TextParameter, int)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must not be shorter than for the check to pass.
     * @return {@code true} if the value is not shorter than the provided length, {@code false} in all other cases.
     */
    public boolean notShorterThan(int n)
    {
        return notShorterThan(n, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isLongerThan(int)} method
     * check fails.
     */
    @FunctionalInterface public interface IsLongerThanErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#isLongerThan(int)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#isLongerThan(int)} check method failed.
         * @param check     The length provided to the {@link TextParameter#isLongerThan(int)} check that failed.
         */
        void isLongerThanError(TextParameter parameter, int check);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is longer than the provided length.
     * <p>
     * The {@link IsLongerThanErrorCallback#isLongerThanError(TextParameter, int)} method on all the provided
     * {@link IsLongerThanErrorCallback} instances will be called if the check fails.
     *
     * @param n              The length the internal value of the {@link TextParameter} must be longer than for the check to pass.
     * @param errorCallbacks The {@link IsLongerThanErrorCallback}s called in case the check fails.
     * @return {@code true} if the length of the value is longer than the provided length, {@code false} in all other cases.
     */
    public boolean isLongerThan(int n, IsLongerThanErrorCallback... errorCallbacks)
    {
        boolean result = value.length() > n;
        if (!result) {
            errorCount++;
            for (IsLongerThanErrorCallback errorCallback : errorCallbacks)
                errorCallback.isLongerThanError(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is longer than the provided length.
     * <p>
     * The {@link TextParameterErrorHandler#isLongerThanError(TextParameter, int)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @return {@code true} if the value is longer than the provided length, {@code false} in all other cases.
     */
    public boolean isLongerThan(int n)
    {
        return isLongerThan(n, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#notLongerThan(int)} method
     * check fails.
     */
    @FunctionalInterface public interface NotLongerThanErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#notLongerThan(int)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#notLongerThan(int)} check method failed.
         * @param check     The length provided to the {@link TextParameter#notLongerThan(int)} check that failed.
         */
        void notLongerThanError(TextParameter parameter, int check);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not longer than the provided length.
     * <p>
     * The {@link NotLongerThanErrorCallback#notLongerThanError(TextParameter, int)} method on all the provided
     * {@link NotLongerThanErrorCallback} instances will be called if the check fails.
     *
     * @param n              The length the internal value of the {@link TextParameter} must not be shorter than for the check to pass.
     * @param errorCallbacks The {@link NotLongerThanErrorCallback}s called in case the check fails.
     * @return {@code true} if the value is not longer than the provided length, {@code false} in all other cases.
     */
    public boolean notLongerThan(int n, NotLongerThanErrorCallback... errorCallbacks)
    {
        boolean result = value.length() <= n;
        if (!result) {
            errorCount++;
            for (NotLongerThanErrorCallback errorCallback : errorCallbacks)
                errorCallback.notLongerThanError(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not longer than the provided length.
     * <p>
     * The {@link TextParameterErrorHandler#notLongerThanError(TextParameter, int)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @return {@code true} if the value is not longer than the provided length, {@code false} in all other cases.
     */
    public boolean notLongerThan(int n)
    {
        return notLongerThan(n, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isMatch(Pattern)} method
     * check fails.
     */
    @FunctionalInterface public interface IsMatchErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#isMatch(Pattern)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#isMatch(Pattern)} check method failed.
         * @param pattern   The {@code Pattern} provided to the {@link TextParameter#isMatch(Pattern)} check that failed.
         */
        void isMatchError(TextParameter parameter, Pattern pattern);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} matches that of the provided {@code Pattern}.
     * <p>
     * The {@link IsMatchErrorCallback#isMatchError(TextParameter, Pattern)} method on all the provided
     * {@link IsMatchErrorCallback} instances will be called if the check fails.
     *
     * @param pattern        The {@code Pattern} the internal value of the {@link TextParameter} must match for the check to pass.
     * @param errorCallbacks The {@link IsMatchErrorCallback}s called in case the check fails.
     * @return {@code true} if the value matches that of the provided {@code Pattern}, {@code false} in all other cases.
     */
    public boolean isMatch(Pattern pattern, IsMatchErrorCallback... errorCallbacks)
    {
        boolean result = pattern.matcher(value).find();
        if (!result) {
            errorCount++;
            for (IsMatchErrorCallback errorCallback : errorCallbacks)
                errorCallback.isMatchError(this, pattern);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} matches that of the provided {@code Pattern}.
     * <p>
     * The {@link TextParameterErrorHandler#isMatchError(TextParameter, Pattern)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @param pattern The {@code Pattern} the internal value of the {@link TextParameter} must match for the check to pass.
     * @return {@code true} if the value matches that of the provided {@code Pattern}, {@code false} in all other cases.
     */
    public boolean isMatch(Pattern pattern)
    {
        return isMatch(pattern, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#notMatch(Pattern)} method
     * check fails.
     */
    @FunctionalInterface public interface NotMatchErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#notMatch(Pattern)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#notMatch(Pattern)} check method failed.
         * @param pattern   The {@code Pattern} provided to the {@link TextParameter#notMatch(Pattern)} check that failed.
         */
        void notMatchError(TextParameter parameter, Pattern pattern);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} doesn't match that of the provided {@code Pattern}.
     * <p>
     * The {@link NotMatchErrorCallback#notMatchError(TextParameter, Pattern)} method on all the provided {@link NotMatchErrorCallback}
     * instances will be called if the check fails.
     *
     * @param pattern        The {@code Pattern} the internal value of the {@link TextParameter} must match for the check to pass.
     * @param errorCallbacks The {@link NotMatchErrorCallback}s called in case the check fails.
     * @return {@code true} if the value doesn't match that of the provided {@code Pattern}, {@code false} in all other cases.
     */
    public boolean notMatch(Pattern pattern, NotMatchErrorCallback... errorCallbacks)
    {
        boolean result = !pattern.matcher(value).find();
        if (!result) {
            errorCount++;
            for (NotMatchErrorCallback errorCallback : errorCallbacks)
                errorCallback.notMatchError(this, pattern);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} doesn't match that of the provided {@code Pattern}.
     * <p>
     * The {@link TextParameterErrorHandler#notMatchError(TextParameter, Pattern)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @param pattern The {@code Pattern} the internal value of the {@link TextParameter} must match for the check to pass.
     * @return {@code true} if the value doesn't match that of the provided {@code Pattern}, {@code false} in all other cases.
     */
    public boolean notMatch(Pattern pattern)
    {
        return notMatch(pattern, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isIn(List)} method
     * check fails.
     */
    @FunctionalInterface public interface IsInErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#isIn(List)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#isIn(List)} check method failed.
         * @param patterns  The list of {@code CharSequence} instances provided to the {@link TextParameter#isIn(List)} check that failed.
         */
        void isInError(TextParameter parameter, List<? extends CharSequence> patterns);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals one of the provided instances of {@code CharSequence}.
     * <p>
     * The {@link IsInErrorCallback#isInError(TextParameter, List)} method on all the provided {@link IsInErrorCallback}
     * instances will be called if the check fails.
     *
     * @param patterns       The list of {@code CharSequence} instances the internal value of the {@link TextParameter} must match for the check to pass.
     * @param errorCallbacks The {@link IsInErrorCallback}s called in case the check fails.
     * @return {@code true} if the value equals at least one of the provided instances of {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean isIn(List<? extends CharSequence> patterns, IsInErrorCallback... errorCallbacks)
    {
        for (CharSequence pattern : patterns) {
            if (value.equals(pattern)) {
                return true;
            }
        }

        errorCount++;
        for (IsInErrorCallback errorCallback : errorCallbacks)
            errorCallback.isInError(this, patterns);
        return false;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals one of the provided instances of {@code CharSequence}.
     * <p>
     * The {@link TextParameterErrorHandler#isInError(TextParameter, List)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @param patterns The list of {@code CharSequence} instances the internal value of the {@link TextParameter} must match for the check to pass.
     * @return {@code true} if the value equals at least one of the provided instances of {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean isIn(List<? extends CharSequence> patterns)
    {
        return isIn(patterns, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#notIn(List)} method
     * check fails.
     */
    @FunctionalInterface public interface NotInErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#notIn(List)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#notIn(List)} check method failed.
         * @param patterns  The list of {@code CharSequence} instances provided to the {@link TextParameter#notIn(List)} check that failed.
         * @param collision The index of the {@code CharSequence} in the provided list that caused the check to fail.
         */
        void notInError(TextParameter parameter, List<? extends CharSequence> patterns, int collision);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals none of the provided instances of {@code CharSequence}.
     * <p>
     * The {@link NotInErrorCallback#notInError(TextParameter, List, int)} method on all the provided {@link NotInErrorCallback}
     * instances will be called if the check fails.
     *
     * @param patterns       The list of {@code CharSequence} instances the internal value of the {@link TextParameter} must not match for the check to pass.
     * @param errorCallbacks The {@link NotInErrorCallback}s called in case the check fails.
     * @return {@code true} if the value equals none of the provided instances of {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean notIn(List<? extends CharSequence> patterns, NotInErrorCallback... errorCallbacks)
    {
        int size = patterns.size();
        for (int x = 0; x < size; x++) {
            if (patterns.get(x).equals(value)) {
                errorCount++;
                for (NotInErrorCallback errorCallback : errorCallbacks)
                    errorCallback.notInError(this, patterns, x);
                return false;
            }
        }

        return true;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals none of the provided instances of {@code CharSequence}.
     * <p>
     * The {@link TextParameterErrorHandler#notInError(TextParameter, List, int)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @param patterns The list of {@code CharSequence} instances the internal value of the {@link TextParameter} must not match for the check to pass.
     * @return {@code true} if the value equals none of the provided instances of {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean notIn(List<? extends CharSequence> patterns)
    {
        return notIn(patterns, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#isContained(CharSequence)} method
     * check fails.
     */
    @FunctionalInterface public interface IsContainedErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#isContained(CharSequence)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#isContained(CharSequence)} check method failed.
         * @param other     The {@code CharSequence} provided to the {@link TextParameter#isContained(CharSequence)} check that failed.
         */
        void isContainedError(TextParameter parameter, CharSequence other);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} isContained the provided {@code CharSequence}.
     * <p>
     * The {@link IsContainedErrorCallback#isContainedError(TextParameter, CharSequence)} method on all the provided
     * {@link IsContainedErrorCallback} instances will be called if the check fails.
     *
     * @param other          The other {@code CharSequence} instance the internal value of the {@link TextParameter} must contain for the check to pass.
     * @param errorCallbacks The {@link IsContainedErrorCallback}s called in case the check fails.
     * @return {@code true} if the value isContained the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean isContained(CharSequence other, IsContainedErrorCallback... errorCallbacks)
    {
        boolean result = this.value.contains(other);
        if (!result) {
            errorCount++;
            for (IsContainedErrorCallback errorCallback : errorCallbacks)
                errorCallback.isContainedError(this, other);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} isContained the provided {@code CharSequence}.
     * <p>
     * The {@link TextParameterErrorHandler#isContainedError(TextParameter, CharSequence)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @param other The other {@code CharSequence} instance the internal value of the {@link TextParameter} must contain for the check to pass.
     * @return {@code true} if the value isContained the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean isContained(CharSequence other)
    {
        return isContained(other, errorHandlersArray);
    }

    /**
     * The functional interface defining the routine notified when the {@link TextParameter#notContained(CharSequence)} method
     * check fails.
     */
    @FunctionalInterface public interface NotContainedErrorCallback
    {

        /**
         * The method called when the {@link TextParameter#notContained(CharSequence)} check method fails.
         *
         * @param parameter The {@link TextParameter} on which the {@link TextParameter#notContained(CharSequence)} check method failed.
         * @param other     The {@code CharSequence} provided to the {@link TextParameter#notContained(CharSequence)} check that failed.
         */
        void notContainedError(TextParameter parameter, CharSequence other);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} doesn't contain the provided {@code CharSequence}.
     * <p>
     * The {@link NotContainedErrorCallback#notContainedError(TextParameter, CharSequence)} method on all the provided
     * {@link NotContainedErrorCallback} instances will be called if the check fails.
     *
     * @param other          The other {@code CharSequence} instance the internal value of the {@link TextParameter} must not contain for the check to pass.
     * @param errorCallbacks The {@link NotContainedErrorCallback}s called in case the check fails.
     * @return {@code true} if the value doesn't contain the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean notContained(CharSequence other, NotContainedErrorCallback... errorCallbacks)
    {
        boolean result = !value.contains(other);
        if (!result) {
            errorCount++;
            for (NotContainedErrorCallback errorCallback : errorCallbacks)
                errorCallback.notContainedError(this, other);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} does not contain of the provided instances of {@code CharSequence}.
     * <p>
     * The {@link TextParameterErrorHandler#notContainedError(TextParameter, CharSequence)} methods on the instances of
     * {@link TextParameterErrorHandler} registered with this object will be called if the check fails.
     *
     * @param other The other {@code CharSequence} instance the internal value of the {@link TextParameter} must not contain for the check to pass.
     * @return {@code true} if the value doesn't contain the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean notContained(CharSequence other)
    {
        return notContained(other, errorHandlersArray);
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
     * Returns the number of errors that occurred while performing checks on this {@link Parameter}.
     *
     * @return The number of errors.
     */
    @Override public int getErrorCount()
    {
        return errorCount;
    }
}
