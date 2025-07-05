package br.com.farmshop.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record OrderItemResponseDTO(Long id, @JsonIgnore OrderResponseDTO order, ProductResponseDTO product, Long quantity, Float subtotal) {

}
