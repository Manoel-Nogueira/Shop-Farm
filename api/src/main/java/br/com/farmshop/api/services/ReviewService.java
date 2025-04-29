package br.com.farmshop.api.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.farmshop.api.dtos.ReviewCreateDTO;
import br.com.farmshop.api.dtos.ReviewResponseDTO;
import br.com.farmshop.api.dtos.ReviewUpdateDTO;
import br.com.farmshop.api.entities.Product;
import br.com.farmshop.api.entities.Review;
import br.com.farmshop.api.entities.User;
import br.com.farmshop.api.mappers.ReviewMapper;
import br.com.farmshop.api.repositories.ProductRepository;
import br.com.farmshop.api.repositories.ReviewRepository;
import br.com.farmshop.api.repositories.UserRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public ReviewResponseDTO storeReview(ReviewCreateDTO reviewCreateDTO) {
		
		User user = userRepository.findById(reviewCreateDTO.user_id()).orElseThrow(() -> new RuntimeException("Error in ReviewService store"));
		Product product = productRepository.findById(reviewCreateDTO.product_id()).orElseThrow(() -> new RuntimeException("Error in ReviewService store"));
		
		Review review = ReviewMapper.toEntity(reviewCreateDTO);
		review.setUser(user);
		review.setProduct(product);
		
		reviewRepository.save(review);
		
		product.setRating(reviewRepository.ratingAverage(product.getId()));
		productRepository.save(product);
		
		return ReviewMapper.toDTO(review);
		
	}
	
	public ReviewResponseDTO updateReview(ReviewUpdateDTO reviewUpdateDTO) {
			
		Review review = reviewRepository.findById(reviewUpdateDTO.id()).orElseThrow(() -> new RuntimeException("Error in ReviewService update"));
		User user = userRepository.findById(reviewUpdateDTO.user_id()).orElseThrow(() -> new RuntimeException("Error in ReviewService update"));
		Product product = productRepository.findById(reviewUpdateDTO.product_id()).orElseThrow(() -> new RuntimeException("Error in ReviewService update"));
		
		review.setRating(reviewUpdateDTO.rating());
		review.setComment(reviewUpdateDTO.comment());
		review.setUser(user);
		review.setProduct(product);
		
		reviewRepository.save(review);
		
		product.setRating(reviewRepository.ratingAverage(product.getId()));
		productRepository.save(product);

		return ReviewMapper.toDTO(review);
		
	}
	
	// Listando todas as review de um produto
	public List<ReviewResponseDTO> listAllReview(Long id){
		
		return reviewRepository.findByProductId(id).stream().map(ReviewMapper::toDTO).toList();
		
	}
	
	public ReviewResponseDTO listReviewById(Long id) {
		
		Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in ReviewService listById"));
		
		return ReviewMapper.toDTO(review);
		
	}
	
	public Boolean destroyReview(Long id) {
		
		Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in ReviewService destroy"));
		Product product = productRepository.findById(review.getProduct().getId()).orElseThrow(() -> new RuntimeException("Error in ReviewService destroy")) ;
		
		reviewRepository.delete(review);
		
		product.setRating(reviewRepository.ratingAverage(product.getId()));
		productRepository.save(product);
		
		return true;
		
	}

}
