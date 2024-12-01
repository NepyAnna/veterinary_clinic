ALTER TABLE patients
DROP CONSTRAINT IF EXISTS FK6JY7JJ1AKAKJUQS53OVILSDAD;

ALTER TABLE appointments
DROP CONSTRAINT IF EXISTS FK2QY49B66J7A055M24WBXKK74M;

ALTER TABLE patients
ADD CONSTRAINT FK_PATIENT_USER
FOREIGN KEY (id_user)
REFERENCES users(id_user)
ON DELETE SET NULL;

ALTER TABLE appointments
ADD CONSTRAINT FK_APPOINTMENTS_PATIENT
FOREIGN KEY (id_patient)
REFERENCES patients(id_patient)
ON DELETE CASCADE;

INSERT INTO users (id_user, name, password, email) 
VALUES (1, 'Owner John', 'password123', 'owner.john@example.com');

INSERT INTO patients (id_patient, id_user, name, age, breed, gender, identification_number, guardian_name, guardian_phone, date_of_entry, type) 
VALUES 
(1, 1, 'Buddy', 3, 'Golden Retriever', 'Male', 'ID123456', 'John Doe', '123-456-7890', '2024-01-01', 'Dog');

INSERT INTO appointments (id_appointment, appointment_date_time, type, reason, status, id_patient) 
VALUES 
(1, '2024-12-01 10:00:00', 'Consultation', 'Regular check-up', 'Scheduled', 1),
(2, '2024-12-02 11:30:00', 'Surgery', 'Dental extraction', 'Pending', 1);