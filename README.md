# Moneytapp web ui tests
These are web UI test to check adding app functionality.
## Installation
###### Requirements:
```
Apache Maven 3.2.3 
Java version: 1.8.0_45
javac 1.8.0_45
```
Type in console `mvn --version` to see the version of maven and Java if you have one.
## Usage
To run whole test suite

`mvn clean install`

To run particular test:

`mvn clean install -Dtest=AddAppsBasicTest#addAndroidApp`

To run specify browser:

`mvn clean install -Dbrowser.name=chrome`

To set user email and login:

`mvn clean install -Duser.email=<user_email> -Duser.password=<user_password>`

