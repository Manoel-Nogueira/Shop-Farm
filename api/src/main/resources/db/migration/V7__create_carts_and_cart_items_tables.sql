CREATE TABLE carts (

	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	user_id BIGINT,
	FOREIGN KEY (user_id) REFERENCES users(id)

);

CREATE TABLE cart_items (

	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	quantity BIGINT NOT NULL,
	subtotal DECIMAL(15,2) NOT NULL,
	cart_id BIGINT,
	product_id BIGINT,
	FOREIGN KEY (cart_id) REFERENCES carts(id),
	FOREIGN KEY (product_id) REFERENCES products(id)

);