package br.com.farmshop.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.farmshop.api.dtos.CartItemResponseDTO;
import br.com.farmshop.api.dtos.OrderCreateDTO;
import br.com.farmshop.api.dtos.OrderItemResponseDTO;
import br.com.farmshop.api.dtos.OrderResponseDTO;
import br.com.farmshop.api.dtos.OrderStatusUpdateDTO;
import br.com.farmshop.api.dtos.OrderTrackingCodeUpdateDTO;
import br.com.farmshop.api.dtos.ProductUpdateDTO;
import br.com.farmshop.api.entities.Cart;
import br.com.farmshop.api.entities.CartItem;
import br.com.farmshop.api.entities.Order;
import br.com.farmshop.api.entities.OrderItem;
import br.com.farmshop.api.entities.Payment;
import br.com.farmshop.api.entities.Product;
import br.com.farmshop.api.entities.User;
import br.com.farmshop.api.mappers.CartItemMapper;
import br.com.farmshop.api.mappers.OrderItemMapper;
import br.com.farmshop.api.mappers.OrderMapper;
import br.com.farmshop.api.repositories.CartItemRepository;
import br.com.farmshop.api.repositories.CartRepository;
import br.com.farmshop.api.repositories.OrderItemRepository;
import br.com.farmshop.api.repositories.OrderRepository;
import br.com.farmshop.api.repositories.PaymentRepository;
import br.com.farmshop.api.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductService productService;
	
	public OrderResponseDTO storeOrder(OrderCreateDTO orderCreateDTO) {
		
		Order order = OrderMapper.toEntity(orderCreateDTO);
		User user = userRepository.findById(orderCreateDTO.user_id()).orElseThrow(() -> new RuntimeException("Error in OrderService store"));
		Payment payment = paymentRepository.findById(orderCreateDTO.payment_id()).orElseThrow(() -> new RuntimeException("Error in OrderService store"));
		
		Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Error in OrderService store"));
		
		order.setTrackingCode("Tem que terminar isso, não sei como :)");
		order.setTotalPrice(cartRepository.getTotalPrice(cart.getId()));
		order.setUser(user);
		order.setPayment(payment);
		
		orderRepository.save(order);
		
		// Armazenando os items de cartItem em orderItem
		List<CartItemResponseDTO> cartItems = cartItemRepository.findByCartId(cart.getId()).stream().map(CartItemMapper::toDTO).toList();
		
		for(CartItemResponseDTO items : cartItems) {
			
			CartItem cartItem = cartItemRepository.findById(items.id()).orElseThrow(() -> new RuntimeException("Error in OrderService store"));
			Product product = cartItem.getProduct();
			OrderItem orderItem = new OrderItem();
			
			orderItem.setOrder(order);
			orderItem.setProduct(product);
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setSubtotal(cartItem.getSubtotal());
			
			orderItemRepository.save(orderItem);
			
			// Atualizando o stock do product
			ProductUpdateDTO productUpdateDTO = new ProductUpdateDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock() - cartItem.getQuantity(), product.getCategory().getId(), product.getBrand().getId());
			productService.updateProduct(productUpdateDTO);
			
		}
		
		// Limpando o cart
		cartItemRepository.deleteByCartId(cart.getId());
		
		return OrderMapper.toDTO(order);
		
	}
	
	public OrderResponseDTO updateOrderStatus(OrderStatusUpdateDTO orderStatusUpdateDTO) {
		
		Order order = orderRepository.findById(orderStatusUpdateDTO.id()).orElseThrow(() -> new RuntimeException("Error in OrderService update"));
		
		order.setStatus(orderStatusUpdateDTO.status());
		
		return OrderMapper.toDTO(order);
		
	}
	
	public OrderResponseDTO updateOrderTrackingCode(OrderTrackingCodeUpdateDTO orderTrackingCodeUpdateDTO) {
		
		Order order = orderRepository.findById(orderTrackingCodeUpdateDTO.id()).orElseThrow(() -> new RuntimeException("Error in OrderService update"));
		
		order.setTrackingCode(orderTrackingCodeUpdateDTO.trackingCode());
		
		return OrderMapper.toDTO(order);
	}
	
	public List<OrderResponseDTO> listAllOrder(Long id) {
		
		return orderRepository.findByUserId(id).stream().map(OrderMapper::toDTO).toList();
		
	}
	
	public List<OrderItemResponseDTO> listAllOrderItem(Long id) {
		
		return orderItemRepository.findByOrderId(id).stream().map(OrderItemMapper::toDTO).toList();
		
	}
	
	public OrderResponseDTO showOrderById(Long id) {
		
		Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in OrderService showById"));
		
		return OrderMapper.toDTO(order);
		
	}
	
	public OrderItemResponseDTO showOrderItemById(Long id) {
		
		OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in OrderService showById"));
		
		return OrderItemMapper.toDTO(orderItem);
		
	}
	
	@Transactional
	public Boolean destroyOrder(Long id) {
		
		orderRepository.delete(orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in OrderService delete")));
		
		return true;

	}

}
