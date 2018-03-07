package tvestergaard.webhelpers.parameters;

import java.util.List;
import java.util.regex.Pattern;

public interface TextParameterErrorHandler
{

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
     * @param check     The length parameter passed to the {@link TextParameter#isLength(int)} method.
     */
    default void isLength(TextParameter parameter, int check)
    {

    }

    default void notLength(TextParameter parameter, int check)
    {

    }

    default void isShorter(TextParameter parameter, int check)
    {

    }

    default void notShorter(TextParameter parameter, int check)
    {

    }

    default void isLonger(TextParameter parameter, int check)
    {

    }

    default void notLonger(TextParameter parameter, int check)
    {

    }

    default void isMatch(TextParameter parameter, Pattern check)
    {

    }

    default void notMatch(TextParameter parameter, Pattern check)
    {

    }

    default void isIn(TextParameter parameter, List<? extends CharSequence> others)
    {

    }

    default void notIn(TextParameter parameter, List<? extends CharSequence> others, int collision)
    {

    }

    default void notInt(TextParameter parameter, List<? extends CharSequence> others, int collision)
    {

    }

    default void contains(TextParameter parameter, CharSequence other)
    {

    }

    default void notContains(TextParameter parameter, CharSequence other)
    {

    }
}
