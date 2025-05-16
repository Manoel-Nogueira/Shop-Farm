package br.com.farmshop.api.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.farmshop.api.dtos.BrandCreateDTO;
import br.com.farmshop.api.dtos.BrandResponseOrUpdateDTO;
import br.com.farmshop.api.entities.Brand;
import br.com.farmshop.api.mappers.BrandMapper;
import br.com.farmshop.api.repositories.BrandRepository;

@Service
public class BrandService {

	@Autowired
	private BrandRepository brandRepository;

	public BrandResponseOrUpdateDTO storeBrand(BrandCreateDTO brandCreateDTO) {
		
		BrandResponseOrUpdateDTO brandResponseOrUpdateDTO = BrandMapper.toDTO(brandRepository.save(BrandMapper.toEntity(brandCreateDTO)));
		
		return brandResponseOrUpdateDTO;

	}
	
	public BrandResponseOrUpdateDTO updateBrand(BrandResponseOrUpdateDTO brandResponseAndUpdateDTO){
		
		Brand brand = brandRepository.findById(brandResponseAndUpdateDTO.id()).orElseThrow(() -> new RuntimeException("Error in BrandService update"));
		brand.setName(brandResponseAndUpdateDTO.name());
		
		return BrandMapper.toDTO(brandRepository.save(brand));
		
	}
	
	public List<BrandResponseOrUpdateDTO> listAllBrand(){
		
		return brandRepository.findAll().stream().map(BrandMapper::toDTO).toList();
		
	}
	
	public BrandResponseOrUpdateDTO listBrandById(Long id){
		
		Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in BrandService listById"));
		
		return BrandMapper.toDTO(brand);
		
	}
	
	public Boolean destroyBrand(Long id) {
		
		brandRepository.delete(brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in BrandService delete")));
		
		return true;
			
	}
	
}

// nome de class CM
// java camelcase
