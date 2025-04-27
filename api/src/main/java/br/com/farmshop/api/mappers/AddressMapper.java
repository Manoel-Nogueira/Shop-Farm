package br.com.farmshop.api.mappers;
import br.com.farmshop.api.dtos.AddressCreateDTO;
import br.com.farmshop.api.dtos.AddressResponseDTO;
import br.com.farmshop.api.entities.Address;

public class AddressMapper {

	public static AddressResponseDTO toDTO(Address address) {
		
		return new AddressResponseDTO(address.getId(), address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState() , address.getComplement(), address.getZipcode(), UserMapper.toDTO(address.getUser()));
		
	}
	
	public static Address toEntity(AddressCreateDTO addressCreateDTO) {
		
		Address address = new Address();
		address.setStreet(addressCreateDTO.street());
		address.setNumber(addressCreateDTO.number());
		address.setNeighborhood(addressCreateDTO.neighborhood());
		address.setCity(addressCreateDTO.city());
		address.setState(addressCreateDTO.state());
		address.setComplement(addressCreateDTO.complement());
		address.setZipcode(addressCreateDTO.zipcode());
	
		return address;
		
	}
	
}
