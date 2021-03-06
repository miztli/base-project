# base-project

Base project configuration for a RESTful API, adapted for glassfish server from Baeldung's REST with Spring course, original repo at: https://github.com/eugenp/REST-With-Spring

###Build
Locate yourself in : ${PROJECT_HOME}/pom.xml
```sh
mvn clean install
```

###Configuration for a Glassfish3 server with JDK 1.7

Create the following environment variables in the server:

Locate yourself in : ${GLASSFISH_HOME}/glassfish/bin

execute commands:

> In case you're not using default port, specify in the command

```sh
./asadmin create-jvm-options -DenvTarget=dev -p 25994
```
> In case you need to switch from a Dev environment to a Production environment, change -DenvTarget=prod

```sh
./asadmin create-jvm-options -DpersistenceTarget=mysqlDev -p 25994
```
> In case you need to switch from a Dev environment to a Production environment, change -DpersistenceTarget=mysqlProd

###Datasource creation

Options
* You can specify the pool size for the connection pool with parameters --maxpoolsize
* Change the host and the port in case it is a remote environment for the database
* Change the admin console port if necessary (-p)

```sh
./asadmin -p 25994 create-jdbc-connection-pool --restype java.sql.Driver --driverclassname com.mysql.jdbc.Driver --property "user=root:password=toor:url=jdbc\\:mysql\\://localhost\\:3306/template?createDatabaseIfNotExist\=true" template-test-pool
```
###Create jndi resource in the server
```sh
./asadmin -p 25994 create-jdbc-resource --connectionpoolid template-test-pool jdbc/mysql-template-test
```

###Deployment
```sh
./asadmin deploy -p 25994 --contextroot "/template" ~/Documents/Baeldung-spring-course/template-spring-rest-webapp/template-webapp/target/template-webapp-0.1.0-SNAPSHOT.war
```
###Docs
* https://www.lucidchart.com/invitations/accept/f70c7792-f1f7-424f-9ba4-7b3ca3187402
