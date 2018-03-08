package tvestergaard.webhelpers.parameters;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

public class TextParameterTest
{

    private final TextParameterErrorHandler errorHandler = new TextParameterErrorHandler()
    {

    };

    @Test
    public void isConsumer() throws Exception
    {
        TextParameter                   parameter;
        TextParameter.IsFailureCallback consumer;
        CharSequence                    other;

        parameter = new TextParameter("name", "a");
        consumer = Mockito.spy(TextParameter.IsFailureCallback.class);
        other = "a";
        assertTrue(parameter.is(other, consumer));
        Mockito.verify(consumer, Mockito.times(0)).isFailure(same(parameter), same(other));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "a");
        consumer = Mockito.spy(TextParameter.IsFailureCallback.class);
        other = "b";
        assertFalse(parameter.is(other, consumer));
        Mockito.verify(consumer, Mockito.times(1)).isFailure(same(parameter), same(other));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void isNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;
        CharSequence              other;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "a", mock);
        other = "a";
        assertTrue(parameter.is(other));
        Mockito.verify(mock, Mockito.times(0)).isFailure(same(parameter), same(other));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "a", mock);
        other = "b";
        assertFalse(parameter.is(other));
        Mockito.verify(mock, Mockito.times(1)).isFailure(same(parameter), same(other));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notConsumer() throws Exception
    {
        TextParameter                  parameter;
        TextParameter.NotErrorCallback consumer;
        CharSequence                   other;

        parameter = new TextParameter("name", "a");
        consumer = Mockito.spy(TextParameter.NotErrorCallback.class);
        other = "b";
        assertTrue(parameter.not(other, consumer));
        Mockito.verify(consumer, Mockito.times(0)).notError(same(parameter), same(other));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "a");
        consumer = Mockito.spy(TextParameter.NotErrorCallback.class);
        other = "a";
        assertFalse(parameter.not(other, consumer));
        Mockito.verify(consumer, Mockito.times(1)).notError(same(parameter), same(other));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;
        CharSequence              other;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "a", mock);
        other = "b";
        assertTrue(parameter.not(other));
        Mockito.verify(mock, Mockito.times(0)).notError(same(parameter), same(other));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "a", mock);
        other = "a";
        assertFalse(parameter.not(other));
        Mockito.verify(mock, Mockito.times(1)).notError(same(parameter), same(other));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void isEmptyConsumer() throws Exception
    {
        TextParameter                      parameter;
        TextParameter.IsEmptyErrorCallback consumer;

        parameter = new TextParameter("name", "");
        consumer = Mockito.spy(TextParameter.IsEmptyErrorCallback.class);
        assertTrue(parameter.isEmpty(consumer));
        Mockito.verify(consumer, Mockito.times(0)).isEmptyError(same(parameter));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.IsEmptyErrorCallback.class);
        assertFalse(parameter.isEmpty(consumer));
        Mockito.verify(consumer, Mockito.times(1)).isEmptyError(same(parameter));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void isEmptyNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "", mock);
        assertTrue(parameter.isEmpty());
        Mockito.verify(mock, Mockito.times(0)).isEmptyError(same(parameter));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertFalse(parameter.isEmpty());
        Mockito.verify(mock, Mockito.times(1)).isEmptyError(same(parameter));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notEmptyConsumer() throws Exception
    {
        TextParameter                       parameter;
        TextParameter.NotEmptyErrorCallback consumer;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.NotEmptyErrorCallback.class);
        assertTrue(parameter.notEmpty(consumer));
        Mockito.verify(consumer, Mockito.times(0)).notEmptyError(same(parameter));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "");
        consumer = Mockito.spy(TextParameter.NotEmptyErrorCallback.class);
        assertFalse(parameter.notEmpty(consumer));
        Mockito.verify(consumer, Mockito.times(1)).notEmptyError(same(parameter));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notEmptyNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertTrue(parameter.notEmpty());
        Mockito.verify(mock, Mockito.times(0)).notEmptyError(same(parameter));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "", mock);
        assertFalse(parameter.notEmpty());
        Mockito.verify(mock, Mockito.times(1)).notEmptyError(same(parameter));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void isLengthConsumer() throws Exception
    {
        TextParameter                       parameter;
        TextParameter.IsLengthErrorCallback consumer;

        parameter = new TextParameter("name", "");
        consumer = Mockito.spy(TextParameter.IsLengthErrorCallback.class);
        assertTrue(parameter.isLength(0, consumer));
        Mockito.verify(consumer, Mockito.times(0)).isLengthError(same(parameter), any(Integer.class));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.IsLengthErrorCallback.class);
        assertFalse(parameter.isLength(6, consumer));
        Mockito.verify(consumer, Mockito.times(1)).isLengthError(same(parameter), eq(6));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void isLengthNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "", mock);
        assertTrue(parameter.isLength(0));
        Mockito.verify(mock, Mockito.times(0)).isLengthError(same(parameter), any(Integer.class));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertFalse(parameter.isLength(1));
        Mockito.verify(mock, Mockito.times(1)).isLengthError(same(parameter), eq(1));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notLengthConsumer() throws Exception
    {
        TextParameter                        parameter;
        TextParameter.NotLengthErrorCallback consumer;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.NotLengthErrorCallback.class);
        assertTrue(parameter.notLength(0, consumer));
        Mockito.verify(consumer, Mockito.times(0)).notLengthError(same(parameter), any(Integer.class));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.NotLengthErrorCallback.class);
        assertFalse(parameter.notLength(5, consumer));
        Mockito.verify(consumer, Mockito.times(1)).notLengthError(same(parameter), eq(5));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notLengthNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertTrue(parameter.notLength(0));
        assertTrue(parameter.notLength(1));
        assertTrue(parameter.notLength(4));
        assertTrue(parameter.notLength(6));
        Mockito.verify(mock, Mockito.times(0)).notLengthError(same(parameter), any(Integer.class));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertFalse(parameter.notLength(5));
        Mockito.verify(mock, Mockito.times(1)).notLengthError(same(parameter), eq(5));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void isShorterConsumer() throws Exception
    {
        TextParameter                            parameter;
        TextParameter.IsShorterThanErrorCallback consumer;

        parameter = new TextParameter("name", "");
        consumer = Mockito.spy(TextParameter.IsShorterThanErrorCallback.class);
        assertTrue(parameter.isShorterThan(1, consumer));
        assertTrue(parameter.isShorterThan(2, consumer));
        assertTrue(parameter.isShorterThan(10, consumer));
        Mockito.verify(consumer, Mockito.times(0)).isShorterThanError(same(parameter), any(Integer.class));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.IsShorterThanErrorCallback.class);
        assertFalse(parameter.isShorterThan(0, consumer));
        Mockito.verify(consumer, Mockito.times(1)).isShorterThanError(same(parameter), eq(0));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void isShorterNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "", mock);
        assertTrue(parameter.isShorterThan(1));
        Mockito.verify(mock, Mockito.times(0)).isShorterThanError(same(parameter), any(Integer.class));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertFalse(parameter.isShorterThan(0));
        Mockito.verify(mock, Mockito.times(1)).isShorterThanError(same(parameter), eq(0));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notShorterThanConsumer() throws Exception
    {
        TextParameter                             parameter;
        TextParameter.NotShorterThanErrorCallback consumer;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.NotShorterThanErrorCallback.class);
        assertTrue(parameter.notShorterThan(1, consumer));
        Mockito.verify(consumer, Mockito.times(0)).notShorterThanError(same(parameter), any(Integer.class));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.NotShorterThanErrorCallback.class);
        assertFalse(parameter.notShorterThan(6, consumer));
        Mockito.verify(consumer, Mockito.times(1)).notShorterThanError(same(parameter), eq(6));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notShorterThanNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertTrue(parameter.notShorterThan(1));
        Mockito.verify(mock, Mockito.times(0)).notShorterThanError(same(parameter), any(Integer.class));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertFalse(parameter.notShorterThan(7));
        Mockito.verify(mock, Mockito.times(1)).notShorterThanError(same(parameter), eq(7));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void isLongerThanConsumer() throws Exception
    {
        TextParameter                           parameter;
        TextParameter.IsLongerThanErrorCallback consumer;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.IsLongerThanErrorCallback.class);
        assertTrue(parameter.isLongerThan(1, consumer));
        Mockito.verify(consumer, Mockito.times(0)).isLongerThanError(same(parameter), any(Integer.class));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.IsLongerThanErrorCallback.class);
        assertFalse(parameter.isLongerThan(6, consumer));
        Mockito.verify(consumer, Mockito.times(1)).isLongerThanError(same(parameter), eq(6));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void isLongerThanNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertTrue(parameter.isLongerThan(1));
        Mockito.verify(mock, Mockito.times(0)).isLongerThanError(same(parameter), any(Integer.class));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertFalse(parameter.isLongerThan(7));
        Mockito.verify(mock, Mockito.times(1)).isLongerThanError(same(parameter), eq(7));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notLongerThanConsumer() throws Exception
    {
        TextParameter                            parameter;
        TextParameter.NotLongerThanErrorCallback consumer;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.NotLongerThanErrorCallback.class);
        assertTrue(parameter.notLongerThan(6, consumer));
        Mockito.verify(consumer, Mockito.times(0)).notLongerThanError(same(parameter), any(Integer.class));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.NotLongerThanErrorCallback.class);
        assertFalse(parameter.notLongerThan(4, consumer));
        Mockito.verify(consumer, Mockito.times(1)).notLongerThanError(same(parameter), eq(4));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notLongerThanNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertTrue(parameter.notLongerThan(6));
        Mockito.verify(mock, Mockito.times(0)).notLongerThanError(same(parameter), any(Integer.class));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertFalse(parameter.notLongerThan(4));
        Mockito.verify(mock, Mockito.times(1)).notLongerThanError(same(parameter), eq(4));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void isMatchConsumer() throws Exception
    {
        TextParameter                      parameter;
        TextParameter.IsMatchErrorCallback consumer;
        Pattern                            pattern;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.IsMatchErrorCallback.class);
        pattern = Pattern.compile("^val");
        assertTrue(parameter.isMatch(pattern, consumer));
        Mockito.verify(consumer, Mockito.times(0)).isMatchError(same(parameter), same(pattern));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.IsMatchErrorCallback.class);
        pattern = Pattern.compile("^$");
        assertFalse(parameter.isMatch(pattern, consumer));
        Mockito.verify(consumer, Mockito.times(1)).isMatchError(same(parameter), same(pattern));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void isMatchNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;
        Pattern                   pattern;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        pattern = Pattern.compile("^val");
        assertTrue(parameter.isMatch(pattern));
        Mockito.verify(mock, Mockito.times(0)).isMatchError(same(parameter), same(pattern));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        pattern = Pattern.compile("^$");
        assertFalse(parameter.isMatch(pattern));
        Mockito.verify(mock, Mockito.times(1)).isMatchError(same(parameter), same(pattern));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notMatchConsumer() throws Exception
    {
        TextParameter                       parameter;
        TextParameter.NotMatchErrorCallback consumer;
        Pattern                             pattern;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.NotMatchErrorCallback.class);
        pattern = Pattern.compile("^$");
        assertTrue(parameter.notMatch(pattern, consumer));
        Mockito.verify(consumer, Mockito.times(0)).notMatchError(same(parameter), same(pattern));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.NotMatchErrorCallback.class);
        pattern = Pattern.compile("^val");
        assertFalse(parameter.notMatch(pattern, consumer));
        Mockito.verify(consumer, Mockito.times(1)).notMatchError(same(parameter), same(pattern));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notMatchNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;
        Pattern                   pattern;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        pattern = Pattern.compile("^$");
        assertTrue(parameter.notMatch(pattern));
        Mockito.verify(mock, Mockito.times(0)).notMatchError(same(parameter), same(pattern));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        pattern = Pattern.compile("^val");
        assertFalse(parameter.notMatch(pattern));
        Mockito.verify(mock, Mockito.times(1)).notMatchError(same(parameter), same(pattern));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void isInConsumer() throws Exception
    {
        TextParameter                   parameter;
        TextParameter.IsInErrorCallback consumer;
        List<String>                    list;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.IsInErrorCallback.class);
        list = list("a", "b", "value");
        assertTrue(parameter.isIn(list, consumer));
        Mockito.verify(consumer, Mockito.times(0)).isInError(same(parameter), same(list));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.IsInErrorCallback.class);
        list = list("a", "b");
        assertFalse(parameter.isIn(list, consumer));
        Mockito.verify(consumer, Mockito.times(1)).isInError(same(parameter), same(list));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void isInNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;
        List<String>              list;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        list = list("a", "b", "value");
        assertTrue(parameter.isIn(list));
        Mockito.verify(mock, Mockito.times(0)).isInError(same(parameter), same(list));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        list = list("a", "b");
        assertFalse(parameter.isIn(list));
        Mockito.verify(mock, Mockito.times(1)).isInError(same(parameter), same(list));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notInConsumer() throws Exception
    {
        TextParameter                    parameter;
        TextParameter.NotInErrorCallback consumer;
        List<String>                     list;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.NotInErrorCallback.class);
        list = list("a", "b");
        assertTrue(parameter.notIn(list, consumer));
        Mockito.verify(consumer, Mockito.times(0)).notInError(same(parameter), same(list), any(Integer.class));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.NotInErrorCallback.class);
        list = list("a", "b", "value");
        assertFalse(parameter.notIn(list, consumer));
        Mockito.verify(consumer, Mockito.times(1)).notInError(same(parameter), same(list), eq(2));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notInNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;
        List<String>              list;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        list = list("a", "b");
        assertTrue(parameter.notIn(list));
        Mockito.verify(mock, Mockito.times(0)).notInError(same(parameter), same(list), any(Integer.class));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        list = list("a", "b", "value");
        assertFalse(parameter.notIn(list));
        Mockito.verify(mock, Mockito.times(1)).notInError(same(parameter), same(list), eq(2));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void containsConsumer() throws Exception
    {
        TextParameter                          parameter;
        TextParameter.IsContainedErrorCallback consumer;
        String                                 sub;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.IsContainedErrorCallback.class);
        sub = "val";
        assertTrue(parameter.isContained(sub, consumer));
        Mockito.verify(consumer, Mockito.times(0)).isContainedError(same(parameter), same(sub));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.IsContainedErrorCallback.class);
        sub = "value2";
        assertFalse(parameter.isContained(sub, consumer));
        Mockito.verify(consumer, Mockito.times(1)).isContainedError(same(parameter), same(sub));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void containsNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;
        String                    sub;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        sub = "val";
        assertTrue(parameter.isContained(sub));
        Mockito.verify(mock, Mockito.times(0)).isContainedError(same(parameter), same(sub));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        sub = "value2";
        assertFalse(parameter.isContained(sub));
        Mockito.verify(mock, Mockito.times(1)).isContainedError(same(parameter), same(sub));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notContainsConsumer() throws Exception
    {
        TextParameter                           parameter;
        TextParameter.NotContainedErrorCallback consumer;
        String                                  sub;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.NotContainedErrorCallback.class);
        sub = "value2";
        assertTrue(parameter.notContained(sub, consumer));
        Mockito.verify(consumer, Mockito.times(0)).notContainedError(same(parameter), same(sub));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TextParameter.NotContainedErrorCallback.class);
        sub = "val";
        assertFalse(parameter.notContained(sub, consumer));
        Mockito.verify(consumer, Mockito.times(1)).notContainedError(same(parameter), same(sub));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void notContainsNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;
        String                    sub;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        sub = "value2";
        assertTrue(parameter.notContained(sub));
        Mockito.verify(mock, Mockito.times(0)).notContainedError(same(parameter), same(sub));
        assertEquals(0, parameter.getErrorCount());
        assertFalse(parameter.hasErrors());

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        sub = "val";
        assertFalse(parameter.notContained(sub));
        Mockito.verify(mock, Mockito.times(1)).notContainedError(same(parameter), same(sub));
        assertEquals(1, parameter.getErrorCount());
        assertTrue(parameter.hasErrors());
    }

    @Test
    public void getValue() throws Exception
    {
        TextParameter parameter;

        parameter = new TextParameter("name", "a");
        assertEquals("a", parameter.getValue());

        parameter = new TextParameter("name", "b");
        assertEquals("b", parameter.getValue());

        parameter = new TextParameter("name", "c");
        assertEquals("c", parameter.getValue());
    }

    @Test
    public void getName() throws Exception
    {
        TextParameter parameter;

        parameter = new TextParameter("a", "value");
        assertEquals("a", parameter.getName());

        parameter = new TextParameter("b", "value");
        assertEquals("b", parameter.getName());

        parameter = new TextParameter("c", "value");
        assertEquals("c", parameter.getName());
    }

    private <T> List<T> list(T... arguments)
    {
        return Arrays.asList(arguments);
    }
}