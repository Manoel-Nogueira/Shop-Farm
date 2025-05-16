package br.com.farmshop.api.dtos;

public record OrderItemCreateDTO(Long quantity, Float subtotal, Long order_id, Long product_id) {

}
