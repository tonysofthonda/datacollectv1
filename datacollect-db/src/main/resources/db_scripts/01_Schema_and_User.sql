/* 
 * Honda de Mexico 2018
 * All rights reserved
 */
/**
 * Author:  Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * Created: Jul 31, 2018
 * 
 * References: 
 *  - Create a Tablespace (https://www.techonthenet.com/oracle/tablespaces/create_tablespace.php)
 *  - Create a Schema (https://www.techonthenet.com/oracle/schemas/create_schema.php)
 */


/**
 * NOTES: 
 *   - Run this Script logged as SYSTEM.
 *   - "dcollectdb" user must not be used as admin user, will be used explicitly as
 *      a schema.
 *   - "dcollectadm" must be used as unique admin user for database.
 *      In order to accomplish this, each object into "dcollectdb" all privileges schema must be
 *      granted to "dcollectadm".
 */

---- Create Tablespaces

    -- Permanent Tablespace
    CREATE TABLESPACE tbs_perm_dcollect_01
      DATAFILE 'tbs_perm_dcollect_01.dat'
        SIZE 200M
        AUTOEXTEND ON;

    -- Temporary Tablespace
    CREATE TEMPORARY TABLESPACE tbs_temp_dcollect_01
      TEMPFILE 'tbs_temp_dcollect_01.dbf'
        SIZE 100M
        AUTOEXTEND ON;

---- Create Project Schema

    -- Create Schema
    CREATE USER dcollectdb
      IDENTIFIED BY "DColHonda2018!"
      DEFAULT TABLESPACE tbs_perm_dcollect_01
      TEMPORARY TABLESPACE tbs_temp_dcollect_01
      QUOTA UNLIMITED on tbs_perm_dcollect_01;

    -- Assign SYSTEM privileges
    GRANT create session TO dcollectdb;
    GRANT create table TO dcollectdb;
    GRANT create view TO dcollectdb;
    GRANT create any trigger TO dcollectdb;
    GRANT create any procedure TO dcollectdb;
    GRANT create sequence TO dcollectdb;
    GRANT create synonym TO dcollectdb;

    -- Assign privilege for create public synonyms
    GRANT CREATE PUBLIC SYNONYM, DROP PUBLIC SYNONYM TO dcollectdb;

---- Create Admin User
    -- Create Schema
    CREATE USER dcollectadm
      IDENTIFIED BY "AdminDCol2018!"
      DEFAULT TABLESPACE tbs_perm_dcollect_01
      TEMPORARY TABLESPACE tbs_temp_dcollect_01
      QUOTA 20M on tbs_perm_dcollect_01;

    -- Assign SYSTEM privileges
    GRANT create session TO dcollectadm;
    GRANT create table TO dcollectadm;
    GRANT create view TO dcollectadm;
    GRANT create any trigger TO dcollectadm;
    GRANT create any procedure TO dcollectadm;
    GRANT create sequence TO dcollectadm;
    GRANT create synonym TO dcollectadm;
