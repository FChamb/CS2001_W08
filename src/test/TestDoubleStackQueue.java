package test;

import common.QueueEmptyException;
import common.QueueFullException;
import common.StackOverflowException;
import interfaces.IDoubleStack;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import interfaces.IQueue;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Tests double stack queue implementation.
 */
public class TestDoubleStackQueue extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;

    /**
     * Tests that the factory constructs a non-null object.
     */
    @Test
    public void factoryReturnsNonNullDoubleStackQueue() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        assertNotNull(queue, "Failure: IFactory.makeDoubleStackQueue returns null, expected non-null object");
    }

    /**
     * Tests that the queue is properly created and that the size method returns 0 when
     * no objects have been enqueued.
     */
    @Test
    public void queueSize() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        assertEquals(0, queue.size());
    }

    /**
     * Tests that the queue can properly add elements when a queue is created.
     * @throws QueueFullException is the queue is full
     */
    @Test
    public void enqueueQueueElements() throws QueueFullException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        assertEquals(1, queue.size());
    }

    /**
     * Tests that the queue can properly add and remove elements when a queue is created.
     * @throws QueueFullException if the queue is full
     * @throws QueueEmptyException if the queue is empty
     */
    @Test
    public void dequeueQueueElements() throws QueueFullException, QueueEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    /**
     * Tests that the dequeue returns the proper element.
     * @throws QueueFullException if the queue is full
     * @throws QueueEmptyException if the queue is empty
     */
    @Test
    public void queueDequeue() throws QueueFullException, QueueEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.dequeue());
    }

    /**
     * Tests that the queue is empty when nothing has been added.
     */
    @Test
    public void queueIsEmpty() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        assertTrue(queue.isEmpty());
    }

    /**
     * Tests that the queue can be properly cleared once elements have been enqueued.
     * @throws QueueFullException if the queue is full
     */
    @Test
    public void queueClear() throws QueueFullException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.clear();
        assertTrue(queue.isEmpty());
    }

    /**
     * Tests that the queue can properly be cleared and then more elements added.
     * @throws QueueFullException if the queue is full
     */
    @Test
    public void queueClearAndEnqueue() throws QueueFullException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.clear();
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(2, queue.size());
    }

    /**
     * Tests that the queue is empty when nothing has been added.
     * @throws QueueFullException if the queue is full
     * @throws QueueEmptyException if the queue is empty
     */
    @Test
    public void queueDequeueAndEnqueue() throws QueueFullException, QueueEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.dequeue();
        queue.enqueue(3);
        queue.enqueue(4);
        assertEquals(3, queue.size());
    }

    /**
     * Tests that the queue throws the proper exception when enqueue is called with
     * filled elements.
     * @throws QueueFullException if the queue is full
     */
    @Test
    public void queueMaxSizeEnqueue() throws QueueFullException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        assertEquals(10, queue.size());
        assertThrows(QueueFullException.class, () -> queue.enqueue(11));
    }

    /**
     * Tests that the queue can properly add and remove all elements.
     * @throws QueueFullException if the queue is full
     */
    @Test
    public void queueMaxEnqueueAndDequeueAll() throws QueueFullException, QueueEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    /**
     * Tests that the queue throws the proper exception when dequeue is called with
     * cleared elements.
     * @throws QueueFullException if the queue is full
     */
    @Test
    public void queueClearAndDequeue() throws QueueFullException, QueueEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        queue.clear();
        assertThrows(QueueEmptyException.class, () -> queue.dequeue());
    }

    /**
     * Tests that the proper exception is thrown when dequeue is called on an
     * empty queue.
     * @throws QueueEmptyException if the queue is empty
     */
    @Test
    public void queueDequeueEmpty() throws QueueEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        assertThrows(QueueEmptyException.class, () -> queue.dequeue());
    }

    /**
     * Tests that the proper exception is thrown when dequeue is called after .
     * @throws QueueEmptyException if the queue is empty
     */
    @Test
    public void queueEnqueueAndDequeueTwice() throws QueueFullException, QueueEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.dequeue();
        assertThrows(QueueEmptyException.class, () -> queue.dequeue());
    }

    /**
     * Tests that the queue can accept different objects.
     * @throws QueueFullException if the queue is full
     */
    @Test
    public void queueAddDifferentObjects() throws QueueFullException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue("Dog");
        queue.enqueue("Cat");
        assertFalse(queue.isEmpty());
    }

    /**
     * Tests that creates a queue of size zero, and attempts to add element.
     * @throws StackOverflowException if the stack is full
     */
    @Test
    public void pushToStackWithSizeZero() throws StackOverflowException {
        IQueue queue = getFactory().makeDoubleStackQueue(0);
        assertThrows(StackOverflowException.class, () -> queue.enqueue(1));
    }
}
