package br.com.farmshop.api.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.farmshop.api.dtos.UserCreateDTO;
import br.com.farmshop.api.dtos.UserResponseDTO;
import br.com.farmshop.api.dtos.UserUpdateDTO;
import br.com.farmshop.api.entities.User;
import br.com.farmshop.api.mappers.UserMapper;
import br.com.farmshop.api.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserResponseDTO storeUser(UserCreateDTO userCreateDTO) {
		
		User user = UserMapper.toEntity(userCreateDTO);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		
		return UserMapper.toDTO(userRepository.save(user));
		
	}
	
	public List<UserResponseDTO> listAllUser(){
		
		return userRepository.findAll().stream().map(UserMapper::toDTO).toList();
		
	}
	
	public UserResponseDTO updateUser(UserUpdateDTO userUpdateDTO) {
		
		User user = userRepository.findById(userUpdateDTO.id()).orElseThrow(() -> new RuntimeException("Error in UserService update"));
		
		user.setName(userUpdateDTO.name());
		user.setEmail(userUpdateDTO.email());
		user.setPassword(userUpdateDTO.password());
		user.setRole(userUpdateDTO.role());
		
		return UserMapper.toDTO(userRepository.save(user));
		
	}
	
	public UserResponseDTO showUserById(Long id) {
		
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in UserService show"));
	
		return UserMapper.toDTO(user);
	
	}
	
	public Boolean destroyUser(Long id) {
		
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in UserService destroy"));
		userRepository.delete(user);

		return true;
	}
	
}


// nome de class CM
// java camelcase

//Address address = new Address();
//address.setUser(userResponse);
//addressRepository.save(address);
