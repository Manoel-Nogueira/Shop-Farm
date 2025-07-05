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
import br.com.farmshop.api.mappers.ProductMapper;
import br.com.farmshop.api.repositories.BrandRepository;
import br.com.farmshop.api.repositories.CategoryRepository;
import br.com.farmshop.api.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BrandRepository brandRepository;

	public ProductResponseDTO storeProduct(ProductCreateDTO productCreateDTO) {
		
		Product product = ProductMapper.toEntity(productCreateDTO);
		Category category = categoryRepository.findById(productCreateDTO.category_id()).orElseThrow(() -> new RuntimeException("Error in ProductService store"));
		Brand brand = brandRepository.findById(productCreateDTO.brand_id()).orElseThrow(() -> new RuntimeException("Error in ProductService store"));
		
		if(category == null) {
			
			category = categoryRepository.findById((long) 1).orElseThrow(() -> new RuntimeException("Error in ProductService store"));
			
		}
		
		if(brand == null) {
			
			brand = brandRepository.findById((long) 1).orElseThrow(() -> new RuntimeException("Error in ProductService store"));
			
		}
		
		product.setCategory(category);
		product.setBrand(brand);
		
		return ProductMapper.toDTO(productRepository.save(product));

	}
	
	public ProductResponseDTO updateProduct(ProductUpdateDTO productUpdateDTO){
		
		Product product = productRepository.findById(productUpdateDTO.id()).orElseThrow(() -> new RuntimeException("Error in ProductService update"));
		Category category = categoryRepository.findById(productUpdateDTO.category_id()).orElseThrow(() -> new RuntimeException("Error in ProductService update"));
		
		product.setName(productUpdateDTO.name());
		product.setDescription(productUpdateDTO.description());
		product.setPrice(productUpdateDTO.price());
		product.setStock(productUpdateDTO.stock());
		product.setCategory(category);
		
		 
		return ProductMapper.toDTO(productRepository.save(product));
		
	}
	
	public List<ProductResponseDTO> listAllProduct(){
		
		return productRepository.findAll().stream().map(ProductMapper::toDTO).toList();
		
	}
	
	public ProductResponseDTO showProductById(Long id){
		
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
