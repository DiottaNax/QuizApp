package it.unibo.quiz.logic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class InfiniteCircularIteratorTest {

    @Test
    public void testHasNext() {
        List<Integer> elements = Arrays.asList(1, 2, 3);
        InfiniteCircularIterator<Integer> iterator = new InfiniteCircularIterator<>(elements);

        for (int i = 0; i < 10; i++) {
            assertTrue(iterator.hasNext());
            iterator.next();
        }
    }

    @Test
    public void testNext() {
        List<String> elements = Arrays.asList("a", "b", "c");
        InfiniteCircularIterator<String> iterator = new InfiniteCircularIterator<>(elements);

        List<String> actual = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            actual.add(iterator.next());
        }

        assertEquals(Arrays.asList("a", "b", "c", "a", "b", "c"), actual);
    }

    @Test
    public void testPrevious() {
        List<Integer> elements = Arrays.asList(1, 2, 3);
        InfiniteCircularIterator<Integer> iterator = new InfiniteCircularIterator<>(elements);

        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(2), iterator.next());

        assertEquals(Integer.valueOf(1), iterator.previous());
        assertEquals(Integer.valueOf(3), iterator.previous());
        assertEquals(Integer.valueOf(2), iterator.previous());
    }

    @Test
    public void testPrevious2() {
        List<String> elements = Arrays.asList("a", "b", "c");
        InfiniteCircularIterator<String> iterator = new InfiniteCircularIterator<>(elements);

        List<String> actual = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            actual.add(iterator.previous());
        }

        assertEquals(Arrays.asList("c", "b", "a", "c", "b", "a"), actual);
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyCollection() {
        InfiniteCircularIterator<Object> iterator = new InfiniteCircularIterator<>(new ArrayList<>());

        assertFalse(iterator.hasNext());
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testNullCollection() {
        InfiniteCircularIterator<Object> iterator = new InfiniteCircularIterator<>(null);

        assertFalse(iterator.hasNext());
        iterator.next();
    }
}

