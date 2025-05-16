package br.com.farmshop.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.farmshop.api.dtos.CartItemCreateDTO;
import br.com.farmshop.api.dtos.CartItemResponseDTO;
import br.com.farmshop.api.dtos.CartItemUpdateDTO;
import br.com.farmshop.api.services.CartService;

@RestController
@RequestMapping("/api/cartitems")
public class CartItemController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping
	public ResponseEntity<CartItemResponseDTO>  createCartItem(@RequestBody CartItemCreateDTO cartItemCreateDTO) {
		
		CartItemResponseDTO cartItemResponseDTO = cartService.storeCartItem(cartItemCreateDTO);
		
		return new ResponseEntity<>(cartItemResponseDTO, HttpStatus.CREATED);
		
	}
	
	@PutMapping
	public ResponseEntity<CartItemResponseDTO> updateCartItem(@RequestBody CartItemUpdateDTO cartItemUpdateDTO) {
		
		CartItemResponseDTO cartItemResponseDTO = cartService.updateCartItem(cartItemUpdateDTO);
		
		return new ResponseEntity<>(cartItemResponseDTO, HttpStatus.OK);
		
	}
	
	@GetMapping("/all/{cart_id}")
	public List<CartItemResponseDTO> listAllCartItem(@PathVariable("cart_id") Long id) {
		
		return cartService.listAllCartItem(id);
		
	}
	
	@GetMapping("/{cartItem_id}")
	public ResponseEntity<CartItemResponseDTO> listCartItemById(@PathVariable("cartItem_id") Long id) {
		
		CartItemResponseDTO cartItemResponseDTO = cartService.showCartItemById(id);
		
		return new ResponseEntity<>(cartItemResponseDTO, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/all/{cart_id}")
	public void clearCart(@PathVariable("cart_id") Long id) {
		
		cartService.clearCart(id);
		
	} 
	
	@DeleteMapping("/{cartItem_id}")
	public Boolean destroyCartItem(@PathVariable("cartItem_id") Long id) {
		
		return cartService.destroyCartItem(id);
		
	}

}
