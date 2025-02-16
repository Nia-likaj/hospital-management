# Hospital Management System

## Overview

The **Hospital Management System** is a web application designed to help manage the operations of a hospital, including patient registration, department management, clinical data, and admission states. The system provides a user-friendly interface for adding patients, assigning them to departments, and tracking their clinical data and admission status.

This system is a full-stack project, utilizing a **React.js** frontend, a **Spring Boot** backend, and a **MySQL** database.

## Features

- Add, update, and view patients.
- Assign patients to departments.
- Record and update clinical data.
- Track admission and discharge states.
- View department details.

## Technologies Used

- **Frontend**: React.js
- **Backend**: Spring Boot (Java)
- **Database**: MySQL
- **API Communication**: REST API

## Database Schema

The system uses a relational database with the following key entities:

- **Department**: Stores information about hospital departments.
- **Patient**: Stores personal information about patients, including the department they belong to.
- **Admission State**: Tracks when a patient is admitted and discharged, along with reasons and causes for admission.
- **Clinical Data**: Stores detailed clinical records for each patient.

Refer to the database schema diagram for more details on the relationships between these entities.

## Installation and Setup

### Prerequisites

- Node.js
- MySQL
- Java (for the backend)
- Maven (for building the Spring Boot project)

### Step-by-Step Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/ergishasani/hospital-management-V2.git
   ```

2. **Backend Setup**:
    - Navigate to the backend directory.
   ```bash
   cd hospital-management-backend
   ```
    - Build the project using Maven:
   ```bash
   mvn clean install
   ```
    - Configure the `application.properties` file to connect to your MySQL database:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
    - Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

3. **Frontend Setup**:
    - Navigate to the frontend directory.
   ```bash
   cd hospital-management-frontend
   ```
    - Install dependencies:
   ```bash
   npm install
   ```
    - Start the frontend development server:
   ```bash
   npm start
   ```
   The application will be accessible at `http://localhost:3000`.

4. **Database Setup**:
    - Import the provided database schema into your MySQL instance.
    - Ensure the correct schema is being used as per the application's configuration.

## API Endpoints

### Patients
- **POST** `/patients`: Add a new patient.
- **GET** `/patients`: Retrieve all patients.
- **GET** `/patients/{id}`: Retrieve patient by ID.
- **PUT** `/patients/{id}`: Update patient details.

### Departments
- **POST** `/departments`: Add a new department.
- **GET** `/departments`: Retrieve all departments.

### Admission States
- **POST** `/admission-states`: Add a new admission state.
- **GET** `/admission-states`: Retrieve all admission states.

### Clinical Data
- **POST** `/clinical-data`: Add new clinical data for a patient.
- **GET** `/clinical-data`: Retrieve clinical data for patients.

## Workflow

The workflow of the application is as follows:
1. **Adding a Patient**: Users can fill out a patient form to add a new patient to the system.
2. **Assigning a Department**: Once a patient is added, they are assigned to a specific department.
3. **Managing Admission**: Users can track admission and discharge information for each patient.
4. **Viewing Clinical Data**: Clinical data such as medical history can be entered and viewed for each patient.

## Authors

This project was created by:
- [**Ergis Hasnai**](https://www.linkedin.com/in/ergis-hasani-bb9ba0174/)
- [**Hysnie Likaj**](https://www.linkedin.com/in/hysnie-likaj-140261280/)

