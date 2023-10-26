package test;

import common.StackOverflowException;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import interfaces.IDoubleStack;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests array collection implementation.
 */
public class TestArrayDoubleStack extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;

    /**
     * Tests that the factory constructs a non-null double stack.
     */
    @Test
    public void factoryReturnsNonNullDoubleStackObject() {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertNotNull(doubleStack1, "Failure: IFactory.makeDoubleStack returns null, expected non-null object");
    }

    /**
     * Tests that the factory creates first stack in the DoubleStack object.
     */
    @Test
    public void nonNullFirstDoubleStackObject() {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertNotNull(doubleStack.getFirstStack());
    }

    /**
     * Tests that the factory creates second stack in the DoubleStack object.
     */
    @Test
    public void nonNullSecondDoubleStackObject() {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertNotNull(doubleStack.getSecondStack());
    }

    /**
     * Tests that push properly added elements to the first stack when max size is even.
     * @throws StackOverflowException if the stack is full
     */
    @Test
    public void pushObjectsIntoFirstStackEven() throws StackOverflowException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack.getFirstStack().push(1);
        doubleStack.getFirstStack().push(2);
        doubleStack.getFirstStack().push(3);
        doubleStack.getFirstStack().push(4);
        doubleStack.getFirstStack().push(5);
        assertEquals(5, doubleStack.getFirstStack().size());
    }

    /**
     * Tests that push properly added elements to the first stack when max size is odd.
     * @throws StackOverflowException if the stack is full
     */
    @Test
    public void pushObjectsIntoFirstStackOdd() throws StackOverflowException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE - 1);
        doubleStack.getFirstStack().push(1);
        doubleStack.getFirstStack().push(2);
        doubleStack.getFirstStack().push(3);
        doubleStack.getFirstStack().push(4);
        doubleStack.getFirstStack().push(5);
        assertEquals(5, doubleStack.getFirstStack().size());
    }

    /**
     * Tests that push properly added elements to the second stack when max size is even.
     * @throws StackOverflowException if the stack is full
     */
    @Test
    public void pushObjectsIntoSecondStackEven() throws StackOverflowException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack.getSecondStack().push(1);
        doubleStack.getSecondStack().push(2);
        doubleStack.getSecondStack().push(3);
        doubleStack.getSecondStack().push(4);
        assertEquals(4, doubleStack.getSecondStack().size());
    }

    /**
     * Tests that push properly added elements to the second stack when max size is odd.
     * @throws StackOverflowException if the stack is full
     */
    @Test
    public void pushObjectsIntoSecondStackOdd() throws StackOverflowException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE - 1);
        doubleStack.getSecondStack().push(1);
        doubleStack.getSecondStack().push(2);
        doubleStack.getSecondStack().push(3);
        doubleStack.getSecondStack().push(4);
        doubleStack.getSecondStack().push(5);
        assertEquals(5, doubleStack.getSecondStack().size());
    }

    /**
     * Tests that push properly added elements to both stack when max size is even.
     * @throws StackOverflowException if the stack is full
     */
    @Test
    public void pushObjectsIntoBothStackEven() throws StackOverflowException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack.getFirstStack().push(1);
        doubleStack.getSecondStack().push(2);
        doubleStack.getFirstStack().push(3);
        doubleStack.getSecondStack().push(4);
        doubleStack.getSecondStack().push(5);
        assertEquals(2, doubleStack.getFirstStack().size());
        assertEquals(3, doubleStack.getSecondStack().size());
    }

    /**
     * Tests that push properly added elements to both stack when max size is odd.
     * @throws StackOverflowException if the stack is full
     */
    @Test
    public void pushObjectsIntoBothStackOdd() throws StackOverflowException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE - 1);
        doubleStack.getFirstStack().push(1);
        doubleStack.getSecondStack().push(2);
        doubleStack.getFirstStack().push(3);
        doubleStack.getSecondStack().push(4);
        doubleStack.getSecondStack().push(5);
        assertEquals(2, doubleStack.getFirstStack().size());
        assertEquals(3, doubleStack.getSecondStack().size());
    }

    /**
     * Tests that error is thrown when too many elements are added to stack when size is even.
     * @throws StackOverflowException if the stack is full
     */
    @Test
    public void pushObjectsUntilMaxStackEven() throws StackOverflowException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack.getFirstStack().push(1);
        doubleStack.getFirstStack().push(2);
        doubleStack.getFirstStack().push(3);
        doubleStack.getFirstStack().push(4);
        doubleStack.getFirstStack().push(5);
        doubleStack.getSecondStack().push(6);
        doubleStack.getSecondStack().push(7);
        doubleStack.getSecondStack().push(8);
        doubleStack.getSecondStack().push(9);
        doubleStack.getSecondStack().push(10);
        assertThrows(StackOverflowException.class, () -> doubleStack.getSecondStack().push(11));
    }

    /**
     * Tests that error is thrown when too many elements are added to stack when size is odd.
     * @throws StackOverflowException if the stack is full
     */
    @Test
    public void pushObjectsUntilMaxStackOdd() throws StackOverflowException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE - 1);
        doubleStack.getFirstStack().push(1);
        doubleStack.getFirstStack().push(2);
        doubleStack.getFirstStack().push(3);
        doubleStack.getFirstStack().push(4);
        doubleStack.getFirstStack().push(5);
        doubleStack.getSecondStack().push(6);
        doubleStack.getSecondStack().push(7);
        doubleStack.getSecondStack().push(8);
        doubleStack.getSecondStack().push(9);
        assertThrows(StackOverflowException.class, () -> doubleStack.getSecondStack().push(10));
    }
}
