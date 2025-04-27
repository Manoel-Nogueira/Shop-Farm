package br.com.farmshop.api.dtos;

public record AddressUpdateDTO(Long id, String street, String number, String neighborhood, String city, String state, String complement, Long zipcode, Long user_id){

}
