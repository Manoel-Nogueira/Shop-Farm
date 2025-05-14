package br.com.farmshop.api.mappers;

import br.com.farmshop.api.dtos.OrderCreateDTO;
import br.com.farmshop.api.dtos.OrderResponseDTO;
import br.com.farmshop.api.entities.Order;

public class OrderMapper {
	
	public static OrderResponseDTO toDTO(Order order) {
		
		return new OrderResponseDTO(order.getId(), order.getTotalPrice(), order.getStatus(), order.getTrackingCode(), order.getCreated_at(), order.getUpdated_at(), UserMapper.toDTO(order.getUser()), PaymentMapper.toDTO(order.getPayment()));
		
	}
	
	public static Order toEntity(OrderCreateDTO orderCreateDTO) {
		
		Order order = new Order();
		
		return order;
		
	}

}
