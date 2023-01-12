/**
 * Create table DC_ORDER_PROCESS
 * Author:  VJC80496
 * Created: June 25, 2019
 */
CREATE TABLE dcollectdb.dc_order_process (
    id                  	INTEGER NOT NULL,
    svc_order_num       	VARCHAR2(20),
    vin        				VARCHAR2(17),
    status			     	CHAR(1) CONSTRAINT check_status_order
              			    CHECK (status IN ('E','P')),
    dc_income_file_error    NUMBER(22,0),
    dc_income_file_correct  NUMBER(22,0),
    dc_record_status_id		INTEGER,
    create_timestamp        TIMESTAMP DEFAULT current_timestamp,
    update_timestamp        TIMESTAMP
);

ALTER TABLE dcollectdb.dc_order_process ADD CONSTRAINT dc_order_process_pk PRIMARY KEY ( id );

--SEQUENCE FOR PRIMARY 
CREATE SEQUENCE dcollectdb.dc_order_process_id_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

--TRIGGER FOR EXECUTE SEQUENCE
CREATE TRIGGER dcollectdb.dc_order_process_id_trg
BEFORE INSERT OR UPDATE ON dcollectdb.dc_order_process
FOR EACH ROW
BEGIN
   :NEW.id := dcollectdb.dc_order_process_id_seq.NextVal;
END;

--CONSTRAINT FOR FOREIGN KEY dc_record_status_id COLUMN 
ALTER TABLE dcollectdb.dc_order_process
ADD CONSTRAINT dc_order_process_fk_record FOREIGN KEY ( dc_record_status_id )
REFERENCES dcollectdb.dc_record_status ( id );


CREATE OR REPLACE TRIGGER dcollectdb.dc_order_process_upd_trg BEFORE
    UPDATE ON dcollectdb.dc_order_process
    FOR EACH ROW
BEGIN
    SELECT
        SYSDATE
    INTO :new.update_timestamp
    FROM
        sys.dual;
END;

--ADD PRIVILEGES FOR dcollectadm
GRANT ALL PRIVILEGES ON DCOLLECTDB.DC_ORDER_PROCESS TO dcollectadm; 
GRANT ALL PRIVILEGES ON DCOLLECTDB.dc_order_process_id_seq TO dcollectadm;

