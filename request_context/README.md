# Request Context Module

This sub-module contains all the code that is needed to add the request id and session id
to the MDC for logging purposes, plus the interceptor will add the request id and session id
to any outgoing requests from any `RestTemplate` that it is added to.

Adding `RequestContextConfiguration` to your application context will provide you with the following beans:

- `headerInterceptor`: Interceptor added to the provided `RestTemplate`
- `mdcWrapper`: A component wrapper around MDC
- `requestContext`: A `RequestContext` bean scoped to the request storing request ID and session ID 
- `requestIdFilter`: A filter used on incoming requests to add the provided request and session ID to the MDC for logging
- `restTemplateWithIdHeaders`: A `RestTemplate` that adds session ID and request ID headers to each request