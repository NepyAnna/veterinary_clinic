package com.factoria.veterinary_clinic.models;

import java.time.LocalDateTime;

public class Appointment {
    private Long id;
    private Long patientId;
    private String patientName;
    private LocalDateTime appointmentDateTime;
    private String type;
    private String reason;
    private String status;

    protected Appointment() {}

    public Appointment(Long patientId, String patientName, LocalDateTime appointmentDateTime,String type, String reason, String status) {
        this.patientId =patientId;
        this.patientName = patientName;
        this.type = type;
        this.reason = reason;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
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
    return String.format(
        "Appointment[id=%d, patientId=%d, patientName='%s', type='%s', reason='%s', status='%s']",
        id, patientId, patientName, type, reason, status);
    }
}
