package br.com.farmshop.api.dtos;

import java.time.Instant;
import br.com.farmshop.api.enums.OrderStatus;

public record OrderResponseDTO(Long id, Float totalPrice, OrderStatus orderStatus, String trackingCode, Instant create_at, Instant update_at, UserResponseDTO user, PaymentResponseDTO payment) {

}
