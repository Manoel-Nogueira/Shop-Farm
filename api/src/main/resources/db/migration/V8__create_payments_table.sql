CREATE TABLE payments (
	
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	paymentMethod VARCHAR(6) NOT NULL,
	amount DECIMAL(15,2) NOT NULL,
	paymentStatus VARCHAR(12) NOT NULL,
	user_id BIGINT,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES users(id)

);