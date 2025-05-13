package br.com.farmshop.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.farmshop.api.dtos.PaymentCreateDTO;
import br.com.farmshop.api.dtos.PaymentResponseDTO;
import br.com.farmshop.api.dtos.PaymentUpdateDTO;
import br.com.farmshop.api.entities.Cart;
import br.com.farmshop.api.entities.Payment;
import br.com.farmshop.api.entities.User;
import br.com.farmshop.api.mappers.PaymentMapper;
import br.com.farmshop.api.repositories.CartRepository;
import br.com.farmshop.api.repositories.PaymentRepository;
import br.com.farmshop.api.repositories.UserRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	
	public PaymentResponseDTO storePayment(PaymentCreateDTO paymentCreateDTO) {
		
		Payment payment = PaymentMapper.toEntity(paymentCreateDTO);
		User user = userRepository.findById(paymentCreateDTO.user_id()).orElseThrow(() -> new RuntimeException("Error in OrderService store"));
		Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Error in OrderService store"));
		
		payment.setAmount(cartRepository.getTotalPrice(cart.getId()));
		payment.setUser(user);
		
		return PaymentMapper.toDTO(paymentRepository.save(payment));
		
		
	}
	
	public PaymentResponseDTO updatePayment(PaymentUpdateDTO paymentUpdateDTO) {
		
		Payment payment = paymentRepository.findById(paymentUpdateDTO.id()).orElseThrow(() -> new RuntimeException("Error in OrderService update"));
		
		payment.setPaymentStatus(paymentUpdateDTO.paymentStatus());
		
		return PaymentMapper.toDTO(paymentRepository.save(payment));
		
	}
	
}
