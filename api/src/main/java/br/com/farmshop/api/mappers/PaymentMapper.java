package br.com.farmshop.api.mappers;

import br.com.farmshop.api.dtos.PaymentCreateDTO;
import br.com.farmshop.api.dtos.PaymentResponseDTO;
import br.com.farmshop.api.entities.Payment;

public class PaymentMapper {
	
	public static PaymentResponseDTO toDTO(Payment payment) {
		
		return new PaymentResponseDTO(payment.getId(), payment.getPaymentMethod(), payment.getAmount(), payment.getPaymentStatus(), UserMapper.toDTO(payment.getUser()), payment.getCreated_at(), payment.getUpdated_at());
		
	}
	
	public static Payment toEntity(PaymentCreateDTO paymentCreateDTO) {
		
		Payment payment = new Payment();
		
		payment.setPaymentMethod(paymentCreateDTO.paymentMethod());
		
		return payment;
		
	}

}
