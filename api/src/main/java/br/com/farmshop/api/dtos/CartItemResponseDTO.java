package br.com.farmshop.api.dtos;

public record CartItemResponseDTO(Long id, Long quantity, Float subtotal, ProductResponseDTO product, CartResponseDTO cart) {

}
