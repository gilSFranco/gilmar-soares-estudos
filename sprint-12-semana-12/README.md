
# Challenge: Microservices using Docker with AWS instance

## Description

**Challenge proposed by Compass UOL** which consists of creating **two microservices**. The **user microservice** (**msuser**) and the **notification microservice** (**msnotify**). The **msuser** needs to meet the following requirements:

- **Implement Spring Security with JWT authentication**.
- Use **Kafka or RabbitMQ** to send messages to the **msnotify** microservice.
- Integrate with the **ViaCEP API**.

For the **msnotify** microservice:

- Integrate with the **messaging service**.
- Register the **message in the MongoDB database**.

## Requirements

- Java Development Kit (JDK) 21+
- Spring Boot 3.3.5
- Apache Maven
- MySQL 8.0
- MongoDB 5.0
- RabbitMQ 4.0-management
- Docker

## Installation

Follow the steps below to configure the project in your development environment:

- **Clone the repository**

```bash
git clone https://github.com/gilSFranco/gilmar-soares-estudos.git
```

- **Enter the root folder**

```bash
cd gilmar-soares-estudos
cd sprint-12-semana-12
cd atividade-12
cd api-springboot-dockerizada-aws
```

- **Upload the images to docker**

```bash
docker-compose up --build
```

And that's it, it should now be possible to test the project.

### Project Structure

* `src/main/java`: Project main source code
* `pom.xml`: Maven configuration file
* `application.yml`: Application configuration file
* `docker-compose.yml`: Application Docker configuration file

## Main System Features

#### Endpoints:
- User Registration: **POST /api/users/register**.
- Password Update: **PUT /api/users/update-password**.

#### Security:
- Implement **Spring Security** with **JWT authentication**.
- The **User Registration** endpoint must be **accessible** to **everyone**.
- The **Password Update** endpoint must be **restricted** to **authenticated users**.
- The **password** must be **encrypted** before being **saved in the database**.

#### Messaging:
- Use **Kafka or RabbitMQ** to send messages to a microservice called **"msnotify"**.
- The **"notify" microservice** should **receive the message** and print it to the **console**.
- **Save** the **received message** to the **MongoDB database**.

#### Integration with ViaCEP:
- When **registering the user**, search for the **address** from the **zip code** using the **ViaCEP API**.
- After searching for the complete address using the **ViaCEP API**, **save** the user in the **database**.

## Additional Features

#### Docker:

- Make the application **run entirely** in a **docker environment**.

#### AWS:

- Upload an **EC2 instance** with the project **running inside it**.

## Requirements met

#### Endpoints:
- User Registration: **POST /api/users/register**. ✔️
- Password Update: **PUT /api/users/update-password**. ✔️

#### Security:
- Implement **Spring Security** with **JWT authentication**. ✔️
- The **User Registration** endpoint must be **accessible** to **everyone**. ✔️
- The **Password Update** endpoint must be **restricted** to **authenticated users**. ✔️
- The **password** must be **encrypted** before being **saved in the database**. ✔️

#### Messaging:
- Use **Kafka or RabbitMQ** to send messages to a microservice called **"msnotify"**. ✔️
- The **"notify" microservice** should **receive the message** and print it to the **console**. ✔️
- **Save** the **received message** to the **MongoDB database**. ✔️

#### Integration with ViaCEP:
- When **registering the user**, search for the **address** from the **zip code** using the **ViaCEP API**. ✔️
- After searching for the complete address using the **ViaCEP API**, **save** the user in the **database**. ✔️

#### Docker:

- Make the application **run entirely** in a **docker environment**. ✔️

## Additional features implemented in the system

#### Endpoints:

- User Login: **POST /api/users/login**.

#### Documentation:

- **Swagger** used to document **all API routes**.
## API documentation

The complete API documentation is automatically generated using Swagger. This allows a detailed view of all available endpoints, accepted parameters, response types, and request examples.

### How to access the documentation
After starting the application server, you can access the interactive documentation in Swagger through the following URL ```http://localhost:8080/swagger-ui/index.html```.

### Documentation features
- **Endpoints**: View and explore all API endpoints.
- **Parameters**: See which parameters are accepted in each request.
- **Response models**: Understand how the data returned by the API will be structured.
- **Direct tests**: Make direct calls to endpoints, testing requests directly in the Swagger interface.

## API reference

### Endpoint users

#### User **registration**.

- **Endpoint**:

```http
  POST /api/users/register
```

- **Description**: Resource to **create** a **new user**.
- **Authentication Required**: No.

#### Request **Body**

```json
{
  "username": "string",
  "password": "string",
  "email": "string",
  "cep": "string"
}
```

#### Response **Body**

##### **Success** Response

- Status code: ````201````
- Description: Resource **created successfully**.
- Content

```json
{
  "username": "string",
  "email": "string",
  "address": {
    "zipCode": "12345678",
    "street": "Example Street",
    "complement": "Apt 101",
    "neighborhood": "Example Neighborhood",
    "city": "Example City",
    "state": "SP"
  }
}
```

##### **Error** Response

- Status code: ````409````
- Description: Username **already in use**.
- Content

```json
{
    "path": "/api/users/register",
    "method": "POST",
    "status": 409,
    "statusText": "Conflict",
    "message": "The user already exists."
}
```

#### User **sign in**.

- **Endpoint**:

```http
  POST /api/users/login
```

- **Description**: Resource to **sign in** a **user**.
- **Authentication Required**: No.

#### Request **Body**

```json
{
  "username": "string",
  "password": "string"
}
```

#### Response **Body**

##### **Success** Response

- Status code: ````200````
- Description: User **successfully signed in**.
- Content

```json
{
    "username": "victor",
    "authenticated": true,
    "created": 1730903154809,
    "expiration": 1730903214809,
    "accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9",
    "refreshToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9"
}
```

##### **Error** Response

- Status code: ````401````
- Description: User **credentials are incorrect**.
- Content

```json
{
    "path": "/api/users/login",
    "method": "POST",
    "status": 401,
    "statusText": "Unauthorized",
    "message": "Your credentials are incorrect."
}
```

#### User **password update**.

- **Endpoint**:

```http
  PUT /api/users/update-password
```

- **Description**: Resource to **sign in** a **user**.
- **Authentication Required**: Yes.

##### Header

- **Authorization**: `Bearer {token}`
  - Example: `Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...`

#### Request **Body**

```json
{
  "username": "string",
  "oldPassword": "string",
  "newPassword": "string"
}
```

#### Response **Body**

##### **Success** Response

- Status code: ````204````
- Description: Password **updated successfully**.
- No-Content

##### **Error** Response

- Status code: ````404````
- Description: User **not found**.
- Content

```json
{
    "path": "/api/users/login",
    "method": "POST",
    "status": 404,
    "statusText": "Not Found",
    "message": "User with username 'username' not found."
}
```
## Contato

Para dúvidas ou problemas, entre em contato com:

* Nome: Gilmar Soares Franco
* Email: gilmarsoaresfranco@gmail.com
* GitHub: [github.com/gilSFranco ](https://github.com/gilSFranco)