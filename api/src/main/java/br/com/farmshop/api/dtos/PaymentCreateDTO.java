package br.com.farmshop.api.dtos;

import br.com.farmshop.api.enums.PaymentMethod;

public record PaymentCreateDTO(PaymentMethod paymentMethod, Long user_id) {

}
