package br.com.farmshop.api.mappers;
import br.com.farmshop.api.dtos.UserCreateDTO;
import br.com.farmshop.api.dtos.UserResponseDTO;
import br.com.farmshop.api.entities.User;

public class UserMapper {

	public static UserResponseDTO toDTO(User user) {
		
		return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getCreated_at(), user.getUpdated_at());
		
	}
	
	public static User toEntity(UserCreateDTO userCreateDTO) {
		
		User user = new User();
		user.setName(userCreateDTO.name());
		user.setEmail(userCreateDTO.email());
		user.setPassword(userCreateDTO.password());
		user.setRole(userCreateDTO.role());
		
		return user;
		
	}
	
}
