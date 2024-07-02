# Setup
Download the project from the repository and extract the zip file. Open the project in your favorite IDE.

# Building, Running, and Testing the Project

This project uses Maven for dependency management and build automation. Make sure you have [Maven](https://maven.apache.org/download.cgi) and [Java 8 or later](https://adoptopenjdk.net/) installed on your machine.

## Building the Project

To build the project, navigate to the root directory of the project (where the `pom.xml` file is located) in your terminal and run the following command:

```bash
mvn clean install
```
## Running the Project

```bash
mvn spring-boot:run
```
## Running the Tests

```bash
mvn test
```

# API Endpoints

## Transactions

### GET /transactions

Returns a list of all transactions.

**URL:** http://localhost:8080/transactions

**Method:** GET

**Response:**

- **Code:** 200
- **Content:** A list of transactions

## Monthly Rewards

### GET /transactions/rewards/monthly

Returns a map of customers to another map of months to reward points for each month.

**URL:** http://localhost:8080/transactions/rewards/monthly

**Method:** GET

**Response:**

- **Code:** 200
- **Content:** A map where the key is the customer ID and the value is another map. The inner map's key is the month and year (in the format `MONTH-YEAR`), and the value is the total reward points for that month.

## Total Rewards

### GET /transactions/rewards/total

Returns a map of customers to their total reward points.

**URL:** http://localhost:8080/transactions/rewards/total

**Method:** GET

**Response:**

- **Code:** 200
- **Content:** A map where the key is the customer ID and the value is the total reward points for that customer.