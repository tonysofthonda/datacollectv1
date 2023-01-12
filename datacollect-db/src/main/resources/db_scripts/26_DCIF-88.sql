CREATE TABLE dcollectdb.dc_position (
    id  INTEGER,
	name_desc VARCHAR2(100 CHAR) NOT NULL,
	job_id VARCHAR2(100 CHAR) NOT NULL,
    dc_record_status_id INTEGER CONSTRAINT nnc_dcposition_dc_record_statu NOT NULL,
    create_timestamp TIMESTAMP DEFAULT current_timestamp CONSTRAINT nnc_dcposition_create_timestam NOT NULL,
    update_timestamp TIMESTAMP,
	CONSTRAINT dc_position_pk PRIMARY KEY ( id ),
	CONSTRAINT dc_position_status_fk1 FOREIGN KEY(dc_record_status_id)
        REFERENCES dcollectdb.dc_record_status( id )
);

ALTER TABLE dcollectdb.dc_position ADD CONSTRAINT unique_job_id UNIQUE ( job_id );

CREATE SEQUENCE dc_position_seq START WITH 1;

CREATE OR REPLACE TRIGGER position_id_increment 
BEFORE INSERT ON dcollectdb.dc_position 
FOR EACH ROW
BEGIN
  SELECT dc_position_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

INSERT INTO dcollectdb.dc_position (id,name_desc,job_id,dc_record_status_id) VALUES(1,'Admin of Accounting','J001',1);
INSERT INTO dcollectdb.dc_position (id,name_desc,job_id,dc_record_status_id) VALUES(2,'General Manager','J002',1);
INSERT INTO dcollectdb.dc_position (id,name_desc,job_id,dc_record_status_id) VALUES(3,'Sales Manager','J003',1);
INSERT INTO dcollectdb.dc_position (id,name_desc,job_id,dc_record_status_id) VALUES(4,'Services Manager','J004',1);

INSERT INTO dcollectdb.dc_role (id,name) VALUES(18,'Admin of Accounting');
INSERT INTO dcollectdb.dc_role (id,name) VALUES(19,'General Manager');
INSERT INTO dcollectdb.dc_role (id,name) VALUES(20,'Sales Manager');
INSERT INTO dcollectdb.dc_role (id,name) VALUES(21,'Services Manager');

CREATE TABLE dcollectdb.dc_position_x_role (
	dc_position_id INTEGER CONSTRAINT nnc_position_fk_notnull_id NOT NULL,
	dc_role_id INTEGER  CONSTRAINT nnc_role_fk_notnull_id NOT NULL,
	CONSTRAINT dc_position_x_role_fk1 FOREIGN KEY(dc_position_id)
		REFERENCES dcollectdb.dc_position( id ),
	CONSTRAINT dc_role_x_position_fk2 FOREIGN KEY(dc_role_id)
		REFERENCES dcollectdb.dc_role( id )
);

ALTER TABLE dcollectdb.dc_position_x_role ADD CONSTRAINT unique_dc_position_x_role_id UNIQUE ( dc_position_id, dc_role_id );

INSERT INTO dcollectdb.dc_position_x_role (dc_position_id,dc_role_id) VALUES(1,18);
INSERT INTO dcollectdb.dc_position_x_role (dc_position_id,dc_role_id) VALUES(2,19);
INSERT INTO dcollectdb.dc_position_x_role (dc_position_id,dc_role_id) VALUES(3,20);
INSERT INTO dcollectdb.dc_position_x_role (dc_position_id,dc_role_id) VALUES(4,21);

ALTER TABLE dcollectdb.dc_contact_x_dealer DROP CONSTRAINT dc_contact_x_dealer_pk;
ALTER TABLE dcollectdb.dc_contact_x_dealer ADD (id INTEGER, dc_position_id INTEGER NOT NULL);
ALTER TABLE dcollectdb.dc_contact_x_dealer ADD CONSTRAINT dc_contact_x_dealer_pk PRIMARY KEY ( id );
ALTER TABLE dcollectdb.dc_contact_x_dealer ADD CONSTRAINT dc_contact_x_dealer_x_position_fk2 FOREIGN KEY(dc_position_id) REFERENCES dcollectdb.dc_position( id );

CREATE SEQUENCE dc_contact_x_dealer_seq START WITH 1;

CREATE OR REPLACE TRIGGER dc_contact_x_dealer_id_increment
	BEFORE INSERT ON dcollectdb.dc_contact_x_dealer 
	FOR EACH ROW
	WHEN ( new.id IS NULL )
BEGIN
	:new.id := dcollectdb.dc_contact_x_dealer_seq.nextval;
END;

CREATE TABLE dcollectdb.dc_sys_serv_x_contact_dealer (
	dc_contact_x_dealer_id INTEGER CONSTRAINT nnc_contdeal_notnull_id NOT NULL,
	dc_system_service_id INTEGER  CONSTRAINT nnc_sys_serv_notnull_id NOT NULL,
	CONSTRAINT dc_sys_x_condealer_fk1 FOREIGN KEY(dc_contact_x_dealer_id)
		REFERENCES dcollectdb.dc_contact_x_dealer( id ),
	CONSTRAINT dc_condealer_x_sys_fk1 FOREIGN KEY(dc_system_service_id)
		REFERENCES dcollectdb.dc_system_service( id )
);

GRANT ALL PRIVILEGES ON dcollectdb.dc_position to dcollectadm;
GRANT ALL PRIVILEGES ON dcollectdb.dc_position_x_role to dcollectadm;
GRANT ALL PRIVILEGES ON dcollectdb.dc_sys_serv_x_contact_dealer to dcollectadm;
GRANT ALL PRIVILEGES ON dcollectdb.dc_contact_x_dealer_seq to dcollectadm;
