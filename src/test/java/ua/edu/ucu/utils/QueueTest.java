package ua.edu.ucu.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class QueueTest {
    private Queue queue;
    private Object[] arr;


    @Before
    public void setUp() {
        arr = new Object[] { 1, "Hi!", true, 2.78};
        queue = new Queue(arr);
    }

    @Test
    public void testPeek() {
        Object expectedValue = arr[0];

        assertEquals(expectedValue, queue.peek());
    }

    @Test
    public void testPeekWithEmptyQueue() {
        Queue empty = new Queue();

        assertNull(empty.peek());
    }

    @Test
    public void testDequeue() {
        int expectedLength = queue.size() - 1;

        Object topEl = queue.dequeue();

        assertEquals(expectedLength, queue.size());
        assertEquals(topEl, arr[0]);
    }

    @Test
    public void testDequeueWithEmptyQueue() {
        Queue empty = new Queue();

        assertNull(empty.dequeue());
    }

    @Test
    public void enqueue() {
        int expectedLength = queue.size() + 1;

        Object lastEl = new Date();

        queue.enqueue(lastEl);

        assertEquals(expectedLength, queue.size());
    }

    @Test
    public void testZeroSize() {
        int expectedValue = 0;
        assertEquals(expectedValue , new Queue().size());
    }
}
