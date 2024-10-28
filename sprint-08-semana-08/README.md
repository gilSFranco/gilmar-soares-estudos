# Challenge: Parking Management System

## Description

This challenge consists of developing a parking management system using **Java Spring Boot** and **MySQL**. The goal is to create a system to manage parking spots that records the entry and exit of vehicles, calculating fees and ensuring that entry and exit rules are respected.

## Requirements

- Java 21+
- Spring Boot 3.x.x
- Maven
- MySQL

## Database Requirements

- The database will be **MySQL**.
- Use the database name as `parking_management`.
- Use the default credentials (`username: root`, `password: root`).

## Main System Features
- **Space Management**: The system allows you to control the total number of available spaces, allowing adjustments in capacity and reservation of spaces for monthly vehicles.

- **Entry and Exit Record**: Vehicle entry and exit will be recorded with tickets, which will contain information such as entry/exit time, occupied space, amount to be paid and vehicle information.

- **Space Control**: The system allows you to control the number of spaces for individual and monthly vehicles, increasing or decreasing this number.

- **Vehicle Categories**: The system handles different categories of vehicles, such as monthly vehicles, delivery trucks, individual vehicles and public service vehicles.

- **Vehicle Types**: Supports cars, motorcycles, delivery trucks and public service vehicles.

- **Charging**: Implements a rate calculation based on the time spent in the parking lot, with minimum rates for individual vehicles and fixed charges for monthly users.


## Requirements met

- **Vehicle Categories**   
    - Monthly ✅
    - Delivery Trucks ✅
    - Public Service ✅
    - Casual ✅

- **Vehicle Types**
    - Passenger Cars ✅
    - Motorcycles ✅
    - Delivery Trucks ✅
    - Public Service ✅

- **Gate Management**
    - Entry Gate. ✅
    - Exit Gate. ✅

- **Vacation Management**
    - The system must control the total number of available spaces, numbering them to facilitate management. ✅
    - The parking capacity (number of spaces) can be adjusted through one of the routes. ✅
    - Spaces reserved for monthly users. ✅
    - Vacancy logic for Monthly Drivers, Casual Drivers, Delivery Trucks, Public Service and Motorcycles. ✅

- **Entry and Exit Registration through Tickets**
    - All vehicles receive tickets. ✅
    - Logic for creating tickets based on the vehicle category. ✅
    - Tickets are persisted in github. ✅

- **Vehicle Registration**
    - Do not allow vehicles that are already registered to be “unregistered”. ✅
    - Vehicle registration made with the POST route of the vehicle CRUD. ✅
    - Only Motorcycles and Passenger Cars can be in the “Occasional” and “Monthly” categories. ✅

- **Charge**
    - Public Service Vehicles and Delivery Trucks are exempt from charge. ✅
    - Monthly users pay a fixed monthly fee of R$250.00. This will not be charged on the ticket. ✅
    - Minimum charge of R$5.00 for individual vehicles. ✅
    - Value per minute parked: R$0.10. ✅
    - Calculation of the value based on the time the vehicle remains in the parking lot for individual vehicles. ✅

## API Documentation

The complete API documentation is automatically generated using Swagger. This allows a detailed view of all available endpoints, accepted parameters, response types, and request examples.

### How to access the documentation
After starting the application server, you can access the interactive documentation in Swagger through the following URL ```http://localhost:8080/swagger-ui/index.html```

### Documentation features
- **Endpoints**: View and explore all API endpoints.
- **Parameters**: See which parameters are accepted in each request.
- **Response models**: Understand how the data returned by the API will be structured.
- **Direct tests**: Make direct calls to endpoints, testing requests directly in the Swagger interface.
## API Reference

### Endpoint **tickets**

#### GET

- Query of tickets registered in the system.

```http
GET /api/v1/tickets
```

- Query of tickets registered in the system with filters.

```http
GET /api/v1/tickets?plate=<valid plate>
```

#### POST

- Registers new entries in the parking lot.

```http
POST /api/v1/tickets/enter
```

- Registers vehicle exits from the parking lot and calculates the amount to be paid.

```http
POST /api/v1/tickets/{id}/leave
```

### Endpoint **spots**

#### GET

- Check the number of spots in the system.

```http
GET /api/v1/spots
```

#### PATCH

- Change the number of spots in the system.

```http
PATCH /api/v1/spots
```

### Endpoint **vehicles**

#### GET

- Returns the list of registered vehicles

```http
GET /api/v1/vehicles
```

- Returns a vehicle based on its plate

```http
GET /api/v1/vehicles?plate=
```

#### POST

- Registers a new vehicle

```http
POST /api/v1/vehicles
```

#### PATCH

- Updates the payment date for monthly vehicles

```http
PATCH /api/v1/vehicles
```

#### DELETE

- Removes a vehicle from the parking lot

```http
DELETE /api/v1/vehicles/{id}
```


## Authors

- [@Gilmar](https://github.com/gilSFranco)
- [@Lincoln](https://github.com/link1nk)
- [@Matheus](https://github.com/MatheusGomes00)
- [@Litman](https://github.com/litmanbite)
