package br.com.farmshop.api.dtos;

import java.math.BigDecimal;

public record MPPaymentDTO(String token, String issuerId , BigDecimal amount, String CPF, String description, int installments, String paymentMethodId, String email) {

}
