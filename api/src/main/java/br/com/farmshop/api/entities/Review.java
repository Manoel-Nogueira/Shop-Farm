package br.com.farmshop.api.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reviews")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long rating;
	private String comment;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@CreationTimestamp
	private Instant created_at;
	
	@CreationTimestamp
	private Instant updated_at;

	public Review() {}

	public Review(Long id, Long rating, String comment, User user, Product product, Instant created_at, Instant update_at) {
		this.id = id;
		this.rating = rating;
		this.comment = comment;
		this.user = user;
		this.product = product;
		this.created_at = created_at;
		this.updated_at = update_at;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Instant getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Instant created_at) {
		this.created_at = created_at;
	}

	public Instant getUpdate_at() {
		return updated_at;
	}

	public void setUpdate_at(Instant update_at) {
		this.updated_at = update_at;
	}

}
