# FSCF Contacts [![Build Status](https://travis-ci.org/denis-colliot/fscf-contacts.svg?branch=develop)](https://travis-ci.org/denis-colliot/fscf-contacts)

Contacts database for [FSCF association (*Fédération Sportive et Culturelle de France*)](http://www.fscf.asso.fr).

Application access: [http://fscf-contacts.herokuapp.com](http://fscf-contacts.herokuapp.com)

## Architecture
The project's architecture is built around [GWT](http://www.gwtproject.org) 2.7.0.

## Pre-requisites
* Git
* Java 8
* Maven 3
* IDE: Eclipse, IntelliJ, etc. (tested with `Intellij IDEA 14.1`)

## Configure persistence
In development (local) environment, the application expects a configuration file named `env/local.properties` in the classpath.  
This file is already ignored by git configuration.

#### Required properties

To fulfill this expectation, execute the following steps:
 1. Create the `env/local.properties` file in the project classpath (most-likely in `src/main/resources` directory).
 2. Declare the following necessary properties inside it:
  * `hibernate.dialect`
  * `hibernate.connection.driver_class`
  * `hibernate.connection.url`
  * `hibernate.connection.username`
  * `hibernate.connection.password`

*Example `persistence.properties` file for `PostgreSQL 9.x` database:*
```
# Hibernate database connection.
hibernate.dialect=org.hibernate.dialect.PostgreSQL9Dialect
hibernate.connection.driver_class=org.postgresql.Driver
hibernate.connection.url=jdbc:postgresql://localhost:5432/<dbname>
hibernate.connection.username=<username>
hibernate.connection.password=<password>

# Hibernate configuration.
hibernate.hbm2ddl.auto=
hibernate.show_sql=false
hibernate.format_sql=false

# Hibernate pool configuration.
hibernate.connection.provider_class=org.hibernate.c3p0.internal.C3P0ConnectionProvider
hibernate.c3p0.min_size=1
hibernate.c3p0.max_size=15
hibernate.c3p0.max_statements=100
hibernate.c3p0.timeout=0
hibernate.c3p0.acquire_increment=1
hibernate.c3p0.numHelperThreads=6
```




## Run application

Run following maven command to run the `SuperDevMode`:
```
mvn gwt:run
```

Once embedded jetty server is ready, simply click on `Launch Default Browser` button to load the app.
