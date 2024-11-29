CREATE TABLE users (
    id_user BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE patients (
    id_patient BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    breed VARCHAR(100),
    gender VARCHAR(20),
    id_number VARCHAR(100) UNIQUE NOT NULL, 
    guardian_name VARCHAR(100) NOT NULL,   
    guardian_phone VARCHAR(20) NOT NULL,   
    id_user BIGINT, 
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE SET NULL
);

CREATE TABLE appointments (
    id_appointment BIGINT AUTO_INCREMENT PRIMARY KEY,
    time TIMESTAMP NOT NULL, 
    type VARCHAR(50) NOT NULL,  
    reason VARCHAR(255),      
    status VARCHAR(50) NOT NULL, 
    id_patient BIGINT NOT NULL,  
    FOREIGN KEY (id_patient) REFERENCES patients(id_patient) ON DELETE CASCADE
);