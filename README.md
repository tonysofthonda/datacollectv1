# Data Collect
**D**ata **C**ollect

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 
    *1. Clone repository from Git/Gogs server.
    *2. Open "datacollect" pom project using NetBeans 8.1 or above.
    *3. Follow instructions described in the **Installing** section below.
    *4. Open datacollect parent project and all its sub-modules (*-service, *-domain, *-persistence,...).
    *5. Clean and Build "datacollect" parent project. 
        *5.1. If needed (an "unloadable" error message appears on sub-modules), clean and build each child first and then clean and build the parent project.
    *6. Run "datacollect-ear" project. 
        *6.1. If required, point to your WildFly server previously added to NetBeans.
    *7. Navigate to http://localhost:8085/datacollect-web . Port may change according to your WildFly **standole*.xml** configuration.

See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Things you need to install the software and how to install them

```
- WildFly 11.0 (WildFly 10.1 works fine also)
- Maven 2
- GitBash
- Oracle Database: 11g Express Edition (XE) for development purposes, 12cR2 for production.
- Oracle JDBC driver: ojdbc7.jar. You can obtain a copy from Oracle website or from [HDM Artifactory](http://hclweb01:8081/artifactory/third-party-lib-release-local/com/oracle/ojdbc7/12.1.0.1/ojdbc7-12.1.0.1.jar)
- Get an enabled account on HDM Artifactory (uses LDAP): http://hclweb01:8081/artifactory
- NetBeans 8.1
```

### Installing

A step by step series of examples that tell you have to get a development environment running

A. Set your Maven configuration for using HDM Artifactory as principal repository (need to connect to Honda Network - VPN).
>   *1. Make a backup of your current Maven configuration file **\settings.xml**. 
> 
>   *2. Then replace the content of file with following config pointing to **hdm-{lib/plugin}-{release/snapshot}** repos.
> 
>   *3. Note: you can generate this file from Artifactory using the **Set Me Up** option.
> 
>   *4. Optional: If you don't want to expose your password as clear text, you can research on how to encrypt it using maven tools.
```
    <?xml version="1.0" encoding="UTF-8"?>
    <settings xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.1.0 http://maven.apache.org/xsd/settings-1.1.0.xsd" xmlns="http://maven.apache.org/SETTINGS/1.1.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <servers>
        <server>
          <username>{hdm-artifactory-user}</username>
          <password>{your-pass}</password>
          <id>central</id>
        </server>
        <server>
          <username>{hdm-artifactory-user}</username>
          <password>{your-pass}</password>
          <id>snapshots</id>
        </server>
      </servers>
      <profiles>
        <profile>
          <repositories>
            <repository>
              <snapshots>
                <enabled>false</enabled>
              </snapshots>
              <id>central</id>
              <name>hdm-lib-release</name>
              <url>http://hclweb01:8081/artifactory/hdm-lib-release</url>
            </repository>
            <repository>
              <snapshots />
              <id>snapshots</id>
              <name>hdm-lib-snapshot</name>
              <url>http://hclweb01:8081/artifactory/hdm-lib-snapshot</url>
            </repository>
          </repositories>
          <pluginRepositories>
            <pluginRepository>
              <snapshots>
                <enabled>false</enabled>
              </snapshots>
              <id>central</id>
              <name>hdm-plugin-release</name>
              <url>http://hclweb01:8081/artifactory/hdm-plugin-release</url>
            </pluginRepository>
            <pluginRepository>
              <snapshots />
              <id>snapshots</id>
              <name>hdm-plugin-snapshot</name>
              <url>http://hclweb01:8081/artifactory/hdm-plugin-snapshot</url>
            </pluginRepository>
          </pluginRepositories>
          <id>artifactory</id>
        </profile>
      </profiles>
      <activeProfiles>
        <activeProfile>artifactory</activeProfile>
      </activeProfiles>
    </settings>
```

B. Add Oracle JDBC driver to your WildFly server as a module.
> Create a new module in WildFly. [Reference] (https://wiki.snowflakesoftware.com/display/GPWDOC/Install+JDBC+drivers+for+WildFly+10) .
> *1. Copy the ojdbc7.jar file to {WildFly-install-dir}\modules\system\layers\base\com\oracle\ojdbc7\main\
> 
>        You may need to create the \com\oracle\ojdbc7\main\ directory
> 
> *2. Create a **module.xml** file in same directory and fill with following content (notice we are using **jboss:module:1.3** version): 
```
    <?xml version="1.0" encoding="UTF-8"?> 
    <!-- Oracle JDBC driver -->
    <module xmlns="urn:jboss:module:1.3" name="com.oracle.ojdbc7">
      <resources>
        <resource-root path="ojdbc7.jar"/>
      </resources>
      <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
      </dependencies>
    </module>
```

> Add a driver definition to your  WildFly **standalone-full.xml** file (or your preferred WildFly profile). [Reference] (https://muonnguyen.wordpress.com/2015/03/28/installing-oracle-jdbc-driver-on-wildfly-jboss/)
```
    ...
    <datasources>
        ...
        <driver name="ojdbc7" module="com.oracle.ojdbc7">
            <driver-class>oracle.jdbc.OracleDriver</driver-class>
        </driver>
        ...
    </datasources>
    ...
```

> Start your WildFly server and **verify** the correct module installation from a new command terminal (cmd in Windows).
        Notice: If default port (8080) is already used by Oracle TNS Listener, modify your WildFly **standalone-full.xml** config for using another port.
```
#Navigate to {WildFly-install-dir}\bin directory and run the "installed-driver-list" command
    C:\> cd C:\EAI\wildfly-11.0.0.Final\bin
    C:\EAI\wildfly-11.0.0.Final\bin> jboss-cli.bat --connect /subsystem=datasources:installed-drivers-list
#You should be able to see something like:
    {
        "outcome" => "success",
        "result" => [
            {
                "driver-name" => "ojdbc7",
                "deployment-name" => undefined,
                "driver-module-name" => "com.oracle.ojdbc7",
                "module-slot" => "main",
                "driver-datasource-class-name" => "",
                "driver-xa-datasource-class-name" => "",
                "driver-class-name" => "oracle.jdbc.OracleDriver",
                "driver-major-version" => 12,
                "driver-minor-version" => 1,
                "jdbc-compliant" => true
            },
            ...
        ]
    }
```

C. Create a new Non-XA Datasource, using the **ojdbc7 driver**, from your WildFly Admin console or directly on your **standalone-full.xml** file.
```
    ...
    <datasources>
        ...
        <datasource jta="true" jndi-name="java:jboss/datasources/jBossDataCollectDS" pool-name="jBossDataCollectDS" enabled="true" use-ccm="true">
            <connection-url>jdbc:oracle:thin:@localhost:1521:XE</connection-url>
            <driver-class>oracle.jdbc.OracleDriver</driver-class>
            <driver>ojdbc7</driver>
            <security>
                <user-name>datacollectadm</user-name>
                <password>{PUT HERE PASSWORD FOR datacollectadm}</password>
            </security>
            <validation>
                <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.oracle.OracleValidConnectionChecker"/>
                <background-validation>true</background-validation>
                <stale-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.oracle.OracleStaleConnectionChecker"/>
                <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.oracle.OracleExceptionSorter"/>
            </validation>
        </datasource>
        ...
    </datasources>
    ...
```

D. Add the WildFly server to your IDE environment (NetBeans). [How to] (http://blog.arungupta.me/netbeans8-and-wildfly8-techtip-6/)

E. ...

## Running the tests

How to run the automated tests for this system...
TBD...

## Deployment

Additional notes about how to deploy this on a live system...

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://projects.spring.io/spring-boot/) - Server-side framework used
* [Spring JPA] (https://projects.spring.io/spring-data-jpa/) - JPA Data Access Abstraction
* [Hibernate ORM] (http://hibernate.org) - JPA Implementation
* [WebJars](https://www.webjars.org/) - Client-side web libraries (such as jQuery, bootstrap) packaged into JAR files

## Contributing

For each feature added, create a new branch following the convention defined at [Git style convention HDMC/HDMG Dev-Team] (http://confluence.amerhonda.com/x/BKlTAg) .

## Versioning

We use [Gogs](http://gogs.io/) for versioning. For the versions available, see the [releases on this repository](http://hclweb01:3000/org-sales-logistics/datacollect/releases) . 

## Authors

* **Cesar Martinez Garcia** - *Initial work* - [cesar_x_martinez@hdm.honda.com](mailto:cesar_x_martinez@hdm.honda.com)
* **Fabian Fonseca** - *Initial work* - [fabian_fonseca@hdm.honda.com](mailto:fabian_fonseca@hdm.honda.com)
* **Angel Vergara** - *Initial work* - [angel_vergara@hdm.honda.com](mailto:angel_vergara@hdm.honda.com)

See also the list of [contributors](http://hclweb01:3000/org-sales-logistics/datacollect) who participated in this project.

## License

This project is exclusive property of Honda de Mexico, all rights reserved.

## Acknowledgments

* Thanks to ....
* Some links to external tools or profiles.

