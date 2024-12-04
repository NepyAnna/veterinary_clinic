package com.factoria.veterinary_clinic.models;

import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

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

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_entry")
    private Date dateOfEntry;

    @Column(name = "type", length = 50)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = true)
    private User user;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;

    protected Patient() {
    }

    public Patient(String name, int age, String breed, String gender, String identificationNumber,
            String guardianName, String guardianPhone, Date dateOfEntry, String type, User user) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.gender = gender;
        this.identificationNumber = identificationNumber;
        this.guardianName = guardianName;
        this.guardianPhone = guardianPhone;
        this.dateOfEntry = dateOfEntry;
        this.type = type;
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

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public String getType() {
        return type;
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
                id_patient, name, age, breed, gender, type);
    }
}
