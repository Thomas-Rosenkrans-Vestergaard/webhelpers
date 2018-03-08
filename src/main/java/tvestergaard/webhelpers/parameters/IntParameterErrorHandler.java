package tvestergaard.webhelpers.parameters;

public interface IntParameterErrorHandler extends IntParameter.IsErrorCallback,
                                                  IntParameter.NotErrorCallback,
                                                  IntParameter.IsPositiveError,
                                                  IntParameter.NotPositiveError,
                                                  IntParameter.IsNegativeErrorCallback,
                                                  IntParameter.NotNegativeErrorCallback,
                                                  IntParameter.IsGreaterThanErrorCallback,
                                                  IntParameter.NotGreaterThanErrorCallback,
                                                  IntParameter.IsLessThanErrorCallback,
                                                  IntParameter.NotLessThanErrorCallback,
                                                  IntParameter.IsBetweenErrorCallback,
                                                  IntParameter.NotBetweenErrorCallback

{

}
