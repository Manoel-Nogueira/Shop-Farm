package br.com.farmshop.api.dtos;

import java.time.Instant;

public record ReviewResponseDTO(Long id, Long rating, String comment, Instant create_at, Instant update_at, UserResponseDTO user_id, ProductResponseDTO product_id) {

}
