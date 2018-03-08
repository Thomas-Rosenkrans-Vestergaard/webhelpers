package tvestergaard.webhelpers.parameters;


import java.util.List;
import java.util.regex.Pattern;

public interface TextParameterFailureHandler extends TextParameter.IsFailureCallback,
                                                     TextParameter.NotFailureCallback,
                                                     TextParameter.IsEmptyFailureCallback,
                                                     TextParameter.NotEmptyFailureCallback,
                                                     TextParameter.IsLengthFailureCallback,
                                                     TextParameter.NotLengthFailureCallback,
                                                     TextParameter.IsShorterThanFailureCallback,
                                                     TextParameter.NotShorterThanFailureCallback,
                                                     TextParameter.IsLongerThanFailureCallback,
                                                     TextParameter.NotLongerThanFailureCallback,
                                                     TextParameter.IsMatchFailureCallback,
                                                     TextParameter.NotMatchFailureCallback,
                                                     TextParameter.IsInFailureCallback,
                                                     TextParameter.NotInFailureCallback,
                                                     TextParameter.IsContainedFailureCallback,
                                                     TextParameter.NotContainedFailureCallback
{

    /**
     * An no-op implementation of the {@link TextParameterFailureHandler}.
     */
    TextParameterFailureHandler EMPTY_FAILURE_HANDLER = new TextParameterFailureHandler()
    {

    };

    /**
     * The method called when the {@link TextParameter#is(CharSequence)} check method fails.
     *
     * @param parameter The {@link Parameter} on which the {@link TextParameter#is(CharSequence)} check method failed.
     * @param other     The {@code CharSequence} provided to the {@link TextParameter#is(CharSequence)}.
     */
    @Override default void isFailure(TextParameter parameter, CharSequence other)
    {

    }

    /**
     * The method called when the {@link TextParameter#not(CharSequence)} check method fails.
     *
     * @param parameter The {@link Parameter} on which the {@link TextParameter#not(CharSequence)} check method failed.
     * @param other     The {@code CharSequence} provided to the {@link TextParameter#not(CharSequence)}.
     */
    @Override default void notFailure(TextParameter parameter, CharSequence other)
    {

    }

    /**
     * The method called when the {@link TextParameter#isEmpty()} check method fails.
     *
     * @param parameter The {@link Parameter} on which the {@link TextParameter#isEmpty()} check method failed.
     */
    @Override default void isEmptyFailure(TextParameter parameter)
    {

    }

    /**
     * The method called when the {@link TextParameter#notEmpty()} check method fails.
     *
     * @param parameter The {@link Parameter} on which the {@link TextParameter#notEmpty()} check method failed.
     */
    @Override default void notEmptyFailure(TextParameter parameter)
    {

    }

    /**
     * The method called when the {@link TextParameter#isLength(int)} check method fails.
     *
     * @param parameter The {@link Parameter} on which the {@link TextParameter#isLength(int)} check method failed.
     * @param check     The length provided to the {@link TextParameter#isLength(int)} check that failed.
     */
    @Override default void isLengthFailure(TextParameter parameter, int check)
    {

    }

    /**
     * The method called when the {@link TextParameter#notLength(int)} check method fails.
     *
     * @param parameter The {@link Parameter} on which the {@link TextParameter#notLength(int)} check method failed.
     * @param check     The length provided to the {@link TextParameter#notLength(int)} check that failed.
     */
    @Override default void notLengthFailure(TextParameter parameter, int check)
    {

    }

    /**
     * The method called when the {@link TextParameter#isShorterThan(int)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#isShorterThan(int)} check method failed.
     * @param check     The length provided to the {@link TextParameter#isShorterThan(int)} check that failed.
     */
    @Override default void isShorterThanFailure(TextParameter parameter, int check)
    {

    }

    /**
     * The method called when the {@link TextParameter#notShorterThan(int)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#notShorterThan(int)} check method failed.
     * @param check     The length provided to the {@link TextParameter#notShorterThan(int)} check that failed.
     */
    @Override default void notShorterThanFailure(TextParameter parameter, int check)
    {

    }

    /**
     * The method called when the {@link TextParameter#isLongerThan(int)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#isLongerThan(int)} check method failed.
     * @param check     The length provided to the {@link TextParameter#isLongerThan(int)} check that failed.
     */
    @Override default void isLongerThanFailure(TextParameter parameter, int check)
    {

    }

    /**
     * The method called when the {@link TextParameter#notLongerThan(int)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#notLongerThan(int)} check method failed.
     * @param check     The length provided to the {@link TextParameter#notLongerThan(int)} check that failed.
     */
    @Override default void notLongerThanFailure(TextParameter parameter, int check)
    {

    }

    /**
     * The method called when the {@link TextParameter#isMatch(Pattern)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#isMatch(Pattern)} check method failed.
     * @param pattern   The {@code Pattern} provided to the {@link TextParameter#isMatch(Pattern)} check that failed.
     */
    @Override default void isMatchFailure(TextParameter parameter, Pattern pattern)
    {

    }

    /**
     * The method called when the {@link TextParameter#notMatch(Pattern)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#notMatch(Pattern)} check method failed.
     * @param pattern   The {@code Pattern} provided to the {@link TextParameter#notMatch(Pattern)} check that failed.
     */
    @Override default void notMatchFailure(TextParameter parameter, Pattern pattern)
    {

    }

    /**
     * The method called when the {@link TextParameter#isIn(List)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#isIn(List)} check method failed.
     * @param patterns  The list of {@code CharSequence} instances provided to the {@link TextParameter#isIn(List)} check that failed.
     */
    @Override default void isInFailure(TextParameter parameter, List<? extends CharSequence> patterns)
    {

    }

    /**
     * The method called when the {@link TextParameter#notIn(List)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#notIn(List)} check method failed.
     * @param patterns  The list of {@code CharSequence} instances provided to the {@link TextParameter#notIn(List)} check that failed.
     * @param collision The index of the {@code CharSequence} in the provided list that caused the check to fail.
     */
    @Override default void notInFailure(TextParameter parameter, List<? extends CharSequence> patterns, int collision)
    {

    }

    /**
     * The method called when the {@link TextParameter#isContained(CharSequence)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#isContained(CharSequence)} check method failed.
     * @param other     The {@code CharSequence} provided to the {@link TextParameter#isContained(CharSequence)} check that failed.
     */
    @Override default void isContainedFailure(TextParameter parameter, CharSequence other)
    {

    }

    /**
     * The method called when the {@link TextParameter#notContained(CharSequence)} check method fails.
     *
     * @param parameter The {@link TextParameter} on which the {@link TextParameter#notContained(CharSequence)} check method failed.
     * @param other     The {@code CharSequence} provided to the {@link TextParameter#notContained(CharSequence)} check that failed.
     */
    @Override default void notContainedFailure(TextParameter parameter, CharSequence other)
    {

    }
}
