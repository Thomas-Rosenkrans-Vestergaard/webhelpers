package tvestergaard.webhelpers.parameters;

import java.util.regex.Pattern;

public class TextParameter<N> extends GenericParameter<N, String>
{

    /**
     * The {@link FailureHandler} instances that will be notified of validation failures.
     */
    private final Iterable<? extends FailureHandler<N>> failureHandlers;

    /**
     * Creates a new {@link TextParameter}.
     *
     * @param name            The name of the {@link TextParameter}.
     * @param value           The value of the {@link TextParameter}.
     * @param failureHandlers The {@link FailureHandler} instances used as callbacks for when checks fails.
     */
    public TextParameter(N name, String value, Iterable<? extends FailureHandler<N>> failureHandlers)
    {
        super(name, value, failureHandlers);

        this.failureHandlers = failureHandlers;
    }


    @FunctionalInterface public interface IsEmptyFailureCallback<N>
    {

        void isEmptyFailure(TextParameter<N> parameter);
    }

    public boolean isEmpty(Iterable<? extends IsEmptyFailureCallback<N>> failureCallbacks) throws NullValueException
    {
        nullCheck();

        boolean result = value.isEmpty();
        if (!result) {
            incrementFailureCount();
            for (IsEmptyFailureCallback failureCallback : failureCallbacks)
                failureCallback.isEmptyFailure(this);
        }

        return result;
    }

    public boolean isEmpty(IsEmptyFailureCallback<N> failureCallback) throws NullValueException
    {
        nullCheck();

        boolean result = value.isEmpty();
        if (!result) {
            incrementFailureCount();
            failureCallback.isEmptyFailure(this);
        }

        return result;
    }

    public boolean isEmpty()
    {
        return isEmpty(failureHandlers);
    }

    @FunctionalInterface public interface NotEmptyFailureCallback<N>
    {

        void notEmptyFailure(TextParameter<N> parameter);
    }

    public boolean notEmpty(Iterable<? extends NotEmptyFailureCallback<N>> failureCallbacks) throws NullValueException
    {
        nullCheck();

        boolean result = !value.isEmpty();
        if (!result) {
            incrementFailureCount();
            for (NotEmptyFailureCallback failureCallback : failureCallbacks)
                failureCallback.notEmptyFailure(this);
        }

        return result;
    }

    public boolean notEmpty(NotEmptyFailureCallback<N> failureCallback) throws NullValueException
    {
        nullCheck();

        boolean result = !value.isEmpty();
        if (!result) {
            incrementFailureCount();
            failureCallback.notEmptyFailure(this);
        }

        return result;
    }

    public boolean notEmpty()
    {
        return notEmpty(failureHandlers);
    }

    @FunctionalInterface public interface IsLengthFailureCallback<N>
    {

        void isLengthFailure(TextParameter<N> parameter, int check);
    }

    public boolean isLength(int n, Iterable<? extends IsLengthFailureCallback<N>> failureCallbacks) throws NullValueException
    {
        nullCheck();

        boolean result = value.length() == n;
        if (!result) {
            incrementFailureCount();
            for (IsLengthFailureCallback failureCallback : failureCallbacks)
                failureCallback.isLengthFailure(this, n);
        }

        return result;
    }

    public boolean isLength(int n, IsLengthFailureCallback<N> failureCallback) throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() == n;
        if (!result) {
            incrementFailureCount();
            failureCallback.isLengthFailure(this, n);
        }

        return result;
    }

    public boolean isLength(int n)
    {
        return isLength(n, failureHandlers);
    }

    @FunctionalInterface public interface NotLengthFailureCallback<N>
    {

        void notLengthFailure(TextParameter<N> parameter, int check);
    }

    public boolean notLength(int n, Iterable<? extends NotLengthFailureCallback<N>> failureCallbacks) throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() != n;
        if (!result) {
            incrementFailureCount();
            for (NotLengthFailureCallback failureCallback : failureCallbacks)
                failureCallback.notLengthFailure(this, n);
        }

        return result;
    }

    public boolean notLength(int n, NotLengthFailureCallback<N> failureCallback) throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() != n;
        if (!result) {
            incrementFailureCount();
            failureCallback.notLengthFailure(this, n);
        }

        return result;
    }

    public boolean notLength(int n)
    {
        return notLength(n, failureHandlers);
    }

    @FunctionalInterface public interface IsShorterThanFailureCallback<N>
    {

        void isShorterThanFailure(TextParameter<N> parameter, int check);
    }

    public boolean isShorterThan(int n, Iterable<? extends IsShorterThanFailureCallback<N>> failureCallbacks) throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() < n;
        if (!result) {
            incrementFailureCount();
            for (IsShorterThanFailureCallback failureCallback : failureCallbacks)
                failureCallback.isShorterThanFailure(this, n);
        }

        return result;
    }

    public boolean isShorterThan(int n, IsShorterThanFailureCallback<N> failureCallback) throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() < n;
        if (!result) {
            incrementFailureCount();
            failureCallback.isShorterThanFailure(this, n);
        }

        return result;
    }

    public boolean isShorterThan(int n)
    {
        return isShorterThan(n, failureHandlers);
    }

    @FunctionalInterface public interface NotShorterThanFailureCallback<N>
    {

        void notShorterThanFailure(TextParameter<N> parameter, int check);
    }

    public boolean notShorterThan(int n, Iterable<? extends NotShorterThanFailureCallback<N>> failureCallbacks)
    {
        boolean result = value.length() >= n;
        if (!result) {
            incrementFailureCount();
            for (NotShorterThanFailureCallback failureCallback : failureCallbacks)
                failureCallback.notShorterThanFailure(this, n);
        }

        return result;
    }

    public boolean notShorterThan(int n, NotShorterThanFailureCallback<N> failureCallback)
    {
        boolean result = value.length() >= n;
        if (!result) {
            incrementFailureCount();
            failureCallback.notShorterThanFailure(this, n);
        }

        return result;
    }

    public boolean notShorterThan(int n)
    {
        return notShorterThan(n, failureHandlers);
    }

    @FunctionalInterface public interface IsLongerThanFailureCallback<N>
    {

        void isLongerThanFailure(TextParameter<N> parameter, int check);
    }

    public boolean isLongerThan(int n, Iterable<? extends IsLongerThanFailureCallback<N>> failureCallbacks) throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() > n;
        if (!result) {
            incrementFailureCount();
            for (IsLongerThanFailureCallback failureCallback : failureCallbacks)
                failureCallback.isLongerThanFailure(this, n);
        }

        return result;
    }

    public boolean isLongerThan(int n, IsLongerThanFailureCallback<N> failureCallback) throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() > n;
        if (!result) {
            incrementFailureCount();
            failureCallback.isLongerThanFailure(this, n);
        }

        return result;
    }


    public boolean isLongerThan(int n)
    {
        return isLongerThan(n, failureHandlers);
    }

    @FunctionalInterface public interface NotLongerThanFailureCallback<N>
    {

        void notLongerThanFailure(TextParameter<N> parameter, int check);
    }

    public boolean notLongerThan(int n, Iterable<? extends NotLongerThanFailureCallback<N>> failureCallbacks) throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() <= n;
        if (!result) {
            incrementFailureCount();
            for (NotLongerThanFailureCallback failureCallback : failureCallbacks)
                failureCallback.notLongerThanFailure(this, n);
        }

        return result;
    }

    public boolean notLongerThan(int n, NotLongerThanFailureCallback<N> failureCallback) throws NullPointerException
    {
        nullCheck();

        boolean result = value.length() <= n;
        if (!result) {
            incrementFailureCount();
            failureCallback.notLongerThanFailure(this, n);
        }

        return result;
    }

    public boolean notLongerThan(int n)
    {
        return notLongerThan(n, failureHandlers);
    }

    @FunctionalInterface public interface IsMatchFailureCallback<N>
    {

        void isMatchFailure(TextParameter<N> parameter, Pattern pattern);
    }

    public boolean isMatch(Pattern pattern, Iterable<? extends IsMatchFailureCallback<N>> failureCallbacks) throws NullValueException
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

    public boolean isMatch(Pattern pattern, IsMatchFailureCallback<N> failureCallback) throws NullValueException
    {
        nullCheck();

        boolean result = pattern.matcher(value).find();
        if (!result) {
            incrementFailureCount();
            failureCallback.isMatchFailure(this, pattern);
        }

        return result;
    }

    public boolean isMatch(Pattern pattern)
    {
        return isMatch(pattern, failureHandlers);
    }

    @FunctionalInterface public interface NotMatchFailureCallback<N>
    {

        void notMatchFailure(TextParameter<N> parameter, Pattern pattern);
    }

    public boolean notMatch(Pattern pattern, Iterable<? extends NotMatchFailureCallback<N>> failureCallbacks) throws NullPointerException
    {
        nullCheck();

        boolean result = !pattern.matcher(value).find();
        if (!result) {
            incrementFailureCount();
            for (NotMatchFailureCallback failureCallback : failureCallbacks)
                failureCallback.notMatchFailure(this, pattern);
        }

        return result;
    }

    public boolean notMatch(Pattern pattern, NotMatchFailureCallback<N> failureCallback) throws NullPointerException
    {
        nullCheck();

        boolean result = !pattern.matcher(value).find();
        if (!result) {
            incrementFailureCount();
            failureCallback.notMatchFailure(this, pattern);
        }

        return result;
    }

    public boolean notMatch(Pattern pattern)
    {
        return notMatch(pattern, failureHandlers);
    }


    @FunctionalInterface public interface IsContainedFailureCallback<K>
    {

        void isContainedFailure(TextParameter<K> parameter, CharSequence other);
    }

    public boolean isContained(CharSequence other, Iterable<? extends IsContainedFailureCallback<N>> failureCallbacks) throws NullValueException
    {
        nullCheck();

        boolean result = this.value.contains(other);
        if (!result) {
            incrementFailureCount();
            for (IsContainedFailureCallback failureCallback : failureCallbacks)
                failureCallback.isContainedFailure(this, other);
        }

        return result;
    }

    public boolean isContained(CharSequence other, IsContainedFailureCallback<N> failureCallback) throws NullValueException
    {
        nullCheck();

        boolean result = this.value.contains(other);
        if (!result) {
            incrementFailureCount();
            failureCallback.isContainedFailure(this, other);
        }

        return result;
    }

    public boolean isContained(CharSequence other)
    {
        return isContained(other, failureHandlers);
    }

    @FunctionalInterface public interface NotContainedFailureCallback<N>
    {

        void notContainedFailure(TextParameter<N> parameter, CharSequence other);
    }

    public boolean notContained(CharSequence other, Iterable<? extends NotContainedFailureCallback<N>> failureCallbacks) throws NullValueException
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

    public boolean notContained(CharSequence other, NotContainedFailureCallback<N> failureCallback) throws NullValueException
    {
        nullCheck();

        boolean result = !value.contains(other);
        if (!result) {
            incrementFailureCount();
            failureCallback.notContainedFailure(this, other);
        }

        return result;
    }

    public boolean notContained(CharSequence other)
    {
        return notContained(other, failureHandlers);
    }

    private void nullCheck() throws NullValueException
    {
        if (value == null)
            throw new NullValueException();
    }

    interface FailureHandler<N> extends GenericParameter.FailureHandler<N, String>,
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

    }
}
