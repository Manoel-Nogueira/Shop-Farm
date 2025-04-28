package br.com.farmshop.api.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.farmshop.api.dtos.ProductCreateDTO;
import br.com.farmshop.api.dtos.ProductResponseDTO;
import br.com.farmshop.api.dtos.ProductUpdateDTO;
import br.com.farmshop.api.entities.Brand;
import br.com.farmshop.api.entities.Category;
import br.com.farmshop.api.entities.Product;
import br.com.farmshop.api.entities.User;
import br.com.farmshop.api.mappers.ProductMapper;
import br.com.farmshop.api.repositories.BrandRepository;
import br.com.farmshop.api.repositories.CategoryRepository;
import br.com.farmshop.api.repositories.ProductRepository;
import br.com.farmshop.api.repositories.UserRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BrandRepository brandRepository;

	public ProductResponseDTO storeProduct(ProductCreateDTO productCreateDTO) {
		
		
		Product product = ProductMapper.toEntity(productCreateDTO);
		User user = userRepository.findById(productCreateDTO.user_id()).orElseThrow(() -> new RuntimeException("Error in ProductService store 1"));
		Category category = categoryRepository.findById(productCreateDTO.category_id()).orElseThrow(() -> new RuntimeException("Error in ProductService store 2"));
		Brand brand = brandRepository.findById(productCreateDTO.brand_id()).orElseThrow(() -> new RuntimeException("Error in ProductService store 3"));
		
		/*
				 {
			"name": "Fertilizante Foliar Cropvit Mol Forplant - 1 Litro",
			"description": "CROPVIT MOL é um fertilizante com formulação balanceada pronta para atender principalmente as culturas mais exigentes em Cobalto e Molibdênio. ",
			"price": 223.12,
			"stock": 50,
			"user_id": 1,
			"category_id": 0
		} 
		 */
		
		if(category == null) {
			
			category = categoryRepository.findById((long) 1).orElseThrow(() -> new RuntimeException("Error in ProductService store 4"));
			
		}
		
		if(brand == null) {
			
			brand = brandRepository.findById((long) 1).orElseThrow(() -> new RuntimeException("Error in ProductService store"));
			
		}
		
		product.setUser(user);
		product.setCategory(category);
		product.setBrand(brand);
		
		return ProductMapper.toDTO(productRepository.save(product));

	}
	
	public ProductResponseDTO updateProduct(ProductUpdateDTO productUpdateDTO){
		
		Product product = productRepository.findById(productUpdateDTO.id()).orElseThrow(() -> new RuntimeException("Error in ProductService update"));
		User user = userRepository.findById(productUpdateDTO.user_id()).orElseThrow(() -> new RuntimeException("Error in ProductService update"));
		Category category = categoryRepository.findById(productUpdateDTO.category_id()).orElseThrow(() -> new RuntimeException("Error in ProductService update"));
		
		product.setName(productUpdateDTO.name());
		product.setDescription(productUpdateDTO.description());
		product.setPrice(productUpdateDTO.price());
		product.setStock(productUpdateDTO.stock());
		product.setUser(user);
		product.setCategory(category);
		
		 
		return ProductMapper.toDTO(productRepository.save(product));
		
	}
	
	public List<ProductResponseDTO> listAllProduct(){
		
		return productRepository.findAll().stream().map(ProductMapper::toDTO).toList();
		
	}
	
	public ProductResponseDTO listProductById(Long id){
		
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in ProductService listById"));
		
		return ProductMapper.toDTO(product);
		
	}
	
	public Boolean destroyProduct(Long id) {
		
		productRepository.delete(productRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in ProductService delete")));
		
		return true;
			
	}
	
}

// nome de class CM
// java camelcase
