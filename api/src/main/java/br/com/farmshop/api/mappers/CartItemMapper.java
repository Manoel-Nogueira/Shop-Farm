package br.com.farmshop.api.mappers;

import br.com.farmshop.api.dtos.CartItemCreateDTO;
import br.com.farmshop.api.dtos.CartItemResponseDTO;
import br.com.farmshop.api.entities.CartItem;

public class CartItemMapper {
	
	public static CartItemResponseDTO toDTO(CartItem cartItem) {
		
		return new CartItemResponseDTO(cartItem.getId(), cartItem.getQuantity(), cartItem.getSubtotal(), ProductMapper.toDTO(cartItem.getProduct()), CartMapper.toDTO(cartItem.getCart()));
		
	}
	
	public static CartItem toEntity(CartItemCreateDTO cartItemCreateDTO) {
		
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(cartItemCreateDTO.quantity());
		
		return cartItem;
		
	}

}
