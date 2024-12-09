INSERT INTO users (name, password, email, role)
          VALUES 
          ('John Doe', '$2b$12$8bCaneck4QA5xTJdgj0GFe4gsiQUz8dvNg0kj9cE3QsOaIknc81Wi', 'johndoe@example.com', 'USER'),
          ('Jane Smith', '$2b$12$gee.U45CrCcpwOk24A.r9eP1GoUu61V2PK6Hy33y8g1IIVyPG7V36', 'janesmith@example.com', 'ADMIN'),
          ('Tom White', '$2b$12$JKHK7Z7LXFpLqhhWIxgZO.6l2olKuFBg2HMu98v.HPWBZzEyU5o46', 'tomwhite@example.com', 'USER'),
          ('Alice Brown', '$2b$12$YvrHxhT2RzWU9VD/gCFQSu94.95vYc5LaU/8D20YMoXjBPlPgnp2C', 'alicebrown@example.com', 'USER'),
          ('Bob Green', '$2b$12$qpoYhwV1fKJ95LIlC7zccuBpnkyZMmk0PiFQDEkG3O/AzMQRlYbee', 'bobgreen@example.com', 'USER');


INSERT INTO patients (name, age, breed, gender, identification_number, guardian_name, guardian_phone, id_user) VALUES 
('Zeus', 2, 'Dog', 'Male', '12345', 'John Doe', '1234567890', 1),
('Bella', 3, 'Cat', 'Female', '12346', 'Jane Smith', '0987654321', 2),
('Charlie', 4, 'Rabbit', 'Male', '12347', 'Tom White', '1112233445', 3),
('Lucy', 1, 'Dog', 'Female', '12348', 'Alice Brown', '2233445566', 4),
('Max', 5, 'Dog', 'Male', '12349', 'Bob Green', '3344556677', 5);


INSERT INTO appointments (id_patient, appointment_date_time, type, reason, status) VALUES 
(1, '2024-12-01 10:00:00', 'STANDARD', 'Routine checkup', 'PENDING'),
(2, '2024-12-02 14:00:00', 'STANDARD', 'Routine checkup', 'PENDING'),
(3, '2024-12-03 09:00:00', 'STANDARD', 'Routine checkup', 'PENDING'),
(4, '2024-12-04 11:00:00', 'STANDARD', 'Routine checkup', 'PENDING'),
(5, '2024-12-05 16:00:00', 'STANDARD', 'Routine checkup', 'PENDING'),
(1, '2024-12-06 10:00:00', 'STANDARD', 'Vaccination booster shot', 'PENDING');  