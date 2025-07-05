package br.com.farmshop.api.controllers;

import java.util.ArrayList;
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
import br.com.farmshop.api.dtos.CartItemImageResponseDTO;
import br.com.farmshop.api.dtos.CartItemResponseDTO;
import br.com.farmshop.api.dtos.CartItemUpdateDTO;
import br.com.farmshop.api.services.CartService;
import br.com.farmshop.api.services.ImageService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	ImageService imageService;
	
	@PostMapping
	public ResponseEntity<CartItemResponseDTO>  createCartItem(@RequestBody CartItemCreateDTO cartItemCreateDTO) {
		
		CartItemResponseDTO cartItemResponseDTO = cartService.storeCartItem(cartItemCreateDTO);
		
		return new ResponseEntity<>(cartItemResponseDTO, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<CartItemResponseDTO> updateCartItem(@RequestBody CartItemUpdateDTO cartItemUpdateDTO) {
		
		CartItemResponseDTO cartItemResponseDTO = cartService.updateCartItem(cartItemUpdateDTO);
		
		return new ResponseEntity<>(cartItemResponseDTO, HttpStatus.OK);
		
	}
	
	@GetMapping("/list_all/{user_id}")
	public List<CartItemImageResponseDTO> listAllCartItem(@PathVariable("user_id") Long id) {
		
		try {
			
			List<CartItemResponseDTO> cartItem = cartService.listAllCartItem(id);
			List<CartItemImageResponseDTO> cartItemImage = new ArrayList<>();
			
			for(CartItemResponseDTO cartItemResponseDTO : cartItem) {
				
				CartItemImageResponseDTO cartItemImageResponseDTO = new CartItemImageResponseDTO(cartItemResponseDTO, imageService.listAllImagesProduct(cartItemResponseDTO.product().id()));
				
				cartItemImage.add(cartItemImageResponseDTO);
				
			}
			
			return cartItemImage;
			
		}catch (Exception e) {
			
			return null;
			
		}
		
	}
	
	@GetMapping("/show/{cartItem_id}")
	public ResponseEntity<CartItemImageResponseDTO> listCartItemById(@PathVariable("cartItem_id") Long id) {
		
		CartItemResponseDTO cartItemResponseDTO = cartService.showCartItemById(id);
		CartItemImageResponseDTO cartItemImageResponseDTO = new CartItemImageResponseDTO(cartItemResponseDTO, imageService.listAllImagesProduct(cartItemResponseDTO.product().id()));
		
		return new ResponseEntity<>(cartItemImageResponseDTO, HttpStatus.OK);
		
	}
	
	@DeleteMapping("delete/all/{cart_id}")
	public void clearCart(@PathVariable("cart_id") Long id) {
		
		cartService.clearCart(id);
		
	} 
	
	@DeleteMapping("delete/{cartItem_id}")
	public Boolean destroyCartItem(@PathVariable("cartItem_id") Long id) {
		
		return cartService.destroyCartItem(id);
		
	}
	
	@GetMapping("/total_price/{cart_id}")
	public Float getTotalPrice(@PathVariable("cart_id") Long id) {
		
		return cartService.getTotalPrice(id);
		
	}
	
	@GetMapping("/quantity/{user_id}")
	public Long getQuantityItems(@PathVariable("user_id") Long id) {
		
		try {
			
			return cartService.getQuantityItems(id);
			
		} catch (Exception e) {
			
			return (long) 0;
			
		}
		
	}

}
