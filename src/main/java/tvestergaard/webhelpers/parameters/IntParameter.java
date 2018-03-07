package tvestergaard.webhelpers.parameters;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class IntParameter extends AbstractParameter
{

    private int                      value;
    private IntParameterErrorHandler errorHandler;

    public IntParameter(String name, int value, IntParameterErrorHandler errorHandler)
    {
        super(name);
        this.value = value;
        this.errorHandler = errorHandler;
    }

    public IntParameter(String name, int value)
    {
        this(name, value, null);
    }

    public boolean is(int other, BiConsumer<IntParameter, Integer> onError)
    {
        boolean result = value == other;
        if (!result)
            onError.accept(this, other);

        return result;
    }

    public boolean is(int other)
    {
        return is(other, errorHandler::is);
    }

    public boolean not(int other, BiConsumer<IntParameter, Integer> onError)
    {
        boolean result = value != other;
        if (!result)
            onError.accept(this, other);

        return result;
    }

    public boolean not(int other)
    {
        return not(other, errorHandler::not);
    }

    public boolean isPositive(Consumer<IntParameter> onError)
    {
        boolean result = value > 0;
        if (!result)
            onError.accept(this);

        return result;
    }

    public boolean isPositive()
    {
        return isPositive(errorHandler::isPositive);
    }

    public boolean notPositive(Consumer<IntParameter> onError)
    {
        boolean result = value <= 0;
        if (!result)
            onError.accept(this);

        return result;
    }

    public boolean notPositive()
    {
        return notPositive(errorHandler::notPositive);
    }

    public boolean isNegative(Consumer<IntParameter> onError)
    {
        boolean result = value < 0;
        if (!result)
            onError.accept(this);

        return result;
    }

    public boolean isNegative()
    {
        return isNegative(errorHandler::isNegative);
    }

    public boolean notNegative(Consumer<IntParameter> onError)
    {
        boolean result = value >= 0;
        if (!result)
            onError.accept(this);

        return result;
    }

    public boolean notNegative()
    {
        return notNegative(errorHandler::notNegative);
    }

    public boolean isAbove(int lower, BiConsumer<IntParameter, Integer> onError)
    {
        boolean result = value > lower;
        if (!result)
            onError.accept(this, lower);

        return result;
    }

    public boolean isAbove(int lower)
    {
        return isAbove(lower, errorHandler::isAbove);
    }

    public boolean notAbove(int lower, BiConsumer<IntParameter, Integer> onError)
    {
        boolean result = value <= lower;
        if (!result)
            onError.accept(this, lower);

        return result;
    }

    public boolean notAbove(int lower)
    {
        return notAbove(lower, errorHandler::notAbove);
    }

    public boolean isBelow(int upper, BiConsumer<IntParameter, Integer> onError)
    {
        boolean result = value < upper;
        if (!result)
            onError.accept(this, upper);

        return result;
    }

    public boolean isBelow(int upper)
    {
        return isBelow(upper, errorHandler::isBelow);
    }

    public boolean notBelow(int upper, BiConsumer<IntParameter, Integer> onError)
    {
        boolean result = value >= upper;
        if (!result)
            onError.accept(this, upper);

        return result;
    }

    public boolean notBelow(int upper)
    {
        return notBelow(upper, errorHandler::notBelow);
    }

    public boolean isBetween(int lower, int upper, boolean inclusive, QuadConsumer<IntParameter, Integer, Integer, Boolean> onError)
    {
        boolean result;

        if (inclusive)
            result = value >= lower && value <= upper;
        else
            result = value > lower && value < upper;

        if (!result)
            onError.accept(this, lower, upper, inclusive);

        return result;
    }

    public boolean isBetween(int lower, int upper, QuadConsumer<IntParameter, Integer, Integer, Boolean> onError)
    {
        return isBetween(lower, upper, true, onError);
    }

    public boolean isBetween(int lower, int upper, boolean inclusive)
    {
        return isBetween(lower, upper, inclusive, errorHandler::isBetween);
    }

    public boolean isBetween(int lower, int upper)
    {
        return isBetween(lower, upper, true);
    }

    public boolean notBetween(int lower, int upper, boolean inclusive, QuadConsumer<IntParameter, Integer, Integer, Boolean> onError)
    {
        boolean result;

        if (inclusive)
            result = value < lower || value > upper;
        else
            result = value <= lower || value >= upper;

        if (!result)
            onError.accept(this, lower, upper, inclusive);

        return result;
    }

    public boolean notBetween(int lower, int upper, QuadConsumer<IntParameter, Integer, Integer, Boolean> onError)
    {
        return notBetween(lower, upper, true, onError);
    }

    public boolean notBetween(int lower, int upper, boolean inclusive)
    {
        return notBetween(lower, upper, inclusive, errorHandler::notBetween);
    }

    public boolean notBetween(int lower, int upper)
    {
        return notBetween(lower, upper, true);
    }

    /**
     * Returns the value of this parameter.
     *
     * @return The value of this parameter. This method never return {@code null} values.
     */
    @Override public Integer getValue()
    {
        return value;
    }
}
