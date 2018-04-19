package br.com.dbc.dbc.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dbc.dbc.model.Endereco;
import br.com.dbc.dbc.service.EnderecoService;

@RestController
@RequestMapping("/v1/api")
public class EnderecoController {

	private static final Logger logger = LoggerFactory.getLogger(EnderecoController.class);

	@Autowired
	private EnderecoService service;

	@GetMapping("/endereco/find/{id}")
	public ResponseEntity<Endereco> getById(@PathVariable(value = "id") Long id) {
		Endereco endereco = service.findById(id);
		if (endereco == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(endereco);
	}

	@PostMapping("/endereco/salvar")
	public ResponseEntity<String> salvar(@Valid @RequestBody Endereco endereco) {
		try {
			service.salvar(endereco);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			logger.error("Ocorreu um erro inesperado ao tentar salvar o endereço.");
			return ResponseEntity.badRequest()
					.body("Ocorreu um erro inesperado ao tentar salvar o endereço. Motivo: " + e.getMessage());
		}
	}
	
	@DeleteMapping("/endereco/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
		Endereco endereco = service.findById(id);
		if (endereco == null) {
			return ResponseEntity.notFound().build();
		}
		service.deletar(id);
		return ResponseEntity.ok().build();
	}

}
