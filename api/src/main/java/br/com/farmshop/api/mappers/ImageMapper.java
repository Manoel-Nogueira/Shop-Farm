package br.com.farmshop.api.mappers;

import br.com.farmshop.api.dtos.ImageCreateDTO;
import br.com.farmshop.api.dtos.ImageResponseDTO;
import br.com.farmshop.api.entities.Image;

public class ImageMapper {

	public static ImageResponseDTO toDTO(Image image) {
		
		return new ImageResponseDTO(image.getId(), image.getUrl(), ProductMapper.toDTO(image.getProduct()));
		
	}
	
	public static Image toEntity(ImageCreateDTO imageCreateDTO) {
		
		Image image = new Image();
		image.setUrl(imageCreateDTO.url());

		// Fazer o set product no service;
		
		return image;
		
	}
	
}
