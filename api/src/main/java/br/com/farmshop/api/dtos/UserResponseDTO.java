package br.com.farmshop.api.dtos;

import br.com.farmshop.api.enums.Role;
import java.time.Instant;

public record UserResponseDTO(Long id, String name, String email, Role role, Instant created_at, Instant updated_at) {

}
