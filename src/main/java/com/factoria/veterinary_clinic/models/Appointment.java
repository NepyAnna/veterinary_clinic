package com.factoria.veterinary_clinic.models;

import java.time.LocalDateTime;

public class Appointment {
    private Long id;
    private LocalDateTime appointmentDateTime;
    private String type;
    private String reason;
    private String status;

    protected Appointment() {}

    public Appointment(Long id, LocalDateTime appointmentDateTime,String type, String reason, String status) {
        this.type = type;
        this.reason = reason;
        this.status = status;
    }

    public Long getId() {
        return id;
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
}
