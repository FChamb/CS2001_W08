package impl;

import common.QueueEmptyException;
import common.QueueFullException;
import interfaces.IQueue;

public class Queue implements IQueue {
    @Override
    public void enqueue(Object element) throws QueueFullException {

    }

    @Override
    public Object dequeue() throws QueueEmptyException {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }
}
