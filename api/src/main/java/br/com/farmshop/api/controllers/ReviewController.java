package br.com.farmshop.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.farmshop.api.dtos.ReviewCreateDTO;
import br.com.farmshop.api.dtos.ReviewResponseDTO;
import br.com.farmshop.api.dtos.ReviewUpdateDTO;
import br.com.farmshop.api.services.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<ReviewResponseDTO> createReview(@RequestBody ReviewCreateDTO reviewCreateDTO) {
		
		ReviewResponseDTO reviewResponseDTO = reviewService.storeReview(reviewCreateDTO);
		
		return new ResponseEntity<>(reviewResponseDTO, HttpStatus.CREATED);
		
	}
	
	@PutMapping
	public ResponseEntity<ReviewResponseDTO> updateReview(@RequestBody ReviewUpdateDTO reviewUpdateDTO) {
		
		ReviewResponseDTO reviewResponseDTO = reviewService.updateReview(reviewUpdateDTO);
		
		return new ResponseEntity<>(reviewResponseDTO, HttpStatus.OK);
		
	}
	
	@GetMapping("/all/{product_id}")
	public List<ReviewResponseDTO> listAllReview(@PathVariable("product_id") Long id) {
		
		return reviewService.listAllReview(id);
		
	}
	
	@GetMapping("/{review_id}")
	public ResponseEntity<ReviewResponseDTO> listReviewById(@PathVariable("review_id") Long id) {
		
		ReviewResponseDTO reviewResponseDTO = reviewService.listReviewById(id);
		
		return new ResponseEntity<>(reviewResponseDTO, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{review_id}")
	public Boolean destroyReview(@PathVariable("review_id") Long id) {
		
		return reviewService.destroyReview(id);
		
	}

}
