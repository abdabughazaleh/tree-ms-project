# Tree MS Project (Abd AbuGhazaleh) 
---
**Important Note**
In the SonarQube report you will find Idenity MS not pass. the becouse i used H2 Database with data.sql default records. to insert test users. and also (2	Vulnerabilities) that becouse i used some way to ecnypt accounts number. 
---
### Note : The postman collection within project folder.
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

The below table will describe the microservices and ports. 
| Port | Service |
| ------ | ------ |
| 6379 | API-Gateway |
| 8761 | EUREKA-SERVER |
| 8080 | Idenity |
| 9999 | Account |
| 7777 | Statement |

## Microservices
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

# Run Front-end 
Not need to apache to run it. just click on index.html from anywhere. and the API-gateway should be run it on 6379 port. 


## Run SonarQube Report
First run the below mvn:
```sh
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install  
```
then
```sh
mvn sonar:sonar
```

## Front-End 
The frontend techlogies : HTML5, CSS3, Javascript, JQuery,Ajax, and Bootstrap.
You can use front-end direct from folder project tree-project, click on index.html and enter user details:
| Username | Password |
| ------ | ------ |
| admin | admin |
| user | user |

## Images
![Login](https://i.ibb.co/PmW7VqN/user-login.png)
![Admin Dashboard](https://i.ibb.co/PWxH9Tg/admin-dashboard.png)
![Select Dates](https://i.ibb.co/x24rG3x/select-account.png)
![Select Amount](https://i.ibb.co/nL42rnT/select-amount.png)

