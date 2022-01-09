# peopleflow


[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)

[![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)](https://forthebadge.com)

[![forthebadge](https://forthebadge.com/images/badges/built-with-swag.svg)](https://forthebadge.com)

[![forthebadge](https://forthebadge.com/images/badges/uses-git.svg)](https://forthebadge.com)


   
 | API Endpoint                    | Details                                                                                 |
   | --------------------------- | --------------------------------------------------------------------------------------- |
   | `Post api/v1/employee`             | create Employee                                                                        |
   | `Patch api/v1/employee/{id}`             | change Employee status                                                                       |
   
1. `Employee Creation Request`  --> request Body  - data needed to create an employee sended in the request body for

`Post api/v1/employee`  

   | Field     | Type          | Meaning                                                |
   | --------- | ------------- | ------------------------------------------------------ |
   | `name`    | `string`      | The Name of the employee                            |
   | `phoneNumber`   | `string`      | The employee phone number                              |
   | `age`      | `string`            |   The employee age                                 |   
   | `email`      | `string`         | The employee email                                              |   

2. `Employee Status Change`--> id param and request body - data needed to update an employew. The status is sent in the request body for

 `Patch api/v1/employee/{id}` 

   | Field     | Type          | Meaning                                                |
   | --------- | ------------- | ------------------------------------------------------ |
   | `{id}`    | `string`      | The employee Id                                         |
   | `status`   | `string`      | The employee phone number                              |
 
   
To Build Docker Image for backend go to backend directory root then change to docker directory after that please run: 

`docker-compose build`

Finally to start the app:

`docker-compose up`

Simply access both apis on 

 `http://localhost:8080/api/v1/employee`
 `http://localhost:8080/api/v1/employee/{id}`

