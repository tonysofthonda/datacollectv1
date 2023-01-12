/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */
package com.honda.hdm.datacollect.model.exception;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 */
public class DataCollectProcessorDBException extends Exception {

    private static final long serialVersionUID = 295505870215586104L;

    /**
     * Creates a new instance of <code>DataCollectProcessorDBException</code>
     * without detail message.
     */
    public DataCollectProcessorDBException() {
    }

    /**
     * Constructs an instance of <code>DataCollectProcessorDBException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public DataCollectProcessorDBException(String msg) {
        super(msg);
    }
}
