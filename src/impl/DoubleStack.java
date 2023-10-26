package impl;

import interfaces.IDoubleStack;
import interfaces.IStack;

public final class DoubleStack implements IDoubleStack {
    private IStack firstStack;
    private IStack secondStack;
    private Object[] doubleStack;

    /**
     * Method which returns the first IStack object in the IDoubleStack for subsequent use with {@link IStack} operations.
     * @return the first stack in the double stack
     */
    @Override
    public IStack getFirstStack() {
        return this.firstStack;
    }

    /**
     * Method which returns the second IStack in the IDoubleStack object for subsequent use with {@link IStack} operations.
     * @return the first stack in the double stack
     */
    @Override
    public IStack getSecondStack() {
        return this.secondStack;
    }

    /**
     * Constructor for DoubleStack which takes a max size input. The object array, doubleStack is initialized
     * to the correct size and then a conditional statement determines if the size provided is even and odd.
     * Should the size be even, the first stack and second stack are of equal length. Should the size be odd,
     * the first stack is one index longer than the second stack.
     * @param maxSize
     */
    public DoubleStack(int maxSize) {
        this.doubleStack = new Object[maxSize];
        if (maxSize % 2 == 1) {
            this.firstStack = new Stack(maxSize / 2 + 1, true, this.doubleStack);
        } else {
            this.firstStack = new Stack(maxSize / 2, true, this.doubleStack);
        }
        this.secondStack = new Stack(maxSize / 2, false, this.doubleStack);
    }
}
