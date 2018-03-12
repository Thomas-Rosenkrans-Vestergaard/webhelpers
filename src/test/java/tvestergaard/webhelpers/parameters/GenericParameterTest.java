package tvestergaard.webhelpers.parameters;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.*;
import static tvestergaard.webhelpers.parameters.GenericParameter.IsPresentFailureCallback;

public class GenericParameterTest
{

    private ParameterFactory<Integer, Integer, ? extends GenericParameter<Integer, Integer>> factory;

    public GenericParameterTest(ParameterFactory<Integer, Integer, ? extends GenericParameter<Integer, Integer>> factory)
    {
        this.factory = factory;
    }

    @Test
    public void isPresent() throws Exception
    {
        GenericParameter<Integer, Integer>                parameter;
        GenericParameter.FailureHandler<Integer, Integer> mock;

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 6, mock);
        assertTrue(parameter.isPresent());
        verify(mock, times(0)).isPresentFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, null, mock);
        assertFalse(parameter.isPresent());
        verify(mock, times(1)).isPresentFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test
    public void isPresentCallback() throws Exception
    {
        GenericParameter<Integer, Integer>         parameter;
        IsPresentFailureCallback<Integer, Integer> mock;

        mock = mock(IsPresentFailureCallback.class);
        parameter = new GenericParameter(null, 6);
        assertTrue(parameter.isPresent(mock));
        verify(mock, times(0)).isPresentFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(IsPresentFailureCallback.class);
        parameter = factory.getInstance(null, null);
        assertFalse(parameter.isPresent(mock));
        verify(mock, times(1)).isPresentFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test
    public void isPresentIterable() throws Exception
    {
        GenericParameter<Integer, Integer>         parameter;
        IsPresentFailureCallback<Integer, Integer> mock;

        mock = mock(IsPresentFailureCallback.class);
        parameter = new GenericParameter(null, 6);
        assertTrue(parameter.isPresent(list(mock)));
        verify(mock, times(0)).isPresentFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(IsPresentFailureCallback.class);
        parameter = factory.getInstance(null, null);
        assertFalse(parameter.isPresent(list(mock)));
        verify(mock, times(1)).isPresentFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test
    public void notPresent() throws Exception
    {
        GenericParameter<Integer, Integer>                parameter;
        GenericParameter.FailureHandler<Integer, Integer> mock;

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, null, mock);
        assertTrue(parameter.notPresent());
        verify(mock, times(0)).notPresentFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 5, mock);
        assertFalse(parameter.notPresent());
        verify(mock, times(1)).notPresentFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test
    public void notPresentCallback() throws Exception
    {
        GenericParameter<Integer, Integer>                           parameter;
        GenericParameter.NotPresentFailureCallback<Integer, Integer> mock;

        mock = mock(GenericParameter.NotPresentFailureCallback.class);
        parameter = new GenericParameter(null, null);
        assertTrue(parameter.notPresent(mock));
        verify(mock, times(0)).notPresentFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.NotPresentFailureCallback.class);
        parameter = factory.getInstance(null, 5);
        assertFalse(parameter.notPresent(mock));
        verify(mock, times(1)).notPresentFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test
    public void notPresentIterable() throws Exception
    {
        GenericParameter<Integer, Integer>                           parameter;
        GenericParameter.NotPresentFailureCallback<Integer, Integer> mock;

        mock = mock(GenericParameter.NotPresentFailureCallback.class);
        parameter = new GenericParameter(null, null);
        assertTrue(parameter.notPresent(list(mock)));
        verify(mock, times(0)).notPresentFailure(same(parameter));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.NotPresentFailureCallback.class);
        parameter = factory.getInstance(null, 5);
        assertFalse(parameter.notPresent(list(mock)));
        verify(mock, times(1)).notPresentFailure(same(parameter));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test
    public void isEqual() throws Exception
    {
        GenericParameter<Integer, Integer>                parameter;
        GenericParameter.FailureHandler<Integer, Integer> mock;

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 6, mock);
        assertTrue(parameter.isEqual(6));
        verify(mock, times(0)).isEqualFailure(same(parameter), eq(6));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 5, mock);
        assertFalse(parameter.isEqual(6));
        verify(mock, times(1)).isEqualFailure(same(parameter), eq(6));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void isEqualThrowsNullValueException() throws Exception
    {
        GenericParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.isEqual(5);
    }

    @Test
    public void isEqualCallback() throws Exception
    {
        GenericParameter<Integer, Integer>                        parameter;
        GenericParameter.IsEqualFailureCallback<Integer, Integer> mock;

        mock = mock(GenericParameter.IsEqualFailureCallback.class);
        parameter = new GenericParameter(null, 6);
        assertTrue(parameter.isEqual(6, mock));
        verify(mock, times(0)).isEqualFailure(same(parameter), eq(6));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.IsEqualFailureCallback.class);
        parameter = factory.getInstance(null, 5);
        assertFalse(parameter.isEqual(6, mock));
        verify(mock, times(1)).isEqualFailure(same(parameter), eq(6));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void isEqualCallbackThrowsNullValueException() throws Exception
    {
        GenericParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.isEqual(5, (a, b) -> {});
    }

    @Test
    public void isEqualIterable() throws Exception
    {
        GenericParameter<Integer, Integer>                        parameter;
        GenericParameter.IsEqualFailureCallback<Integer, Integer> mock;

        mock = mock(GenericParameter.IsEqualFailureCallback.class);
        parameter = new GenericParameter(null, 6);
        assertTrue(parameter.isEqual(6, list(mock)));
        verify(mock, times(0)).isEqualFailure(same(parameter), eq(6));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.IsEqualFailureCallback.class);
        parameter = factory.getInstance(null, 5);
        assertFalse(parameter.isEqual(6, list(mock)));
        verify(mock, times(1)).isEqualFailure(same(parameter), eq(6));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void isEqualIterableThrowsNullValueException() throws Exception
    {
        GenericParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.isEqual(5, list(null));
    }

    @Test
    public void notEqual() throws Exception
    {
        GenericParameter<Integer, Integer>                parameter;
        GenericParameter.FailureHandler<Integer, Integer> mock;

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 5, mock);
        assertTrue(parameter.notEqual(6));
        verify(mock, times(0)).notEqualFailure(same(parameter), eq(6));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 6, mock);
        assertFalse(parameter.notEqual(6));
        verify(mock, times(1)).notEqualFailure(same(parameter), eq(6));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void notEqualThrowsNullValueException() throws Exception
    {
        GenericParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.notEqual(5);
    }

    @Test
    public void notEqualCallback() throws Exception
    {
        GenericParameter<Integer, Integer>                         parameter;
        GenericParameter.NotEqualFailureCallback<Integer, Integer> mock;

        mock = mock(GenericParameter.NotEqualFailureCallback.class);
        parameter = new GenericParameter(null, 5);
        assertTrue(parameter.notEqual(6, mock));
        verify(mock, times(0)).notEqualFailure(same(parameter), eq(6));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.NotEqualFailureCallback.class);
        parameter = factory.getInstance(null, 6);
        assertFalse(parameter.notEqual(6, mock));
        verify(mock, times(1)).notEqualFailure(same(parameter), eq(6));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void notEqualCallbackThrowsNullValueException() throws Exception
    {
        GenericParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.notEqual(5, (a, b) -> {});
    }

    @Test
    public void notEqualIterable() throws Exception
    {
        GenericParameter<Integer, Integer>                         parameter;
        GenericParameter.NotEqualFailureCallback<Integer, Integer> mock;

        mock = mock(GenericParameter.NotEqualFailureCallback.class);
        parameter = new GenericParameter(null, 5);
        assertTrue(parameter.notEqual(6, list(mock)));
        verify(mock, times(0)).notEqualFailure(same(parameter), eq(6));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.NotEqualFailureCallback.class);
        parameter = factory.getInstance(null, 6);
        assertFalse(parameter.notEqual(6, list(mock)));
        verify(mock, times(1)).notEqualFailure(same(parameter), eq(6));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }


    @Test(expected = NullParameterValueException.class)
    public void notEqualIterableThrowsNullValueException() throws Exception
    {
        GenericParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.notEqual(5, list(null));
    }

    @Test
    public void isIn() throws Exception
    {
        GenericParameter<Integer, Integer>                parameter;
        GenericParameter.FailureHandler<Integer, Integer> mock;
        List<Integer>                                     integers = Arrays.asList(4, 5, 6);

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 6, mock);
        assertTrue(parameter.isIn(integers));
        verify(mock, times(0)).isInFailure(same(parameter), eq(integers));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 1, mock);
        assertFalse(parameter.isIn(integers));
        verify(mock, times(1)).isInFailure(same(parameter), eq(integers));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void isInThrowsNullValueException() throws Exception
    {
        GenericParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.isIn();
    }

    @Test
    public void isInCallback() throws Exception
    {
        GenericParameter<Integer, Integer>                parameter;
        GenericParameter.FailureHandler<Integer, Integer> mock;
        List<Integer>                                     integers = Arrays.asList(4, 5, 6);

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 6);
        assertTrue(parameter.isIn(integers, mock));
        verify(mock, times(0)).isInFailure(same(parameter), eq(integers));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 1);
        assertFalse(parameter.isIn(integers, mock));
        verify(mock, times(1)).isInFailure(same(parameter), eq(integers));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void isInCallbackThrowsNullValueException() throws Exception
    {
        GenericParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.isIn(list(1), (a, b) -> {});
    }

    @Test
    public void isInIterable() throws Exception
    {
        GenericParameter<Integer, Integer>                parameter;
        GenericParameter.FailureHandler<Integer, Integer> mock;
        List<Integer>                                     integers = Arrays.asList(4, 5, 6);

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 6);
        assertTrue(parameter.isIn(integers, list(mock)));
        verify(mock, times(0)).isInFailure(same(parameter), eq(integers));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 1);
        assertFalse(parameter.isIn(integers, list(mock)));
        verify(mock, times(1)).isInFailure(same(parameter), eq(integers));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void isInIterableThrowsNullValueException() throws Exception
    {
        GenericParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.isIn(list(1), list(null));
    }

    @Test
    public void isInVarargs() throws Exception
    {
        GenericParameter<Integer, Integer>                parameter;
        GenericParameter.FailureHandler<Integer, Integer> mock;
        Iterable<Integer>                                 integers = Arrays.asList(4, 5, 6);

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 6, mock);
        assertTrue(parameter.isIn(4, 5, 6));
        verify(mock, times(0)).isInFailure(same(parameter), eq(integers));
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 1, mock);
        assertFalse(parameter.isIn(4, 5, 6));
        verify(mock, times(1)).isInFailure(same(parameter), eq(integers));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void isInVarargsThrowsNullValueException() throws Exception
    {
        GenericParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.isIn(1, 2, 3);
    }

    @Test
    public void notIn() throws Exception
    {
        GenericParameter<Integer, Integer>                parameter;
        GenericParameter.FailureHandler<Integer, Integer> mock;
        List<Integer>                                     integers = Arrays.asList(4, 5, 6);

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 3, mock);
        assertTrue(parameter.notIn(integers));
        verify(mock, times(0)).notInFailure(same(parameter), eq(integers), anyInt());
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 5, mock);
        assertFalse(parameter.notIn(integers));
        verify(mock, times(1)).notInFailure(same(parameter), eq(integers), eq(1));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void notInThrowsNullValueException() throws Exception
    {
        GenericParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.notIn();
    }

    @Test
    public void notInCallback() throws Exception
    {
        GenericParameter<Integer, Integer>                parameter;
        GenericParameter.FailureHandler<Integer, Integer> mock;
        List<Integer>                                     integers = Arrays.asList(4, 5, 6);

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 3);
        assertTrue(parameter.notIn(integers, mock));
        verify(mock, times(0)).notInFailure(same(parameter), eq(integers), anyInt());
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 4);
        assertFalse(parameter.notIn(integers, mock));
        verify(mock, times(1)).notInFailure(same(parameter), eq(integers), eq(0));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void notInCallbackThrowsNullValueException() throws Exception
    {
        GenericParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.notIn(list(1), ((a, b, c) -> {}));
    }

    @Test
    public void notInIterable() throws Exception
    {
        GenericParameter<Integer, Integer>                parameter;
        GenericParameter.FailureHandler<Integer, Integer> mock;
        List<Integer>                                     integers = Arrays.asList(4, 5, 6);

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 2);
        assertTrue(parameter.notIn(integers, list(mock)));
        verify(mock, times(0)).notInFailure(same(parameter), eq(integers), anyInt());
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 6);
        assertFalse(parameter.notIn(integers, list(mock)));
        verify(mock, times(1)).notInFailure(same(parameter), eq(integers), eq(2));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void notInIterableThrowsNullValueException() throws Exception
    {
        GenericParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.notIn(list(1), list(null));
    }

    @Test
    public void notInVarargs() throws Exception
    {
        GenericParameter<Integer, Integer>                parameter;
        GenericParameter.FailureHandler<Integer, Integer> mock;
        List<Integer>                                     integers = Arrays.asList(4, 5, 6);

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 7, mock);
        assertTrue(parameter.notIn(4, 5, 6));
        verify(mock, times(0)).notInFailure(same(parameter), eq(integers), anyInt());
        assertEquals(0, parameter.getFailureCount());
        assertFalse(parameter.hasFailures());

        mock = mock(GenericParameter.FailureHandler.class);
        parameter = factory.getInstance(null, 5, mock);
        assertFalse(parameter.notIn(4, 5, 6));
        verify(mock, times(1)).notInFailure(same(parameter), eq(integers), eq(1));
        assertEquals(1, parameter.getFailureCount());
        assertTrue(parameter.hasFailures());
    }

    @Test(expected = NullParameterValueException.class)
    public void notInVarargsThrowsNullValueException() throws Exception
    {
        GenericParameter<Integer, Integer> parameter = factory.getInstance(null, null);
        parameter.notIn(1, 2, 3);
    }

    protected final <T> List<T> list(T value)
    {
        List<T> list = new LinkedList<>();
        list.add(value);
        return list;
    }
}