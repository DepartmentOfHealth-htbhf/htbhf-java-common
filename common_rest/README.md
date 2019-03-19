# Common Rest Module

This sub-module contains all the code that is needed to return formatted error responses and add the request id and session id to the MDC for logging purposes. 

Adding `ErrorHandlerConfiguration` to your application context will provide you with the following beans:

- `headerInterceptor`: Interceptor added to the provided `RestTemplate` which adds the request ID and session ID to all outgoing requests. 
- `mdcWrapper`: A component wrapper around MDC
- `requestContext`: A `RequestContext` bean scoped to the request storing request ID and session ID 
- `requestIdFilter`: A filter used on incoming requests to add the provided request and session ID to the MDC for logging
- `restTemplateWithIdHeaders`: A `RestTemplate` that adds session ID and request ID headers to each request
- `errorHandler`: Controller advice that captures validation exceptions and returns an error response with field information, timestamp and request id. e.g.
```
{
    "fieldErrors": [
        {
            "field": "dateOfBirth",
            "message": "must not be null"
        }
    ],
    "requestId": "c6590849-c470-4cfe-ae2a-984776338382",
    "timestamp": "2019-03-18T11:52:46.704941",
    "status": 400,
    "message": "There were validation issues with the request."
}
```
