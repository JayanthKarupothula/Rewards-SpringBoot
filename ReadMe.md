# Project Structure
customer-transactions-rewards/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/customer/transactions/
│   │   │       ├── controllers/  # REST controllers
│   │   │       ├── models/       # Entity classes
│   │   │       ├── repository/   # Spring Data JPA repositories
│   │   │       └── services/     # Service classes
│   │   └── resources/
│   │       ├── application.properties  # Configuration properties
│   │       └── data.sql                # Sample data for initial setup
│   └── test/                           # Unit and integration tests
└── pom.xml                             # Project dependencies and build configuration

This section outlines the main directories and files in this project, providing a quick overview of the project's architecture.
# Setup
Prerequisites
Ensure you have the following installed on your machine:
Maven
Java 11,17 or later
An Oracle database

git clone https://github.com/JayanthKarupothula/Rewards-SpringBoot
cd Rewards-SpringBoot

# database setup
Update src/main/resources/application.properties with your Oracle database configuration:


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

## steps to  populate the database with sample data
   1) Create the Database Table
        Make sure your database has the transactions table created
         example:
      CREATE TABLE transactions (
      id NUMBER PRIMARY KEY,
      amount NUMBER(10, 2),
      transaction_date DATE
      );
      2) Place data.sql in Resources

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
- **Content:**
  {
  "JANUARY-2023": 290,
  "FEBRUARY-2023": 25,
  "MARCH-2023": 300
  }


## Total Rewards

### GET /transactions/rewards/total

Returns a map of customers to their total reward points.

**URL:** http://localhost:8080/transactions/rewards/total

**Method:** GET

**Response:**

- **Code:** 200
- **Content:**
  {
  "totalRewardPoints": 615
  }
