package br.com.farmshop.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.farmshop.api.entities.Cart;
import br.com.farmshop.api.entities.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Optional<Cart> findByUser(User user);

	@Query(value = ("SELECT SUM(subtotal) FROM cart_items WHERE cart_id = :cart_id"), nativeQuery = true)
	Float getTotalPrice(@Param("cart_id") Long cart_id);  
	
}
