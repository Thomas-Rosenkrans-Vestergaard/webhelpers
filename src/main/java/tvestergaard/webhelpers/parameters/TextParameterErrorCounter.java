package tvestergaard.webhelpers.parameters;

import java.util.List;
import java.util.regex.Pattern;

public class TextParameterErrorCounter implements TextParameterErrorHandler
{

    /**
     * The internal counter, counting the number of errors that have occured.
     */
    private int counter = 0;

    /**
     * Returns the number of errors that have occurred.
     *
     * @return The number of errors that have occurred.
     */
    public int getCount()
    {
        return counter;
    }

    /**
     * Callback for when the {@link TextParameter#is(CharSequence)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#is(CharSequence)} method check failed.
     * @param other     The {@code CharSequence} provided to the {@link TextParameter#is(CharSequence)} check.
     */
    @Override public void is(TextParameter parameter, CharSequence other)
    {
        counter++;
    }

    /**
     * Callback for when the {@link TextParameter#not(CharSequence)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#not(CharSequence)} method check failed.
     * @param other     The {@code CharSequence} provided to the {@link TextParameter#not(CharSequence)} check.
     */
    @Override public void not(TextParameter parameter, CharSequence other)
    {
        counter++;
    }

    /**
     * Callback for when the {@link TextParameter#isEmpty()} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#isEmpty()} method check failed.
     */
    @Override public void isEmpty(TextParameter parameter)
    {
        counter++;
    }

    /**
     * Callback for when the {@link TextParameter#notEmpty()} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#notEmpty()} method check failed.
     */
    @Override public void notEmpty(TextParameter parameter)
    {
        counter++;
    }

    /**
     * Callback for when the {@link TextParameter#isLength(int)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#isLength(int)} method check failed.
     * @param check     The length parameter passed to the {@link TextParameter#isLength(int)} check.
     */
    @Override public void isLength(TextParameter parameter, int check)
    {
        counter++;
    }

    /**
     * Callback for when the {@link TextParameter#notLength(int)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#notLength(int)} method check failed.
     * @param check     The length parameter provided to the {@link TextParameter#notLength(int)} check.
     */
    @Override public void notLength(TextParameter parameter, int check)
    {
        counter++;
    }

    /**
     * Callback for when the {@link TextParameter#isShorterThan(int)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#isShorterThan(int)} method check failed.
     * @param check     The length parameter provided to the {@link TextParameter#isShorterThan(int)} check.
     */
    @Override public void isShorterThan(TextParameter parameter, int check)
    {
        counter++;
    }

    /**
     * Callback for when the {@link TextParameter#notShorterThan(int)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#notShorterThan(int)} method check failed.
     * @param check     The length parameter provided to the {@link TextParameter#notShorterThan(int)} check.
     */
    @Override public void notShorterThan(TextParameter parameter, int check)
    {
        counter++;
    }

    /**
     * Callback for when the {@link TextParameter#isLongerThan(int)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#isLongerThan(int)} method check failed.
     * @param check     The length parameter provided to the {@link TextParameter#isLongerThan(int)} check.
     */
    @Override public void isLongerThan(TextParameter parameter, int check)
    {
        counter++;
    }

    /**
     * Callback for when the {@link TextParameter#notLongerThan(int)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#notLongerThan(int)} method check failed.
     * @param check     The length parameter passed to the {@link TextParameter#notLongerThan(int)} check.
     */
    @Override public void notLongerThan(TextParameter parameter, int check)
    {
        counter++;
    }

    /**
     * Callback for when the {@link TextParameter#isMatch(Pattern)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#isMatch(Pattern)} method check failed.
     * @param pattern   The {@code Pattern} provided to the {@link TextParameter#isMatch(Pattern)} check.
     */
    @Override public void isMatch(TextParameter parameter, Pattern pattern)
    {
        counter++;
    }

    /**
     * Callback for when the {@link TextParameter#notEmpty()} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#notEmpty()} method check failed.
     * @param pattern   The {@code Pattern} provided to the {@link TextParameter#notMatch(Pattern)}.
     */
    @Override public void notMatch(TextParameter parameter, Pattern pattern)
    {
        counter++;
    }

    /**
     * Callback for when the {@link TextParameter#isIn(List)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#isIn(List)} method check failed.
     * @param others    The list of {@code CharSequence} instances provided to the {@link TextParameter#isIn(List)}.
     */
    @Override public void isIn(TextParameter parameter, List<? extends CharSequence> others)
    {
        counter++;
    }

    /**
     * Callback for when the {@link TextParameter#notIn(List)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#notIn(List)} method check failed.
     * @param others    The list of {@code CharSequence} instances provided to the {@link TextParameter#notIn(List)}.
     * @param collision
     */
    @Override public void notIn(TextParameter parameter, List<? extends CharSequence> others, int collision)
    {
        counter++;
    }

    /**
     * Callback for when the {@link TextParameter#contains(CharSequence)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#contains(CharSequence)} method check failed.
     * @param other     The {@code CharSequence} provided to the {@link TextParameter#contains(CharSequence)}.
     */
    @Override public void contains(TextParameter parameter, CharSequence other)
    {
        counter++;
    }

    /**
     * Callback for when the {@link TextParameter#notContains(CharSequence)} method check fails.
     *
     * @param parameter The instance of {@link TextParameter} on which the {@link TextParameter#notContains(CharSequence)} method check failed.
     * @param other     The {@code CharSequence} provided to the {@link TextParameter#notContains(CharSequence)}.
     */
    @Override public void notContains(TextParameter parameter, CharSequence other)
    {
        counter++;
    }
}
