package br.com.farmshop.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.farmshop.api.entities.CartItem;
import br.com.farmshop.api.entities.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	CartItem findByProduct(Product product);
	
	List<CartItem> findByCartId(Long cart_id);
	
	Void deleteByCartId(Long cart_id);

}
