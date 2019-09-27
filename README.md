# Must-See-Movies-API-REST
A movie recommendation app. https://moviesmustsee.com
# Requirements 
`Java 1.8.x` `Spring` `Mongodb` `Maven` `aws S3`
# Architecture

![alt text](https://github.com/JoaquinBustamante96/Must-See-Movies-API-REST/blob/develop/docs/architecture.png)

 * `aspect` Classes that encapsulate cross-cutting concerns.
 * `config` __Spring__ configuration classes.
 * `exceptions` Error handling, resolves any exception thrown by the application to their corresponding http error response.
 * `restControllers` Classes that handle HTTP requests.
   * Defines resource path.
   * Field validations are performed by dtos.
   * Delegates the execution of the request to the __businessControllers__.
 * `bussinesControllers` Contains classes that process the request.
   * Builds __documents__ from the input __dtos__.
   * Uses __repositories__ to perfom basic acces to the DB.
   * Uses __dataServices__ to perfrom advanced acces to DB.
 * `businessServices` business servicess classes, advanced processes of the business layer.
 * `dataServices` Service classes that perfrom advanced access to DB.
 * `dtos` Data transfer objects, used for receiving data(input) and return data(output).
 * `repositories` Classes that provide access to DB.
   * Crud operations, queries.
 * `documents` Persistent classes and utilities.
