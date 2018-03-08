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
    private final TextParameterErrorHandler errorHandler;

    /**
     * Creates a new {@link TextParameter}.
     *
     * @param name         The name of the {@link TextParameter}.
     * @param value        The value of the {@link TextParameter}.
     * @param errorHandler The object containing the errorHandler for check onErrors.
     */
    public TextParameter(String name, String value, TextParameterErrorHandler errorHandler)
    {
        super(name);

        this.value = value;
        this.length = value.length();
        this.errorHandler = errorHandler;
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
     * Notifies the provided {@code BiConsumer} if the check fails.
     *
     * @param other   The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @param onError The {@code BiConsumer} that is called when the check fails.
     * @return {@code true} if the value equals the provided {code CharSequence}, {@code false} in all other cases.
     */
    public boolean is(CharSequence other, BiConsumer<TextParameter, CharSequence> onError)
    {
        boolean result = other.equals(value);
        if (!result && onError != null)
            onError.accept(this, other);

        return result;
    }


    /**
     * Checks that the internal value of the {@link TextParameter} equals the provided {@code CharSequence}.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#is(TextParameter, CharSequence)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @param other The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @return {@code true} if the value equals the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean is(CharSequence other)
    {
        return is(other, errorHandler::is);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} does not equal the provided {@code CharSequence}.
     * <p>
     * Notifies the provided {@code BiConsumer} if the check fails.
     *
     * @param other   The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @param onError The {@code BiConsumer} that is called when the check fails.
     * @return {@code true} if the value does not equal the provided {code CharSequence}, {@code false} in all other cases.
     */
    public boolean not(CharSequence other, BiConsumer<TextParameter, CharSequence> onError)
    {
        boolean result = !other.equals(value);
        if (!result && onError != null)
            onError.accept(this, other);

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} does not equal the provided {@code CharSequence}.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#not(TextParameter, CharSequence)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @param other The {@code CharSequence} to compare the internal value of the {@link TextParameter} to.
     * @return {@code true} if the value does not equal the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean not(CharSequence other)
    {
        return not(other, errorHandler::not);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is empty.
     * <p>
     * Notifies the provided {@code Consumer} if the check fails.
     *
     * @param onError The {@code Consumer} that is called when the check fails.
     * @return {@code true} if the value is empty, {@code false} in all other cases.
     */
    public boolean isEmpty(Consumer<TextParameter> onError)
    {
        boolean result = isLength(0);
        if (!result && onError != null)
            onError.accept(this);
        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is empty.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#isEmpty(TextParameter)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @return {@code true} if the value is empty, {@code false} in all other cases.
     */
    public boolean isEmpty()
    {
        return isEmpty(errorHandler::isEmpty);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not empty.
     * <p>
     * Notifies the provided {@code Consumer} if the check fails.
     *
     * @param onError The {@code Consumer} that is called when the check fails.
     * @return {@code true} if the value is not empty, {@code false} in all other cases.
     */
    public boolean notEmpty(Consumer<TextParameter> onError)
    {
        boolean result = !isLength(0);
        if (!result && onError != null)
            onError.accept(this);

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not empty.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#isEmpty(TextParameter)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @return {@code true} if the value is not empty, {@code false} in all other cases.
     */
    public boolean notEmpty()
    {
        return notEmpty(errorHandler::notEmpty);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is empty.
     * <p>
     * Notifies the provided {@code BiConsumer} if the check fails.
     *
     * @param n       The length the internal value of the {@link TextParameter} must be for the check to pass.
     * @param onError The {@code BiConsumer} that is called when the check fails.
     * @return {@code true} if the value is empty, {@code false} in all other cases.
     */
    public boolean isLength(int n, BiConsumer<TextParameter, Integer> onError)
    {
        boolean result = length == n;
        if (!result && onError != null)
            onError.accept(this, n);

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is of the provided length.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#isLength(TextParameter, int)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @param n The length the internal value of the {@link TextParameter} must be for the check to pass.
     * @return {@code true} if the value is of the provided length, {@code false} in all other cases.
     */
    public boolean isLength(int n)
    {
        return isLength(n, errorHandler::isLength);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not of the provided length.
     * <p>
     * Notifies the provided {@code BiConsumer} if the check fails.
     *
     * @param n       The length the internal value of the {@link TextParameter} must not be for the check to pass.
     * @param onError The {@code BiConsumer} that is called when the check fails.
     * @return {@code true} if the value is not of the provided length, {@code false} in all other cases.
     */
    public boolean notLength(int n, BiConsumer<TextParameter, Integer> onError)
    {
        boolean result = length != n;
        if (!result && onError != null)
            onError.accept(this, n);

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not of the provided length.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#notLength(TextParameter, int)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @param n The length the internal value of the {@link TextParameter} must not be for the check to pass.
     * @return {@code true} if the value is not of the provided length, {@code false} in all other cases.
     */
    public boolean notLength(int n)
    {
        return notLength(n, errorHandler::notLength);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is shorter than the provided length.
     * <p>
     * Notifies the provided {@code BiConsumer} if the check fails.
     *
     * @param n       The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @param onError The {@code BiConsumer} that is called when the check fails.
     * @return {@code true} if the value is shorter than the provided length, {@code false} in all other cases.
     */
    public boolean isShorterThan(int n, BiConsumer<TextParameter, Integer> onError)
    {
        boolean result = length < n;
        if (!result && onError != null)
            onError.accept(this, n);

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is shorter than the provided length.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#isShorterThan(TextParameter, int)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @param n The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @return {@code true} if the value is shorter than the provided length, {@code false} in all other cases.
     */
    public boolean isShorterThan(int n)
    {
        return isShorterThan(n, errorHandler::isShorterThan);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not shorter than the provided length.
     * <p>
     * Notifies the provided {@code BiConsumer} if the check fails.
     *
     * @param n       The length the internal value of the {@link TextParameter} must not be shorter than for the check to pass.
     * @param onError The {@code BiConsumer} that is called when the check fails.
     * @return {@code true} if the value is not shorter than the provided length, {@code false} in all other cases.
     */
    public boolean notShorterThan(int n, BiConsumer<TextParameter, Integer> onError)
    {
        boolean result = length >= n;
        if (!result && onError != null)
            onError.accept(this, n);

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not shorter than the provided length.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#notShorter(TextParameter, int)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @param n The length the internal value of the {@link TextParameter} must not be shorter than for the check to pass.
     * @return {@code true} if the value is not shorter than the provided length, {@code false} in all other cases.
     */
    public boolean notShorterThan(int n)
    {
        return notShorterThan(n, errorHandler::notShorter);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is longer than the provided length.
     * <p>
     * Notifies the provided {@code BiConsumer} if the check fails.
     *
     * @param n       The length the internal value of the {@link TextParameter} must be longer than for the check to pass.
     * @param onError The {@code BiConsumer} that is called when the check fails.
     * @return {@code true} if the length of the value is longer than the provided length, {@code false} in all other cases.
     */
    public boolean isLongerThan(int n, BiConsumer<TextParameter, Integer> onError)
    {
        boolean result = length > n;
        if (!result && onError != null)
            onError.accept(this, n);

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is longer than the provided length.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#isLongerThan(TextParameter, int)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @param n The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @return {@code true} if the value is longer than the provided length, {@code false} in all other cases.
     */
    public boolean isLongerThan(int n)
    {
        return isLongerThan(n, errorHandler::isLongerThan);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not longer than the provided length.
     * <p>
     * Notifies the provided {@code BiConsumer} if the check fails.
     *
     * @param n       The length the internal value of the {@link TextParameter} must not be shorter than for the check to pass.
     * @param onError The {@code BiConsumer} that is called when the check fails.
     * @return {@code true} if the value is not longer than the provided length, {@code false} in all other cases.
     */
    public boolean notLongerThan(int n, BiConsumer<TextParameter, Integer> onError)
    {
        boolean result = length <= n;
        if (!result && onError != null)
            onError.accept(this, n);

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} is not longer than the provided length.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#notLongerThan(TextParameter, int)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @param n The length the internal value of the {@link TextParameter} must be shorter than for the check to pass.
     * @return {@code true} if the value is not longer than the provided length, {@code false} in all other cases.
     */
    public boolean notLongerThan(int n)
    {
        return notLongerThan(n, errorHandler::notLongerThan);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} matches that of the provided {@code Pattern}.
     * <p>
     * Notifies the provided {@code BiConsumer} if the check fails.
     *
     * @param pattern The {@code Pattern} the internal value of the {@link TextParameter} must match for the check to pass.
     * @param onError The {@code BiConsumer} that is called when the check fails.
     * @return {@code true} if the value matches that of the provided {@code Pattern}, {@code false} in all other cases.
     */
    public boolean isMatch(Pattern pattern, BiConsumer<TextParameter, Pattern> onError)
    {
        boolean result = pattern.matcher(this.value).find();
        if (!result && onError != null)
            onError.accept(this, pattern);

        return result;
    }

    /**
     * Checks that the internal value of the {@link TextParameter} matches that of the provided {@code Pattern}.
     * <p>
     * Notifies the {@link TextParameterErrorHandler#isMatch(TextParameter, Pattern)} method on the
     * {@link TextParameterErrorHandler} object provided to the {@link TextParameter}.
     *
     * @param pattern The {@code Pattern} the internal value of the {@link TextParameter} must match for the check to pass.
     * @return {@code true} if the value matches that of the provided {@code Pattern}, {@code false} in all other cases.
     */
    public boolean isMatch(Pattern pattern)
    {
        return isMatch(pattern, errorHandler::isMatch);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} doesn't match that of the provided {@code Pattern}.
     * <p>
     * Notifies the provided {@code BiConsumer} if the check fails.
     *
     * @param pattern The {@code Pattern} the internal value of the {@link TextParameter} must match for the check to pass.
     * @param onError The {@code BiConsumer} that is called when the check fails.
     * @return {@code true} if the value doesn't match that of the provided {@code Pattern}, {@code false} in all other cases.
     */
    public boolean notMatch(Pattern pattern, BiConsumer<TextParameter, Pattern> onError)
    {
        boolean result = !pattern.matcher(this.value).find();
        if (!result && onError != null)
            onError.accept(this, pattern);

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
        return notMatch(pattern, errorHandler::notMatch);
    }

    /**
     * Checks that the internal value of the {@link TextParameter} equals one of the provided instances of {@code CharSequence}.
     * <p>
     * Notifies the provided {@code BiConsumer} if the check fails.
     *
     * @param patterns The list of {@code CharSequence} instances the internal value of the {@link TextParameter} must match for the check to pass.
     * @param onError  The {@code BiConsumer} that is called when the check fails.
     * @return {@code true} if the value equals at least one of the provided instances of {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean isIn(List<? extends CharSequence> patterns, BiConsumer<TextParameter, List<? extends CharSequence>> onError)
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
     * @param patterns The list of {@code CharSequence} instances the internal value of the {@link TextParameter} must not match for the check to pass.
     * @param onError  The {@code TriConsumer} that is called when the check fails.
     * @return {@code true} if the value equals none of the provided instances of {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean notIn(List<? extends CharSequence> patterns, TriConsumer<TextParameter, List<? extends CharSequence>, Integer> onError)
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
     * @param other   The other {@code CharSequence} instance the internal value of the {@link TextParameter} must contain for the check to pass.
     * @param onError The {@code BiConsumer} that is called when the check fails.
     * @return {@code true} if the value contains the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean contains(CharSequence other, BiConsumer<TextParameter, CharSequence> onError)
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
     * @param other   The other {@code CharSequence} instance the internal value of the {@link TextParameter} must not contain for the check to pass.
     * @param onError The {@code BiConsumer} that is called when the check fails.
     * @return {@code true} if the value doesn't contain the provided {@code CharSequence}, {@code false} in all other cases.
     */
    public boolean notContains(CharSequence other, BiConsumer<TextParameter, CharSequence> onError)
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
