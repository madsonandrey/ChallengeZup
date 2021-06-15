package com.zup.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zup.challenge.entities.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

}
