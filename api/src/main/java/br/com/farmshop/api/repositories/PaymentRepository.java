package br.com.farmshop.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.farmshop.api.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
