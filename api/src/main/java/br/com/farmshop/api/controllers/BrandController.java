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
import br.com.farmshop.api.dtos.BrandCreateDTO;
import br.com.farmshop.api.dtos.BrandResponseOrUpdateDTO;
import br.com.farmshop.api.services.BrandService;

@RestController 
@RequestMapping("/api/brands")
public class BrandController {
	
	@Autowired
	BrandService brandService; 
	
	//para nomear o caminho do PostMapping(nome)
	@PostMapping
	public ResponseEntity<BrandResponseOrUpdateDTO> createBrand(@RequestBody BrandCreateDTO categoryCreateDTO) {
		
		BrandResponseOrUpdateDTO brandResponseOrUpdateDTO = brandService.storeBrand(categoryCreateDTO);
		return new ResponseEntity<>(brandResponseOrUpdateDTO, HttpStatus.CREATED);
		
	}
	
	@PutMapping
	public ResponseEntity<BrandResponseOrUpdateDTO> updateBrand(@RequestBody BrandResponseOrUpdateDTO categoryResponseAndUpdateDTO) {
		
		BrandResponseOrUpdateDTO categoryDTO = brandService.updateBrand(categoryResponseAndUpdateDTO);
		return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
		
	}
	
	//para nomear o caminho do GetMapping(nome)
	@GetMapping
	public List<BrandResponseOrUpdateDTO> listAllBrand(){
		
		return brandService.listAllBrand();
		
	}
	
	@GetMapping("/{brand_id}")
	public ResponseEntity<BrandResponseOrUpdateDTO> listBrandById(@PathVariable("category_id") Long id){
		
		BrandResponseOrUpdateDTO categoryResponseAndUpdateDTO = brandService.listBrandById(id);
		return new ResponseEntity<>(categoryResponseAndUpdateDTO, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{brand_id}")
	public Boolean destroyBrand(@PathVariable("category_id") Long id) {
		
		return brandService.destroyBrand(id);
		
	}
	
}


// estudar java isidro