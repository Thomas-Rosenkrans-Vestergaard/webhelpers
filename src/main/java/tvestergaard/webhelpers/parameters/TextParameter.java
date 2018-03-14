package tvestergaard.webhelpers.parameters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParameter<N> extends ComparableParameter<N, String>
{

    /**
     * The {@link FailureHandler} instances that will be notified of validation failures.
     */
    private final Iterable<? extends FailureHandler<N>> failureHandlers;

    /**
     * Creates a new {@link TextParameter}.
     *
     * @param name       The name of the {@link TextParameter}.
     * @param value      The value of the {@link TextParameter}.
     * @param onFailures The failure handlers to register with the {@link TextParameter}.
     */
    public TextParameter(N name, String value, Iterable<? extends FailureHandler<N>> onFailures)
    {
        super(name, value, onFailures);

        this.failureHandlers = onFailures;
    }

    /**
     * Functional interface for {@code isEmpty} check failure handler.
     *
     * @param <N> The type of the name of the {@link TextParameter} on which the {@code isEmpty} check failed.
     *
     * @see TextParameter#isEmpty()
     * @see TextParameter#isEmpty(IsEmptyFailureCallback)
     * @see TextParameter#isEmpty(Iterable)
     */
    @FunctionalInterface public interface IsEmptyFailureCallback<N>
    {

        /**
         * Notifies the {@link IsEmptyFailureCallback} that an {@code isEmpty} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code isEmpty} check failed.
         *
         * @see TextParameter#isEmpty()
         * @see TextParameter#isEmpty(IsEmptyFailureCallback)
         * @see TextParameter#isEmpty(Iterable)
         */
        void isEmptyFailure(TextParameter<N> parameter);
    }

    /**
     * Checks that the value in the {@link TextParameter} has length {@code 0}. The value in the {@link TextParameter} must
     * not be {@code null}.
     *
     * @param failureCallbacks The failure callbacks to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isEmpty(Iterable<? extends IsEmptyFailureCallback<N>> failureCallbacks) throws NullParameterValueException
    {
        nullCheck();

        boolean result = value.isEmpty();
        if (!result) {
            incrementFailureCount();
            for (IsEmptyFailureCallback<N> failureCallback : failureCallbacks)
                failureCallback.isEmptyFailure(this);
        }

        return result;
    }

    /**
     * Checks that the value in the {@link TextParameter} has length {@code 0}. The value in the {@link TextParameter} must
     * not be {@code null}.
     *
     * @param failureCallback The failure callback to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isEmpty(IsEmptyFailureCallback<N> failureCallback) throws NullParameterValueException
    {
        nullCheck();

        boolean result = value.isEmpty();
        if (!result) {
            incrementFailureCount();
            failureCallback.isEmptyFailure(this);
        }

        return result;
    }

    /**
     * Checks that the value in the {@link TextParameter} has length {@code 0}. The value in the {@link TextParameter} must
     * not be {@code null}.
     * <p>
     * Notifies the {@link TextParameter.FailureHandler} instances provided to this object in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isEmpty()
    {
        return isEmpty(failureHandlers);
    }

    /**
     * Functional interface for {@code notEmpty} check failure handler.
     *
     * @param <N> The type of the name of the {@link TextParameter} on which the {@code notEmpty} check failed.
     *
     * @see TextParameter#notEmpty()
     * @see TextParameter#notEmpty(NotEmptyFailureCallback)
     * @see TextParameter#notEmpty(Iterable)
     */
    @FunctionalInterface public interface NotEmptyFailureCallback<N>
    {

        /**
         * Notifies this object that a {@code notEmpty} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code notEmpty} check failed.
         *
         * @see TextParameter#notEmpty()
         * @see TextParameter#notEmpty(NotEmptyFailureCallback)
         * @see TextParameter#notEmpty(Iterable)
         */
        void notEmptyFailure(TextParameter<N> parameter);
    }

    /**
     * Checks that the value in the {@link TextParameter} is not of length 0.
     *
     * @param failureCallbacks The failure callbacks to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notEmpty(Iterable<? extends NotEmptyFailureCallback<N>> failureCallbacks) throws NullParameterValueException
    {
        nullCheck();

        boolean result = !value.isEmpty();
        if (!result) {
            incrementFailureCount();
            for (NotEmptyFailureCallback<N> failureCallback : failureCallbacks)
                failureCallback.notEmptyFailure(this);
        }

        return result;
    }

    /**
     * Checks that the value in the {@link TextParameter} is not of length 0.
     *
     * @param failureCallback The failure callback to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notEmpty(NotEmptyFailureCallback<N> failureCallback) throws NullParameterValueException
    {
        nullCheck();

        boolean result = !value.isEmpty();
        if (!result) {
            incrementFailureCount();
            failureCallback.notEmptyFailure(this);
        }

        return result;
    }

    /**
     * Checks that the value in the {@link TextParameter} is not of length 0.
     * <p>
     * Notifies the {@link TextParameter.FailureHandler} instances provided to this object in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notEmpty() throws NullParameterValueException
    {
        return notEmpty(failureHandlers);
    }

    /**
     * Functional interface for {@code isLength} check failure handler.
     *
     * @param <N> The type of the name of the {@link TextParameter} on which the {@code isLength} check failed.
     *
     * @see TextParameter#isLength(int)
     * @see TextParameter#isLength(int, IsLengthFailureCallback)
     * @see TextParameter#isLength(int, Iterable)
     */
    @FunctionalInterface public interface IsLengthFailureCallback<N>
    {

        /**
         * Notifies this object that a {@code isLength} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code isLength} check failed.
         * @param check     The length provided to the {@code isLength} check that failed.
         *
         * @see TextParameter#isLength(int)
         * @see TextParameter#isLength(int, IsLengthFailureCallback)
         * @see TextParameter#isLength(int, Iterable)
         */
        void isLengthFailure(TextParameter<N> parameter, int check);
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} equals the provided {@code length}.
     *
     * @param length           The integer value the value in the {@link TextParameter} must equal for the check to pass.
     * @param failureCallbacks The failure callbacks to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isLength(int length, Iterable<? extends IsLengthFailureCallback<N>> failureCallbacks)
            throws NullParameterValueException
    {
        nullCheck();

        boolean result = value.length() == length;
        if (!result) {
            incrementFailureCount();
            for (IsLengthFailureCallback<N> failureCallback : failureCallbacks)
                failureCallback.isLengthFailure(this, length);
        }

        return result;
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} equals the provided {@code length}.
     *
     * @param length          The integer value the value in the {@link TextParameter} must equal for the check to pass.
     * @param failureCallback The failure callback to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isLength(int length, IsLengthFailureCallback<N> failureCallback) throws NullParameterValueException
    {
        nullCheck();

        boolean result = value.length() == length;
        if (!result) {
            incrementFailureCount();
            failureCallback.isLengthFailure(this, length);
        }

        return result;
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} equals the provided {@code length}.
     * <p>
     * Notifies the {@link TextParameter.FailureHandler} instances provided to this object in case the check fails.
     *
     * @param length The integer value the value in the {@link TextParameter} must equal for the check to pass.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isLength(int length) throws NullParameterValueException
    {
        return isLength(length, failureHandlers);
    }

    /**
     * Functional interface for {@code notLength} check failure handler.
     *
     * @param <N> The type of the name of the {@link TextParameter} on which the {@code notLength} check failed.
     *
     * @see TextParameter#notLength(int)
     * @see TextParameter#notLength(int, NotLengthFailureCallback)
     * @see TextParameter#notLength(int, Iterable)
     */
    @FunctionalInterface public interface NotLengthFailureCallback<N>
    {

        /**
         * Notifies this object that a {@code notLength} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code notLength} check failed.
         * @param check     The length provided to the {@code notLength} check that failed.
         *
         * @see TextParameter#notLength(int)
         * @see TextParameter#notLength(int, NotLengthFailureCallback)
         * @see TextParameter#notLength(int, Iterable)
         */
        void notLengthFailure(TextParameter<N> parameter, int check);
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} does not equal the provided {@code length}.
     *
     * @param length           The integer value the value in the {@link TextParameter} must not equal for the check to pass.
     * @param failureCallbacks The failure callbacks to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notLength(int length, Iterable<? extends NotLengthFailureCallback<N>> failureCallbacks) throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() != length;
        if (!result) {
            incrementFailureCount();
            for (NotLengthFailureCallback<N> failureCallback : failureCallbacks)
                failureCallback.notLengthFailure(this, length);
        }

        return result;
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} does not equal the provided {@code length}.
     *
     * @param length          The integer value the value in the {@link TextParameter} must not equal for the check to pass.
     * @param failureCallback The failure callback to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notLength(int length, NotLengthFailureCallback<N> failureCallback) throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() != length;
        if (!result) {
            incrementFailureCount();
            failureCallback.notLengthFailure(this, length);
        }

        return result;
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} does not equal the provided {@code length}.
     * <p>
     * Notifies the {@link TextParameter.FailureHandler} instances provided to this object in case the check fails.
     *
     * @param length The integer value the value in the {@link TextParameter} must not equal for the check to pass.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notLength(int length)
    {
        return notLength(length, failureHandlers);
    }

    /**
     * Functional interface for {@code isShorterThan} check failure handler.
     *
     * @param <N> The type of the name of the {@link TextParameter} on which the {@code isShorterThan} check failed.
     *
     * @see TextParameter#isShorterThan(int)
     * @see TextParameter#isShorterThan(int, IsShorterThanFailureCallback)
     * @see TextParameter#isShorterThan(int, Iterable)
     */
    @FunctionalInterface public interface IsShorterThanFailureCallback<N>
    {

        /**
         * Notifies this object that a {@code isShorterThan} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code isShorterThan} check failed.
         * @param check     The length provided to the {@code isShorterThan} check that failed.
         *
         * @see TextParameter#isShorterThan(int)
         * @see TextParameter#isShorterThan(int, IsShorterThanFailureCallback)
         * @see TextParameter#isShorterThan(int, Iterable)
         */
        void isShorterThanFailure(TextParameter<N> parameter, int check);
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} is shorter than provided {@code length}.
     *
     * @param length           The integer value the value in the {@link TextParameter} must be smaller than for the check
     *                         to pass.
     * @param failureCallbacks The failure callbacks to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isShorterThan(int length, Iterable<? extends IsShorterThanFailureCallback<N>> failureCallbacks)
            throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() < length;
        if (!result) {
            incrementFailureCount();
            for (IsShorterThanFailureCallback<N> failureCallback : failureCallbacks)
                failureCallback.isShorterThanFailure(this, length);
        }

        return result;
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} is shorter than provided {@code length}.
     *
     * @param length          The integer value the value in the {@link TextParameter} must be smaller than for the check
     *                        to pass.
     * @param failureCallback The failure callback to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isShorterThan(int length, IsShorterThanFailureCallback<N> failureCallback) throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() < length;
        if (!result) {
            incrementFailureCount();
            failureCallback.isShorterThanFailure(this, length);
        }

        return result;
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} is shorter than provided {@code length}.
     * <p>
     * Notifies the {@link TextParameter.FailureHandler} instances provided to this object in case the check fails.
     *
     * @param length The integer value the value in the {@link TextParameter} must be smaller than for the check
     *               to pass.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isShorterThan(int length) throws NullParameterValueException
    {
        return isShorterThan(length, failureHandlers);
    }

    /**
     * Functional interface for {@code notShorterThan} check failure handler.
     *
     * @param <N> The type of the name of the {@link TextParameter} on which the {@code notShorterThan} check failed.
     *
     * @see TextParameter#notShorterThan(int)
     * @see TextParameter#notShorterThan(int, NotShorterThanFailureCallback)
     * @see TextParameter#notShorterThan(int, Iterable)
     */
    @FunctionalInterface public interface NotShorterThanFailureCallback<N>
    {

        /**
         * Notifies this object that a {@code notShorterThan} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code notShorterThan} check failed.
         * @param lower     The upper length provided to the {@code notShorterThan} check that failed.
         *
         * @see TextParameter#notShorterThan(int)
         * @see TextParameter#notShorterThan(int, NotShorterThanFailureCallback)
         * @see TextParameter#notShorterThan(int, Iterable)
         */
        void notShorterThanFailure(TextParameter<N> parameter, int lower);
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} is not shorter than provided {@code length}.
     *
     * @param lower            The size the length of the value in the {@link TextParameter} must not be be smaller than
     *                         for the check to pass.
     * @param failureCallbacks The failure callbacks to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notShorterThan(int lower, Iterable<? extends NotShorterThanFailureCallback<N>> failureCallbacks)
            throws NullParameterValueException
    {
        nullCheck();

        boolean result = value.length() >= lower;
        if (!result) {
            incrementFailureCount();
            for (NotShorterThanFailureCallback<N> failureCallback : failureCallbacks)
                failureCallback.notShorterThanFailure(this, lower);
        }

        return result;
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} is not shorter than provided {@code length}.
     *
     * @param lower           The size the length of the value in the {@link TextParameter} must not be be smaller than
     *                        for the check to pass.
     * @param failureCallback The failure callback to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notShorterThan(int lower, NotShorterThanFailureCallback<N> failureCallback)
            throws NullParameterValueException
    {
        nullCheck();

        boolean result = value.length() >= lower;
        if (!result) {
            incrementFailureCount();
            failureCallback.notShorterThanFailure(this, lower);
        }

        return result;
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} is not shorter than provided {@code length}.
     * <p>
     * Notifies the {@link TextParameter.FailureHandler} instances provided to this object in case the check fails.
     *
     * @param lower The size the length of the value in the {@link TextParameter} must not be be smaller than
     *              for the check to pass.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notShorterThan(int lower)
    {
        return notShorterThan(lower, failureHandlers);
    }

    /**
     * Functional interface for {@code isLongerThan} check failure handler.
     *
     * @param <N> The type of the name of the {@link TextParameter} on which the {@code isLongerThan} check failed.
     *
     * @see TextParameter#isLongerThan(int)
     * @see TextParameter#isLongerThan(int, IsLongerThanFailureCallback)
     * @see TextParameter#isLongerThan(int, Iterable)
     */
    @FunctionalInterface public interface IsLongerThanFailureCallback<N>
    {

        /**
         * Notifies this object that a {@code isLongerThan} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code isLongerThan} check failed.
         * @param lower     The upper length provided to the {@code isLongerThan} check that failed.
         *
         * @see TextParameter#isLongerThan(int)
         * @see TextParameter#isLongerThan(int, IsLongerThanFailureCallback)
         * @see TextParameter#isLongerThan(int, Iterable)
         */
        void isLongerThanFailure(TextParameter<N> parameter, int lower);
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} is longer than provided {@code length}.
     *
     * @param upper            The size the length of the value in the {@link TextParameter} must exceed for the
     *                         check to pass.
     * @param failureCallbacks The failure callbacks to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isLongerThan(int upper, Iterable<? extends IsLongerThanFailureCallback<N>> failureCallbacks)
            throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() > upper;
        if (!result) {
            incrementFailureCount();
            for (IsLongerThanFailureCallback<N> failureCallback : failureCallbacks)
                failureCallback.isLongerThanFailure(this, upper);
        }

        return result;
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} is longer than provided {@code length}.
     *
     * @param upper           The size the length of the value in the {@link TextParameter} must exceed for the
     *                        check to pass.
     * @param failureCallback The failure callback to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isLongerThan(int upper, IsLongerThanFailureCallback<N> failureCallback) throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() > upper;
        if (!result) {
            incrementFailureCount();
            failureCallback.isLongerThanFailure(this, upper);
        }

        return result;
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} is longer than provided {@code length}.
     * <p>
     * Notifies the {@link TextParameter.FailureHandler} instances provided to this object in case the check fails.
     *
     * @param upper The size the length of the value in the {@link TextParameter} must exceed for the
     *              check to pass.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isLongerThan(int upper)
    {
        return isLongerThan(upper, failureHandlers);
    }

    /**
     * Functional interface for {@code notLongerThan} check failure handler.
     *
     * @param <N> The type of the name of the {@link TextParameter} on which the {@code notLongerThan} check failed.
     *
     * @see TextParameter#notLongerThan(int)
     * @see TextParameter#notLongerThan(int, NotLongerThanFailureCallback)
     * @see TextParameter#notLongerThan(int, Iterable)
     */
    @FunctionalInterface public interface NotLongerThanFailureCallback<N>
    {

        /**
         * Notifies this object that a {@code notLongerThan} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code notLongerThan} check failed.
         * @param lower     The upper length provided to the {@code notLongerThan} check that failed.
         *
         * @see TextParameter#notLongerThan(int)
         * @see TextParameter#notLongerThan(int, NotLongerThanFailureCallback)
         * @see TextParameter#notLongerThan(int, Iterable)
         */
        void notLongerThanFailure(TextParameter<N> parameter, int lower);
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} is not longer than provided {@code length}.
     *
     * @param upper            The size the length of the value in the {@link TextParameter} must not exceed for the
     *                         check to pass.
     * @param failureCallbacks The failure callbacks to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notLongerThan(int upper, Iterable<? extends NotLongerThanFailureCallback<N>> failureCallbacks)
            throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() <= upper;
        if (!result) {
            incrementFailureCount();
            for (NotLongerThanFailureCallback<N> failureCallback : failureCallbacks)
                failureCallback.notLongerThanFailure(this, upper);
        }

        return result;
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} is not longer than provided {@code length}.
     *
     * @param upper           The size the length of the value in the {@link TextParameter} must not exceed for the
     *                        check to pass.
     * @param failureCallback The failure callback to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notLongerThan(int upper, NotLongerThanFailureCallback<N> failureCallback) throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() <= upper;
        if (!result) {
            incrementFailureCount();
            failureCallback.notLongerThanFailure(this, upper);
        }

        return result;
    }

    /**
     * Checks that the length of the value in the {@link TextParameter} is not longer than provided {@code length}.
     * <p>
     * Notifies the {@link TextParameter.FailureHandler} instances provided to this object in case the check fails.
     *
     * @param upper The size the length of the value in the {@link TextParameter} must not exceed for the
     *              check to pass.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notLongerThan(int upper)
    {
        return notLongerThan(upper, failureHandlers);
    }

    /**
     * Functional interface for {@code isMatch} check failure handler.
     *
     * @param <N> The type of the name of the {@link TextParameter} on which the {@code isMatch} check failed.
     *
     * @see TextParameter#isMatch(Pattern)
     * @see TextParameter#isMatch(Pattern, IsMatchFailureCallback)
     * @see TextParameter#isMatch(Pattern, Iterable)
     */
    @FunctionalInterface public interface IsMatchFailureCallback<N>
    {

        /**
         * Notifies this object that a {@code isMatch} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code isMatch} check failed.
         * @param pattern   The {@code Pattern} provided to the {@code isMatch} check that failed.
         *
         * @see TextParameter#isMatch(Pattern)
         * @see TextParameter#isMatch(Pattern, IsMatchFailureCallback)
         * @see TextParameter#isMatch(Pattern, Iterable)
         */
        void isMatchFailure(TextParameter<N> parameter, Pattern pattern);
    }

    /**
     * Checks that the value in the {@link TextParameter} matches the provided {@code Pattern}. The internal implementation
     * uses the {@link Matcher#find()} method to perform the matching.
     *
     * @param pattern          The {@code Pattern} the value in the {@link TextParameter} must match for the check to pass.
     * @param failureCallbacks The failure handlers to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isMatch(Pattern pattern, Iterable<? extends IsMatchFailureCallback<N>> failureCallbacks)
            throws NullParameterValueException
    {
        nullCheck();

        boolean result = pattern.matcher(value).find();
        if (!result) {
            incrementFailureCount();
            for (IsMatchFailureCallback<N> failureCallback : failureCallbacks)
                failureCallback.isMatchFailure(this, pattern);
        }

        return result;
    }

    /**
     * Checks that the value in the {@link TextParameter} matches the provided {@code Pattern}. The internal implementation
     * uses the {@link Matcher#find()} method to perform the matching.
     *
     * @param pattern         The {@code Pattern} the value in the {@link TextParameter} must match for the check to pass.
     * @param failureCallback The failure handler to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isMatch(Pattern pattern, IsMatchFailureCallback<N> failureCallback) throws NullParameterValueException
    {
        nullCheck();

        boolean result = pattern.matcher(value).find();
        if (!result) {
            incrementFailureCount();
            failureCallback.isMatchFailure(this, pattern);
        }

        return result;
    }

    /**
     * Checks that the value in the {@link TextParameter} matches the provided {@code Pattern}. The internal implementation
     * uses the {@link Matcher#find()} method to perform the matching.
     * <p>
     * Notifies the {@link TextParameter.FailureHandler} instances provided to this object in case the check fails.
     *
     * @param pattern The {@code Pattern} the value in the {@link TextParameter} must match for the check to pass.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isMatch(Pattern pattern) throws NullParameterValueException
    {
        return isMatch(pattern, failureHandlers);
    }

    /**
     * Functional interface for {@code notMatch} check failure handler.
     *
     * @param <N> The type of the name of the {@link TextParameter} on which the {@code notMatch} check failed.
     *
     * @see TextParameter#notMatch(Pattern)
     * @see TextParameter#notMatch(Pattern, NotMatchFailureCallback)
     * @see TextParameter#notMatch(Pattern, Iterable)
     */
    @FunctionalInterface public interface NotMatchFailureCallback<N>
    {

        /**
         * Notifies this object that a {@code notMatch} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code notMatch} check failed.
         * @param pattern   The {@code Pattern} provided to the {@code notMatch} check that failed.
         *
         * @see TextParameter#notMatch(Pattern)
         * @see TextParameter#notMatch(Pattern, NotMatchFailureCallback)
         * @see TextParameter#notMatch(Pattern, Iterable)
         */
        void notMatchFailure(TextParameter<N> parameter, Pattern pattern);
    }

    /**
     * Checks that the value in the {@link TextParameter} does not match the provided {@code Pattern}. The internal
     * implementation uses the {@link Matcher#find()} method to perform the matching.
     *
     * @param pattern          The {@code Pattern} the value in the {@link TextParameter} must not match for the check to pass.
     * @param failureCallbacks The failure handlers to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notMatch(Pattern pattern, Iterable<? extends NotMatchFailureCallback<N>> failureCallbacks)
            throws NullParameterValueException
    {
        nullCheck();

        boolean result = !pattern.matcher(value).find();
        if (!result) {
            incrementFailureCount();
            for (NotMatchFailureCallback<N> failureCallback : failureCallbacks)
                failureCallback.notMatchFailure(this, pattern);
        }

        return result;
    }

    /**
     * Checks that the value in the {@link TextParameter} does not match the provided {@code Pattern}. The internal
     * implementation uses the {@link Matcher#find()} method to perform the matching.
     *
     * @param pattern         The {@code Pattern} the value in the {@link TextParameter} must not match for the check to pass.
     * @param failureCallback The failure handler to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notMatch(Pattern pattern, NotMatchFailureCallback<N> failureCallback) throws NullParameterValueException
    {
        nullCheck();

        boolean result = !pattern.matcher(value).find();
        if (!result) {
            incrementFailureCount();
            failureCallback.notMatchFailure(this, pattern);
        }

        return result;
    }

    /**
     * Checks that the value in the {@link TextParameter} does not match the provided {@code Pattern}. The internal
     * implementation uses the {@link Matcher#find()} method to perform the matching.
     * <p>
     * Notifies the {@link TextParameter.FailureHandler} instances provided to this object in case the check fails.
     *
     * @param pattern The {@code Pattern} the value in the {@link TextParameter} must not match for the check to pass.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notMatch(Pattern pattern) throws NullParameterValueException
    {
        return notMatch(pattern, failureHandlers);
    }


    /**
     * Functional interface for {@code isContained} check failure handler.
     *
     * @param <N> The type of the name of the {@link TextParameter} on which the {@code isContained} check failed.
     *
     * @see TextParameter#isContained(CharSequence)
     * @see TextParameter#isContained(CharSequence, IsContainedFailureCallback)
     * @see TextParameter#isContained(CharSequence, Iterable)
     */
    @FunctionalInterface public interface IsContainedFailureCallback<N>
    {


        /**
         * Notifies this object that a {@code isContained} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code isContained} check failed.
         * @param other     The {@code CharSequence} provided to the {@code isContained} check that failed.
         *
         * @see TextParameter#isContained(CharSequence)
         * @see TextParameter#isContained(CharSequence, IsContainedFailureCallback)
         * @see TextParameter#isContained(CharSequence, Iterable)
         */
        void isContainedFailure(TextParameter<N> parameter, CharSequence other);
    }

    /**
     * Checks that the value in the {@link TextParameter} does contains the provided {@code CharSequence}. The internal
     * implementation uses the {@link String#contains(CharSequence)}} method to check if the {@code CharSequence} is
     * provided.
     *
     * @param other            The {@code CharSequence} the value in the {@link TextParameter} must contain for the check
     *                         to pass.
     * @param failureCallbacks The failure handlers to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isContained(CharSequence other, Iterable<? extends IsContainedFailureCallback<N>> failureCallbacks)
            throws NullParameterValueException
    {
        nullCheck();

        boolean result = this.value.contains(other);
        if (!result) {
            incrementFailureCount();
            for (IsContainedFailureCallback<N> failureCallback : failureCallbacks)
                failureCallback.isContainedFailure(this, other);
        }

        return result;
    }

    /**
     * Checks that the value in the {@link TextParameter} does contains the provided {@code CharSequence}. The internal
     * implementation uses the {@link String#contains(CharSequence)}} method to check if the provided {@code CharSequence}
     * is contained.
     *
     * @param other           The {@code CharSequence} the value in the {@link TextParameter} must contain for the check
     *                        to pass.
     * @param failureCallback The failure handler to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isContained(CharSequence other, IsContainedFailureCallback<N> failureCallback)
            throws NullParameterValueException
    {
        nullCheck();

        boolean result = this.value.contains(other);
        if (!result) {
            incrementFailureCount();
            failureCallback.isContainedFailure(this, other);
        }

        return result;
    }

    /**
     * Checks that the value in the {@link TextParameter} does contains the provided {@code CharSequence}. The internal
     * implementation uses the {@link String#contains(CharSequence)}} method to check if the provided {@code CharSequence}
     * is contained.
     * <p>
     * Notifies the {@link TextParameter.FailureHandler} instances provided to this object in case the check fails.
     *
     * @param other The {@code CharSequence} the value in the {@link TextParameter} must contain for the check
     *              to pass.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean isContained(CharSequence other) throws NullParameterValueException
    {
        return isContained(other, failureHandlers);
    }

    /**
     * Functional interface for {@code notContained} check failure handler.
     *
     * @param <N> The type of the name of the {@link TextParameter} on which the {@code notContained} check failed.
     *
     * @see TextParameter#notContained(CharSequence)
     * @see TextParameter#notContained(CharSequence, NotContainedFailureCallback)
     * @see TextParameter#notContained(CharSequence, Iterable)
     */
    @FunctionalInterface public interface NotContainedFailureCallback<N>
    {

        /**
         * Notifies this object that a {@code notContained} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code notContained} check failed.
         * @param other     The {@code CharSequence} provided to the {@code notContained} check that failed.
         *
         * @see TextParameter#notContained(CharSequence)
         * @see TextParameter#notContained(CharSequence, NotContainedFailureCallback)
         * @see TextParameter#notContained(CharSequence, Iterable)
         */
        void notContainedFailure(TextParameter<N> parameter, CharSequence other);
    }

    /**
     * Checks that the value in the {@link TextParameter} does not contain the provided {@code CharSequence}. The internal
     * implementation uses the {@link String#contains(CharSequence)}} method to check if the provided {@code CharSequence} is
     * not contained.
     *
     * @param other            The {@code CharSequence} the value in the {@link TextParameter} must not contain for the
     *                         check to pass.
     * @param failureCallbacks The failure handlers to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notContained(CharSequence other, Iterable<? extends NotContainedFailureCallback<N>> failureCallbacks)
            throws NullParameterValueException
    {
        nullCheck();

        boolean result = !value.contains(other);
        if (!result) {
            incrementFailureCount();
            for (NotContainedFailureCallback<N> failureCallback : failureCallbacks)
                failureCallback.notContainedFailure(this, other);
        }

        return result;
    }

    /**
     * Checks that the value in the {@link TextParameter} does not contain the provided {@code CharSequence}. The internal
     * implementation uses the {@link String#contains(CharSequence)}} method to check if the provided {@code CharSequence} is
     * not contained.
     *
     * @param other           The {@code CharSequence} the value in the {@link TextParameter} must not contain for the
     *                        check to pass.
     * @param failureCallback The failure handler to notify in case the check fails.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notContained(CharSequence other, NotContainedFailureCallback<N> failureCallback) throws NullParameterValueException
    {
        nullCheck();

        boolean result = !value.contains(other);
        if (!result) {
            incrementFailureCount();
            failureCallback.notContainedFailure(this, other);
        }

        return result;
    }

    /**
     * Checks that the value in the {@link TextParameter} does not contain the provided {@code CharSequence}. The internal
     * implementation uses the {@link String#contains(CharSequence)}} method to check if the provided {@code CharSequence} is
     * not contained.
     * <p>
     * Notifies the {@link TextParameter.FailureHandler} instances provided to this object in case the check fails.
     *
     * @param other The {@code CharSequence} the value in the {@link TextParameter} must not contain for the
     *              check to pass.
     *
     * @return {@code true} if the check passes, {@code false} if the check fails.
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    public boolean notContained(CharSequence other) throws NullParameterValueException
    {
        return notContained(other, failureHandlers);
    }

    /**
     * Throws a {@link NullParameterValueException} is the value in the {@link TextParameter} is {@code null}.
     *
     * @throws NullParameterValueException When the value in the {@link TextParameter} is {@code null}.
     */
    private void nullCheck() throws NullParameterValueException
    {
        if (value == null)
            throw new NullParameterValueException();
    }

    /**
     * The interface defining the public API of a failure handler that can handle failures of checks on instances of
     * {@link TextParameter}.
     *
     * @param <N> The type of the name on the instance of {@link TextParameter} handled by the failure handler.
     */
    interface FailureHandler<N> extends ComparableParameter.FailureHandler<N, String>,
                                        IsEmptyFailureCallback<N>,
                                        NotEmptyFailureCallback<N>,
                                        IsLengthFailureCallback<N>,
                                        NotLengthFailureCallback<N>,
                                        IsLongerThanFailureCallback<N>,
                                        NotLongerThanFailureCallback<N>,
                                        IsShorterThanFailureCallback<N>,
                                        NotShorterThanFailureCallback<N>,
                                        IsMatchFailureCallback<N>,
                                        NotMatchFailureCallback<N>,
                                        IsContainedFailureCallback<N>,
                                        NotContainedFailureCallback<N>
    {

        /**
         * Notifies the {@link IsEmptyFailureCallback} that an {@code isEmpty} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code isEmpty} check failed.
         *
         * @see TextParameter#isEmpty()
         * @see TextParameter#isEmpty(IsEmptyFailureCallback)
         * @see TextParameter#isEmpty(Iterable)
         */
        @Override default void isEmptyFailure(TextParameter<N> parameter)
        {

        }

        /**
         * Notifies this object that a {@code notEmpty} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code notEmpty} check failed.
         *
         * @see TextParameter#notEmpty()
         * @see TextParameter#notEmpty(NotEmptyFailureCallback)
         * @see TextParameter#notEmpty(Iterable)
         */
        @Override default void notEmptyFailure(TextParameter<N> parameter)
        {

        }

        /**
         * Notifies this object that a {@code isLength} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code isLength} check failed.
         * @param check     The length provided to the {@code isLength} check that failed.
         *
         * @see TextParameter#isLength(int)
         * @see TextParameter#isLength(int, IsLengthFailureCallback)
         * @see TextParameter#isLength(int, Iterable)
         */
        @Override default void isLengthFailure(TextParameter<N> parameter, int check)
        {

        }

        /**
         * Notifies this object that a {@code notLength} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code notLength} check failed.
         * @param check     The length provided to the {@code notLength} check that failed.
         *
         * @see TextParameter#notLength(int)
         * @see TextParameter#notLength(int, NotLengthFailureCallback)
         * @see TextParameter#notLength(int, Iterable)
         */
        @Override default void notLengthFailure(TextParameter<N> parameter, int check)
        {

        }

        /**
         * Notifies this object that a {@code isShorterThan} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code isShorterThan} check failed.
         * @param check     The length provided to the {@code isShorterThan} check that failed.
         *
         * @see TextParameter#isShorterThan(int)
         * @see TextParameter#isShorterThan(int, IsShorterThanFailureCallback)
         * @see TextParameter#isShorterThan(int, Iterable)
         */
        @Override default void isShorterThanFailure(TextParameter<N> parameter, int check)
        {

        }

        /**
         * Notifies this object that a {@code notShorterThan} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code notShorterThan} check failed.
         * @param lower     The upper length provided to the {@code notShorterThan} check that failed.
         *
         * @see TextParameter#notShorterThan(int)
         * @see TextParameter#notShorterThan(int, NotShorterThanFailureCallback)
         * @see TextParameter#notShorterThan(int, Iterable)
         */
        @Override default void notShorterThanFailure(TextParameter<N> parameter, int lower)
        {

        }

        /**
         * Notifies this object that a {@code isLongerThan} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code isLongerThan} check failed.
         * @param lower     The upper length provided to the {@code isLongerThan} check that failed.
         *
         * @see TextParameter#isLongerThan(int)
         * @see TextParameter#isLongerThan(int, IsLongerThanFailureCallback)
         * @see TextParameter#isLongerThan(int, Iterable)
         */
        @Override default void isLongerThanFailure(TextParameter<N> parameter, int lower)
        {

        }

        /**
         * Notifies this object that a {@code notLongerThan} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code notLongerThan} check failed.
         * @param lower     The upper length provided to the {@code notLongerThan} check that failed.
         *
         * @see TextParameter#notLongerThan(int)
         * @see TextParameter#notLongerThan(int, NotLongerThanFailureCallback)
         * @see TextParameter#notLongerThan(int, Iterable)
         */
        @Override default void notLongerThanFailure(TextParameter<N> parameter, int lower)
        {

        }

        /**
         * Notifies this object that a {@code isMatch} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code isMatch} check failed.
         * @param pattern   The {@code Pattern} provided to the {@code isMatch} check that failed.
         *
         * @see TextParameter#isMatch(Pattern)
         * @see TextParameter#isMatch(Pattern, IsMatchFailureCallback)
         * @see TextParameter#isMatch(Pattern, Iterable)
         */
        @Override default void isMatchFailure(TextParameter<N> parameter, Pattern pattern)
        {

        }

        /**
         * Notifies this object that a {@code notMatch} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code notMatch} check failed.
         * @param pattern   The {@code Pattern} provided to the {@code notMatch} check that failed.
         *
         * @see TextParameter#notMatch(Pattern)
         * @see TextParameter#notMatch(Pattern, NotMatchFailureCallback)
         * @see TextParameter#notMatch(Pattern, Iterable)
         */
        @Override default void notMatchFailure(TextParameter<N> parameter, Pattern pattern)
        {

        }

        /**
         * Notifies this object that a {@code isContained} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code isContained} check failed.
         * @param other     The {@code CharSequence} provided to the {@code isContained} check that failed.
         *
         * @see TextParameter#isContained(CharSequence)
         * @see TextParameter#isContained(CharSequence, IsContainedFailureCallback)
         * @see TextParameter#isContained(CharSequence, Iterable)
         */
        @Override default void isContainedFailure(TextParameter<N> parameter, CharSequence other)
        {

        }

        /**
         * Notifies this object that a {@code notContained} check failed.
         *
         * @param parameter The {@link TextParameter} instance on which the {@code notContained} check failed.
         * @param other     The {@code CharSequence} provided to the {@code notContained} check that failed.
         *
         * @see TextParameter#notContained(CharSequence)
         * @see TextParameter#notContained(CharSequence, NotContainedFailureCallback)
         * @see TextParameter#notContained(CharSequence, Iterable)
         */
        @Override default void notContainedFailure(TextParameter<N> parameter, CharSequence other)
        {

        }
    }
}
