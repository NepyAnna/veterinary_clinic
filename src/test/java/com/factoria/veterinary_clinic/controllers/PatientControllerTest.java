package com.factoria.veterinary_clinic.controllers;

import com.factoria.veterinary_clinic.dtos.PatientDto;
import com.factoria.veterinary_clinic.models.Patient;
import com.factoria.veterinary_clinic.services.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*prueba de cambios */
@WebMvcTest(PatientController.class)
public class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PatientService patientService;
    
    @Autowired
    private ObjectMapper objectMapper;

    private Patient patient;
    private PatientDto patientDto;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(1);
        patient.setName("Bella");
        patient.setAge(3);
        patient.setBreed("Golden Retriever");
        patient.setGender("Female");
        patient.setIdentificationNumber("12345");
        patient.setGuardianName("John Doe");
        patient.setGuardianPhone("555-1234");

        patientDto = new PatientDto(
                1L,
                "Bella",
                3,
                "Golden Retriever",
                "Female",
                "12345",
                "John Doe",
                "555-1234",
                1L,
                Collections.emptyList()
        );
    }

    @Test
    void testCreatePatient() throws Exception {
when(patientService.createPatient(any(PatientDto.class))).thenReturn(patient);

        mockMvc.perform(post("/api/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(patient.getId().intValue())))
                .andExpect(jsonPath("$.name", is(patient.getName())));

        verify(patientService, times(1)).createPatient(any(PatientDto.class));
    }

    @Test
    void testDeletePatient() throws Exception {
        doNothing().when(patientService).deletePatient(1L);

        mockMvc.perform(delete("/api/patients/1"))
                .andExpect(status().isNoContent());

        verify(patientService, times(1)).deletePatient(1L);
    }

    @Test
    void testGetAllPatients() throws Exception {
        when(patientService.getAllPatients()).thenReturn(Arrays.asList(patient));

        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(patient.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(patient.getName())));

        verify(patientService, times(1)).getAllPatients();
    }

    @Test
    void testGetPatientById() throws Exception {
        when(patientService.getPatientById(1L)).thenReturn(Optional.of(patient));

        mockMvc.perform(get("/api/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(patient.getId().intValue())))
                .andExpect(jsonPath("$.name", is(patient.getName())));

        verify(patientService, times(1)).getPatientById(1L);
    }

    @Test
    void testUpdatePatient() throws Exception {
        when(patientService.updatePatient(eq(1L), any(PatientDto.class))).thenReturn(patient);

        mockMvc.perform(put("/api/patients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(patient.getId().intValue())))
                .andExpect(jsonPath("$.name", is(patient.getName())));

        verify(patientService, times(1)).updatePatient(eq(1L), any(PatientDto.class));
    }
}
