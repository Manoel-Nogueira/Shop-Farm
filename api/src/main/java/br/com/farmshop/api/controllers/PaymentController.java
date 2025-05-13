package br.com.farmshop.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.farmshop.api.dtos.PaymentCreateDTO;
import br.com.farmshop.api.dtos.PaymentResponseDTO;
import br.com.farmshop.api.dtos.PaymentUpdateDTO;
import br.com.farmshop.api.services.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentCreateDTO paymentCreateDTO) {
	
		PaymentResponseDTO paymentResponseDTO = paymentService.storePayment(paymentCreateDTO);
	
		return new ResponseEntity<>(paymentResponseDTO, HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<PaymentResponseDTO> updatePayment(@RequestBody PaymentUpdateDTO paymentUpdateDTO) {
		
		PaymentResponseDTO paymentResponseDTO = paymentService.updatePayment(paymentUpdateDTO);
		
		return new ResponseEntity<>(paymentResponseDTO, HttpStatus.OK);
		
	}
	
}
