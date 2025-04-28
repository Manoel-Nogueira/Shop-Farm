package br.com.farmshop.api.dtos;

import java.time.Instant;

public record ProductResponseDTO(Long id, String name, String description, Float price, Float rating, Long stock, Instant create_at, Instant update_at, UserResponseDTO user, CategoryResponseOrUpdateDTO category, BrandResponseOrUpdateDTO brand_id) {

}
