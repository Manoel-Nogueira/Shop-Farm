package br.com.farmshop.api.mappers;

import br.com.farmshop.api.dtos.BrandCreateDTO;
import br.com.farmshop.api.dtos.BrandResponseOrUpdateDTO;
import br.com.farmshop.api.entities.Brand;

public class BrandMapper {

	public static BrandResponseOrUpdateDTO toDTO(Brand brand) {
		
		return new BrandResponseOrUpdateDTO(brand.getId(), brand.getName());
		
	}
	
	public static Brand toEntity(BrandCreateDTO brandCreateDTO) {
		
		Brand category = new Brand();
		category.setName(brandCreateDTO.name());

		return category;
		
	}
	
}
