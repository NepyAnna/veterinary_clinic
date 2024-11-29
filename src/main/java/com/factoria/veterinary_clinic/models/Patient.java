package com.factoria.veterinary_clinic.models;

import java.lang.reflect.Type;

import javax.xml.crypto.Data;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String breed;
    private String gender;
    private String identificationNumber;
    private String guardianName;
    private String guardianPhone;

    @Temporal(TemporalType.DATE)
    private Data dateOfEntry;

    private Type type;

    protected Patient() {
    }

    public Patient(Long id, String name, int age, String breed, String gender, String identificationNumber,
            String guardianName, String guardianPhone, Data dateOfEntry, Type type) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.gender = gender;
        this.identificationNumber = identificationNumber;
        this.guardianName = guardianName;
        this.guardianPhone = guardianPhone;
        this.dateOfEntry = dateOfEntry;
        this.type = type;
    }

    public Long getId() {
        return id;
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

    public Data getDateOfEntry() {
        return dateOfEntry;
    }

    public Type getType() {
        return type;
    }

}
