package com.targa.labs.quarkushop.service;

import org.starterProj.Service.PaymentService;
import org.starterProj.dto.PaymentDto;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    @Test
    void testMapToDtoForNullInput() {
        PaymentDto paymentDto = PaymentService.mapToDto(null, new Random().nextLong());
        assertThat(paymentDto).isNull();
    }
}