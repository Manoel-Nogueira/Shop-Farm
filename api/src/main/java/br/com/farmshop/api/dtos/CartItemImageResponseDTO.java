package br.com.farmshop.api.dtos;

import java.util.List;

public record CartItemImageResponseDTO(CartItemResponseDTO cartItemResponseDTO , List<ImageResponseDTO> images) {

}
