CREATE TABLE products(

	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255) NOT NULL,
	price DECIMAL(15,2) NOT NULL,
	rating FLOAT DEFAULT 0,
	stock BIGINT NOT NULL,
	user_id BIGINT,
	category_id BIGINT DEFAULT 1,
	brand_id BIGINT DEFAULT 1,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
	FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,
	FOREIGN KEY (brand_id) REFERENCES brands(id) ON DELETE CASCADE

);

CREATE TABLE images(

	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	url TEXT NOT NULL,
	product_id BIGINT,
	FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE

);