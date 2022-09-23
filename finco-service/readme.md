# FINCO-SERVICE
Welcome to the project **FINCO-SERVICE**.

This sub-module takes a query string from finco-web module and expose a endpoint to finco-nlp. finco-nlp in response after performing NER and Intent classification returns back labelled response to finco-service.
After getting response from finco-nlp it communicates with sonata and distributions and returns back query response back to finco-web via exposed endpoint. 

The Components feature:
- Swagger UI as a part of development.
- API endpoints to Sonata, Distributions, finco-nlp and finco-service.
- Database for storing query.

###  Project structure

        finco-service/  ----- api module
          src/
            main/               --------- backend source folder
          ...

Change the current working directory (CWD) of the sell to `finco-service` that was newly created by the `git clone`
command. It will be the workspace directory that all CLI commands should run in.

Before opening the project in VS Code / IntelliJ for the first time, please run the Maven build command to prepare the project
dependencies:

```shell
mvn clean install
```
