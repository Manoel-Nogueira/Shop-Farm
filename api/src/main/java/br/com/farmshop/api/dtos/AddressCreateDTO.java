package br.com.farmshop.api.dtos;

public record AddressCreateDTO(String street, String number, String neighborhood, String city, String state, String complement, Long zipcode, Long user_id){

}
