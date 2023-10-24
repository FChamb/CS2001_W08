package impl;

import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IStack;

import java.util.ArrayList;
import java.util.List;

public class Stack implements IStack {
    private List<Object> stack;
    private final int maxSize = 10;


    public Stack() {
        this.stack = new ArrayList<>();
    }

    /**
     * Pushes an element onto the stack.
     *
     * @param element the element to be pushed
     * @throws StackOverflowException if there is no room on the stack for the new element
     */
    @Override
    public void push(Object element) throws StackOverflowException {
        if (size() == 10) {
            throw new StackOverflowException();
        }
        this.stack.add(element);
    }

    /**
     * Pops an element from the stack.
     *
     * @return the popped element
     * @throws StackEmptyException if the stack is empty
     */
    @Override
    public Object pop() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException();
        }
        Object val = this.stack.get(size());
        this.stack.remove(val);
        return val;
    }

    /**
     * Accesses the top element on the stack without removing it.
     *
     * @return the top element
     * @throws StackEmptyException if the stack is empty
     */
    @Override
    public Object top() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException();
        }
        return this.stack.get(size());
    }

    /**
     * Returns the number of elements on the stack.
     * @return the number of elements on the stack
     */
    @Override
    public int size() {
        return this.stack.size();
    }

    /**
     * Checks whether the stack is empty.
     * @return true if the stack is empty
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Removes all elements from the stack.
     */
    @Override
    public void clear() {
        try {
            while(!isEmpty()) {
                pop();
            }
        } catch (StackEmptyException e) {
            System.err.println("Error: Stack is empty");
        }
    }
}
