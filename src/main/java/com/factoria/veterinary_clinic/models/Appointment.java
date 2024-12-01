package com.factoria.veterinary_clinic.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "reason", length = 255)
    private String reason;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient", nullable = false)
    //@JsonIgnore// або змінити index() в контролері???!!
    private Patient patient;

    protected Appointment() {
    }

    public Appointment(Patient patient, LocalDateTime appointmentDateTime, String type,
            String reason, String status) {
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

    public String getType() {
        return type;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
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
}
