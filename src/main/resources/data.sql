INSERT INTO users (name, password, email) VALUES 
('John Doe', 'password123', 'johndoe@example.com'),
('Jane Smith', 'password456', 'janesmith@example.com'),
('Tom White', 'password789', 'tomwhite@example.com'),
('Alice Brown', 'password101', 'alicebrown@example.com'),
('Bob Green', 'password202', 'bobgreen@example.com');


INSERT INTO patients (name, age, breed, gender, identification_number, guardian_name, guardian_phone, id_user) VALUES 
('Zeus', 2, 'Dog', 'Male', '12345', 'John Doe', '1234567890', CURRENT_DATE, 'Dog', 1),
('Bella', 3, 'Cat', 'Female', '12346', 'Jane Smith', '0987654321', CURRENT_DATE, 'Cat', 2),
('Charlie', 4, 'Rabbit', 'Male', '12347', 'Tom White', '1112233445', CURRENT_DATE, 'Rabbit', 3),
('Lucy', 1, 'Dog', 'Female', '12348', 'Alice Brown', '2233445566', CURRENT_DATE, 'Dog', 4),
('Max', 5, 'Dog', 'Male', '12349', 'Bob Green', '3344556677', CURRENT_DATE, 'Dog', 5);


INSERT INTO appointments (id_patient, appointment_date_time, type, reason, status) VALUES 
(1, '2024-12-01 10:00:00', 'STANDARD', 'Routine checkup', 'PENDING'),
(2, '2024-12-02 14:00:00', 'STANDARD', 'Routine checkup', 'PENDING'),
(3, '2024-12-03 09:00:00', 'STANDARD', 'Routine checkup', 'PENDING'),
(4, '2024-12-04 11:00:00', 'STANDARD', 'Routine checkup', 'PENDING'),
(5, '2024-12-05 16:00:00', 'STANDARD', 'Routine checkup', 'PENDING'),
(1, '2024-12-06 10:00:00', 'STANDARD', 'Vaccination booster shot', 'PENDING');  