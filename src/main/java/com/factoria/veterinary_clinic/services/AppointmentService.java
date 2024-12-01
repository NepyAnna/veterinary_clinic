package com.factoria.veterinary_clinic.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.factoria.veterinary_clinic.models.Appointment;
import com.factoria.veterinary_clinic.repositories.AppointmentRepository;

@Service
public class AppointmentService {
    private AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> findAll(){
        return repository.findAll();
    }

    public void deleteAppointment(Long id) {
        repository.deleteById(id);
    }

    public Appointment updateAppointment(Long id, Appointment appointment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAppointment'");
    }
}
