package com.zup.challenge.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zup.challenge.api.FipeApiService;
import com.zup.challenge.dto.NewCarroDTO;
import com.zup.challenge.entities.Carro;
import com.zup.challenge.services.CarroService;


@RestController
@RequestMapping(value = "/carros")
public class CarroResource {

	@Autowired
	private CarroService carroService;
	
	@Autowired
	private FipeApiService fipeApiService;
    
	@GetMapping
	public ResponseEntity<List<Carro>> findAll() {
		List <Carro> list = carroService.findAll();
		return ResponseEntity.ok().body(list);
	}
    
    @PostMapping
	public ResponseEntity<Carro> insert(@Valid @RequestBody NewCarroDTO dto){
    	Carro obj = fipeApiService.get(dto.getMarca(), dto.getModelo(), dto.getAno());
    	obj = carroService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Carro> findById(@PathVariable Long id) {
		Carro obj = carroService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	
}