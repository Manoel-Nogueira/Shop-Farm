package br.com.farmshop.api.mappers;

import br.com.farmshop.api.dtos.OrderItemCreateDTO;
import br.com.farmshop.api.dtos.OrderItemResponseDTO;
import br.com.farmshop.api.entities.OrderItem;

public class OrderItemMapper {
	
	public static OrderItemResponseDTO toDTO(OrderItem orderItem) {
		
		return new OrderItemResponseDTO(orderItem.getId(), OrderMapper.toDTO(orderItem.getOrder()), ProductMapper.toDTO(orderItem.getProduct()), orderItem.getQuantity(), orderItem.getSubtotal());
		
	}
	
	public static OrderItem toEntity(OrderItemCreateDTO orderItemCreateDTO) {
		
		OrderItem orderItem = new OrderItem();
		
		orderItem.setQuantity(orderItemCreateDTO.quantity());
		orderItem.setSubtotal(orderItemCreateDTO.subtotal());
		
		return orderItem;
		
	}

}
