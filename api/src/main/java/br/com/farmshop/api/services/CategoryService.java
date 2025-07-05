package br.com.farmshop.api.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.farmshop.api.dtos.CategoryCreateDTO;
import br.com.farmshop.api.dtos.CategoryResponseOrUpdateDTO;
import br.com.farmshop.api.entities.Category;
import br.com.farmshop.api.mappers.CategoryMapper;
import br.com.farmshop.api.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryResponseOrUpdateDTO storeCategory(CategoryCreateDTO categoryCreateDTO) {
		
		CategoryResponseOrUpdateDTO categoryResponseOrUpdateDTO = CategoryMapper.toDTO(categoryRepository.save(CategoryMapper.toEntity(categoryCreateDTO)));
		
		return categoryResponseOrUpdateDTO;

	}
	
	public CategoryResponseOrUpdateDTO updateCategory(CategoryResponseOrUpdateDTO categoryResponseAndUpdateDTO){
		
		Category category = categoryRepository.findById(categoryResponseAndUpdateDTO.id()).orElseThrow(() -> new RuntimeException("Error in CategoryService update"));
		category.setName(categoryResponseAndUpdateDTO.name());
		
		return CategoryMapper.toDTO(categoryRepository.save(category));
		
	}
	
	public List<CategoryResponseOrUpdateDTO> listAllCategory(){
		
		return categoryRepository.findAll().stream().map(CategoryMapper::toDTO).toList();
		
	}
	
	public CategoryResponseOrUpdateDTO listCategoryById(Long id){
		
		Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in CategoryService listById"));
		
		return CategoryMapper.toDTO(category);
		
	}
	
	public Boolean destroyCategory(Long id) {
		
		categoryRepository.delete(categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in CategoryService destroy")));
		
		return true;
			
	}
	
}

// nome de class CM
// java camelcase
