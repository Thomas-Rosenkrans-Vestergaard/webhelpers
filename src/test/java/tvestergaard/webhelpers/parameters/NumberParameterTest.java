package tvestergaard.webhelpers.parameters;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.times;

public abstract class NumberParameterTest extends ComparableParameterTest
{

    private final ParameterFactory<Integer, Integer, ? extends NumberParameter<Integer, Integer>> factory;

    public NumberParameterTest(ParameterFactory<Integer, Integer, ? extends NumberParameter<Integer, Integer>> factory)
    {
        super(factory);
        this.factory = factory;
    }

    @Test
    public void isPositive() throws Exception
    {
        NumberParameter<Integer, Integer>                parameter;
        NumberParameter.FailureHandler<Integer, Integer> mock;

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 1, mock);
        assertTrue(parameter.isPositive());
        Mockito.verify(mock, times(0)).isPositiveFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 0, mock);
        assertFalse(parameter.isPositive());
        Mockito.verify(mock, times(1)).isPositiveFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, -1, mock);
        assertFalse(parameter.isPositive());
        Mockito.verify(mock, times(1)).isPositiveFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void isPositiveThrowsNullValueException() throws Exception
    {
        NumberParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.isPositive();
    }

    @Test
    public void isPositiveCallback() throws Exception
    {
        NumberParameter<Integer, Integer>                parameter;
        NumberParameter.FailureHandler<Integer, Integer> mock;

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 1);
        assertTrue(parameter.isPositive(mock));
        Mockito.verify(mock, times(0)).isPositiveFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 0);
        assertFalse(parameter.isPositive(mock));
        Mockito.verify(mock, times(1)).isPositiveFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, -1);
        assertFalse(parameter.isPositive(mock));
        Mockito.verify(mock, times(1)).isPositiveFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void isPositiveCallbackThrowsNullValueException() throws Exception
    {
        NumberParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.isPositive((NumberParameter.FailureHandler) null);
    }

    @Test
    public void isPositiveIterable() throws Exception
    {
        NumberParameter<Integer, Integer>                parameter;
        NumberParameter.FailureHandler<Integer, Integer> mock;

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 1);
        assertTrue(parameter.isPositive(list(mock)));
        Mockito.verify(mock, times(0)).isPositiveFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 0);
        assertFalse(parameter.isPositive(list(mock)));
        Mockito.verify(mock, times(1)).isPositiveFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, -1);
        assertFalse(parameter.isPositive(list(mock)));
        Mockito.verify(mock, times(1)).isPositiveFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void isPositiveIterableThrowsNullValueException() throws Exception
    {
        NumberParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.isPositive((Iterable) null);
    }

    @Test
    public void notPositive() throws Exception
    {
        NumberParameter<Integer, Integer>                parameter;
        NumberParameter.FailureHandler<Integer, Integer> mock;

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, -1, mock);
        assertTrue(parameter.notPositive());
        Mockito.verify(mock, times(0)).notPositiveFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 0, mock);
        assertTrue(parameter.notPositive());
        Mockito.verify(mock, times(1)).notPositiveFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 1, mock);
        assertFalse(parameter.notPositive());
        Mockito.verify(mock, times(1)).notPositiveFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void notPositiveThrowsNullValueException() throws Exception
    {
        NumberParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.notPositive();
    }

    @Test
    public void notPositiveCallback() throws Exception
    {
        NumberParameter<Integer, Integer>                parameter;
        NumberParameter.FailureHandler<Integer, Integer> mock;

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, -1);
        assertTrue(parameter.notPositive(mock));
        Mockito.verify(mock, times(0)).notPositiveFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 0);
        assertTrue(parameter.notPositive(mock));
        Mockito.verify(mock, times(1)).notPositiveFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 1);
        assertFalse(parameter.notPositive(mock));
        Mockito.verify(mock, times(1)).notPositiveFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void notPositiveCallbackThrowsNullValueException() throws Exception
    {
        NumberParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.notPositive((NumberParameter.FailureHandler) null);
    }

    @Test
    public void notPositiveIterable() throws Exception
    {
        NumberParameter<Integer, Integer>                parameter;
        NumberParameter.FailureHandler<Integer, Integer> mock;

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, -1);
        assertTrue(parameter.notPositive(list(mock)));
        Mockito.verify(mock, times(0)).notPositiveFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 0);
        assertTrue(parameter.notPositive(list(mock)));
        Mockito.verify(mock, times(1)).notPositiveFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 1);
        assertFalse(parameter.notPositive(list(mock)));
        Mockito.verify(mock, times(1)).notPositiveFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void notPositiveIterableThrowsNullValueException() throws Exception
    {
        NumberParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.notPositive((Iterable) null);
    }

    @Test
    public void isNegative() throws Exception
    {
        NumberParameter<Integer, Integer>                parameter;
        NumberParameter.FailureHandler<Integer, Integer> mock;

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, -1, mock);
        assertTrue(parameter.isNegative());
        Mockito.verify(mock, times(0)).isNegativeFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 0, mock);
        assertFalse(parameter.isNegative());
        Mockito.verify(mock, times(1)).isNegativeFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 1, mock);
        assertFalse(parameter.isNegative());
        Mockito.verify(mock, times(1)).isNegativeFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void isNegativeThrowsNullValueException() throws Exception
    {
        NumberParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.isNegative();
    }

    @Test
    public void isNegativeCallback() throws Exception
    {
        NumberParameter<Integer, Integer>                parameter;
        NumberParameter.FailureHandler<Integer, Integer> mock;

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, -1);
        assertTrue(parameter.isNegative(mock));
        Mockito.verify(mock, times(0)).isNegativeFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 0);
        assertFalse(parameter.isNegative(mock));
        Mockito.verify(mock, times(1)).isNegativeFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 1);
        assertFalse(parameter.isNegative(mock));
        Mockito.verify(mock, times(1)).isNegativeFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void isNegativeCallbackThrowsNullValueException() throws Exception
    {
        NumberParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.isNegative((NumberParameter.FailureHandler) null);
    }

    @Test
    public void isNegativeIterable() throws Exception
    {
        NumberParameter<Integer, Integer>                parameter;
        NumberParameter.FailureHandler<Integer, Integer> mock;

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, -1);
        assertTrue(parameter.isNegative(list(mock)));
        Mockito.verify(mock, times(0)).isNegativeFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 0);
        assertFalse(parameter.isNegative(list(mock)));
        Mockito.verify(mock, times(1)).isNegativeFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 1);
        assertFalse(parameter.isNegative(list(mock)));
        Mockito.verify(mock, times(1)).isNegativeFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void isNegativeIterableThrowsNullValueException() throws Exception
    {
        NumberParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.isNegative((Iterable) null);
    }

    @Test
    public void notNegative() throws Exception
    {
        NumberParameter<Integer, Integer>                parameter;
        NumberParameter.FailureHandler<Integer, Integer> mock;

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 1, mock);
        assertTrue(parameter.notNegative());
        Mockito.verify(mock, times(0)).notNegativeFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 0, mock);
        assertFalse(parameter.notNegative());
        Mockito.verify(mock, times(1)).notNegativeFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, -1, mock);
        assertFalse(parameter.notNegative());
        Mockito.verify(mock, times(1)).notNegativeFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void notNegativeThrowsNullValueException() throws Exception
    {
        NumberParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.notNegative();
    }

    @Test
    public void notNegativeCallback() throws Exception
    {
        NumberParameter<Integer, Integer>                parameter;
        NumberParameter.FailureHandler<Integer, Integer> mock;

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 1);
        assertTrue(parameter.notNegative(mock));
        Mockito.verify(mock, times(0)).notNegativeFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 0);
        assertFalse(parameter.notNegative(mock));
        Mockito.verify(mock, times(1)).notNegativeFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, -1);
        assertFalse(parameter.notNegative(mock));
        Mockito.verify(mock, times(1)).notNegativeFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void notNegativeCallbackThrowsNullValueException() throws Exception
    {
        NumberParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.notNegative((NumberParameter.FailureHandler) null);
    }

    @Test
    public void notNegativeIterable() throws Exception
    {
        NumberParameter<Integer, Integer>                parameter;
        NumberParameter.FailureHandler<Integer, Integer> mock;

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 1);
        assertTrue(parameter.notNegative(list(mock)));
        Mockito.verify(mock, times(0)).notNegativeFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, 0);
        assertFalse(parameter.notNegative(list(mock)));
        Mockito.verify(mock, times(1)).notNegativeFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());

        mock = Mockito.mock(NumberParameter.FailureHandler.class);
        parameter = factory.getInstance(0, -1);
        assertFalse(parameter.notNegative(list(mock)));
        Mockito.verify(mock, times(1)).notNegativeFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void notNegativeIterableThrowsNullValueException() throws Exception
    {
        NumberParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.notNegative((Iterable) null);
    }
}