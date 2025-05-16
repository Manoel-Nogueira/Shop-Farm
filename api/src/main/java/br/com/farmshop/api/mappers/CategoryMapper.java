package br.com.farmshop.api.mappers;

import br.com.farmshop.api.dtos.CategoryCreateDTO;
import br.com.farmshop.api.dtos.CategoryResponseOrUpdateDTO;
import br.com.farmshop.api.entities.Category;

public class CategoryMapper {

	public static CategoryResponseOrUpdateDTO toDTO(Category category) {
		
		return new CategoryResponseOrUpdateDTO(category.getId(), category.getName());
		
	}
	
	public static Category toEntity(CategoryCreateDTO categoryCreateDTO) {
		
		Category category = new Category();
		category.setName(categoryCreateDTO.name());

		return category;
		
	}
	
}
