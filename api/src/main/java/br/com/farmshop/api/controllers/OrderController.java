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

import br.com.farmshop.api.dtos.OrderCreateDTO;
import br.com.farmshop.api.dtos.OrderItemResponseDTO;
import br.com.farmshop.api.dtos.OrderResponseDTO;
import br.com.farmshop.api.dtos.OrderStatusUpdateDTO;
import br.com.farmshop.api.dtos.OrderTrackingCodeUpdateDTO;
import br.com.farmshop.api.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
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
	
	@GetMapping("/all/{user_id}")
	public List<OrderResponseDTO> listAllOrder(@PathVariable("user_id") Long id){
		
		return orderService.listAllOrder(id);
		
	}
	
	@GetMapping("/all/items/{order_id}")
	public List<OrderItemResponseDTO> listAllOrderItem(@PathVariable("order_id") Long id) {
		
		return orderService.listAllOrderItem(id);
		
	}
	
	@GetMapping("/{order_id}")
	public ResponseEntity<OrderResponseDTO> listOrderById(@PathVariable("order_id") Long id){
		
		OrderResponseDTO orderResponseDTO = orderService.showOrderById(id);
		
		return new ResponseEntity<>(orderResponseDTO, HttpStatus.OK);
		
	}
	
	@GetMapping("/items/{orderItem_id}")
	public ResponseEntity<OrderItemResponseDTO> listOrderItemById(@PathVariable("orderItem_id") Long id) {
		
		OrderItemResponseDTO orderItemResponseDTO = orderService.showOrderItemById(id);
		
		return new ResponseEntity<>(orderItemResponseDTO, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{order_id}")
	public Boolean destroyOrder(@PathVariable("order_id") Long id){
		
		return orderService.destroyOrder(id);
		
	}

}
