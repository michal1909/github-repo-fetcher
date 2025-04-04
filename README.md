# GitHub Repository Fetcher

## Description

GitHub Repository Fetcher is a Spring Boot application that retrieves non-fork GitHub repositories of a given user. It also fetches branch details, including the latest commit SHA. The application interacts with the GitHub API to gather the required data.

## Features

- Fetch non-fork repositories of a specified GitHub user.
- Retrieve branch details (name and last commit SHA) for each repository.
- Return a 404 response if the user does not exist.

## Technologies Used

- Java 21
- Spring Boot
- Spring Web
- Spring Boot Test
- JUnit 5
- Mockito
- Maven

## API Endpoints

### 1. Get Non-Fork Repositories of a User

**Request:**  

GET /repositories/{username}

## Example Requests

### 1. Valid User

**Request:**

```
GET http://localhost:8080/repositories/michal1909
```

**Response:**  
![github1](https://github.com/user-attachments/assets/6a5446de-a707-48cd-9fef-29665812f819)


### 2. Invalid User

**Request:**

```
GET http://localhost:8080/repositories/notExistingUser
```

**Response:**  
![github2](https://github.com/user-attachments/assets/327ee47f-0da1-4406-bcfc-43caa5c4ee10)


## Installation and Running

1. Clone the repository:  
   ```bash
   git clone https://github.com/michal1909/github-repo-fetcher.git
   cd github-repo-fetcher
   ```

2. Build and run the application:  
   ```bash
   mvn spring-boot:run
   ```

3. The application will be available at:  
   ```
   http://localhost:8080
   ```

## Running Tests

To execute tests, run:  
```bash
mvn test
```

## Requirements

- Java 21
- Maven


