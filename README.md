# Counter Application test.

This is a simple Spring boot app that creates unique counter Object with a name, to test this API you will need download 
the code and run it on a local instance using Java 1.8 with Maven.

There is no persistence layer so all data will exists in memory as long the app is deployed but all data will disappear 
as soon as the app stops running.

To test the API from Postman you will need to use basic Auth: username: "test" and password: "test"
The reachable methods are:

Create a new counter:

http://localhost:7001/apsis/api/counter/create

with basic auth and JSON body ex:

{
"counterName": "test1",
"counterNumber": 0
}

To Increase the counter:

http://localhost:7001/apsis/api/counter/increase

with basic auth and sending ex: x-www-form-urlencoded :  counterName = test1



To get the value state of an existing counter:

http://localhost:7001/apsis/api/counter/getCounterNumber

with basic auth and sending ex: x-www-form-urlencoded :  counterName = test1



To get all counters as a Json list:

http://localhost:7001/apsis/api/counter/getCounters



