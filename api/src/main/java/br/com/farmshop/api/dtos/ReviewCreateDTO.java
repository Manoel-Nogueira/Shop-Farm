package br.com.farmshop.api.dtos;

public record ReviewCreateDTO(Long rating, String comment, Long user_id, Long product_id) {

}
