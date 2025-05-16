package br.com.farmshop.api.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.farmshop.api.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
	
	List<Image> findByProductId(Long product_id);
	
}
