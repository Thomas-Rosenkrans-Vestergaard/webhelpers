package tvestergaard.webhelpers.parameters;

import java.util.List;
import java.util.regex.Pattern;

public interface TextParameterErrorHandler
{

    default void is(TextParameter parameter, CharSequence other)
    {

    }

    default void not(TextParameter parameter, CharSequence other)
    {

    }

    /**
     * Callback for when the {@link TextParameter#isEmpty()} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#isEmpty()} method check failed.
     */
    default void isEmpty(TextParameter parameter)
    {

    }

    /**
     * Callback for when the {@link TextParameter#notEmpty()} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#notEmpty()} method check failed.
     */
    default void notEmpty(TextParameter parameter)
    {

    }

    /**
     * Callback for when the {@link TextParameter#isLength(int)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#isLength(int)} method check failed.
     * @param check     The length parameter passed to the {@link TextParameter#isLength(int)} check.
     */
    default void isLength(TextParameter parameter, int check)
    {

    }

    /**
     * Callback for when the {@link TextParameter#notLength(int)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#notLength(int)} method check failed.
     * @param check     The length parameter provided to the {@link TextParameter#notLength(int)} check.
     */
    default void notLength(TextParameter parameter, int check)
    {

    }

    /**
     * Callback for when the {@link TextParameter#isShorterThan(int)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#isShorterThan(int)} method check failed.
     * @param check     The length parameter provided to the {@link TextParameter#isShorterThan(int)} check.
     */
    default void isShorterThan(TextParameter parameter, int check)
    {

    }

    /**
     * Callback for when the {@link TextParameter#notShorterThan(int)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#notShorterThan(int)} method check failed.
     * @param check     The length parameter provided to the {@link TextParameter#notShorterThan(int)} check.
     */
    default void notShorter(TextParameter parameter, int check)
    {

    }

    /**
     * Callback for when the {@link TextParameter#isLongerThan(int)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#isLongerThan(int)} method check failed.
     * @param check     The length parameter provided to the {@link TextParameter#isLongerThan(int)} check.
     */
    default void isLongerThan(TextParameter parameter, int check)
    {

    }

    /**
     * Callback for when the {@link TextParameter#notLongerThan(int)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#notLongerThan(int)} method check failed.
     * @param check     The length parameter passed to the {@link TextParameter#notLongerThan(int)} check.
     */
    default void notLongerThan(TextParameter parameter, int check)
    {

    }

    /**
     * Callback for when the {@link TextParameter#isMatch(Pattern)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#isMatch(Pattern)} method check failed.
     * @param pattern   The {@code Pattern} provided to the {@link TextParameter#isMatch(Pattern)} check.
     */
    default void isMatch(TextParameter parameter, Pattern pattern)
    {

    }

    /**
     * Callback for when the {@link TextParameter#notEmpty()} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#notEmpty()} method check failed.
     * @param pattern   The {@code Pattern} provided to the {@link TextParameter#notMatch(Pattern)}.
     */
    default void notMatch(TextParameter parameter, Pattern pattern)
    {

    }

    /**
     * Callback for when the {@link TextParameter#isIn(List)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#isIn(List)} method check failed.
     * @param others    The list of {@code CharSequence} instances provided to the {@link TextParameter#isIn(List)}.
     */
    default void isIn(TextParameter parameter, List<? extends CharSequence> others)
    {

    }

    /**
     * Callback for when the {@link TextParameter#notIn(List)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#notIn(List)} method check failed.
     * @param others    The list of {@code CharSequence} instances provided to the {@link TextParameter#notIn(List)}.
     */
    default void notIn(TextParameter parameter, List<? extends CharSequence> others, int collision)
    {

    }

    /**
     * Callback for when the {@link TextParameter#contains(CharSequence)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#contains(CharSequence)} method check failed.
     * @param other     The {@code CharSequence} provided to the {@link TextParameter#contains(CharSequence)}.
     */
    default void contains(TextParameter parameter, CharSequence other)
    {

    }

    /**
     * Callback for when the {@link TextParameter#notContains(CharSequence)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#notContains(CharSequence)} method check failed.
     * @param other     The {@code CharSequence} provided to the {@link TextParameter#notContains(CharSequence)}.
     */
    default void notContains(TextParameter parameter, CharSequence other)
    {

    }
}
