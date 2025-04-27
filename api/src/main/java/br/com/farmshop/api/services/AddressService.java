package br.com.farmshop.api.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.farmshop.api.dtos.AddressCreateDTO;
import br.com.farmshop.api.dtos.AddressResponseDTO;
import br.com.farmshop.api.dtos.AddressUpdateDTO;
import br.com.farmshop.api.entities.Address;
import br.com.farmshop.api.entities.User;
import br.com.farmshop.api.mappers.AddressMapper;
import br.com.farmshop.api.repositories.AddressRepository;
import br.com.farmshop.api.repositories.UserRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository; 
	
	public AddressResponseDTO storeAddress(AddressCreateDTO addressCreateDTO) {
		
		User user = userRepository.findById(addressCreateDTO.user_id()).orElseThrow(() -> new RuntimeException("Error in AddressService update"));
		Address address = AddressMapper.toEntity(addressCreateDTO);
		
		address.setUser(user);

		return AddressMapper.toDTO(addressRepository.save(address));
		
	}
	
	public List<AddressResponseDTO> listAllAddress(){
		
		return addressRepository.findAll().stream().map(AddressMapper::toDTO).toList();
		
	}
	
	public AddressResponseDTO updateAddress(AddressUpdateDTO addressUpdateDTO) {
		
		Address address = addressRepository.findById(addressUpdateDTO.id()).orElseThrow(() -> new RuntimeException("Error in AddressService update"));
		User user = userRepository.findById(addressUpdateDTO.user_id()).orElseThrow(() -> new RuntimeException("Error in AddressService update"));
		
		address.setId(addressUpdateDTO.id());
		address.setStreet(addressUpdateDTO.street());
		address.setNumber(addressUpdateDTO.number());
		address.setNeighborhood(addressUpdateDTO.neighborhood());
		address.setCity(addressUpdateDTO.city());
		address.setState(addressUpdateDTO.state());
		address.setComplement(addressUpdateDTO.complement());
		address.setZipcode(addressUpdateDTO.zipcode());
		address.setUser(user);
	
		addressRepository.save(address);
		
		return AddressMapper.toDTO(address);
		
	}
	
	public AddressResponseDTO showAddressById(Long id) {
		
		Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in AddressService show"));
	
		return AddressMapper.toDTO(address);
	
	}
	
	public Boolean destroyAddress(Long id) {
		
		Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in AddressService destroy"));
		addressRepository.delete(address);

		return true;
	}
	
}


// nome de class CM
// java camelcase

//Address address = new Address();
//address.setUser(userResponse);
//addressRepository.save(address);
