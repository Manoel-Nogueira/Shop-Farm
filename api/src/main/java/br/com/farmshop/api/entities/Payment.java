package br.com.farmshop.api.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import br.com.farmshop.api.enums.PaymentMethod;
import br.com.farmshop.api.enums.PaymentStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	private Float amount;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus = PaymentStatus.INPROCESSING;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	@CreationTimestamp
	private Instant created_at;
	
	@UpdateTimestamp
	private Instant updated_at;

	public Payment() {}

	public Payment(Long id, PaymentMethod paymentMethod, Float amount, PaymentStatus paymentStatus, User user, Instant created_at, Instant updated_at) {
		this.id = id;
		this.paymentMethod = paymentMethod;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.user = user;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Instant getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Instant created_at) {
		this.created_at = created_at;
	}

	public Instant getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Instant updated_at) {
		this.updated_at = updated_at;
	}

}
