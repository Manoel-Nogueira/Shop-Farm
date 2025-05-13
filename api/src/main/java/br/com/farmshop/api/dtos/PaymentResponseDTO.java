package br.com.farmshop.api.dtos;

import java.time.Instant;

import br.com.farmshop.api.enums.PaymentMethod;
import br.com.farmshop.api.enums.PaymentStatus;

public record PaymentResponseDTO(Long id, PaymentMethod paymentMethod, Float amount, PaymentStatus paymentStatus, UserResponseDTO user, Instant create_at, Instant update_at) {

}
