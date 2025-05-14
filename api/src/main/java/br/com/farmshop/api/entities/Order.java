package br.com.farmshop.api.entities;

import java.time.Instant;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import br.com.farmshop.api.enums.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders") // Nome da tabela no bd
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Float totalPrice;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.PROCESSING;
	
	private String trackingCode;
	
	@CreationTimestamp
	private Instant created_at;
	
	@UpdateTimestamp
	private Instant updated_at;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	public Order() {}

	public Order(Long id, Float totalPrice, OrderStatus status, String trackingCode, Instant created_at, Instant updated_at, User user, Payment payment) {
		this.id = id;
		this.totalPrice = totalPrice;
		this.status = status;
		this.trackingCode = trackingCode;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.user = user;
		this.payment = payment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getTrackingCode() {
		return trackingCode;
	}

	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
