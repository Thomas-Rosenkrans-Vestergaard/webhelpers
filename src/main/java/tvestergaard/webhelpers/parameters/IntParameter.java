package tvestergaard.webhelpers.parameters;

import java.util.List;

public class IntParameter extends AbstractParameter
{

    /**
     * The internal value of the {@link IntParameter}.
     */
    private final int value;

    /**
     * The registered list of {@link IntParameterErrorHandler} instances. These objects are notified when checks in the
     * {@link TextParameter} fails.
     */
    private final List<IntParameterErrorHandler> errorHandlers;
    private final IntParameterErrorHandler[]     errorHandlersArray;
    private int errorCount = 0;

    public IntParameter(String name, int value, List<IntParameterErrorHandler> errorHandlers)
    {
        super(name);
        this.value = value;
        this.errorHandlers = errorHandlers;
        this.errorHandlersArray = errorHandlers.toArray(new IntParameterErrorHandler[errorHandlers.size()]);
    }

    @FunctionalInterface public interface IsErrorCallback
    {
        boolean isError(IntParameter parameter, int other);
    }

    public boolean is(int other, IsErrorCallback... errorCallbacks)
    {
        boolean result = value == other;
        if (!result) {
            errorCount++;
            for (IsErrorCallback errorCallback : errorCallbacks)
                errorCallback.isError(this, other);
        }

        return result;
    }

    public boolean is(int other)
    {
        return is(other, errorHandlersArray);
    }

    @FunctionalInterface public interface NotErrorCallback
    {
        boolean notError(IntParameter parameter, int other);
    }

    public boolean not(int other, NotErrorCallback... errorCallbacks)
    {
        boolean result = value != other;
        if (!result) {
            errorCount++;
            for (NotErrorCallback errorCallback : errorCallbacks)
                errorCallback.notError(this, other);
        }

        return result;
    }

    public boolean not(int other)
    {
        return not(other, errorHandlersArray);
    }

    @FunctionalInterface public interface IsPositiveError
    {
        boolean isPositiveError(IntParameter parameter);
    }

    public boolean isPositive(IsPositiveError... errorCallbacks)
    {
        boolean result = value > 0;
        if (!result) {
            errorCount++;
            for (IsPositiveError errorCallback : errorCallbacks)
                errorCallback.isPositiveError(this);
        }

        return result;
    }

    public boolean isPositive()
    {
        return isPositive(errorHandlersArray);
    }

    @FunctionalInterface public interface NotPositiveError
    {
        boolean notPositiveError(IntParameter parameter);
    }

    public boolean notPositive(NotPositiveError... errorCallbacks)
    {
        boolean result = value <= 0;
        if (!result) {
            errorCount++;
            for (NotPositiveError errorCallback : errorCallbacks)
                errorCallback.notPositiveError(this);
        }

        return result;
    }

    public boolean notPositive()
    {
        return notPositive(errorHandlersArray);
    }

    @FunctionalInterface public interface IsNegativeErrorCallback
    {
        boolean isNegativeError(IntParameter parameter);
    }

    public boolean isNegative(IsNegativeErrorCallback... errorCallbacks)
    {
        boolean result = value < 0;
        if (!result) {
            errorCount++;
            for (IsNegativeErrorCallback errorCallback : errorCallbacks)
                errorCallback.isNegativeError(this);
        }

        return result;
    }

    public boolean isNegative()
    {
        return isNegative(errorHandlersArray);
    }

    @FunctionalInterface public interface NotNegativeErrorCallback
    {
        boolean notNegativeError(IntParameter parameter);
    }

    public boolean notNegative(NotNegativeErrorCallback... errorCallbacks)
    {
        boolean result = value >= 0;
        if (!result) {
            errorCount++;
            for (NotNegativeErrorCallback errorCallback : errorCallbacks)
                errorCallback.notNegativeError(this);
        }

        return result;
    }

    public boolean notNegative()
    {
        return notNegative(errorHandlersArray);
    }

    @FunctionalInterface public interface IsGreaterThanErrorCallback
    {
        boolean isGreaterThanError(IntParameter parameter);
    }

    public boolean isGreaterThan(int lower, IsGreaterThanErrorCallback... errorCallbacks)
    {
        boolean result = value > lower;
        if (!result) {
            errorCount++;
            for (IsGreaterThanErrorCallback errorCallback : errorCallbacks)
                errorCallback.isGreaterThanError(this);
        }

        return result;
    }

    public boolean isGreaterThan(int lower)
    {
        return isGreaterThan(lower, errorHandlersArray);
    }

    @FunctionalInterface public interface NotGreaterThanErrorCallback
    {
        boolean notGreaterThanError(IntParameter parameter);
    }

    public boolean notGreaterThan(int lower, NotGreaterThanErrorCallback... errorCallbacks)
    {
        boolean result = value <= lower;
        if (!result) {
            errorCount++;
            for (NotGreaterThanErrorCallback errorCallback : errorCallbacks)
                errorCallback.notGreaterThanError(this);
        }

        return result;
    }

    public boolean notGreaterThan(int lower)
    {
        return notGreaterThan(lower, errorHandlersArray);
    }

    @FunctionalInterface public interface IsLessThanErrorCallback
    {
        boolean isLessThanError(IntParameter parameter);
    }

    public boolean isLessThan(int upper, IsLessThanErrorCallback... errorCallbacks)
    {
        boolean result = value < upper;
        if (!result) {
            errorCount++;
            for (IsLessThanErrorCallback errorCallback : errorCallbacks)
                errorCallback.isLessThanError(this);
        }

        return result;
    }

    public boolean isLessThan(int upper)
    {
        return isLessThan(upper, errorHandlersArray);
    }

    @FunctionalInterface public interface NotLessThanErrorCallback
    {
        boolean notLessThanError(IntParameter parameter);
    }


    public boolean notLessThan(int upper, NotLessThanErrorCallback... errorCallbacks)
    {
        boolean result = value >= upper;
        if (!result) {
            errorCount++;
            for (NotLessThanErrorCallback errorCallback : errorCallbacks)
                errorCallback.notLessThanError(this);
        }

        return result;
    }

    public boolean notLessThan(int upper)
    {
        return notLessThan(upper, errorHandlersArray);
    }

    @FunctionalInterface public interface IsBetweenErrorCallback
    {
        boolean isBetweenError(IntParameter parameter, int lower, int upper, boolean inclusive);
    }

    public boolean isBetween(int lower, int upper, boolean inclusive, IsBetweenErrorCallback... errorCallbacks)
    {
        boolean result;

        if (inclusive)
            result = value >= lower && value <= upper;
        else
            result = value > lower && value < upper;

        if (!result) {
            errorCount++;
            for (IsBetweenErrorCallback errorCallback : errorCallbacks)
                errorCallback.isBetweenError(this, lower, upper, inclusive);
        }

        return result;
    }

    public boolean isBetween(int lower, int upper, IsBetweenErrorCallback... errorCallbacks)
    {
        return isBetween(lower, upper, true, errorCallbacks);
    }

    public boolean isBetween(int lower, int upper, boolean inclusive)
    {
        return isBetween(lower, upper, inclusive, errorHandlersArray);
    }

    public boolean isBetween(int lower, int upper)
    {
        return isBetween(lower, upper, true);
    }

    @FunctionalInterface public interface NotBetweenErrorCallback
    {
        boolean notBetweenError(IntParameter parameter, int lower, int upper, boolean inclusive);
    }

    public boolean notBetween(int lower, int upper, boolean inclusive, NotBetweenErrorCallback... errorCallbacks)
    {
        boolean result;

        if (inclusive)
            result = value < lower || value > upper;
        else
            result = value <= lower || value >= upper;

        if (!result) {
            errorCount++;
            for (NotBetweenErrorCallback errorCallback : errorCallbacks)
                errorCallback.notBetweenError(this, lower, upper, inclusive);
        }

        return result;
    }

    public boolean notBetween(int lower, int upper, NotBetweenErrorCallback... errorCallbacks)
    {
        return notBetween(lower, upper, true, errorCallbacks);
    }

    public boolean notBetween(int lower, int upper, boolean inclusive)
    {
        return notBetween(lower, upper, inclusive, errorHandlersArray);
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

    /**
     * Returns the number of errors that occurred while performing checks on this {@link Parameter}.
     *
     * @return The number of errors.
     */
    @Override public int getErrorCount()
    {
        return errorCount;
    }
}
