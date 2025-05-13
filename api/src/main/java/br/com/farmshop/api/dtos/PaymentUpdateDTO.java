package br.com.farmshop.api.dtos;

import br.com.farmshop.api.enums.PaymentStatus;

public record PaymentUpdateDTO(Long id, PaymentStatus paymentStatus) {

}
