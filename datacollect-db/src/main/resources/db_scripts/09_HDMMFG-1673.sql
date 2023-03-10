-- Generated by Oracle SQL Developer Data Modeler 18.2.0.179.0756
--   at:        2019-02-14 11:12:06 CST
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g


/**
 * NOTES: 
 *   - Run this Script logged as 'dcollectdb'.
 */


CREATE TABLE dcollectdb.dc_facility (
    id                    INTEGER NOT NULL,
    concept               VARCHAR2(50),
    description           VARCHAR2(100),
    dc_record_status_id   INTEGER NOT NULL,
    create_timestamp      TIMESTAMP DEFAULT current_timestamp,
    update_timestamp      TIMESTAMP
);

ALTER TABLE dcollectdb.dc_facility ADD CONSTRAINT dc_fac_pk PRIMARY KEY ( id );

ALTER TABLE dcollectdb.dc_facility ADD CONSTRAINT dc_fac_concept_un UNIQUE ( concept );

ALTER TABLE dcollectdb.dc_facility
    ADD CONSTRAINT dc_fac_recst_fk FOREIGN KEY ( dc_record_status_id )
        REFERENCES dcollectdb.dc_record_status ( id );

CREATE SEQUENCE dcollectdb.dc_fac_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER dcollectdb.dc_fac_id_trg BEFORE
    INSERT ON dcollectdb.dc_facility
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := dcollectdb.dc_fac_id_seq.nextval;
END;


CREATE OR REPLACE TRIGGER dcollectdb.dc_fac_upd_trg BEFORE
    UPDATE ON dcollectdb.dc_facility
    FOR EACH ROW
BEGIN
    SELECT
        SYSDATE
    INTO :new.update_timestamp
    FROM
        sys.dual;

END;


/*
* ===========================================================================
* FROM HERE
* MANUALLY CREATED SCRIPTS
*/

--- grant privileges on tables to adm user
GRANT ALL PRIVILEGES ON dcollectdb.dc_facility to dcollectadm;

--- grant privileges on sequences to adm user
GRANT ALL PRIVILEGES ON dcollectdb.dc_fac_id_seq to dcollectadm;