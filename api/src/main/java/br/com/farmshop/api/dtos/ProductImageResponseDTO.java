package br.com.farmshop.api.dtos;

import java.util.List;

public record ProductImageResponseDTO(ProductResponseDTO productResponseDTO, List<ImageResponseDTO> images) {

}
