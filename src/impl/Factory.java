package impl;

import interfaces.IDoubleStack;
import interfaces.IFactory;
import interfaces.IQueue;

/**
 * This class implements a singleton factory.
 *
 */
public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    private Factory() {

    }

    /**
     * Method which returns an instance of the singleton Factory class.
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    /**
     * Creates an instance of {@link IDoubleStack}.
     * @param maxSize the maximum size that is shared over both stacks in this double stack
     * @return the double stack
     */
    @Override
    public IDoubleStack makeDoubleStack(int maxSize) {
        return new DoubleStack(maxSize);
    }

    /**
     * This method creates a DoubleStack-based Queue which conforms with the {@link IQueue} interface.
     * @param maxSize the maximum size of DoubleStack-based queue
     * @return the queue
     */
    @Override
    public IQueue makeDoubleStackQueue(int maxSize) {
        return new Queue(maxSize);
    }

}
