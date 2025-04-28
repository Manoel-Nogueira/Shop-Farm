package br.com.farmshop.api.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.farmshop.api.dtos.ImageCreateDTO;
import br.com.farmshop.api.dtos.ImageResponseDTO;
import br.com.farmshop.api.dtos.ImageUpdateDTO;
import br.com.farmshop.api.entities.Image;
import br.com.farmshop.api.entities.Product;
import br.com.farmshop.api.mappers.ImageMapper;
import br.com.farmshop.api.repositories.ImageRepository;
import br.com.farmshop.api.repositories.ProductRepository;

@Service
public class ImageService {
	
	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private ProductRepository productRepository;
	

	public ImageResponseDTO storeImage(ImageCreateDTO imageCreateDTO) {
		
		Image image = ImageMapper.toEntity(imageCreateDTO);
		Product product = productRepository.findById(imageCreateDTO.product_id()).orElseThrow(() -> new RuntimeException("Error in ProductService store"));
		
		image.setProduct(product);

		return ImageMapper.toDTO(imageRepository.save(image));

	}
	
	public ImageResponseDTO updateImage(ImageUpdateDTO imageUpdateDTO){
		
		Image image = imageRepository.findById(imageUpdateDTO.id()).orElseThrow(() -> new RuntimeException("Error in ProductService update"));
		Product product = productRepository.findById(imageUpdateDTO.product_id()).orElseThrow(() -> new RuntimeException("Error in ProductService update"));
		
		image.setUrl(imageUpdateDTO.url());
		image.setProduct(product);
		
		return ImageMapper.toDTO(imageRepository.save(image));
		
	}
	
	public List<ImageResponseDTO> listAllImage(Long id){
		
		return imageRepository.findByProductId(id).stream().map(ImageMapper::toDTO).toList();
		
	}
	
	public ImageResponseDTO listImageById(Long id){
		
		Image image = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in ProductService list by id"));
		
		return ImageMapper.toDTO(image);
		
	}
	
	public Boolean destroyImage(Long id) {
		
		imageRepository.delete(imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in ProductService delete")));
		
		return true;
			
	}
	
}

// nome de class CM
// java camelcase
