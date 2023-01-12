CREATE TABLE dcollectdb.dc_system_service (
    id  INTEGER,
    name VARCHAR2(100 CHAR) NOT NULL,
    dc_record_status_id INTEGER CONSTRAINT nnc_dcsystem_dc_record_statu NOT NULL,
    create_timestamp TIMESTAMP DEFAULT current_timestamp CONSTRAINT nnc_dcsystem_create_timestam NOT NULL,
    update_timestamp TIMESTAMP,
	CONSTRAINT dc_system_pk PRIMARY KEY ( id ),
	CONSTRAINT dc_system_status_fk1 FOREIGN KEY(dc_record_status_id)
        REFERENCES dcollectdb.dc_record_status( id )
);

ALTER TABLE dcollectdb.dc_system_service ADD CONSTRAINT unique_system_service UNIQUE ( name );

CREATE SEQUENCE dc_system_service_seq START WITH 1;

CREATE OR REPLACE TRIGGER system_service_id_increment 
BEFORE INSERT ON dcollectdb.dc_system_service 
FOR EACH ROW
BEGIN
  SELECT dc_system_service_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

CREATE TABLE dcollectdb.dc_system_service_x_model(
	dc_model_id INTEGER CONSTRAINT nnc_model_notnull_id NOT NULL,
	dc_system_service_id INTEGER  CONSTRAINT nnc_system_service_notnull_id NOT NULL,
	CONSTRAINT dc_system_xmodel_fk1 FOREIGN KEY(dc_model_id)
		REFERENCES dcollectdb.dc_model( id ),
	CONSTRAINT dc_order_model_x_system_fk1 FOREIGN KEY(dc_system_service_id)
		REFERENCES dcollectdb.dc_system_service( id )
);

ALTER TABLE dcollectdb.dc_model ADD account_number VARCHAR2(100 CHAR);

ALTER TABLE dcollectdb.dc_model ADD CONSTRAINT unique_code_year_model UNIQUE ( code,year );

INSERT INTO dcollectdb.dc_system_service (name,dc_record_status_id) VALUES('Datacollect',1);
INSERT INTO dcollectdb.dc_system_service (name,dc_record_status_id) VALUES('Informacion Financiera',1);

GRANT ALL PRIVILEGES ON dcollectdb.dc_system_service to dcollectadm;
GRANT ALL PRIVILEGES ON dcollectdb.dc_system_service_x_model to dcollectadm;
