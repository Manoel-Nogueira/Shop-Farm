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
import br.com.farmshop.api.dtos.CategoryCreateDTO;
import br.com.farmshop.api.dtos.CategoryResponseOrUpdateDTO;
import br.com.farmshop.api.services.CategoryService;

@RestController 
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService; 
	
	//para nomear o caminho do PostMapping(nome)
	@PostMapping
	public ResponseEntity<CategoryResponseOrUpdateDTO> createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
		
		CategoryResponseOrUpdateDTO categoryResponseAndUpdateDTO = categoryService.storeCategory(categoryCreateDTO);
		
		return new ResponseEntity<>(categoryResponseAndUpdateDTO, HttpStatus.CREATED);
		
	}
	
	
	@PutMapping
	public ResponseEntity<CategoryResponseOrUpdateDTO> updateCategory(@RequestBody CategoryResponseOrUpdateDTO categoryResponseAndUpdateDTO) {
		
		CategoryResponseOrUpdateDTO categoryDTO = categoryService.updateCategory(categoryResponseAndUpdateDTO);
		return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
		
	}
	
	//para nomear o caminho do GetMapping(nome)
	@GetMapping("/list_all")
	public List<CategoryResponseOrUpdateDTO> listAllCategory(){
		
		return categoryService.listAllCategory();
		
	}
	
	@GetMapping("/{category_id}")
	public ResponseEntity<CategoryResponseOrUpdateDTO> listCategoryById(@PathVariable("category_id") Long id){
		
		CategoryResponseOrUpdateDTO categoryResponseAndUpdateDTO = categoryService.listCategoryById(id);
		return new ResponseEntity<>(categoryResponseAndUpdateDTO, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{category_id}")
	public Boolean destroyCategory(@PathVariable("category_id") Long id) {
		
		return categoryService.destroyCategory(id);
		
	}
	
}


// estudar java isidro