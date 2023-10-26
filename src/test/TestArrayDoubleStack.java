package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import common.StackOverflowException;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import interfaces.IDoubleStack;

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
     * Tests that push properly adds elements to the first stack.
     * @throws StackOverflowException if the stack is full
     */
    @Test
    public void pushObjectsIntoFirstStack() throws StackOverflowException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack.getFirstStack().push(1);
        doubleStack.getFirstStack().push(2);
        doubleStack.getFirstStack().push(3);
        doubleStack.getFirstStack().push(4);
        doubleStack.getFirstStack().push(5);
        assertEquals(5, doubleStack.getFirstStack().size());
    }

    /**
     * Tests that push properly adds elements to the second stack.
     * @throws StackOverflowException if the stack is full
     */
    @Test
    public void pushObjectsIntoSecondStack() throws StackOverflowException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack.getSecondStack().push(1);
        doubleStack.getFirstStack().push(2);
        doubleStack.getSecondStack().push(3);
        doubleStack.getSecondStack().push(4);
        assertEquals(4, doubleStack.getSecondStack().size());
    }
}
