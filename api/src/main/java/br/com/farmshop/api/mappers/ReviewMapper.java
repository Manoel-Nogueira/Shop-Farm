package br.com.farmshop.api.mappers;

import br.com.farmshop.api.dtos.ReviewCreateDTO;
import br.com.farmshop.api.dtos.ReviewResponseDTO;
import br.com.farmshop.api.entities.Review;

public class ReviewMapper {
	
	public static ReviewResponseDTO toDTO(Review review) {
		
		return new ReviewResponseDTO(review.getId(), review.getRating(), review.getComment(), review.getCreated_at(), review.getUpdate_at(), UserMapper.toDTO(review.getUser()), ProductMapper.toDTO(review.getProduct()));
		
	}
	
	public static Review toEntity(ReviewCreateDTO reviewCreateDTO) {
		
		Review review = new Review();
		review.setRating(reviewCreateDTO.rating());
		review.setComment(reviewCreateDTO.comment());
		
		return review;
		
	}
	
}
