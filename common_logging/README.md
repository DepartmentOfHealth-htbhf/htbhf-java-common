# Common Logging Module

This sub-module contains all the code that is needed to write audit logs using the `EventLogger`.

To use the `EventLogger`, add `LoggingConfiguration` to your Spring application context and autowire where required.
When building an `Event` you will need to provide the main event fields of:

 * `eventType` - which can be an enum of your choice as long as you implement the `EventType` interface
 * `timestamp` - the time of the event as a `LocalDateTime`
 * `eventMetadata` - any other event metadata that needs to be output, provided as a map of key-value pairs, 
    where the key is a String and the value can be any object.
    
The output of the log will be formatted as JSON as such:

`{"eventType":"NEW_CLAIM","timestamp":"2019-04-10T09:29:09.917131","description":"A new claim has been submitted"}`
