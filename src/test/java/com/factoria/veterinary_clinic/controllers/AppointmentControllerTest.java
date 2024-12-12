package com.factoria.veterinary_clinic.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.factoria.veterinary_clinic.dtos.AppointmentDto;
import com.factoria.veterinary_clinic.enums.AppointmentStatus;
import com.factoria.veterinary_clinic.enums.AppointmentType;
import com.factoria.veterinary_clinic.services.AppointmentService;
import com.factoria.veterinary_clinic.services.JwtService;
import com.factoria.veterinary_clinic.services.PatientService;
import com.factoria.veterinary_clinic.services.TokenBlacklistService;

@SuppressWarnings("removal")
@WebMvcTest(controllers = AppointmentController.class)
@AutoConfigureMockMvc(addFilters = false)
@MockBean(JwtService.class)
@MockBean(TokenBlacklistService.class)
public class AppointmentControllerTest {
        @Autowired
        MockMvc mockMvc;

        @MockBean
        AppointmentService appointmentService;

        @MockBean
        private PatientService patientService;

        @Test
        @WithMockUser(roles = "USER")
        void testIndex() throws Exception {
                AppointmentDto appointmentDto = new AppointmentDto(
                                1L, 1L, "John Doe", LocalDateTime.of(2024, 12, 21, 10, 0),
                                AppointmentType.STANDARD, "Check-up", AppointmentStatus.PENDING);

                when(appointmentService.findAll()).thenReturn(List.of(appointmentDto));

                mockMvc.perform(get("/api/appointments"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.size()").value(1))
                                .andExpect(jsonPath("$[0].id").value(1))
                                .andExpect(jsonPath("$[0].patientName").value("John Doe"));
        }

        @Test
        @WithMockUser(roles = "USER")
        void testShow() throws Exception {
                AppointmentDto appointment = new AppointmentDto(
                                1L, 1L, "John Doe", LocalDateTime.of(2024, 12, 21, 10, 0),
                                AppointmentType.STANDARD, "Check-up", AppointmentStatus.PENDING);

                when(appointmentService.findById(1L)).thenReturn(appointment);

                mockMvc.perform(get("/api/appointments/{id}", 1L))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.patientName").value("John Doe"))
                                .andExpect(jsonPath("$.reason").value("Check-up"));
        }

        @Test
        @WithMockUser(roles = "USER")
        void testCreateAppointment() throws Exception {
                AppointmentDto createdAppointment = new AppointmentDto(
                                1L, 1L, "John Doe", LocalDateTime.of(2024, 12, 21, 10, 0),
                                AppointmentType.STANDARD, "Check-up", AppointmentStatus.PENDING);

                when(appointmentService.createAppointment(any(AppointmentDto.class))).thenReturn(createdAppointment);
                when(patientService.isPatientOwnedByUser(1L, "user")).thenReturn(true);

                mockMvc.perform(post("/api/appointments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                                {
                                                    "patientId": 1,
                                                    "appointmentDateTime": "2024-12-21T10:00:00",
                                                    "type": "STANDARD",
                                                    "reason": "Check-up",
                                                    "status": "PENDING"
                                                }
                                                """))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.reason").value("Check-up"));
        }

        @Test
        @WithMockUser(roles = "USER")
        void testUpdateAppointment() throws Exception {
                AppointmentDto updatedAppointment = new AppointmentDto(
                                1L, 1L, "John Doe", LocalDateTime.of(2024, 12, 22, 10, 0),
                                AppointmentType.STANDARD, "Updated reason", AppointmentStatus.PENDING);

                when(appointmentService.updateAppointment(Mockito.eq(1L), any(AppointmentDto.class)))
                                .thenReturn(updatedAppointment);

                mockMvc.perform(put("/api/appointments/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                                {
                                                    "patientId": 1,
                                                    "appointmentDateTime": "2024-12-22T10:00:00",
                                                    "type": "STANDARD",
                                                    "reason": "Updated reason",
                                                    "status": "PENDING"
                                                }
                                                """))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.reason").value("Updated reason"));
        }

        @Test
        @WithMockUser(roles = "USER")
        void testDeleteAppointment() throws Exception {
                when(patientService.isPatientOwnedByUser(1L, "user")).thenReturn(true);
                when(appointmentService.findById(1L)).thenReturn(new AppointmentDto(
                                1L, 1L, "John Doe", LocalDateTime.of(2024, 12, 21, 10, 0),
                                AppointmentType.STANDARD, "Check-up", AppointmentStatus.PENDING));

                mockMvc.perform(delete("/api/appointments/1"))
                                .andExpect(status().isNoContent());
        }
}