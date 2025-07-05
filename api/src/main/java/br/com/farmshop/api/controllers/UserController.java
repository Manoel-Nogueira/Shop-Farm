package br.com.farmshop.api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.farmshop.api.auth.JwtService;
import br.com.farmshop.api.dtos.AuthDTO;
import br.com.farmshop.api.dtos.UserCreateDTO;
import br.com.farmshop.api.dtos.UserResponseDTO;
import br.com.farmshop.api.dtos.UserUpdateDTO;
import br.com.farmshop.api.entities.User;
import br.com.farmshop.api.services.UserService;

@RestController 
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	//para nomear o caminho do PostMapping(nome)
	@PostMapping("/auth")
	public ResponseEntity<?> authUser(@RequestBody AuthDTO authDTO) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.password()));
		
		User  user = (User) authentication.getPrincipal();
		String token = jwtService.generateToken(user);
		
		return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);

	}
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO userCreateDTO) {
		
		UserResponseDTO userResponseDTO = userService.storeUser(userCreateDTO);
		
		return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);

	}
	
	//para nomear o caminho do GetMapping(nome)
	@GetMapping()
	public List<UserResponseDTO> listAllUser(){
		
		return userService.listAllUser();
		
	}
	
	@PutMapping
	public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
		
		UserResponseDTO userResponseDTO = userService.updateUser(userUpdateDTO);
		return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);

	}
	
	@GetMapping("/{user_id}")               
	public ResponseEntity<UserResponseDTO> showUserId(@PathVariable("user_id") Long id){
		
		UserResponseDTO userResponseDTO = userService.showUserById(id);
		return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{user_id}")
	public ResponseEntity<Boolean> destroyUser(@PathVariable("user_id") Long id){
		
		Boolean result = userService.destroyUser(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}


// estudar java isidro
