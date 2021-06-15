package com.zup.challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zup.challenge.entities.Carro;
import com.zup.challenge.repositories.CarroRepository;
import com.zup.challenge.services.exceptions.ResourceNotFoundException;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;
   
    
	@Transactional(readOnly = true)
	public List<Carro> findAll() {
		List<Carro> list = carroRepository.findAll();
		return list;
	}

	@Transactional(readOnly = true)
	public Carro findById(Long id) {
		Optional<Carro> obj = carroRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
	}

	@Transactional
	public Carro insert(Carro obj) {
		if(obj != null) {
			obj = carroRepository.save(obj);
		}
		return obj;
	}


}
