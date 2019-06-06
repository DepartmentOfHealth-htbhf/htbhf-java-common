# Common Database Module

This sub-module contains the `CloudDBConfiguration` configuration class which is needed to make cloud foundry use our application connection pool properties.

Without including this configuration, cloud foundry will create a datasource with it's default settings which sets the maximum connection pool size to 4.

The configuration is annotated with `@Profile("cloud")` (set when deployed to cloud foundry), so will not affect unit tests or local testing. 

See https://github.com/cloudfoundry/java-buildpack-auto-reconfigurationf and https://cloud.spring.io/spring-cloud-connectors/spring-cloud-spring-service-connector.html for more information.
