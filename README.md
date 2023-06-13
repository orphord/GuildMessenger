# GuildMessenger
Simple messenger application for Guild Education tech demo

In developing this demo app I decided to use a "Ports and Adapters" pattern.  This is my current pattern I'm working on,  and I chose to practice it here.  I hope it doesn't ding me in any way, I find P & A to be really clean and understandable (even _readable_), though I would grant that the dev complexity is stepped up a bit.
(there are a couple of more notes about "Ports and Adapters" in the `Description of the structure of the code` section below)

##### A couple of notes
* The current version of the application writes messages to a file in the root directory of the application named `<receiverUserId>_MessageFile.txt`.
* The database is an in-memory `H2` database that can retrieve the messages sent during the current process life, but messages will not be persisted after the process is killed.
* There are a number of shortcomings in this implementation.  For example, I would generally have a more thorough testing regimen and I would have an error check and compensating transaction upon failure in the `MessengerFacade.handle()` method; in the interest of time I did _not_ implement these at this time.
### How do I get this running?
1. In root directory of application, type `mvn spring-boot:run`
2. It should be running after a few seconds
3. In `Postman` (or some application that can send `POST` requests) create a `POST` request in the following manner:
   * In the request body put
   ```
     {
         "message":"some message text here",
         "senderId":"someId",
         "receiverId":"someOtherId"
     }
   ```
   * The URL should be `localhost:8080/addMessage`
   * The media type should be `application/json`
4. Send.
5. The database can be accessed while the system is running
   * Goto [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   * Change the `JDBC URL` field to "jdbc:h2:mem:testdb" (with no quotes).
   * Use password `password`
   * Once the `h2` UI comes up, click the `Messages` element on the left and the `Run` button should return the messages stored in the database.

### Where is the API documentation?
* API documentation is in the form of an `OpenAPI v. 3 swagger.json` file.  The UI for the `OpenAPI` interface is located at [`http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config`](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)
* The `swagger.json` file can be found at [`http://localhost:8080/api-docs/`](http://localhost:8080/api-docs/)

### Testing
* There is one test class, only testing the "happy path" at the moment.  If this was going to be a production application I would be testing as many of the error cases as I could think of for v.1.0
  - To run this test type `mvn test -Dtest=MessengerFacadeTest` in the root directory of the application.
* For integration testing APIs such as this I tend to use `Postman` which is what I did to ensure the front-to-back operation in this case.

### Description of the structure of the code
The structure of the code is an attempt at implementing a "Ports and Adapters" (aka "Hexagonal") architecture pattern.
The idea is similar to the layered design we've used for decades, but enhancing the structure to separate the _core domain_ functionality.  This structure fits nicely with `Domain Driven Design` and (potentially) microservices (though I'm very careful about the "micro" part of "microservice").
The SOLID principles are nicely implemented by this pattern as well.

##### Entry point
* `MessengerController` -- in the current implementation this is the sole entry point to the system. 
  - The `addMessage/` endpoint is to add messages (as if from a sender).  Note that it takes an `AddMessageCommand` object as input.  The `swagger.json` makes the structure of the `AddMessageCommand` simple to send to the `addMessage` endpoint.
  - The `getMessages/` endpoint is to retrieve messages in the database.  Note that there is an _optional_ userId as a string which will retrieve _only_ those messages for a given sender for the past 30 days.
* An advantage of the "Ports and Adapters" pattern is that a different entry point would be very simple to add or change the entry point of the messenger.  So long as an `AddMessageCommand` gets into the system its `handle` method can be invoked on the `AddMessageHandler` and the system will work as currently.

##### Core domain
The core domain has the interfaces that are central to the operation of the system ("Ports") and the implementation of these ("Adapters").  The idea of this is to neatly separate concerns and encapsulate the core functionality of the system.

##### Output
As mentioned above the two outputs are a text file and an in-memory database.  The text file is a representation of what _would_ be sent to a receiving user.  The message will _only_ appear in the text file after the process has _stopped_.