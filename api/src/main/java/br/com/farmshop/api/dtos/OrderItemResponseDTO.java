package br.com.farmshop.api.dtos;

public record OrderItemResponseDTO(Long id, OrderResponseDTO order, ProductResponseDTO product, Long quantity, Float subtotal) {

}
