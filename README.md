# Veterinary Clinic Management System

## ✅Project Description

The Veterinary Clinic Management System is a RESTful API designed to manage patients (dogs and cats) and their appointments. This application supports the core functionalities needed for efficient clinic management, including listing, adding, modifying, and deleting patients and appointments. Data is stored using either an in-memory H2 database.
This project adheres to object-oriented programming principles, emphasizing encapsulation and clean architecture to ensure robust and maintainable code.

## ✅Patient Management
Add Patients: Register new patients with the following details:
- Name
- Age
- Breed
- Gender
- Identification number
- Guardian’s name and surname
- Guardian’s phone number

## ✅List Patients: 
- Retrieve a list of all registered patients.
- Modify Patients: Update patient details.
- Delete Patients: Remove patient records from the system.
- Search Patients by ID: Access detailed information of a patient using their identification number.
- View Treatments: Retrieve a list of treatments performed on a specific patient.

## ✅Appointment Management
- Add Appointments: Schedule appointments with the following details:
- Date and time
- Patient
- Consultation type (Standard/Urgency)

## ✅Appointment reason
- Status (Pending/Past)
- List Appointments: Retrieve all appointments.
- Modify Appointments: Update appointment details.
- Delete Appointments: Cancel appointments.

## ✅Prerequisites
- Java Development Kit (JDK) 8 or higher: Required to compile and run the project.
- Maven: For dependency management and project build.
- IDE: A modern IDE such as IntelliJ IDEA or Eclipse is recommended.

## ✅Installation Steps

1. Clone the repository:

``git clone https:https://github.com/NepyAnna/veterinary_clinic.gitcd Veterinary-Clinic-Management``

2. Set up the project in your IDE:
3. Open the project in your preferred IDE.
4. Ensure the JDK is configured correctly.
5. Verify the presence of pom.xml for dependencies.
6. Configure the database:
- For H2 (default): No additional setup is required.
7. Run the application:
8. Navigate to the Application class in your IDE.

## ✅How to Access:
- Run the application.
- Open your browser and go to:
http://http://localhost:8080/h2-console

## ✅API Endpoints

POST http://localhost:8080/api/patients Update a Patient

PUT http://localhost:8080/api/patients/${id} Delete a Patient

DELETE http://localhost:8080/api/patients/${id} Get All Patients

GET http://localhost:8080/api/patients Appointments Get All Appointments for a Patient

GET http://localhost:8080/appointments/${patientId} Get a Specific Appointment

GET http://localhost:8080/appointments/appointment/${patientId} Add an Appointment

POST http://localhost:8080/appointments Update an Appointment

PUT http://localhost:8080/appointments/${id} Delete an Appointment

DELETE http://localhost:8080/appointments/${id}

## ✅Testing
The project includes unit tests to ensure the functionality of core features.

## ✅Running Tests
- Run the tests to validate the code functionality and observe test coverage.
- The project ensures a minimum of 70% coverage across all methods.

[![temp-Image3pxav0.avif](https://i.postimg.cc/nhP74gyf/temp-Image3pxav0.avif)](https://postimg.cc/5X86179s)

## ✅Class Diagram
[![temp-Image8-Gmv1r.avif](https://i.postimg.cc/SNZz4HNY/temp-Image8-Gmv1r.avif)](https://postimg.cc/VdCNBGtz)

## ✅Database Schema
[![temp-Imageuf-NXi-C.avif](https://i.postimg.cc/q7Kt8Rc2/temp-Imageuf-NXi-C.avif)](https://postimg.cc/QBXxDjyM)

## ✅Technology Stack

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) 
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

## ✅Contributors
NepyAnna [![NepyAnna](https://img.icons8.com/ios-glyphs/30/000000/github.png)](https://github.com/NepyAnna),
Nadiia Alaieva [![izzifona](https://img.icons8.com/ios-glyphs/30/000000/github.png)](https://github.com/tizzifona),
AnaTovar-Arg[![AnaTovar-Arg](https://img.icons8.com/ios-glyphs/30/000000/github.png)](https://github.com/tizzifona),
Maria V [![Snysic](https://img.icons8.com/ios-glyphs/30/000000/github.png)](https://github.com/tizzifona)

## ✅Disclaimer
This project is developed as part of a bootcamp learning experience and is intended for educational purposes only. The creators and contributors are not responsible for any issues, damages, or losses that may occur from using this code.

This project is not meant for commercial use, and any trademarks or references to third-party services (such as Funko) belong to their respective owners. By using this code, you acknowledge that it is a work in progress, created by learners, and comes without warranties or guarantees of any kind.

Use at your own discretion and risk.

Thank You! ❤️
