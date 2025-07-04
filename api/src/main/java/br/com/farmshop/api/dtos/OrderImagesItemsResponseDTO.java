package br.com.farmshop.api.dtos;

import java.util.List;

public record OrderImagesItemsResponseDTO(OrderResponseDTO orderResponseDTO, List<OrderItemImageResponseDTO> orderItemImageResponseDTO) {

}
