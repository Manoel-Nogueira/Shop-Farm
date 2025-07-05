package br.com.farmshop.api.mappers;

import br.com.farmshop.api.dtos.CartResponseDTO;
import br.com.farmshop.api.entities.Cart;

public class CartMapper {
	
	public static CartResponseDTO toDTO(Cart cart) {
		
		return new CartResponseDTO(cart.getId(), UserMapper.toDTO(cart.getUser()));
		
	}
	
}
