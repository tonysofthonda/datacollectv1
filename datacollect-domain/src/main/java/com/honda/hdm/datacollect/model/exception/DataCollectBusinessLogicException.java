/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */
package com.honda.hdm.datacollect.model.exception;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 */
public class DataCollectBusinessLogicException extends Exception {

    private static final long serialVersionUID = 3298134355983399118L;

    /**
     * Creates a new instance of <code>DataCollectBusinessLogicException</code>
     * without detail message.
     */
    public DataCollectBusinessLogicException() {
    }

    /**
     * Constructs an instance of <code>DataCollectBusinessLogicException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public DataCollectBusinessLogicException(String msg) {
        super(msg);
    }
}
