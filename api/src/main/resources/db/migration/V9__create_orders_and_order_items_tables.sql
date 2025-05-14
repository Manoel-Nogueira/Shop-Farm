CREATE TABLE orders (

	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	total_price DECIMAL(15,2) NOT NULL,
	status VARCHAR(10),
	tracking_code VARCHAR(255),
	user_id BIGINT,
	payment_id BIGINT,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
	FOREIGN KEY (payment_id) REFERENCES payments(id) ON DELETE CASCADE

);

CREATE TABLE order_items (

	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	quantity BIGINT NOT NULL,
	subtotal DECIMAL(15,2) NOT NULL,
	order_id BIGINT,
	product_id BIGINT,
	FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
	FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE

);