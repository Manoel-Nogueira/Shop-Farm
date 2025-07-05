package br.com.farmshop.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record ImageResponseDTO(Long id, String url, @JsonIgnore ProductResponseDTO product) {

}
