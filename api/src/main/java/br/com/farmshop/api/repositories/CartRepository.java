package br.com.farmshop.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.farmshop.api.entities.Cart;
import br.com.farmshop.api.entities.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Optional<Cart> findByUser(User user);

}
