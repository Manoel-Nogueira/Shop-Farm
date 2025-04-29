CREATE TABLE reviews (

	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	rating BIGINT DEFAULT 0,
	comment VARCHAR(255),
	user_id BIGINT,
	product_id BIGINT,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (product_id) REFERENCES products(id)

);