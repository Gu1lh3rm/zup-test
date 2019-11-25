# Before You Start

### Reference Documentation
For further reference, please consider the following sections:

* [Swagger Implementation Documentation](https://github.com/Gu1lh3rm/api-test/blob/master/document/documentacao-api-com-swagger.pdf)
* [Authentication and Authorization Implementation Documentation](https://github.com/Gu1lh3rm/api-test/blob/master/document/documentacao-autenticacao-e-autorizacao.pdf)
* [Entity and Relationship Implementation Document](https://github.com/Gu1lh3rm/api-test/blob/master/document/entity-relationship-diagram-api.jpeg)
* [API REST Guide](https://github.com/Gu1lh3rm/api-test/blob/master/document/api-rest-guide.pdf)

### Guides
The following guides illustrate how to use some features correctly:
* [REST API Documentation](http://api-test.herokuapp.com/api/swagger-ui.html)
* [REST API Local Documentation](http://localhost:8080/api/swagger-ui.html)

### Build project

``
 mvn clean install
``

### Start project
``
java -jar backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod & disown
``

### Docker project
``
docker build -t gui/spring-zup .
docker run -p 8080:8080 -t gui/spring-zup
``