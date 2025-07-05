package br.com.farmshop.api.dtos;

public record AddressResponseDTO(Long id, String street, String number, String neighborhood, String city, String state, String complement, Long zipcode, UserResponseDTO user) {

}
