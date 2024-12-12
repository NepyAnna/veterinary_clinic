package com.factoria.veterinary_clinic.dtos;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AuthResponseDtoTest {

    @Test
    void testAuthResponseDtoConstructorAndGetters() {

        String token = "dummyToken123";
        String role = "USER";

        AuthResponseDto authResponseDto = new AuthResponseDto(token, role);

        assertThat(authResponseDto, is(notNullValue()));
        assertThat(authResponseDto.token(), is(equalTo(token)));
        assertThat(authResponseDto.role(), is(equalTo(role)));
    }
}
