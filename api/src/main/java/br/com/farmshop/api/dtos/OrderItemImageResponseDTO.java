package br.com.farmshop.api.dtos;

import java.util.List;

public record OrderItemImageResponseDTO(OrderItemResponseDTO orderItemResponseDTO , List<ImageResponseDTO> images) {

}
