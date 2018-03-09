package tvestergaard.webhelpers.parameters;

public interface IntParameterErrorHandler extends IntParameter.IsFailureCallback,
                                                  IntParameter.NotFailureCallback,
                                                  IntParameter.IsPositiveFailureCallback,
                                                  IntParameter.NotPositiveFailureCallback,
                                                  IntParameter.IsNegativeFailureCallback,
                                                  IntParameter.NotNegativeFailureCallback,
                                                  IntParameter.IsGreaterThanFailureCallback,
                                                  IntParameter.NotGreaterThanFailureCallback,
                                                  IntParameter.IsLessThanFailureCallback,
                                                  IntParameter.NotLessThanFailureCallback,
                                                  IntParameter.IsBetweenFailureCallback,
                                                  IntParameter.NotBetweenFailureCallback

{

}
