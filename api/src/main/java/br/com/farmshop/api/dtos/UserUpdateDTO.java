package br.com.farmshop.api.dtos;
import br.com.farmshop.api.enums.Role;

public record UserUpdateDTO(Long id, String name, String email, String password, Role role) {

}
