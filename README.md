### Spring Boot and Redis Queue
Code sample to publish an object to a Redis Queue and then have a message listner consume the messaeg. All built using Spring Boot, Redis for Spring Data and Spring MVC exposed RESTful API. Example uses the same application to publish and subsccribe for illustration purposes only. In a real production application you probably would want to reconsider that based on your needs.

To run this
```
brew install redis
redis-server
mvn spring-boot:run
```


Publish a Blog object to Redis Queue using cURL via a RESTful API
```
curl --location --request POST 'http://localhost:8080/blog' \
--header 'Accept-Language: application/json' \
--header 'Accept-Encoding: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id" : 1,
    "title" : "my first blog",
    "content" : "this is an amazing first blog here"
}'
```
As you publish messages you should see log messages showing that the subscriber consumed the message. The example uses a Blog domain object vs a String type as value (therefore the need to use an object serializer such as Jackson)
