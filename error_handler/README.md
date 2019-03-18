# Request Context Module

This sub-module contains all the code that is needed to return formatted error responses. 

Adding `ErrorHandlerConfiguration` to your application context will provide you with the following bean:

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
