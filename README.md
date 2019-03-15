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

- Provides rest template with request context headers

[See README for more information](./request_context/README.md)
