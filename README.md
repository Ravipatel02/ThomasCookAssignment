# Employee Bonus Manager

## About

This project creates a small Spring Boot application with two endpoints: a POST endpoint to save employee data and a GET endpoint to fetch employees eligible for a bonus before a specified date. It utilizes a MySQL database to store employee information.

## APIs

### POST API Signature and Payload

#### POST /tci/employee-bonus

**Request Payload sample:**

```json
{
	"employees": [
		{
			"empName": "raj singh",
			"department": "accounts",
			"amount": 5000,
			"currency": "INR",
			"joiningDate": "may-20-2022",
			"exitDate": "may-20-2023"
		},
		{
			"empName": "pratap m",
			"department": "accounts",
			"amount": 3000,
			"currency": "INR",
			"joiningDate": "jan-01-2021",
			"exitDate": "may-20-2023"
		},
		...
	]
}
```

### GET API Signature and Payload

#### GET /tci/employee-bonus?date=”may-27-2022”

**Request Payload sample:**

```json
{
	"errorMessage": "",
	"data": [
		{
			"currency": "INR",
			"employees": [
				{
					"empName": "pratap m",
					"amount": 3000
				},
				{
					"name": "raj singh",
					"amount": 5000
				}
			]
		},
		{
			"currency": "USD",
			"employees": [
				{
					"empName": "sam",
					"amount": 2500
				},
				{
					"empName": "susan",
					"amount": 700
				}
			]
		}
	]
}
```

## Implementation Details

The project consists of the following components:
-**Entity:** Create entity represents a table in a database, mapped to a Java class.
- **EmployeeService:** Implements business logic for saving employees and retrieving employees eligible for a bonus.
- **EmployeeRepository:** Manages database interactions for employee entities.
- **EmployeeController:** Defines endpoints for the POST and GET APIs.
- **ExceptionHandler:** Handles exceptions thrown during API execution.
- **payload:** "Paylode" appears to be a typo; likely meant "payload," which is the main data or cargo in transport.

## Testing

The project includes comprehensive unit tests to ensure correctness and robustness. The tests cover various scenarios, including valid and invalid data for both APIs. Mockito is used for mocking dependencies to isolate the tests from external dependencies.

## Database

The application uses a MySQL database to persist employee data. The database schema includes tables for employees and departments.

## Build and Run
To build and run the application, follow these steps:

1. Clone the repository.
2. Configure the MySQL database connection in the application.properties file.
3. Build the project using Gradle.
4. Run the application using the generated JAR file.

# Clone the repository:
git clone https://github.com/your-username/your-repo.git
cd your-repo
# Configure the MySQL database connection:
Edit the src/main/resources/application.properties file to include your MySQL database details:

```
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

# Build the project using Gradle:
./gradlew build
# Run the application using the generated JAR file:
java -jar build/libs/your-app-name-0.0.1-SNAPSHOT.jar



## Project Structure

The project follows a standard Spring Boot structure, with separate packages for controllers, services, repositories, and entity , dao. This structure ensures modularity and maintainability of the codebase.

## Conclusion

The Employee Bonus Manager project demonstrates the implementation of RESTful APIs using Spring Boot, along with best practices for testing, exception handling, and database management. It provides a robust foundation for managing employee data and calculating bonuses effectively.
