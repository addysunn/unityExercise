package csfundamentals;

import org.junit.Test;
import static org.junit.Assert.*;


public class QueueTest {
    private Queue queue = new Queue();

    @Test
    public void testQueueIsEmpty() {
        assertTrue(queue.empty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testIsNotEmpty() {
        queue.enQueue(3);
        queue.enQueue(4);
        assertTrue(!queue.empty());
    }

    @Test
    public void testIsOneEnQueue() throws Exception{
        queue.enQueue(2);
        assertEquals(2, queue.peek());
        assertEquals(1, queue.size());
    }

    @Test
    public void testIsOneDeque() throws Exception {
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.deQueue();
        assertEquals(2, queue.size());
    }

    @Test
    public void testEnQueueThenDeQueue () throws Exception {
        queue.enQueue(1);
        queue.enQueue(2);
        assertTrue(!queue.empty());
        queue.deQueue();
        assertTrue(!queue.empty());
        queue.deQueue();
        assertTrue(queue.empty());
    }

    @Test
    public void testEnQueueThenPeek() {
        queue.enQueue(2);
        assertEquals(2, queue.peek());
    }

    @Test
    public void testOrdering() {
        queue.enQueue(1);
        queue.enQueue(5);
        queue.enQueue(3);
        assertEquals(1,queue.peek());
        queue.deQueue();
        assertEquals(5,queue.peek());
        queue.deQueue();
        assertEquals(3,queue.peek());
    }

    @Test (expected = NullPointerException.class)
    public void testRemovingTillEmpty() {
        int numOfRemovals = 10;
        for (int i=0;i<numOfRemovals;i++) {
            queue.enQueue(i);
        }
        for (int i=0;i<numOfRemovals+1;i++) {
            queue.deQueue();
        }

        assertTrue(queue.empty());
        assertEquals(queue.size(), 0);
    }

    @Test (expected = NullPointerException.class)
    public void testRemoveOnEmptyQueue() {
        assertTrue(queue.empty());
        queue.deQueue();
    }

    @Test (expected = NullPointerException.class)
    public void testPeekIntoEmptyQueue() {
        assertTrue(queue.empty());
        queue.deQueue();
    }
}