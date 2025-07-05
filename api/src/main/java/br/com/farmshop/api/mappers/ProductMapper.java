package br.com.farmshop.api.mappers;

import br.com.farmshop.api.dtos.ProductCreateDTO;
import br.com.farmshop.api.dtos.ProductResponseDTO;
import br.com.farmshop.api.entities.Product;

public class ProductMapper {
	
	public static ProductResponseDTO toDTO(Product product){
		
		return new ProductResponseDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getRating(), product.getStock(), product.getCreated_at(), product.getUpdated_at(), CategoryMapper.toDTO(product.getCategory()), BrandMapper.toDTO(product.getBrand()));
		
	}
	
	public static Product toEntity(ProductCreateDTO productDTO){
		
		Product product = new Product();
		product.setName(productDTO.name());
		product.setDescription(productDTO.description());
		product.setPrice(productDTO.price());
		product.setStock(productDTO.stock());
		
		return product;
		
	}
}
