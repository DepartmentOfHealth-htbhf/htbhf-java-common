# htbhf-java-common
Contains reusable code that can be used by microservices within HTBHF. To use this repository, add
the following to your `build.gradle` file:
```
repositories {
    maven {
        url  "https://dl.bintray.com/departmentofhealth-htbhf/maven" 
    }
}
```

## Common Rest Module

Provides formatted error responses and adds the request id and session id to the MDC for logging purposes.
Includes the Request Context Module. 

To use this module, add the following to your `build.gradle` file (replacing n.n.n with the latest version):
```
implementation 'uk.gov.dhsc.htbhf:htbhf-common-rest:n.n.n'
```

[See README for more information](./common_rest/README.md)


## Request Context Module

Provides rest template with request context headers

[See README for more information](./request_context/README.md)


## Common Test Module

Contains common test utilities which include:

- The ability to test and download Swagger for a project
- Common assertions for checking validation `ConstraintViolation`s.

## Common Database Module

Provides a database configuration used when deployed to cloud foundry.

[See README for more information](./common_database/README.md)
