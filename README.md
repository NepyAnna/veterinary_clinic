## Veterinary Clinic Management System

# Project Description

The Veterinary Clinic Management System is a RESTful API designed to manage patients (dogs and cats) and their appointments. This application supports the core functionalities needed for efficient clinic management, including listing, adding, modifying, and deleting patients and appointments. Data is stored using either an in-memory H2 database.

This project adheres to object-oriented programming principles, emphasizing encapsulation and clean architecture to ensure robust and maintainable code.

# Main Features

# Patient Management

Add Patients: Register new patients with the following details:
Name
Age
Breed
Gender
Identification number
Tutor’s name and surname
Tutor’s phone number

# List Patients: 
Retrieve a list of all registered patients.
Modify Patients: Update patient details.
Delete Patients: Remove patient records from the system.
Search Patients by ID: Access detailed information of a patient using their identification number.
View Treatments: Retrieve a list of treatments performed on a specific patient.

# Appointment Management
Add Appointments: Schedule appointments with the following details:
Date and time
Patient
Consultation type (Standard/Urgent)

# Appointment reason
Status (Pending/Past)
List Appointments: Retrieve all appointments.
Modify Appointments: Update appointment details.
Delete Appointments: Cancel appointments.

# Prerequisites

Java Development Kit (JDK) 8 or higher: Required to compile and run the project.

Maven: For dependency management and project build.

IDE: A modern IDE such as IntelliJ IDEA or Eclipse is recommended.

# Installation Steps

Clone the repository:

git clone https:https://github.com/NepyAnna/veterinary_clinic.gitcd Veterinary-Clinic-Management

Set up the project in your IDE:

Open the project in your preferred IDE.

Ensure the JDK is configured correctly.

Verify the presence of pom.xml for dependencies.

Configure the database:

For H2 (default): No additional setup is required.

Run the application:

Navigate to the Application class in your IDE.

# How to Access:
Run the application.
Open your browser and go to:
http://http://localhost:8080/h2-console

# API Endpoints

POST http://localhost:8080/api/patients Update a Patient

PUT http://localhost:8080/api/patients/${id} Delete a Patient

DELETE http://localhost:8080/api/patients/${id} Get All Patients

GET http://localhost:8080/api/patients Appointments Get All Appointments for a Patient

GET http://localhost:8080/appointments/${patientId} Get a Specific Appointment

GET http://localhost:8080/appointments/appointment/${patientId} Add an Appointment

POST http://localhost:8080/appointments Update an Appointment

PUT http://localhost:8080/appointments/${id} Delete an Appointment

DELETE http://localhost:8080/appointments/${id}
Testing

The project includes unit tests to ensure the functionality of core features.

# Running Tests



Run the tests to validate the code functionality and observe test coverage.

The project ensures a minimum of 70% coverage across all methods.


# Activity Diagram
https://postimg.cc/hfFYjxZP


# Class Diagram
https://postimg.cc/0rK3PZfB


# Database Schema
https://postimg.cc/1fpjNtb0

# Tools
Visual Studio Code
Postman


# FrontEnd
https://postimg.cc/HrzvBNYV
https://postimg.cc/RW4b1mzk

# Contributors
Ana Karina
Nadiia Alaieva
Anna Nepyivoda
Maria Vasilenko

# Disclaimer
This project is developed as part of a bootcamp learning experience and is intended for educational purposes only. The creators and contributors are not responsible for any issues, damages, or losses that may occur from using this code.

This project is not meant for commercial use, and any trademarks or references to third-party services (such as Funko) belong to their respective owners. By using this code, you acknowledge that it is a work in progress, created by learners, and comes without warranties or guarantees of any kind.

Use at your own discretion and risk.

Thank You! ❤️
