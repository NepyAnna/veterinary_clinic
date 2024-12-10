package com.factoria.veterinary_clinic.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.factoria.veterinary_clinic.enums.AppointmentStatus;
import com.factoria.veterinary_clinic.enums.AppointmentType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appointment")
    private Long id;

    @Column(name = "appointment_date_time", nullable = false)
    private LocalDateTime appointmentDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 50)
    private AppointmentType type;

    @Column(name = "reason", length = 255)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private AppointmentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient", nullable = false)
    @JsonBackReference
    private Patient patient;

    public Appointment() {
    }

    public Appointment(Patient patient, LocalDateTime appointmentDateTime, AppointmentType type,
            String reason, AppointmentStatus status) {
        this.patient = patient;
        this.appointmentDateTime = appointmentDateTime;
        this.type = type;
        this.reason = reason;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public AppointmentType getType() {
        return type;
    }

    public String getReason() {
        return reason;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format(
                "Appointment[id=%d, patientId=%d, patientName='%s', appointmentDateTime='%s', type='%s', reason='%s', status='%s']",
                id,
                patient != null ? patient.getId() : null,
                patient != null ? patient.getName() : "null",
                appointmentDateTime != null ? appointmentDateTime.format(formatter) : "null",
                type,
                reason,
                status);
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime2) {
        this.appointmentDateTime = appointmentDateTime2;
    }

    public void setType(AppointmentType entity) {
        this.type = entity;
    }

    public void setReason(String entity) {
        this.reason = entity;
    }

    public void setStatus(AppointmentStatus entity) {
        this.status = entity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
