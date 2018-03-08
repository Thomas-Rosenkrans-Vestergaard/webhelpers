package tvestergaard.webhelpers.parameters;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
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
     * The object containing the errorHandler for check onErrors.
     */
    private final ErrorHandlerList<TextParameterErrorHandler> errorHandlers;

    /**
     * Creates a new {@link TextParameter}.
     *
     * @param name         The name of the {@link TextParameter}.
     * @param value        The value of the {@link TextParameter}.
     * @param errorHandler The object containing the errorHandler for check onErrors.
     */
    public TextParameter(String name, String value, ErrorHandlerList<TextParameterErrorHandler> errorHandler)
    {
        super(name);

        this.value = value;
        this.length = value.length();
        this.errorHandlers = errorHandler;
    }

    public TextParameter(String name, String value, TextParameterErrorHandler... errorHandler)
    {
        this(name, value, new ErrorHandlerList<>(errorHandler));
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
     * Checks that the internal value of the {@link TextParameter} equals the provided {@code CharSequence}.
     * <p>
     * The {@link BiConsumer}s provided to the method, and the {@link TextParameterErrorHandler#is(TextParameter, CharSequence)}
     * method on the {@link TextParameterErrorHandler} objects provided to this object
     * are notified when this check fails.
     *
     * @param other            The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @param onErrorConsumers The {@code BiConsumer}(s) that are called when the check fails.
     * @return {@code true} if the value equals the provided {code CharSequence}, {@code false} in all other cases.
     */
    public boolean is(CharSequence other, BiConsumer<TextParameter, CharSequence>... onErrorConsumers)
    {
        boolean result = is(other);
        if (!result) {
            for (BiConsumer<TextParameter, CharSequence> onErrorConsumer : onErrorConsumers)
                onErrorConsumer.accept(this, other);
            for (TextParameterErrorHandler errorHandler : errorHandlers)
                errorHandler.is(this, other);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals the provided {@code CharSequence}.
     * <p>
     * The the {@link TextParameterErrorHandler#is(TextParameter, CharSequence)} method on the
     * {@link TextParameterErrorHandler} objects provided to this object are notified when this check fails.
     *
     * @param other The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @return {@code true} if the value equals the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean is(CharSequence other)
    {
        return other.equals(value);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} does not equal the provided {@code CharSequence}.
     * <p>
     * The {@link BiConsumer}s provided to the method, and the {@link TextParameterErrorHandler#not(TextParameter, CharSequence)}
     * method on the {@link TextParameterErrorHandler} objects provided to this object are notified when this check fails.
     *
     * @param other            The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @param onErrorConsumers The {@code BiConsumer}(s) that is called when the check fails.
     * @return {@code true} if the value does not equal the provided {code CharSequence}, {@code false} in all other cases.
     */
    public boolean not(CharSequence other, BiConsumer<TextParameter, CharSequence>... onErrorConsumers)
    {
        boolean result = not(other);
        if (!result) {
            for (BiConsumer<TextParameter, CharSequence> onErrorConsumer : onErrorConsumers)
                onErrorConsumer.accept(this, other);
            for (TextParameterErrorHandler errorHandler : errorHandlers)
                errorHandler.not(this, other);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} does not equal the provided {@code CharSequence}.
     * <p>
     * The {@link TextParameterErrorHandler#not(TextParameter, CharSequence)} method on the
     * {@link TextParameterErrorHandler} objects provided to this object are notified when this check fails.
     *
     * @param other The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @return {@code true} if the value does not equal the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean not(CharSequence other)
    {
        return !other.equals(this.value);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is empty.
     * <p>
     * The {@link BiConsumer}s provided to the method, and the {@link TextParameterErrorHandler#isEmpty(TextParameter)}
     * method on the {@link TextParameterErrorHandler} objects provided to this object are notified when this check fails.
     *
     * @param onErrorConsumers The {@code Consumer} that are called when the check fails.
     * @return {@code true} if the value is empty, {@code false} in all other cases.
     */
    public boolean isEmpty(Consumer<TextParameter>... onErrorConsumers)
    {
        boolean result = isEmpty();
        if (!result) {
            for (Consumer<TextParameter> onErrorConsumer : onErrorConsumers)
                onErrorConsumer.accept(this);
            for (TextParameterErrorHandler errorHandler : errorHandlers)
                errorHandler.isEmpty(this);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is empty.
     * <p>
     * The {@link TextParameterErrorHandler#isEmpty(TextParameter)} method on the {@link TextParameterErrorHandler}
     * objects provided to this object are notified when this check fails.
     *
     * @return {@code true} if the value is empty, {@code false} in all other cases.
     */
    public boolean isEmpty()
    {
        return isLength(0);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not empty.
     * <p>
     * The {@link BiConsumer}s provided to the method, and the {@link TextParameterErrorHandler#notEmpty(TextParameter)}
     * method on the {@link TextParameterErrorHandler} objects provided to this object are notified when this check fails.
     *
     * @param onErrorConsumers The {@code Consumer}(s) that are called when the check fails.
     * @return {@code true} if the value is not empty, {@code false} in all other cases.
     */
    public boolean notEmpty(Consumer<TextParameter>... onErrorConsumers)
    {
        boolean result = notEmpty();
        if (!result) {
            for (Consumer<TextParameter> onErrorConsumer : onErrorConsumers)
                onErrorConsumer.accept(this);
            for (TextParameterErrorHandler errorHandler : errorHandlers)
                errorHandler.notEmpty(this);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not empty.
     * <p>
     * The {@link TextParameterErrorHandler#notEmpty(TextParameter)} method on the {@link TextParameterErrorHandler}
     * objects provided to this object are notified when this check fails.
     *
     * @return {@code true} if the value is not empty, {@code false} in all other cases.
     */
    public boolean notEmpty()
    {
        return !isLength(0);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is empty.
     * <p>
     * The {@link BiConsumer}s provided to the method, and the {@link TextParameterErrorHandler#isLength(TextParameter, int)}
     * method on the {@link TextParameterErrorHandler} objects provided to this object are notified when this check fails.
     *
     * @param n                The length the internal value of the {@link TextParameter} must be for the check to pass.
     * @param onErrorConsumers The {@code BiConsumer}(s) that are called when the check fails.
     * @return {@code true} if the value is empty, {@code false} in all other cases.
     */
    public boolean isLength(int n, BiConsumer<TextParameter, Integer>... onErrorConsumers)
    {
        boolean result = length == n;
        if (!result) {
            for (BiConsumer<TextParameter, Integer> onErrorConsumer : onErrorConsumers)
                onErrorConsumer.accept(this, n);
            for (TextParameterErrorHandler errorHandler : errorHandlers)
                errorHandler.isLength(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is of the provided length.
     * <p>
     * The {@link TextParameterErrorHandler#isLength(TextParameter, int)} method on the {@link TextParameterErrorHandler}
     * objects provided to this object are notified when this check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must be for the check to pass.
     * @return {@code true} if the value is of the provided length, {@code false} in all other cases.
     */
    public boolean isLength(int n)
    {
        return this.value.length() == n;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not of the provided length.
     * <p>
     * The {@link BiConsumer}s provided to the method, and the {@link TextParameterErrorHandler#notLength(TextParameter, int)}
     * method on the {@link TextParameterErrorHandler} objects provided to this object are notified when this check fails.
     *
     * @param n                The length the internal value of the {@link TextParameter} must not be for the check to pass.
     * @param onErrorConsumers The {@code BiConsumer}(s) that are called when the check fails.
     * @return {@code true} if the value is not of the provided length, {@code false} in all other cases.
     */
    public boolean notLength(int n, BiConsumer<TextParameter, Integer>... onErrorConsumers)
    {
        boolean result = length != n;
        if (!result) {
            for (BiConsumer<TextParameter, Integer> onErrorConsumer : onErrorConsumers)
                onErrorConsumer.accept(this, n);
            for (TextParameterErrorHandler errorHandler : errorHandlers)
                errorHandler.notLength(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not of the provided length.
     * <p>
     * The {@link TextParameterErrorHandler#notLength(TextParameter, int)} method on the {@link TextParameterErrorHandler}
     * objects provided to this object are notified when this check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must not be for the check to pass.
     * @return {@code true} if the value is not of the provided length, {@code false} in all other cases.
     */
    public boolean notLength(int n)
    {
        return value.length() != n;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is shorter than the provided length.
     * <p>
     * The {@link BiConsumer}s provided to the method, and the {@link TextParameterErrorHandler#isShorterThan(TextParameter, int)}
     * method on the {@link TextParameterErrorHandler} objects provided to this object are notified when this check fails.
     *
     * @param n                The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @param onErrorConsumers The {@code BiConsumer}(s) that are called when the check fails.
     * @return {@code true} if the value is shorter than the provided length, {@code false} in all other cases.
     */
    public boolean isShorterThan(int n, BiConsumer<TextParameter, Integer>... onErrorConsumers)
    {
        boolean result = length < n;
        if (!result) {
            for (BiConsumer<TextParameter, Integer> onErrorConsumer : onErrorConsumers)
                onErrorConsumer.accept(this, n);
            for (TextParameterErrorHandler errorHandler : errorHandlers)
                errorHandler.isShorterThan(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is shorter than the provided length.
     * <p>
     * The {@link TextParameterErrorHandler#isShorterThan(TextParameter, int)} method on the {@link TextParameterErrorHandler}
     * objects provided to this object are notified when this check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @return {@code true} if the value is shorter than the provided length, {@code false} in all other cases.
     */
    public boolean isShorterThan(int n)
    {
        return value.length() < n;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not shorter than the provided length.
     * <p>
     * The {@link BiConsumer}s provided to the method, and the {@link TextParameterErrorHandler#notShorterThan(TextParameter, int)}
     * method on the {@link TextParameterErrorHandler} objects provided to this object are notified when this check fails.
     *
     * @param n                The length the internal value of the {@link TextParameter} must not be shorter than for the check to pass.
     * @param onErrorConsumers The {@code BiConsumer}(s) that are called when the check fails.
     * @return {@code true} if the value is not shorter than the provided length, {@code false} in all other cases.
     */
    public boolean notShorterThan(int n, BiConsumer<TextParameter, Integer>... onErrorConsumers)
    {
        boolean result = notShorterThan(n);
        if (!result) {
            for (BiConsumer<TextParameter, Integer> onErrorConsumer : onErrorConsumers)
                onErrorConsumer.accept(this, n);
            for (TextParameterErrorHandler errorHandler : errorHandlers)
                errorHandler.notShorterThan(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not shorter than the provided length.
     * <p>
     * The {@link TextParameterErrorHandler#notShorterThan(TextParameter, int)} method on the {@link TextParameterErrorHandler}
     * objects provided to this object are notified when this check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must not be shorter than for the check to pass.
     * @return {@code true} if the value is not shorter than the provided length, {@code false} in all other cases.
     */
    public boolean notShorterThan(int n)
    {
        return value.length() >= n;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is longer than the provided length.
     * <p>
     * The {@link BiConsumer}s provided to the method, and the {@link TextParameterErrorHandler#isLongerThan(TextParameter, int)}
     * method on the {@link TextParameterErrorHandler} objects provided to this object are notified when this check fails.
     *
     * @param n                The length the internal value of the {@link TextParameter} must be longer than for the check to pass.
     * @param onErrorConsumers The {@code BiConsumer}(s) that are called when the check fails.
     * @return {@code true} if the length of the value is longer than the provided length, {@code false} in all other cases.
     */
    public boolean isLongerThan(int n, BiConsumer<TextParameter, Integer>... onErrorConsumers)
    {
        boolean result = isLongerThan(n);
        if (!result) {
            for (BiConsumer<TextParameter, Integer> onErrorConsumer : onErrorConsumers)
                onErrorConsumer.accept(this, n);
            for (TextParameterErrorHandler errorHandler : errorHandlers)
                errorHandler.isLongerThan(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is longer than the provided length.
     * <p>
     * The {@link TextParameterErrorHandler#isLongerThan(TextParameter, int)} method on the {@link TextParameterErrorHandler}
     * objects provided to this object are notified when this check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @return {@code true} if the value is longer than the provided length, {@code false} in all other cases.
     */
    public boolean isLongerThan(int n)
    {
        return value.length() > n;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not longer than the provided length.
     * <p>
     * The {@link BiConsumer}s provided to the method, and the {@link TextParameterErrorHandler#notLongerThan(TextParameter, int)}
     * method on the {@link TextParameterErrorHandler} objects provided to this object are notified when this check fails.
     *
     * @param n                The length the internal value of the {@link TextParameter} must not be shorter than for the check to pass.
     * @param onErrorConsumers The {@code BiConsumer}(s) that are called when the check fails.
     * @return {@code true} if the value is not longer than the provided length, {@code false} in all other cases.
     */
    public boolean notLongerThan(int n, BiConsumer<TextParameter, Integer>... onErrorConsumers)
    {
        boolean result = notLongerThan(n);
        if (!result) {
            for (BiConsumer<TextParameter, Integer> onErrorConsumer : onErrorConsumers)
                onErrorConsumer.accept(this, n);
            for (TextParameterErrorHandler errorHandler : errorHandlers)
                errorHandler.notLongerThan(this, n);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not longer than the provided length.
     * <p>
     * The {@link TextParameterErrorHandler#notLongerThan(TextParameter, int)} method on the {@link TextParameterErrorHandler}
     * objects provided to this object are notified when this check fails.
     *
     * @param n The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @return {@code true} if the value is not longer than the provided length, {@code false} in all other cases.
     */
    public boolean notLongerThan(int n)
    {
        return value.length() <= n;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} matches that of the provided {@code Pattern}.
     * <p>
     * The {@link BiConsumer}s provided to the method, and the {@link TextParameterErrorHandler#isMatch(TextParameter, Pattern)}
     * method on the {@link TextParameterErrorHandler} objects provided to this object are notified when this check fails.
     *
     * @param pattern          The {@code Pattern} the internal value of the {@link TextParameter} must match for the check to pass.
     * @param onErrorConsumers The {@code BiConsumer}(s) that are called when the check fails.
     * @return {@code true} if the value matches that of the provided {@code Pattern}, {@code false} in all other cases.
     */
    public boolean isMatch(Pattern pattern, BiConsumer<TextParameter, Pattern>... onErrorConsumers)
    {
        boolean result = isMatch(pattern);
        if (!result) {
            for (BiConsumer<TextParameter, Pattern> onErrorConsumer : onErrorConsumers)
                onErrorConsumer.accept(this, pattern);
            for (TextParameterErrorHandler errorHandler : errorHandlers)
                errorHandler.isMatch(this, pattern);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} matches that of the provided {@code Pattern}.
     * <p>
     * The {@link TextParameterErrorHandler#isMatch(TextParameter, Pattern)} method on the {@link TextParameterErrorHandler}
     * objects provided to this object are notified when this check fails.
     *
     * @param pattern The {@code Pattern} the internal value of the {@link TextParameter} must match for the check to pass.
     * @return {@code true} if the value matches that of the provided {@code Pattern}, {@code false} in all other cases.
     */
    public boolean isMatch(Pattern pattern)
    {
        return pattern.matcher(value).find();
    }

    /**
     * Checks that the internal value of the {@link TextParameter} doesn't match that of the provided {@code Pattern}.
     * <p>
     * Notifies the provided {@code BiConsumer} if the check fails.
     *
     * @param pattern          The {@code Pattern} the internal value of the {@link TextParameter} must match for the check to pass.
     * @param onErrorConsumers The {@code BiConsumer}(s) that are called when the check fails.
     * @return {@code true} if the value doesn't match that of the provided {@code Pattern}, {@code false} in all other cases.
     */
    public boolean notMatch(Pattern pattern, BiConsumer<TextParameter, Pattern>... onErrorConsumers)
    {
        boolean result = notMatch(pattern);
        if (!result) {
            for (BiConsumer<TextParameter, Pattern> onErrorConsumer : onErrorConsumers)
                onErrorConsumer.accept(this, pattern);
            for (TextParameterErrorHandler errorHandler : errorHandlers)
                errorHandler.notMatch(this, pattern);
        }

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} doesn't match that of the provided {@code Pattern}.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#notMatch(TextParameter, Pattern)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @param pattern The {@code Pattern} the internal value of the {@link TextParameter} must match for the check to pass.
     * @return {@code true} if the value doesn't match that of the provided {@code Pattern}, {@code false} in all other cases.
     */
    public boolean notMatch(Pattern pattern)
    {
        return !pattern.matcher(value).find();
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals one of the provided instances of {@code CharSequence}.
     * <p>
     * Notifies the provided {@code BiConsumer} if the check fails.
     *
     * @param patterns         The list of {@code CharSequence} instances the internal value of the {@link TextParameter} must match for the check to pass.
     * @param onErrorConsumers The {@code BiConsumer}(s) that are called when the check fails.
     * @return {@code true} if the value equals at least one of the provided instances of {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean isIn(List<? extends CharSequence> patterns, BiConsumer<TextParameter, List<? extends CharSequence>> onErrorConsumers)
    {
        for (CharSequence pattern : patterns) {
            if (value.equals(pattern)) {
                return true;
            }
        }

        onError.accept(this, patterns);
        return false;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals one of the provided instances of {@code CharSequence}.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#isIn(TextParameter, List)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @param patterns The list of {@code CharSequence} instances the internal value of the {@link TextParameter} must match for the check to pass.
     * @return {@code true} if the value equals at least one of the provided instances of {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean isIn(List<? extends CharSequence> patterns)
    {
        return isIn(patterns, errorHandler::isIn);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals none of the provided instances of {@code CharSequence}.
     * <p>
     * Notifies the provided {@code TriConsumer} if the check fails.
     *
     * @param patterns         The list of {@code CharSequence} instances the internal value of the {@link TextParameter} must not match for the check to pass.
     * @param onErrorConsumers The {@code TriConsumer}(s) that are called when the check fails.
     * @return {@code true} if the value equals none of the provided instances of {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean notIn(List<? extends CharSequence> patterns, TriConsumer<TextParameter, List<? extends CharSequence>, Integer> onErrorConsumers)
    {
        int size = patterns.size();
        for (int x = 0; x < size; x++) {
            if (value.equals(patterns.get(x))) {
                onError.accept(this, patterns, x);
                return false;
            }
        }

        return true;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals none of the provided instances of {@code CharSequence}.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#notIn(TextParameter, List, int)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @param patterns The list of {@code CharSequence} instances the internal value of the {@link TextParameter} must not match for the check to pass.
     * @return {@code true} if the value equals none of the provided instances of {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean notIn(List<? extends CharSequence> patterns)
    {
        return notIn(patterns, errorHandler::notIn);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} contains the provided {@code CharSequence}.
     * <p>
     * Notifies the provided {@code BiConsumer} if the check fails.
     *
     * @param other            The other {@code CharSequence} instance the internal value of the {@link TextParameter} must contain for the check to pass.
     * @param onErrorConsumers The {@code BiConsumer}(s) that are called when the check fails.
     * @return {@code true} if the value contains the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean contains(CharSequence other, BiConsumer<TextParameter, CharSequence> onErrorConsumers)
    {
        boolean result = this.value.contains(other);
        if (!result)
            onError.accept(this, other);

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} contains the provided {@code CharSequence}.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#contains(TextParameter, CharSequence)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @param other The other {@code CharSequence} instance the internal value of the {@link TextParameter} must contain for the check to pass.
     * @return {@code true} if the value contains the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean contains(CharSequence other)
    {
        return contains(other, errorHandler::contains);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} doesn't contain the provided {@code CharSequence}.
     * <p>
     * Notifies the provided {@code BiConsumer} if the check fails.
     *
     * @param other            The other {@code CharSequence} instance the internal value of the {@link TextParameter} must not contain for the check to pass.
     * @param onErrorConsumers The {@code BiConsumer}(s) that are called when the check fails.
     * @return {@code true} if the value doesn't contain the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean notContains(CharSequence other, BiConsumer<TextParameter, CharSequence> onErrorConsumers)
    {
        boolean result = !value.contains(other);
        if (!result)
            onError.accept(this, other);

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} does not contain of the provided instances of {@code CharSequence}.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#notContains(TextParameter, CharSequence)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @param other The other {@code CharSequence} instance the internal value of the {@link TextParameter} must not contain for the check to pass.
     * @return {@code true} if the value doesn't contain the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean notContains(CharSequence other)
    {
        return notContains(other, errorHandler::notContains);
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
}
