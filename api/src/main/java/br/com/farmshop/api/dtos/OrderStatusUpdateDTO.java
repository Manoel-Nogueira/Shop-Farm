package br.com.farmshop.api.dtos;

import br.com.farmshop.api.enums.OrderStatus;

public record OrderStatusUpdateDTO(Long id, OrderStatus status) {
 
}
