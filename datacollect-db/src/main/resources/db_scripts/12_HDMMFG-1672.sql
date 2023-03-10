-- Generated by Oracle SQL Developer Data Modeler 18.2.0.179.0756
--   at:        2019-02-26 17:48:29 CST
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g

CREATE TABLE dcollectdb.dc_workshop (
    id                    INTEGER NOT NULL,
    name                  VARCHAR2(50) NOT NULL,
    dc_record_status_id   INTEGER NOT NULL,
    create_timestamp      TIMESTAMP DEFAULT current_timestamp,
    update_timestamp      TIMESTAMP
);

ALTER TABLE dcollectdb.dc_workshop ADD CONSTRAINT dc_workshop_pk PRIMARY KEY ( id );

ALTER TABLE dcollectdb.dc_workshop ADD CONSTRAINT dcwrkshp_un1 UNIQUE ( name );

ALTER TABLE dcollectdb.dc_workshop
    ADD CONSTRAINT dcwrkshp_recst_fk2 FOREIGN KEY ( dc_record_status_id )
        REFERENCES dcollectdb.dc_record_status ( id );

CREATE SEQUENCE dcollectdb.dcwrkshp_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER dcollectdb.dcwrkshp_id_trg BEFORE
    INSERT ON dcollectdb.dc_workshop
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := dcollectdb.dcwrkshp_id_seq.nextval;
END;

ALTER TABLE dcollectdb.dc_dealer ADD dc_workshop_id INTEGER DEFAULT 1 NOT NULL;

INSERT INTO dcollectdb.dc_workshop(id, name, dc_record_status_id)VALUES(1, 'Outlet', 1);
INSERT INTO dcollectdb.dc_workshop(id, name, dc_record_status_id)VALUES(2, 'Satellite', 1);
INSERT INTO dcollectdb.dc_workshop(id, name, dc_record_status_id)VALUES(3, 'B & P', 1);

ALTER TABLE dcollectdb.dc_dealer
    ADD CONSTRAINT dcdeal_dcwrkshp_fk6 FOREIGN KEY ( dc_workshop_id )
        REFERENCES dcollectdb.dc_workshop ( id );

/*
* ===========================================================================
* FROM HERE
* MANUALLY CREATED SCRIPTS
*/

--- grant privileges on tables to adm user
GRANT ALL PRIVILEGES ON dcollectdb.dc_workshop to dcollectadm;

--- grant privileges on sequences to adm user
GRANT ALL PRIVILEGES ON dcollectdb.dcwrkshp_id_seq to dcollectadm;

