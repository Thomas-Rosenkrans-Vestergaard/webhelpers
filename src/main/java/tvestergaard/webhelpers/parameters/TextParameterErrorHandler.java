package tvestergaard.webhelpers.parameters;


import java.util.List;
import java.util.regex.Pattern;

public interface TextParameterErrorHandler extends TextParameter.IsErrorCallback,
                                                   TextParameter.NotErrorCallback,
                                                   TextParameter.IsEmptyErrorCallback,
                                                   TextParameter.NotEmptyErrorCallback,
                                                   TextParameter.IsLengthErrorCallback,
                                                   TextParameter.NotLengthErrorCallback,
                                                   TextParameter.IsShorterThanErrorCallback,
                                                   TextParameter.NotShorterThanErrorCallback,
                                                   TextParameter.IsLongerThanErrorCallback,
                                                   TextParameter.NotLongerThanErrorCallback,
                                                   TextParameter.IsMatchErrorCallback,
                                                   TextParameter.NotMatchErrorCallback,
                                                   TextParameter.IsInErrorCallback,
                                                   TextParameter.NotInErrorCallback,
                                                   TextParameter.IsContainedErrorCallback,
                                                   TextParameter.NotContainedErrorCallback
{

    /**
     * The method called when the {@link TextParameter#is(CharSequence)} check method fails.
     *
     * @param parameter The {@link Parameter} on which the {@link TextParameter#is(CharSequence)} check method failed.
     * @param other     The {@code CharSequence} provided to the {@link TextParameter#is(CharSequence)}.
     */
    @Override default void isError(TextParameter parameter, CharSequence other)
    {

    }

    /**
     * The method called when the {@link TextParameter#not(CharSequence)} check method fails.
     *
     * @param parameter The {@link Parameter} on which the {@link TextParameter#not(CharSequence)} check method failed.
     * @param other     The {@code CharSequence} provided to the {@link TextParameter#not(CharSequence)}.
     */
    @Override default void notError(TextParameter parameter, CharSequence other)
    {

    }

    /**
     * The method called when the {@link TextParameter#isEmpty()} check method fails.
     *
     * @param parameter The {@link Parameter} on which the {@link TextParameter#isEmpty()} check method failed.
     */
    @Override default void isEmptyError(TextParameter parameter)
    {

    }

    /**
     * The method called when the {@link TextParameter#notEmpty()} check method fails.
     *
     * @param parameter The {@link Parameter} on which the {@link TextParameter#notEmpty()} check method failed.
     */
    @Override default void notEmptyError(TextParameter parameter)
    {

    }

    /**
     * The method called when the {@link TextParameter#isLength(int)} check method fails.
     *
     * @param parameter The {@link Parameter} on which the {@link TextParameter#isLength(int)} check method failed.
     * @param check     The length provided to the {@link TextParameter#isLength(int)} check that failed.
     */
    @Override default void isLengthError(TextParameter parameter, int check)
    {

    }

    /**
     * The method called when the {@link TextParameter#notLength(int)} check method fails.
     *
     * @param parameter The {@link Parameter} on which the {@link TextParameter#notLength(int)} check method failed.
     * @param check     The length provided to the {@link TextParameter#notLength(int)} check that failed.
     */
    @Override default void notLengthError(TextParameter parameter, int check)
    {

    }

    /**
     * The method called when the {@link TextParameter#isShorterThan(int)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#isShorterThan(int)} check method failed.
     * @param check     The length provided to the {@link TextParameter#isShorterThan(int)} check that failed.
     */
    @Override default void isShorterThanError(TextParameter parameter, int check)
    {

    }

    /**
     * The method called when the {@link TextParameter#notShorterThan(int)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#notShorterThan(int)} check method failed.
     * @param check     The length provided to the {@link TextParameter#notShorterThan(int)} check that failed.
     */
    @Override default void notShorterThanError(TextParameter parameter, int check)
    {

    }

    /**
     * The method called when the {@link TextParameter#isLongerThan(int)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#isLongerThan(int)} check method failed.
     * @param check     The length provided to the {@link TextParameter#isLongerThan(int)} check that failed.
     */
    @Override default void isLongerThanError(TextParameter parameter, int check)
    {

    }

    /**
     * The method called when the {@link TextParameter#notLongerThan(int)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#notLongerThan(int)} check method failed.
     * @param check     The length provided to the {@link TextParameter#notLongerThan(int)} check that failed.
     */
    @Override default void notLongerThanError(TextParameter parameter, int check)
    {

    }

    /**
     * The method called when the {@link TextParameter#isMatch(Pattern)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#isMatch(Pattern)} check method failed.
     * @param pattern   The {@code Pattern} provided to the {@link TextParameter#isMatch(Pattern)} check that failed.
     */
    @Override default void isMatchError(TextParameter parameter, Pattern pattern)
    {

    }

    /**
     * The method called when the {@link TextParameter#notMatch(Pattern)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#notMatch(Pattern)} check method failed.
     * @param pattern   The {@code Pattern} provided to the {@link TextParameter#notMatch(Pattern)} check that failed.
     */
    @Override default void notMatchError(TextParameter parameter, Pattern pattern)
    {

    }

    /**
     * The method called when the {@link TextParameter#isIn(List)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#isIn(List)} check method failed.
     * @param patterns  The list of {@code CharSequence} instances provided to the {@link TextParameter#isIn(List)} check that failed.
     */
    @Override default void isInError(TextParameter parameter, List<? extends CharSequence> patterns)
    {

    }

    /**
     * The method called when the {@link TextParameter#notIn(List)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#notIn(List)} check method failed.
     * @param patterns  The list of {@code CharSequence} instances provided to the {@link TextParameter#notIn(List)} check that failed.
     * @param collision The index of the {@code CharSequence} in the provided list that caused the check to fail.
     */
    @Override default void notInError(TextParameter parameter, List<? extends CharSequence> patterns, int collision)
    {

    }

    /**
     * The method called when the {@link TextParameter#isContained(CharSequence)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#isContained(CharSequence)} check method failed.
     * @param other     The {@code CharSequence} provided to the {@link TextParameter#isContained(CharSequence)} check that failed.
     */
    @Override default void isContainedError(TextParameter parameter, CharSequence other)
    {

    }

    /**
     * The method called when the {@link TextParameter#notContained(CharSequence)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#notContained(CharSequence)} check method failed.
     * @param other     The {@code CharSequence} provided to the {@link TextParameter#notContained(CharSequence)} check that failed.
     */
    @Override default void notContainedError(TextParameter parameter, CharSequence other)
    {

    }
}
