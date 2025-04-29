package br.com.farmshop.api.dtos;

public record ReviewUpdateDTO(Long id, Long rating, String comment, Long user_id, Long product_id) {

}
