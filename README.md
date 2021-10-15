# GuildMessenger
Simple messenger application for Guild Education tech demo

In developing this demo app I decided to use a "Ports and Adapters" pattern.  This is my current thing and I chose to practice it here.  I hope it doesn't ding me in any way, I find P & A to be really clean and understandable (even _readable_), though I would grant that the dev complexity is stepped up a bit.

### To get this running
1. In root directory of application, type `mvn spring-boot:run`
2. It should be running after a few seconds
3. In `Postman` (or some application that can send `POST` requests) create a `POST` request in the following manner:
  * In the request body put `{"message":"some message text here"}`
  * The URL should be `localhost:8080/<somethingsomething>`
  * The media type should be `application/json` <-- OR SO I THINK, VERIFY
4. Send.

### Description of the structure of the code
