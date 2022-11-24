# Spring caching example
## Getting started
* Run services from docker-compose.yml
* Launch app

Application contains simple CRUD operations. You can save, edit and delete entities.

## List of available endpoints:

You can use Postman with postman_collection.json file provided. 

### GET localhost:8080/books/
List of all books

### POST localhost:8080/books/
Create new book. Example request body:
`{"title":"Test", "isbn":"1"}`

### GET localhost:8080/books/{isbn}
Get entity by isbn

### PUT localhost:8080/books/{isbn}
Update book by isbn. You need only specify title.
Example request body: `Some title`