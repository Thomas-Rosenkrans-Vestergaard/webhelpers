package tvestergaard.webhelpers.parameters;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
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
        TextParameter                           parameter;
        BiConsumer<TextParameter, CharSequence> consumer;
        CharSequence                            other;

        parameter = new TextParameter("name", "a");
        consumer = Mockito.spy(BiConsumer.class);
        other = "a";
        assertTrue(parameter.is(other, consumer));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter), same(other));

        parameter = new TextParameter("name", "a");
        consumer = Mockito.spy(BiConsumer.class);
        other = "b";
        assertFalse(parameter.is(other, consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter), same(other));
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
        Mockito.verify(mock, Mockito.times(0)).is(same(parameter), same(other));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "a", mock);
        other = "b";
        assertFalse(parameter.is(other));
        Mockito.verify(mock, Mockito.times(1)).is(same(parameter), same(other));
    }

    @Test
    public void notConsumer() throws Exception
    {
        TextParameter                           parameter;
        BiConsumer<TextParameter, CharSequence> consumer;
        CharSequence                            other;

        parameter = new TextParameter("name", "a");
        consumer = Mockito.spy(BiConsumer.class);
        other = "b";
        assertTrue(parameter.not(other, consumer));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter), same(other));

        parameter = new TextParameter("name", "a");
        consumer = Mockito.spy(BiConsumer.class);
        other = "a";
        assertFalse(parameter.not(other, consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter), same(other));
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
        Mockito.verify(mock, Mockito.times(0)).not(same(parameter), same(other));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "a", mock);
        other = "a";
        assertFalse(parameter.not(other));
        Mockito.verify(mock, Mockito.times(1)).not(same(parameter), same(other));
    }

    @Test
    public void isEmptyConsumer() throws Exception
    {
        TextParameter           parameter;
        Consumer<TextParameter> consumer;

        parameter = new TextParameter("name", "");
        consumer = Mockito.spy(Consumer.class);
        assertTrue(parameter.isEmpty(consumer));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter));

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(Consumer.class);
        assertFalse(parameter.isEmpty(consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter));
    }

    @Test
    public void isEmptyNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "", mock);
        assertTrue(parameter.isEmpty());
        Mockito.verify(mock, Mockito.times(0)).isEmpty(same(parameter));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertFalse(parameter.isEmpty());
        Mockito.verify(mock, Mockito.times(1)).isEmpty(same(parameter));
    }

    @Test
    public void notEmptyConsumer() throws Exception
    {
        TextParameter           parameter;
        Consumer<TextParameter> consumer;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(Consumer.class);
        assertTrue(parameter.notEmpty(consumer));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter));

        parameter = new TextParameter("name", "");
        consumer = Mockito.spy(Consumer.class);
        assertFalse(parameter.notEmpty(consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter));
    }

    @Test
    public void notEmptyNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertTrue(parameter.notEmpty());
        Mockito.verify(mock, Mockito.times(0)).notEmpty(same(parameter));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "", mock);
        assertFalse(parameter.notEmpty());
        Mockito.verify(mock, Mockito.times(1)).notEmpty(same(parameter));
    }

    @Test
    public void isLengthConsumer() throws Exception
    {
        TextParameter                      parameter;
        BiConsumer<TextParameter, Integer> consumer;

        parameter = new TextParameter("name", "");
        consumer = Mockito.spy(BiConsumer.class);
        assertTrue(parameter.isLength(0, consumer));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter), any(Integer.class));

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        assertFalse(parameter.isLength(6, consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter), eq(6));
    }

    @Test
    public void isLengthNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "", mock);
        assertTrue(parameter.isLength(0));
        Mockito.verify(mock, Mockito.times(0)).isLength(same(parameter), any(Integer.class));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertFalse(parameter.isLength(1));
        Mockito.verify(mock, Mockito.times(1)).isLength(same(parameter), eq(1));
    }

    @Test
    public void notLengthConsumer() throws Exception
    {
        TextParameter                      parameter;
        BiConsumer<TextParameter, Integer> consumer;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        assertTrue(parameter.notLength(0, consumer));
        assertTrue(parameter.notLength(1, consumer));
        assertTrue(parameter.notLength(4, consumer));
        assertTrue(parameter.notLength(6, consumer));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter), any(Integer.class));

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        assertFalse(parameter.notLength(5, consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter), eq(5));
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
        Mockito.verify(mock, Mockito.times(0)).notLength(same(parameter), any(Integer.class));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertFalse(parameter.notLength(5));
        Mockito.verify(mock, Mockito.times(1)).notLength(same(parameter), eq(5));
    }

    @Test
    public void isShorterConsumer() throws Exception
    {
        TextParameter                      parameter;
        BiConsumer<TextParameter, Integer> consumer;

        parameter = new TextParameter("name", "");
        consumer = Mockito.spy(BiConsumer.class);
        assertTrue(parameter.isShorterThan(1, consumer));
        assertTrue(parameter.isShorterThan(2, consumer));
        assertTrue(parameter.isShorterThan(10, consumer));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter), any(Integer.class));

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        assertFalse(parameter.isShorterThan(0, consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter), eq(0));
    }

    @Test
    public void isShorterNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "", mock);
        assertTrue(parameter.isShorterThan(1));
        Mockito.verify(mock, Mockito.times(0)).isShorterThan(same(parameter), any(Integer.class));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertFalse(parameter.isShorterThan(0));
        Mockito.verify(mock, Mockito.times(1)).isShorterThan(same(parameter), eq(0));
    }

    @Test
    public void notShorterThanConsumer() throws Exception
    {
        TextParameter                      parameter;
        BiConsumer<TextParameter, Integer> consumer;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        assertTrue(parameter.notShorterThan(1));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter), any(Integer.class));

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        assertFalse(parameter.isShorterThan(5, consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter), eq(5));
    }

    @Test
    public void notShorterThanNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertTrue(parameter.notShorterThan(1));
        Mockito.verify(mock, Mockito.times(0)).notShorter(same(parameter), any(Integer.class));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertFalse(parameter.notShorterThan(7));
        Mockito.verify(mock, Mockito.times(1)).notShorter(same(parameter), eq(7));
    }

    @Test
    public void isLongerThanConsumer() throws Exception
    {
        TextParameter                      parameter;
        BiConsumer<TextParameter, Integer> consumer;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        assertTrue(parameter.isLongerThan(1));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter), any(Integer.class));

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        assertFalse(parameter.isLongerThan(6, consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter), eq(6));
    }

    @Test
    public void isLongerThanNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertTrue(parameter.isLongerThan(1));
        Mockito.verify(mock, Mockito.times(0)).isLongerThan(same(parameter), any(Integer.class));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertFalse(parameter.isLongerThan(7));
        Mockito.verify(mock, Mockito.times(1)).isLongerThan(same(parameter), eq(7));
    }

    @Test
    public void notLongerThanConsumer() throws Exception
    {
        TextParameter                      parameter;
        BiConsumer<TextParameter, Integer> consumer;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        assertTrue(parameter.notLongerThan(6));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter), any(Integer.class));

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        assertFalse(parameter.notLongerThan(4, consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter), eq(4));
    }

    @Test
    public void notLongerThanNoConsumer() throws Exception
    {
        TextParameter             parameter;
        TextParameterErrorHandler mock;

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertTrue(parameter.notLongerThan(6));
        Mockito.verify(mock, Mockito.times(0)).notLongerThan(same(parameter), any(Integer.class));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        assertFalse(parameter.notLongerThan(4));
        Mockito.verify(mock, Mockito.times(1)).notLongerThan(same(parameter), eq(4));
    }

    @Test
    public void isMatchConsumer() throws Exception
    {
        TextParameter                      parameter;
        BiConsumer<TextParameter, Pattern> consumer;
        Pattern                            pattern;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        pattern = Pattern.compile("^val");
        assertTrue(parameter.isMatch(pattern, consumer));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter), same(pattern));

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        pattern = Pattern.compile("^$");
        assertFalse(parameter.isMatch(pattern, consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter), same(pattern));
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
        Mockito.verify(mock, Mockito.times(0)).isMatch(same(parameter), same(pattern));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        pattern = Pattern.compile("^$");
        assertFalse(parameter.isMatch(pattern));
        Mockito.verify(mock, Mockito.times(1)).isMatch(same(parameter), same(pattern));
    }

    @Test
    public void notMatchConsumer() throws Exception
    {
        TextParameter                      parameter;
        BiConsumer<TextParameter, Pattern> consumer;
        Pattern                            pattern;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        pattern = Pattern.compile("^$");
        assertTrue(parameter.notMatch(pattern, consumer));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter), same(pattern));

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        pattern = Pattern.compile("^val");
        assertFalse(parameter.notMatch(pattern, consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter), same(pattern));
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
        Mockito.verify(mock, Mockito.times(0)).notMatch(same(parameter), same(pattern));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        pattern = Pattern.compile("^val");
        assertFalse(parameter.notMatch(pattern));
        Mockito.verify(mock, Mockito.times(1)).notMatch(same(parameter), same(pattern));
    }

    @Test
    public void isInConsumer() throws Exception
    {
        TextParameter                                           parameter;
        BiConsumer<TextParameter, List<? extends CharSequence>> consumer;
        List<String>                                            list;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        list = list("a", "b", "value");
        assertTrue(parameter.isIn(list, consumer));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter), same(list));

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        list = list("a", "b");
        assertFalse(parameter.isIn(list, consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter), same(list));
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
        Mockito.verify(mock, Mockito.times(0)).isIn(same(parameter), same(list));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        list = list("a", "b");
        assertFalse(parameter.isIn(list));
        Mockito.verify(mock, Mockito.times(1)).isIn(same(parameter), same(list));
    }

    @Test
    public void notInConsumer() throws Exception
    {
        TextParameter                                                     parameter;
        TriConsumer<TextParameter, List<? extends CharSequence>, Integer> consumer;
        List<String>                                                      list;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TriConsumer.class);
        list = list("a", "b");
        assertTrue(parameter.notIn(list, consumer));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter), same(list), any(Integer.class));

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(TriConsumer.class);
        list = list("a", "b", "value");
        assertFalse(parameter.notIn(list, consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter), same(list), eq(2));
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
        Mockito.verify(mock, Mockito.times(0)).notIn(same(parameter), same(list), any(Integer.class));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        list = list("a", "b", "value");
        assertFalse(parameter.notIn(list));
        Mockito.verify(mock, Mockito.times(1)).notIn(same(parameter), same(list), eq(2));
    }

    @Test
    public void containsConsumer() throws Exception
    {
        TextParameter                           parameter;
        BiConsumer<TextParameter, CharSequence> consumer;
        String                                  sub;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        sub = "val";
        assertTrue(parameter.contains(sub, consumer));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter), same(sub));

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        sub = "value2";
        assertFalse(parameter.contains(sub, consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter), same(sub));
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
        assertTrue(parameter.contains(sub));
        Mockito.verify(mock, Mockito.times(0)).contains(same(parameter), same(sub));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        sub = "value2";
        assertFalse(parameter.contains(sub));
        Mockito.verify(mock, Mockito.times(1)).contains(same(parameter), same(sub));
    }

    @Test
    public void notContainsConsumer() throws Exception
    {
        TextParameter                           parameter;
        BiConsumer<TextParameter, CharSequence> consumer;
        String                                  sub;

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        sub = "value2";
        assertTrue(parameter.notContains(sub, consumer));
        Mockito.verify(consumer, Mockito.times(0)).accept(same(parameter), same(sub));

        parameter = new TextParameter("name", "value");
        consumer = Mockito.spy(BiConsumer.class);
        sub = "val";
        assertFalse(parameter.notContains(sub, consumer));
        Mockito.verify(consumer, Mockito.times(1)).accept(same(parameter), same(sub));
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
        assertTrue(parameter.notContains(sub));
        Mockito.verify(mock, Mockito.times(0)).notContains(same(parameter), same(sub));

        mock = Mockito.spy(errorHandler);
        parameter = new TextParameter("name", "value", mock);
        sub = "val";
        assertFalse(parameter.notContains(sub));
        Mockito.verify(mock, Mockito.times(1)).notContains(same(parameter), same(sub));
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