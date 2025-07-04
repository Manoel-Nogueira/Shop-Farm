package br.com.farmshop.api.dtos;

public record ProductCreateDTO(String name, String description, Float price, Long stock, Long category_id, Long brand_id) {

}
