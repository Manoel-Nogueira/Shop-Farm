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

import br.com.farmshop.api.dtos.OrderCreateDTO;
import br.com.farmshop.api.dtos.OrderImagesItemsResponseDTO;
import br.com.farmshop.api.dtos.OrderItemImageResponseDTO;
import br.com.farmshop.api.dtos.OrderItemResponseDTO;
import br.com.farmshop.api.dtos.OrderResponseDTO;
import br.com.farmshop.api.dtos.OrderStatusUpdateDTO;
import br.com.farmshop.api.dtos.OrderTrackingCodeUpdateDTO;
import br.com.farmshop.api.services.ImageService;
import br.com.farmshop.api.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ImageService imageService;
	
	@PostMapping
	public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderCreateDTO orderCreateDTO){
		
		OrderResponseDTO orderResponseDTO = orderService.storeOrder(orderCreateDTO);
		
		return new ResponseEntity<>(orderResponseDTO, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/status")
	public ResponseEntity<OrderResponseDTO> updateOrderStatus(@RequestBody OrderStatusUpdateDTO orderStatusUpdateDTO){
		
		OrderResponseDTO orderResponseDTO = orderService.updateOrderStatus(orderStatusUpdateDTO);
		
		return new ResponseEntity<>(orderResponseDTO, HttpStatus.OK);
		
	}
	
	@PutMapping("/tracking_code")
	public ResponseEntity<OrderResponseDTO> updateOrderTrackingCode(@RequestBody OrderTrackingCodeUpdateDTO orderTrackingCodeUpdateDTO){
		
		OrderResponseDTO orderResponseDTO = orderService.updateOrderTrackingCode(orderTrackingCodeUpdateDTO);
		
		return new ResponseEntity<>(orderResponseDTO, HttpStatus.OK);
		
	}
	
	@GetMapping("/list_all/{user_id}")
	public List<OrderImagesItemsResponseDTO> listAllOrder(@PathVariable("user_id") Long id){
		
		try {
		
			List<OrderResponseDTO> orders = orderService.listAllOrder(id);
			List<OrderImagesItemsResponseDTO> orderImagesItems = new ArrayList<>();
			
			for(OrderResponseDTO orderResponseDTO : orders) {
				
				List<OrderItemResponseDTO> ordersItems = orderService.listAllOrderItem(orderResponseDTO.id());
				List<OrderItemImageResponseDTO> orderItemImage = new ArrayList<>();
				
				for(OrderItemResponseDTO orderItemResponseDTO : ordersItems) {
					
					OrderItemImageResponseDTO orderItemImageResponseDTO = new OrderItemImageResponseDTO(orderItemResponseDTO, imageService.listAllImagesProduct(orderItemResponseDTO.product().id()));
					
					orderItemImage.add(orderItemImageResponseDTO);
					
				}
				
				OrderImagesItemsResponseDTO orderImagesItemsResponseDTO = new OrderImagesItemsResponseDTO(orderResponseDTO, orderItemImage);
				orderImagesItems.add(orderImagesItemsResponseDTO);
				
			}
			
			return orderImagesItems;
			
		}catch (Exception e) {
			
			return null;
			
		}
		
	}
	
	@GetMapping("/list_all/items/{order_id}")
	public List<OrderItemImageResponseDTO> listAllOrderItem(@PathVariable("order_id") Long id) {
		
		try {
			
			List<OrderItemResponseDTO> orderItem = orderService.listAllOrderItem(id);
			List<OrderItemImageResponseDTO> orderItemImage = new ArrayList<>();
			
			for(OrderItemResponseDTO orderItemResponseDTO : orderItem) {
				
				OrderItemImageResponseDTO orderItemImageResponseDTO = new OrderItemImageResponseDTO(orderItemResponseDTO, imageService.listAllImagesProduct(orderItemResponseDTO.product().id()));
				
				orderItemImage.add(orderItemImageResponseDTO);
				
			}
			
			return orderItemImage;
			
		}catch (Exception e) {
			
			return null;
			
		}
		
	}
	
	@GetMapping("/{order_id}")
	public ResponseEntity<OrderResponseDTO> listOrderById(@PathVariable("order_id") Long id){
		
		OrderResponseDTO orderResponseDTO = orderService.showOrderById(id);
		
		return new ResponseEntity<>(orderResponseDTO, HttpStatus.OK);
		
	}
	
	@GetMapping("/items/{orderItem_id}")
	public ResponseEntity<OrderItemImageResponseDTO> listOrderItemById(@PathVariable("orderItem_id") Long id) {
		
		OrderItemResponseDTO orderItemResponseDTO = orderService.showOrderItemById(id);
		OrderItemImageResponseDTO orderItemImageResponseDTO = new OrderItemImageResponseDTO(orderItemResponseDTO, imageService.listAllImagesProduct(orderItemResponseDTO.product().id()));
		
		return new ResponseEntity<>(orderItemImageResponseDTO, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{order_id}")
	public Boolean destroyOrder(@PathVariable("order_id") Long id){
		
		return orderService.destroyOrder(id);
		
	}

}
