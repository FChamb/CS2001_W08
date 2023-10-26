package impl;

import common.QueueEmptyException;
import common.QueueFullException;
import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IDoubleStack;
import interfaces.IQueue;

public class Queue implements IQueue {
    private final int maxSize;
    private Object[] queue;
    private IDoubleStack doubleStack;
    private int size;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new Object[this.maxSize];
        this.doubleStack = new DoubleStack(this.maxSize);
        this.size = 0;
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to be queued
     * @throws QueueFullException if there is no room in the queue for the new element
     */
    @Override
    public void enqueue(Object element) throws QueueFullException {
        if (size() == this.maxSize) {
            throw new QueueFullException();
        }
        this.queue[this.size] = element;
        try {
            this.doubleStack.getFirstStack().push(element);
        } catch (StackOverflowException e) {
            throw new QueueFullException();
        }
        this.size++;
    }

    /**
     * Removes the element at the head of the queue.
     *
     * @return the element removed
     * @throws QueueEmptyException if the queue is empty
     */
    @Override
    public Object dequeue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException();
        }
        Object val = new Object();
        for (int i = 0; i < this.doubleStack.getFirstStack().size(); i++) {
            try {
                if (this.doubleStack.getSecondStack().isEmpty()) {
                    this.doubleStack.getSecondStack().push(this.doubleStack.getFirstStack().pop());
                } else {
                    this.doubleStack.getFirstStack().push(this.doubleStack.getSecondStack().pop());
                }
                val = this.doubleStack.getSecondStack().top();
            } catch (StackOverflowException | StackEmptyException e) {
                throw new QueueEmptyException();
            }
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
        this.queue = new Object[this.maxSize];
        this.size = 0;
    }
}
