package tvestergaard.webhelpers.parameters;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.*;
import static tvestergaard.webhelpers.parameters.ComparableParameter.FailureHandler;

public abstract class ComparableParameterTest extends GenericParameterTest
{

    abstract <N, V extends Comparable<V>> ComparableParameter<N, V> getInstance(N name, V value);

    abstract <N, V extends Comparable<V>, H extends FailureHandler<N, V>> ComparableParameter<N, V> getInstance(N name, V value, H handler);

    abstract <N, V extends Comparable<V>, H extends FailureHandler<N, V>> ComparableParameter<N, V> getInstance(N name, V value, Iterable<? extends H> handlers);

    @Test
    public void isGreaterThan() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter;
        FailureHandler<Integer, Integer>      mock;

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5, mock);
        assertTrue(parameter.isGreaterThan(2));
        verify(mock, times(0)).isGreaterThanFailure(same(parameter), eq(2));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5, mock);
        assertFalse(parameter.isGreaterThan(5));
        verify(mock, times(0)).isGreaterThanFailure(same(parameter), eq(5));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullValueException.class)
    public void isGreaterThanThrowsNullValueException() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter = getInstance(null, null);
        parameter.isGreaterThan(5);
    }

    @Test
    public void isGreaterThanCallback() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter;
        FailureHandler<Integer, Integer>      mock;

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertTrue(parameter.isGreaterThan(2, mock));
        verify(mock, times(0)).isGreaterThanFailure(same(parameter), eq(2));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertFalse(parameter.isGreaterThan(5, mock));
        verify(mock, times(0)).isGreaterThanFailure(same(parameter), eq(5));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullValueException.class)
    public void isGreaterThanCallbackThrowsNullValueException() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter = getInstance(null, null);
        parameter.isGreaterThan(5, (a, b) -> {});
    }

    @Test
    public void isGreaterThanIterable() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter;
        FailureHandler<Integer, Integer>      mock;

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertTrue(parameter.isGreaterThan(2, list(mock)));
        verify(mock, times(0)).isGreaterThanFailure(same(parameter), eq(2));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertFalse(parameter.isGreaterThan(5, list(mock)));
        verify(mock, times(0)).isGreaterThanFailure(same(parameter), eq(5));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullValueException.class)
    public void isGreaterThanIterableThrowsNullValueException() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter = getInstance(null, null);
        parameter.isGreaterThan(5, list(null));
    }

    @Test
    public void notGreaterThan() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter;
        FailureHandler<Integer, Integer>      mock;

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5, mock);
        assertTrue(parameter.notGreaterThan(7));
        verify(mock, times(0)).notGreaterThanFailure(same(parameter), eq(7));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5, mock);
        assertFalse(parameter.notGreaterThan(4));
        verify(mock, times(0)).notGreaterThanFailure(same(parameter), eq(4));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullValueException.class)
    public void notGreaterThanThrowsNullValueException() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter = getInstance(null, null);
        parameter.notGreaterThan(5);
    }

    @Test
    public void notGreaterThanCallback() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter;
        FailureHandler<Integer, Integer>      mock;

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertTrue(parameter.notGreaterThan(7, mock));
        verify(mock, times(0)).notGreaterThanFailure(same(parameter), eq(7));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertFalse(parameter.notGreaterThan(2, mock));
        verify(mock, times(0)).notGreaterThanFailure(same(parameter), eq(2));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullValueException.class)
    public void notGreaterThanCallbackThrowsNullValueException() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter = getInstance(null, null);
        parameter.notGreaterThan(5, (a, b) -> {});
    }

    @Test
    public void notGreaterThanIterable() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter;
        FailureHandler<Integer, Integer>      mock;

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertTrue(parameter.notGreaterThan(7, list(mock)));
        verify(mock, times(0)).notGreaterThanFailure(same(parameter), eq(7));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertFalse(parameter.notGreaterThan(2, list(mock)));
        verify(mock, times(0)).notGreaterThanFailure(same(parameter), eq(2));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullValueException.class)
    public void notGreaterThanIterableThrowsNullValueException() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter = getInstance(null, null);
        parameter.notGreaterThan(5, list(null));
    }

    @Test
    public void isLessThan() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter;
        FailureHandler<Integer, Integer>      mock;

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5, mock);
        assertTrue(parameter.isLessThan(7));
        verify(mock, times(0)).isLessThanFailure(same(parameter), eq(7));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5, mock);
        assertFalse(parameter.isLessThan(5));
        verify(mock, times(0)).isLessThanFailure(same(parameter), eq(5));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullValueException.class)
    public void isLessThanThrowsNullValueException() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter = getInstance(null, null);
        parameter.isLessThan(5);
    }

    @Test
    public void isLessThanCallback() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter;
        FailureHandler<Integer, Integer>      mock;

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertTrue(parameter.isLessThan(7, mock));
        verify(mock, times(0)).isLessThanFailure(same(parameter), eq(7));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertFalse(parameter.isLessThan(5, mock));
        verify(mock, times(0)).isLessThanFailure(same(parameter), eq(5));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullValueException.class)
    public void isLessThanCallbackThrowsNullValueException() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter = getInstance(null, null);
        parameter.isLessThan(5, (a, b) -> {});
    }

    @Test
    public void isLessThanIterable() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter;
        FailureHandler<Integer, Integer>      mock;

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertTrue(parameter.isLessThan(7, list(mock)));
        verify(mock, times(0)).isLessThanFailure(same(parameter), eq(7));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertFalse(parameter.isLessThan(5, list(mock)));
        verify(mock, times(0)).isLessThanFailure(same(parameter), eq(5));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullValueException.class)
    public void isLessThanIterableThrowsNullValueException() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter = getInstance(null, null);
        parameter.isLessThan(5, list(null));
    }

    @Test
    public void notLessThan() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter;
        FailureHandler<Integer, Integer>      mock;

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5, mock);
        assertTrue(parameter.notLessThan(2));
        verify(mock, times(0)).notLessThanFailure(same(parameter), eq(2));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5, mock);
        assertFalse(parameter.notLessThan(6));
        verify(mock, times(0)).notLessThanFailure(same(parameter), eq(6));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullValueException.class)
    public void notLessThanThrowsNullValueException() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter = getInstance(null, null);
        parameter.notLessThan(5);
    }

    @Test
    public void notLessThanCallback() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter;
        FailureHandler<Integer, Integer>      mock;

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertTrue(parameter.notLessThan(2, mock));
        verify(mock, times(0)).notLessThanFailure(same(parameter), eq(2));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertFalse(parameter.notLessThan(6, mock));
        verify(mock, times(0)).notLessThanFailure(same(parameter), eq(6));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullValueException.class)
    public void notLessThanCallbackThrowsNullValueException() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter = getInstance(null, null);
        parameter.notLessThan(5, (a, b) -> {});
    }

    @Test
    public void notLessThanIterable() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter;
        FailureHandler<Integer, Integer>      mock;

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertTrue(parameter.notLessThan(2, list(mock)));
        verify(mock, times(0)).notLessThanFailure(same(parameter), eq(2));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(FailureHandler.class);
        parameter = getInstance(null, 5);
        assertFalse(parameter.notLessThan(6, list(mock)));
        verify(mock, times(0)).notLessThanFailure(same(parameter), eq(6));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullValueException.class)
    public void notLessThanIterableThrowsNullValueException() throws Exception
    {
        ComparableParameter<Integer, Integer> parameter = getInstance(null, null);
        parameter.notLessThan(5, list(null));
    }

    @Test
    public void isBetween() throws Exception
    {
    }

    @Test
    public void isBetween1() throws Exception
    {
    }

    @Test
    public void isBetween2() throws Exception
    {
    }

    @Test
    public void isBetween3() throws Exception
    {
    }

    @Test
    public void isBetween4() throws Exception
    {
    }

    @Test
    public void isBetween5() throws Exception
    {
    }

    @Test
    public void notBetween() throws Exception
    {
    }

    @Test
    public void notBetween1() throws Exception
    {
    }

    @Test
    public void notBetween2() throws Exception
    {
    }

    @Test
    public void notBetween3() throws Exception
    {
    }

    @Test
    public void notBetween4() throws Exception
    {
    }

    @Test
    public void notBetween5() throws Exception
    {
    }
}