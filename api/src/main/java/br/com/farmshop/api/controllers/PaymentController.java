package br.com.farmshop.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.resources.payment.Payment;
import br.com.farmshop.api.dtos.MPPaymentDTO;
import br.com.farmshop.api.dtos.PaymentCreateDTO;
import br.com.farmshop.api.dtos.PaymentResponseDTO;
import br.com.farmshop.api.dtos.PaymentUpdateDTO;
import br.com.farmshop.api.services.PaymentService;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@Value("${mp.token}")
	private String mercadoPagoToken;
	
	@PostConstruct
	public void init() {
		
		MercadoPagoConfig.setAccessToken(mercadoPagoToken);
		
	}
	
	@PostMapping
	public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentCreateDTO paymentCreateDTO) {
	
		PaymentResponseDTO paymentResponseDTO = paymentService.storePayment(paymentCreateDTO);
	
		return new ResponseEntity<>(paymentResponseDTO, HttpStatus.CREATED);
		
	}
	
	@PutMapping
	public ResponseEntity<PaymentResponseDTO> updatePayment(@RequestBody PaymentUpdateDTO paymentUpdateDTO) {
		
		PaymentResponseDTO paymentResponseDTO = paymentService.updatePayment(paymentUpdateDTO);
		
		return new ResponseEntity<>(paymentResponseDTO, HttpStatus.OK);
		
	}
	
	@PostMapping("/pay")
	public ResponseEntity<?> processPayment(@RequestBody MPPaymentDTO mPPaymentDTO) {
		
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		System.out.println(mPPaymentDTO);
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		
		try {
			
			PaymentClient client = new PaymentClient();
			
			PaymentCreateRequest request = PaymentCreateRequest
					.builder()
					.transactionAmount(mPPaymentDTO.amount())
					.token(mPPaymentDTO.token())
					.description(mPPaymentDTO.description())
					.installments(mPPaymentDTO.installments())
					.paymentMethodId(mPPaymentDTO.paymentMethodId())
					.issuerId(mPPaymentDTO.issuerId())
					.payer(
							PaymentPayerRequest.builder()
							.email(mPPaymentDTO.email())
							.identification(
									IdentificationRequest.builder()
									.type("CPF")
									.number(mPPaymentDTO.CPF())
									.build()
							).build()
					).build();

			Payment payment = client.create(request);
				
			return ResponseEntity.status(HttpStatus.OK).body(payment);
			
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}
		
	}
	
}
