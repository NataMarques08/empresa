# RestAPI for Employee Management in a Company

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)



This project was built in **Java with Spring Boot, Flyway Migrations, Swagger, Spring Security, JWT, and MariaDB** as the database.

The API was developed to complement the Spring Boot course, following the parameters mentioned in the course.

## Table of contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Database](#database)
- [Contributing](#contributing)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/NataMarques08/empresa.git
```

2. Install dependencies with Maven

3. Install [MariaDB](https://mariadb.org/)

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080

## API Endpoints
The API provides the following endpoints:

```markdown
GET /api/v1/getAll or /api/v1/id- Retrieve a list of all employees or one employee by ID

POST /api/v1/save - Register a new employee (ADMIN access required).

POST /api/auth/login - Login into the App

POST /api/auth/register - Register a new user into the App
```

## Authentication
The API uses Spring Security for authentication control. The following roles are available:

```
USER -> Standard user role for logged-in users.
ADMIN -> Admin role for managing partners (registering new partners).
```
To access protected endpoints as an ADMIN user, provide the appropriate authentication credentials in the request header.

## Database
The project utilizes [MariaDB](https://www.mariadb.org/) as the database. The necessary database migrations are managed using Flyway.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request to the repository.

When contributing to this project, please follow the existing code style, [commit conventions](https://www.conventionalcommits.org/en/v1.0.0/), and submit your changes in a separate branch.