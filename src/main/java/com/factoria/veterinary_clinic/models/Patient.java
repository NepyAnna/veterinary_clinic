package com.factoria.veterinary_clinic.models;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient")
    private Long id_patient;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "breed", length = 100)
    private String breed;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "identification_number", unique = true, length = 50)
    private String identificationNumber;

    @Column(name = "guardian_name", length = 100)
    private String guardianName;

    @Column(name = "guardian_phone", length = 20)
    private String guardianPhone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = true)
    private User user;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;

    protected Patient() {
    }

    public Patient(String name, int age, String breed, String gender, String identificationNumber,
            String guardianName, String guardianPhone, User user) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.gender = gender;
        this.identificationNumber = identificationNumber;
        this.guardianName = guardianName;
        this.guardianPhone = guardianPhone;
        this.user = user;
    }

    public Long getId() {
        return id_patient;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public String getGender() {
        return gender;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public User getUser() {
        return user;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public String toString() {
        return String.format("Patient[id=%d, name='%s', age=%d, breed='%s', gender='%s', type='%s']",
                id_patient, name, age, breed, gender);
    }


    public void setId_patient(Long id_patient) {
        this.id_patient = id_patient;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
