package impl;

import common.QueueEmptyException;
import common.QueueFullException;
import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IDoubleStack;
import interfaces.IQueue;

public class Queue implements IQueue {
    private final int maxSize;
    private IDoubleStack doubleStack;
    private int size;

    /**
     * Constructor for a Queue, sets all the private attributes to proper
     * starting positions.
     * @param maxSize the max size of the queue
     */
    public Queue(int maxSize) {
        this.maxSize = maxSize;
        this.doubleStack = new DoubleStack(this.maxSize * 2);
        this.size = 0;
    }

    /**
     * Adds an element to the end of the queue.
     * Checks if the queue is empty and throws a QueueFullException if so.
     * Increases the size argument by one each time.
     * @param element the element to be queued
     * @throws QueueFullException if there is no room in the queue for the new element
     */
    @Override
    public void enqueue(Object element) throws QueueFullException {
        if (size() == this.maxSize) {
            throw new QueueFullException();
        }
        try {
            this.doubleStack.getFirstStack().push(element);
            this.size++;
        } catch (StackOverflowException e) {
            throw new QueueFullException();
        }
    }

    /**
     * Removes the element at the head of the queue.
     * Checks to see if the queue is empty and throws QueueEmptyException if so.
     * The queue is cleared and then a for loop goes through every element of the
     * first stack. At each stop, if the second stack is empty, all the items are pushed to
     * the second stack. And if the first stack is empty, all the items are pushed to the first
     * stack. Each time, top is called so that on the final instance the proper value is grabbed to
     * be returned. Alo every item is re enqueued into the queue.
     * @return the element removed
     * @throws QueueEmptyException if the queue is empty
     */
    @Override
    public Object dequeue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException();
        }
        Object val = new Object();
        clear();
        try {
            if (this.doubleStack.getSecondStack().isEmpty()) {
                while (!this.doubleStack.getFirstStack().isEmpty()) {
                    this.doubleStack.getSecondStack().push(this.doubleStack.getFirstStack().pop());
                }
            }
            if (!this.doubleStack.getSecondStack().isEmpty()) {
                val = this.doubleStack.getSecondStack().pop();
                this.size--;
            }
        } catch (StackOverflowException | StackEmptyException e) {
            throw new QueueEmptyException();
        }
        return val;
    }

    /**
     * Returns the number of elements in the queue.
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Checks whether the queue is empty.
     * @return true if the queue is empty
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Removes all elements from the queue.
     */
    @Override
    public void clear() {
        this.doubleStack.getFirstStack().clear();
        this.doubleStack.getSecondStack().clear();
        this.size = 0;
    }
}
