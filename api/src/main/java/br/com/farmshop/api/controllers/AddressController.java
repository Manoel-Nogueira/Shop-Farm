package br.com.farmshop.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.farmshop.api.dtos.AddressCreateDTO;
import br.com.farmshop.api.dtos.AddressResponseDTO;
import br.com.farmshop.api.dtos.AddressUpdateDTO;
import br.com.farmshop.api.services.AddressService;

@RestController 
@RequestMapping("/api/addresses")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	//para nomear o caminho do PostMapping(nome)
	@PostMapping
	public ResponseEntity<AddressResponseDTO> createUser(@RequestBody AddressCreateDTO addressCreateDTO) {

		AddressResponseDTO addressResponseDTO = addressService.storeAddress(addressCreateDTO);
		
		return new ResponseEntity<>(addressResponseDTO, HttpStatus.CREATED);

	}
	
	//para nomear o caminho do GetMapping(nome)
	@GetMapping()
	public List<AddressResponseDTO> listAllUser(){
		
		return addressService.listAllAddress();
		
	}
	
	@PutMapping
	public ResponseEntity<AddressResponseDTO> updateUser(@RequestBody AddressUpdateDTO addressUpdateDTO){
		
		AddressResponseDTO addressResponseDTO = addressService.updateAddress(addressUpdateDTO);
		return new ResponseEntity<>(addressResponseDTO, HttpStatus.OK);

	}
	
	@GetMapping("/{userId}")               
	public ResponseEntity<AddressResponseDTO> showUserId(@PathVariable("userId") Long id){
		
		AddressResponseDTO addressResponseDTO = addressService.showAddressById(id);
		return new ResponseEntity<>(addressResponseDTO, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Boolean> destroyUser(@PathVariable("userId") Long id){
		
		Boolean result = addressService.destroyAddress(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}


// estudar java isidro
