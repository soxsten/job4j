package Generic;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayTest {

    private SimpleArray<Object> simple;
    private Iterator<Object> iterator;

    @Test(expected = IndexOutOfBoundsException.class)
    public void add_should_trow_exception() {
        //given
        simple = new SimpleArray<>(10);
        addTenElements();

        //when
        simple.add("k");
    }

    @Test
    public void set_should_set_new_values_correctly() {
        //given
        simple = new SimpleArray<>(10);
        addTenElements();

        //when
        simple.set(0, "0");
        simple.set(1, "1");
        simple.set(2, "2");
        simple.set(3, "3");
        simple.set(4, "4");
        simple.set(5, "5");
        simple.set(6, "6");
        simple.set(7, "7");
        simple.set(8, "8");
        simple.set(9, "9");

        //then
        Assert.assertEquals("0", simple.get(0));
        Assert.assertEquals("1", simple.get(1));
        Assert.assertEquals("2", simple.get(2));
        Assert.assertEquals("3", simple.get(3));
        Assert.assertEquals("4", simple.get(4));
        Assert.assertEquals("5", simple.get(5));
        Assert.assertEquals("6", simple.get(6));
        Assert.assertEquals("7", simple.get(7));
        Assert.assertEquals("8", simple.get(8));
        Assert.assertEquals("9", simple.get(9));
    }

    @Test
    public void delete_should_set_variables_to_null() {
        //given
        simple = new SimpleArray<>(10);
        addTenElements();

        //when
        simple.delete(0);
        simple.delete(1);
        simple.delete(2);
        simple.delete(3);
        simple.delete(4);
        simple.delete(5);
        simple.delete(6);
        simple.delete(7);
        simple.delete(8);
        simple.delete(9);

        //then
        Assert.assertNull(simple.get(0));
        Assert.assertNull(simple.get(1));
        Assert.assertNull(simple.get(2));
        Assert.assertNull(simple.get(3));
        Assert.assertNull(simple.get(4));
        Assert.assertNull(simple.get(5));
        Assert.assertNull(simple.get(6));
        Assert.assertNull(simple.get(7));
        Assert.assertNull(simple.get(8));
        Assert.assertNull(simple.get(9));
    }

    @Test
    public void should_iter_correctly() {
        //given
        simple = new SimpleArray<>(10);
        addTenElements();
        iterator = simple.iterator();

        //when
        //then
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("a", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("b", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("c", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("d", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("e", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("f", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("g", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("h", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("i", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("j", iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void iterator_should_trow_exception() {
        //given
        int size = 10;
        simple = new SimpleArray<>(size);
        addTenElements();
        iterator = simple.iterator();

        //when
        for (int i = 0; i < size + 1; i++) {
            iterator.next();
        }
    }

    private void addTenElements() {
        simple.add("a");
        simple.add("b");
        simple.add("c");
        simple.add("d");
        simple.add("e");
        simple.add("f");
        simple.add("g");
        simple.add("h");
        simple.add("i");
        simple.add("j");
    }

    private SimpleArray<Object> getExpectedList() {
        SimpleArray<Object> expected = new SimpleArray<>(10);

        expected.add("a");
        expected.add("b");
        expected.add("c");
        expected.add("d");
        expected.add("e");
        expected.add("f");
        expected.add("g");
        expected.add("h");
        expected.add("i");
        expected.add("j");

        return expected;
    }
}