CREATE TABLE addresses(

	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	street VARCHAR(255),
	number VARCHAR(10),
	neighborhood VARCHAR(255),
	city VARCHAR(255),
	state VARCHAR(255),
	complement VARCHAR(255),
	zipcode BIGINT,
	user_id BIGINT,
	FOREIGN KEY (user_id) REFERENCES users(id)

);