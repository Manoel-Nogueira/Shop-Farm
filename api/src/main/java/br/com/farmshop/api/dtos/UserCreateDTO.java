package br.com.farmshop.api.dtos;
import br.com.farmshop.api.enums.Role;

public record UserCreateDTO(String name, String email, String password, Role role){

}

