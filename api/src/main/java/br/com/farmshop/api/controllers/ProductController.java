package br.com.farmshop.api.controllers;

import java.util.ArrayList;
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

import br.com.farmshop.api.dtos.ImageResponseDTO;
import br.com.farmshop.api.dtos.ProductCreateDTO;
import br.com.farmshop.api.dtos.ProductImageResponseDTO;
import br.com.farmshop.api.dtos.ProductResponseDTO;
import br.com.farmshop.api.dtos.ProductUpdateDTO;
import br.com.farmshop.api.services.ImageService;
import br.com.farmshop.api.services.ProductService;

@RestController 
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	ProductService productService; 
	
	@Autowired
	ImageService imageService;
	
	//para nomear o caminho do PostMapping(nome)
	@PostMapping
	public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
		
		ProductResponseDTO productResponseDTO = productService.storeProduct(productCreateDTO);
		return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
		
	}
	
	@PutMapping
	public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody ProductUpdateDTO productUpdateDTO) {
		
		ProductResponseDTO productResponseDTO = productService.updateProduct(productUpdateDTO);
		return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
		
	}
	
	//para nomear o caminho do GetMapping(nome)
	@GetMapping("/list_all")
	public List<ProductImageResponseDTO> listAll(){
		
		List<ProductResponseDTO> products = productService.listAllProduct();
		List<ProductImageResponseDTO> productsImages = new ArrayList<>();
		
		for(ProductResponseDTO productResponseDTO : products) {
			
			ProductImageResponseDTO productImageResponseDTO = new ProductImageResponseDTO(productResponseDTO, imageService.listAllImagesProduct(productResponseDTO.id()));
			
			productsImages.add(productImageResponseDTO);
			
		}
		
		return productsImages;
		
	}
	
	@GetMapping("/show/{product_id}")
	public ResponseEntity<ProductImageResponseDTO> listProductById(@PathVariable("product_id") Long id){
		
		ProductResponseDTO productResponseDTO = productService.showProductById(id);
		ProductImageResponseDTO productImageResponseDTO = new ProductImageResponseDTO(productResponseDTO, imageService.listAllImagesProduct(productResponseDTO.id()));
		
		return new ResponseEntity<>(productImageResponseDTO, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{product_id}")
	public Boolean destroyProduct(@PathVariable("product_id") Long id) {
		
		return productService.destroyProduct(id);
		
		
	}
	
}


// estudar java isidro