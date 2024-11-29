INSERT INTO users (name, email, password, role) VALUES
('Admin User', 'admin@clinic.com', 'adminpassword', 'admin'),
('Client User', 'client@clinic.com', 'clientpassword', 'client');

INSERT INTO patients (name, age, breed, gender, id_number, guardian_name, guardian_phone, id_user) VALUES
('Buddy', 5, 'Golden Retriever', 'Male', '1234567890', 'John Doe', '123-456-7890', 2),
('Luna', 3, 'Siamese', 'Female', '0987654321', 'Jane Smith', '987-654-3210', 2);

INSERT INTO appointments (time, type, reason, status, id_patient) VALUES
('2024-12-01 10:00:00', 'Standard', 'Regular check-up', 'Pending', 1),
('2024-12-01 14:00:00', 'Urgency', 'Injury on leg', 'Pending', 2);
