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
import br.com.farmshop.api.dtos.ImageCreateDTO;
import br.com.farmshop.api.dtos.ImageResponseDTO;
import br.com.farmshop.api.dtos.ImageUpdateDTO;
import br.com.farmshop.api.services.ImageService;

@RestController 
@RequestMapping("/api/images")
public class ImageController {
	
	@Autowired
	ImageService imageService; 
	
	//para nomear o caminho do PostMapping(nome)
	@PostMapping
	public ResponseEntity<ImageResponseDTO> createImage(@RequestBody ImageCreateDTO imageCreateDTO) {
		
		ImageResponseDTO imageResponseDTO = imageService.storeImage(imageCreateDTO);
		return new ResponseEntity<>(imageResponseDTO, HttpStatus.CREATED);
		
	}
	
	
	@PutMapping
	public ResponseEntity<ImageResponseDTO> updateImage(@RequestBody ImageUpdateDTO imageUpdateDTO) {
		
		ImageResponseDTO imageResponseDTO = imageService.updateImage(imageUpdateDTO);
		return new ResponseEntity<>(imageResponseDTO, HttpStatus.OK);
		
	}
	
	//para nomear o caminho do GetMapping(nome)
	@GetMapping("/all/{product_id}")
	public List<ImageResponseDTO> listAllImage(@PathVariable("product_id") Long id){
		
		return imageService.listAllImage(id);
		
	}
	
	@GetMapping("/{image_id}")
	public ResponseEntity<ImageResponseDTO> listImageById(@PathVariable("image_id") Long id){
		
		ImageResponseDTO imageResponseDTO = imageService.listImageById(id);
		return new ResponseEntity<>(imageResponseDTO, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{image_id}")
	public Boolean destroyImage(@PathVariable("image_id") Long id) {
		
		return imageService.destroyImage(id);
		
		
	}
	
}


// estudar java isidro