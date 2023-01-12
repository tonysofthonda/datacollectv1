/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */

package com.honda.hdm.datacollect.model.dto.csv;

import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @since  Aug 29, 2018
 *
 */
@Record(name = "I8EndOfFileRecord", minOccurs = 1, maxOccurs = 1)
@Fields({
    @Field(literal = "I8", name = "recordType", at = 0, rid = true, required = true) // record ID
})
public class I8EndOfFileRecord extends BaseBeanIoRecord {

    @Override
    public String toString() {
        return "I8EndOfFileRecord{" + '}';
    }

}
