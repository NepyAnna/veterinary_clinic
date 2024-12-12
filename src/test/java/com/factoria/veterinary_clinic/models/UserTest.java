package com.factoria.veterinary_clinic.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.factoria.veterinary_clinic.enums.Role;

public class UserTest {

    @Test
    void testGetRole() {
        User user = new User();
        user.setRole(Role.ADMIN);

        Role role = user.getRole();

        assertEquals(Role.ADMIN, role, "The role should be ADMIN");
    }

    @Test
    void testSetRole() {
         User user = new User();
         user.setRole(Role.USER);
         assertEquals(Role.USER, user.getRole());
         user.setRole(Role.ADMIN);
         assertEquals(Role.ADMIN, user.getRole());
    }
}
