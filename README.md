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

## Request Context Module
This sub-module contains all the code that is needed to add the request id and session id
to the MDC for logging purposes, plus the interceptor will add the request id and session id
to any outgoing requests from any `RestTemplate` that it is added to.
