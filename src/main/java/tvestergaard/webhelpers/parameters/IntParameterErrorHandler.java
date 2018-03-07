package tvestergaard.webhelpers.parameters;

public interface IntParameterErrorHandler
{
    void is(IntParameter parameter, Integer other);

    void not(IntParameter parameter, Integer other);

    void isPositive(IntParameter parameter);

    void notPositive(IntParameter parameter);

    void isNegative(IntParameter parameter);

    void notNegative(IntParameter parameter);

    void isAbove(IntParameter parameter, int other);

    void notAbove(IntParameter parameter, int other);

    void isBelow(IntParameter parameter, int other);

    void notBelow(IntParameter parameter, int other);

    void isBetween(IntParameter parameter, Integer lower, Integer upper, Boolean inclusive);

    void notBetween(IntParameter parameter, Integer lower, Integer upper, Boolean inclusive);
}
