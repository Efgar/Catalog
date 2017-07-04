# Product manager
Simple rest application to be developed as an application test for rakuten.

## Trello board
Trello board with the tasks created for this small project can be found [here](https://trello.com/b/pHzOz5XA/rakuten)

## Regression Tests
To run some basic regression tests you can do:

> $ npm install -g newman

and then

> newman run https://www.getpostman.com/collections/e278a73a4c5197f88011

or download the collection and run it from [postman](https://www.getpostman.com/).

## Implementation specifics:
Next are some details about this specific implementation

#### Available endpoints:

In order to see the available endpoints please check the postman collection previously mentioned.

#### Libraries used:

* Spring (Core, MVC, Data, Security, Boot)
* Lombok

#### Database:

* The application uses a out of the box embedded h2 database, the necessary tables will be created on boot.

#### Known issues:

* No unit tests implemented
* There is a minor bug on product creation where the categories paths are not being properly echoed.

## Contact information
email: edguitar000@gmail.com
