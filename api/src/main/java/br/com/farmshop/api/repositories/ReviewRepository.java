package br.com.farmshop.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.farmshop.api.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	List<Review> findByProductId(Long product_id);
	
	@Query(value = ("SELECT SUM(rating) / COUNT(*) FROM reviews WHERE product_id = :product_id"), nativeQuery = true)
	Float ratingAverage(@Param("product_id") Long product_id);  

}
