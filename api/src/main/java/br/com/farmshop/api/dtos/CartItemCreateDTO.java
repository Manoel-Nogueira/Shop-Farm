package br.com.farmshop.api.dtos;

public record CartItemCreateDTO(Long quantity, Long product_id, Long user_id) {

}
