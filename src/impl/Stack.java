package impl;

import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IStack;

import java.util.ArrayList;
import java.util.List;

public class Stack implements IStack {
    private Object[] stack;
    private final int maxSize;
    private int size;
    private boolean firstStack = true;
    private Object[] doubleStack;


    public Stack(int maxSize, boolean firstStack, Object[] doubleStack) {
        this.stack = new Object[maxSize];
        this.maxSize = maxSize;
        this.size = 0;
        this.firstStack = firstStack;
        this.doubleStack = doubleStack;
    }

    /**
     * Pushes an element onto the stack.
     * Checks to see if the size of the stack is equal to the max size and throws error if so.
     * Increases size at each insert.
     * @param element the element to be pushed
     * @throws StackOverflowException if there is no room on the stack for the new element
     */
    @Override
    public void push(Object element) throws StackOverflowException {
        if (size() == this.maxSize) {
            throw new StackOverflowException();
        }
        this.stack[this.size] = element;
        if (this.firstStack) {
            this.doubleStack[this.size] = element;
        } else {
            this.doubleStack[this.maxSize + this.size] = element;
        }
        this.size++;
    }

    /**
     * Pops an element from the stack.
     * Checks to see if the stack is empty and throws error if it is.
     * Decreases size at each removal.
     * @return the popped element
     * @throws StackEmptyException if the stack is empty
     */
    @Override
    public Object pop() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException();
        }
        Object val;
        if (this.firstStack) {
            val = this.stack[this.size];
        } else {
            val = this.stack[this.maxSize + this.size];
        }
        this.size--;
        return val;
    }

    /**
     * Accesses the top element on the stack without removing it.
     * Checks to see if the stack is empty and throws the proper exception if so.
     * @return the top element
     * @throws StackEmptyException if the stack is empty
     */
    @Override
    public Object top() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException();
        }
        Object val;
        if (this.firstStack) {
            val = this.stack[this.size];
        } else {
            val = this.stack[this.maxSize + this.size];
        }
        return val;
    }

    /**
     * Returns the number of elements on the stack.
     * @return the number of elements on the stack
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Checks whether the stack is empty.
     * @return true if the stack is empty
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Removes all elements from the stack.
     * Uses a try catch loop to pop every element.
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
