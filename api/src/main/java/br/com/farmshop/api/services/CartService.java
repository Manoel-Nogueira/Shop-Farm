package br.com.farmshop.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.farmshop.api.dtos.CartItemCreateDTO;
import br.com.farmshop.api.dtos.CartItemResponseDTO;
import br.com.farmshop.api.dtos.CartItemUpdateDTO;
import br.com.farmshop.api.entities.Cart;
import br.com.farmshop.api.entities.CartItem;
import br.com.farmshop.api.entities.Product;
import br.com.farmshop.api.entities.User;
import br.com.farmshop.api.mappers.CartItemMapper;
import br.com.farmshop.api.repositories.CartItemRepository;
import br.com.farmshop.api.repositories.CartRepository;
import br.com.farmshop.api.repositories.ProductRepository;
import br.com.farmshop.api.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	private Cart createCart(User user) {
	
		Cart cart = new Cart();
		cart.setUser(user);
		
		return cartRepository.save(cart);
		
	} 
	
	public void clearCart(Long id) {
		
		cartItemRepository.deleteByCartId(id);
		
	}
	
	public CartItemResponseDTO storeCartItem(CartItemCreateDTO cartItemCreateDTO) {
		
		User user = userRepository.findById(cartItemCreateDTO.user_id()).orElseThrow(() -> new RuntimeException("Error in CartService create"));
		Product product = productRepository.findById(cartItemCreateDTO.product_id()).orElseThrow(() -> new RuntimeException("Error in CartService create"));
		
		Cart cart = cartRepository.findByUser(user).orElseGet(() -> createCart(user));
		CartItem cartItem = cartItemRepository.findByProduct(product); 
		
		// Verificando se a quantidade do product é maior que 0 e se tem stock do product 
		if(cartItemCreateDTO.quantity() > 0 && cartItemCreateDTO.quantity() <= product.getStock()) {
		
			if(cartItem == null) {
				
				// Caso o item não exista no carrinho
				cartItem = CartItemMapper.toEntity(cartItemCreateDTO);
				
				cartItem.setSubtotal(product.getPrice() * (float) cartItem.getQuantity());
				cartItem.setProduct(product);
				cartItem.setCart(cart);
				
				return CartItemMapper.toDTO(cartItemRepository.save(cartItem));
			
			}else {
				
				// Caso o item já exista no carrinho, como ele já existe eu só somar a quantidade com a existente do item
				CartItemUpdateDTO cartItemUpdateDTO = new CartItemUpdateDTO(cartItem.getId(), cartItem.getQuantity() + cartItemCreateDTO.quantity());
				
				return updateCartItem(cartItemUpdateDTO);
				
			}
			
		}else {
			
			return null;
			
		}
		
	}
	
	public CartItemResponseDTO updateCartItem(CartItemUpdateDTO cartItemUpdateDTO) {
		
		CartItem cartItem = cartItemRepository.findById(cartItemUpdateDTO.id()).orElseThrow(() -> new RuntimeException("Error in CartService update"));

		if(cartItemUpdateDTO.quantity() <= 0) {
			
			destroyCartItem(cartItemUpdateDTO.id());
			
			return null;
			
		}else {
			
			cartItem.setQuantity(cartItemUpdateDTO.quantity());
			cartItem.setSubtotal(cartItem.getProduct().getPrice() * (float) cartItem.getQuantity());
			
			return CartItemMapper.toDTO(cartItemRepository.save(cartItem));
			
		}
		
	}
	
	public List<CartItemResponseDTO> listAllCartItem(Long id) {
		
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in CartService listAllCartItem"));
		Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Error in CartService listAllCartItem")); 
		
		return cartItemRepository.findByCartId(cart.getId()).stream().map(CartItemMapper::toDTO).toList();
		
	}
	
	public CartItemResponseDTO showCartItemById(Long id) {
		
		CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in CartService showCartItemById"));
		
		return CartItemMapper.toDTO(cartItem);
		
	}

	public Boolean destroyCartItem(Long id) {
		
		CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in CartService destroyCartItem"));
		cartItemRepository.delete(cartItem);
		
		return true;
		
	}
	
	public Float getTotalPrice(Long id) {
		
		return cartRepository.getTotalPrice(id);

	}
	
	public Long getQuantityItems(Long id) {
		
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in CartService getQuantityItems"));
		Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Error in CartService getQuantityItems"));
		
		List<CartItemResponseDTO> cartItemResponseDTO = cartItemRepository.findByCartId(cart.getId()).stream().map(CartItemMapper::toDTO).toList();
		
		return (long) cartItemResponseDTO.size();
		
	}
	
}
