package com.factoria.veterinary_clinic.controllers;

import com.factoria.veterinary_clinic.dtos.PatientDto;
import com.factoria.veterinary_clinic.models.Patient;
import com.factoria.veterinary_clinic.models.User;
import com.factoria.veterinary_clinic.services.PatientService;
import com.factoria.veterinary_clinic.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @Mock
    private UserService userService;

    @InjectMocks
    private PatientController patientController;

    private Patient testPatient;
    private PatientDto testPatientDto;
    private User testUser;
    private UserDetails adminUserDetails;
    private UserDetails regularUserDetails;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setEmail("test@example.com");

        testPatient = new Patient();
        testPatient.setId(1L);
        testPatient.setName("Max");
        testPatient.setAge(5);
        testPatient.setBreed("Golden Retriever");
        testPatient.setGender("Male");
        testPatient.setIdentificationNumber("123ABC");
        testPatient.setGuardianName("John Doe");
        testPatient.setGuardianPhone("123-456-7890");
        testPatient.setUser(testUser);
        testPatient.setAppointments(Collections.emptyList());

        testPatientDto = new PatientDto(
                1L, "Max", 5, "Golden Retriever", "Male",
                "123ABC", "John Doe", "123-456-7890",
                1L, Collections.emptyList());

        adminUserDetails = createUserDetails("admin@example.com", "ROLE_ADMIN");
        regularUserDetails = createUserDetails("test@example.com", "ROLE_USER");
    }

    @Test
    void getAllPatients_AdminUser_ReturnsAllPatients() {
        when(patientService.getAllPatients()).thenReturn(Arrays.asList(testPatient));

        ResponseEntity<List<PatientDto>> response = patientController.getAllPatients(adminUserDetails);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), hasSize(1));
        assertThat(response.getBody().get(0).name(), is("Max"));
        verify(patientService).getAllPatients();
    }

    @Test
    void getAllPatients_RegularUser_ReturnsUserPatients() {
        when(userService.getUserByEmail(anyString())).thenReturn(Optional.of(testUser));
        when(patientService.getPatientsByUserId(anyLong())).thenReturn(Arrays.asList(testPatient));

        ResponseEntity<List<PatientDto>> response = patientController.getAllPatients(regularUserDetails);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), hasSize(1));
        assertThat(response.getBody().get(0).name(), is("Max"));
        verify(patientService).getPatientsByUserId(1L);
    }

    @Test
    void getPatientById_AdminUser_ReturnsPatient() {

        when(patientService.getPatientById(1L)).thenReturn(Optional.of(testPatient));

        ResponseEntity<PatientDto> response = patientController.getPatientById(1L, adminUserDetails);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().name(), is("Max"));
    }

    @Test
    void getPatientById_NotFound_Returns404() {
        when(patientService.getPatientById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<PatientDto> response = patientController.getPatientById(1L, adminUserDetails);

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        verify(patientService).getPatientById(1L);
    }

    @Test
    void createPatient_ValidData_ReturnsCreatedPatient() {
        when(patientService.createPatient(any(PatientDto.class))).thenReturn(testPatient);

        ResponseEntity<Patient> response = patientController.createPatient(testPatientDto, adminUserDetails);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getName(), is("Max"));
        verify(patientService).createPatient(any(PatientDto.class));
    }

    @Test
    void updatePatient_ValidData_ReturnsUpdatedPatient() {

        when(patientService.updatePatient(anyLong(), any(PatientDto.class))).thenReturn(testPatient);

        ResponseEntity<PatientDto> response = patientController.updatePatient(1L, testPatientDto, adminUserDetails);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().name(), is("Max"));
        verify(patientService).updatePatient(eq(1L), any(PatientDto.class));
    }

    @Test
    void deletePatient_ExistingPatient_ReturnsNoContent() {

        when(patientService.getPatientById(1L)).thenReturn(Optional.of(testPatient));
        doNothing().when(patientService).deletePatient(1L);

        ResponseEntity<Void> response = patientController.deletePatient(1L, adminUserDetails);

        assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
        verify(patientService).deletePatient(1L);
    }

    @Test
    void deletePatient_NonExistingPatient_Returns404() {

        when(patientService.getPatientById(1L)).thenReturn(Optional.empty());
        doThrow(new IllegalArgumentException()).when(patientService).deletePatient(1L);

        ResponseEntity<Void> response = patientController.deletePatient(1L, adminUserDetails);

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    private UserDetails createUserDetails(String email, String role) {
        return new org.springframework.security.core.userdetails.User(
                email,
                "password",
                Collections.singletonList(new SimpleGrantedAuthority(role)));
    }
}
