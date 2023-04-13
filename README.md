# Tree MS Project (Abd AbuGhazaleh) 
### _Abdelkadem AbuGhazaleh_
#### abdabughazaleh@hotmail.com


# How to run Microservices
### _This project contains more than x microservices and lots of technologies_

# Technologies
- Spring boot freamework
- Spring Cloud OpenFeign
- Spring Cloud Gateway (API Gateway)
- Eureka server
- Mapstruct
- H2 Database (only for user login)

The below table will describe the microservices and ports. 
| Port | Service |
| ------ | ------ |
| 6379 | API-Gateway |
| 8761 | EUREKA-SERVER |
| 8080 | Idenity |
| 9999 | Account |
| 7777 | Statement |

### Microservices
- API-Gateway, used to manage requests and security
- EUREKA-SERVER or Naming-Service, manage services, load balancing, monitor service, and let services communicate with each other through service name
- Idenity, used to manage user login, logout, and validate token.
- Account, used to manage accounts.
- Statement, to manage user accounts and bank statement. 

## How to run?
To run microservice that you can run it direct from any java compiler. 
##### Required Paramter to run microservices.
You can setup this param by the compiler or using java command line: 
By complier (VM Options) :
```sh
    -Dspring.profiles.active=local
```
By command line: 
```sh
    java -jar -Dspring.profiles.active=local jar-file-name.jar
```