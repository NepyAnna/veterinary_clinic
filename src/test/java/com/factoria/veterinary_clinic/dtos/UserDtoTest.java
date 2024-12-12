package com.factoria.veterinary_clinic.dtos;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class UserDtoTest {

    @Test
    void testUserDtoConstructorAndGetters() {

        String name = "John Doe";
        String password = "password123";
        String email = "john.doe@example.com";

        UserDto userDto = new UserDto(name, password, email);

        assertThat(userDto, is(notNullValue()));
        assertThat(userDto.name(), is(equalTo(name)));
        assertThat(userDto.password(), is(equalTo(password)));
        assertThat(userDto.email(), is(equalTo(email)));
    }
}
