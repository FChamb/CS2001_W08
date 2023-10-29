package test;

import common.StackEmptyException;
import common.StackOverflowException;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import interfaces.IDoubleStack;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        doubleStack.getSecondStack().push(5);
        assertEquals(5, doubleStack.getSecondStack().size());
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
        assertEquals(4, doubleStack.getSecondStack().size());
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

    /**
     * Tests that the correct element is returned when pop is called on the first stack.
     * @throws StackOverflowException if the stack is full
     * @throws StackEmptyException if the stack is empty
     */
    @Test
    public void popObjectsFromFirstStack() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack.getFirstStack().push(1);
        doubleStack.getFirstStack().push(2);
        doubleStack.getFirstStack().push(3);
        doubleStack.getFirstStack().push(4);
        doubleStack.getFirstStack().push(5);
        assertEquals(5, doubleStack.getFirstStack().pop());
    }

    /**
     * Tests that the correct element is returned when pop is called on the second stack.
     * @throws StackOverflowException if the stack is full
     * @throws StackEmptyException if the stack is empty
     */
    @Test
    public void popObjectsFromSecondStack() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack.getSecondStack().push(1);
        doubleStack.getSecondStack().push(2);
        doubleStack.getSecondStack().push(3);
        doubleStack.getSecondStack().push(4);
        doubleStack.getSecondStack().push(5);
        assertEquals(5, doubleStack.getSecondStack().pop());
    }

    /**
     * Tests that the correct element is returned when pop is called on both of the stacks.
     * @throws StackOverflowException if the stack is full
     * @throws StackEmptyException if the stack is empty
     */
    @Test
    public void popObjectsFromBothStacks() throws StackOverflowException, StackEmptyException {
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
        assertEquals(5, doubleStack.getFirstStack().pop());
        assertEquals(10, doubleStack.getSecondStack().pop());
    }

    /**
     * Tests that StackEmptyException is thrown when trying to pop an empty first stack.
     */
    @Test
    public void popObjectFromEmptyFirstStack() {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertThrows(StackEmptyException.class, () -> doubleStack.getFirstStack().pop());
    }

    /**
     * Tests that StackEmptyException is thrown after popping the last element from first stack.
     * @throws StackOverflowException if the stack is full
     * @throws StackEmptyException if the stack is empty
     */
    @Test
    public void popObjectFromEmptyStackTwice() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack.getFirstStack().push(1);
        doubleStack.getFirstStack().pop();
        assertThrows(StackEmptyException.class, () -> doubleStack.getSecondStack().pop());
    }

    /**
     * Tests that a true value is returned when calling empty to both stacks.
     */
    @Test
    public void stackIsEmpty() {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertTrue(doubleStack.getFirstStack().isEmpty());
        assertTrue(doubleStack.getSecondStack().isEmpty());
    }

    /**
     * Tests that clears both first and second stack after filling with elements.
     * @throws StackOverflowException if the stack is full
     */
    @Test
    public void clearStack() throws StackOverflowException {
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
        doubleStack.getFirstStack().clear();
        doubleStack.getSecondStack().clear();
        assertTrue(doubleStack.getFirstStack().isEmpty());
        assertTrue(doubleStack.getSecondStack().isEmpty());
    }

    /**
     * Tests that clear returns proper output if called on an empty list.
     */
    @Test
    public void clearEmptyStack() {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack.getFirstStack().clear();
        assertTrue(doubleStack.getFirstStack().isEmpty());
    }

    /**
     * Tests that clears both first and second stack after filling with elements and then add elements again.
     * @throws StackOverflowException if the stack is full
     */
    @Test
    public void clearStackThenAdd() throws StackOverflowException {
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
        doubleStack.getFirstStack().clear();
        doubleStack.getSecondStack().clear();
        doubleStack.getFirstStack().push(1);
        doubleStack.getSecondStack().push(2);
        assertFalse(doubleStack.getFirstStack().isEmpty());
        assertFalse(doubleStack.getSecondStack().isEmpty());
    }

    /**
     * Tests that add elements to both lists and tops both.
     * @throws StackOverflowException if the stack is full
     * @throws StackEmptyException if the stack is empty
     */
    @Test
    public void topStack() throws StackOverflowException, StackEmptyException {
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
        assertEquals(5, doubleStack.getFirstStack().top());
        assertEquals(10, doubleStack.getSecondStack().top());
        assertEquals(5, doubleStack.getFirstStack().size());
        assertEquals(5, doubleStack.getSecondStack().size());
    }

    /**
     * Tests that top throws proper exception when called on empty stacks.
     */
    @Test
    public void topEmptyStack() {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertThrows(StackEmptyException.class, () -> doubleStack.getFirstStack().top());
        assertThrows(StackEmptyException.class, () -> doubleStack.getSecondStack().top());
    }

    /**
     * Tests that creates a double stack of size zero, and attempts to add element.
     * @throws StackOverflowException if the stack is full
     */
    @Test
    public void pushToStackWithSizeZero() throws StackOverflowException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(0);
        assertThrows(StackOverflowException.class, () -> doubleStack.getFirstStack().push(1));
        assertThrows(StackOverflowException.class, () -> doubleStack.getSecondStack().push(2));
    }

    /**
     * Tests that stack can hold different objects.
     * @throws StackOverflowException if the stack is full
     */
    @Test
    public void pushToStackWithDifferentObjects() throws StackOverflowException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack.getFirstStack().push(1);
        doubleStack.getFirstStack().push(2);
        doubleStack.getFirstStack().push("Dog");
        doubleStack.getFirstStack().push("Cat");
        assertFalse(doubleStack.getFirstStack().isEmpty());
    }
}
