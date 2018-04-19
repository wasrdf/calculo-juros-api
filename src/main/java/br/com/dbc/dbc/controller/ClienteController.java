package br.com.dbc.dbc.controller;

import java.util.List;

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

import br.com.dbc.dbc.model.Cliente;
import br.com.dbc.dbc.service.ClientService;

@RestController
@RequestMapping("/v1/api")
public class ClienteController {

	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClientService service;

	@GetMapping("/cliente/getAll")
	public List<Cliente> getAll() {
		return service.findAll();
	}

	@PostMapping("/cliente/salvar")
	public ResponseEntity<String> salvar(@Valid @RequestBody Cliente cliente) {
		try {
			service.save(cliente);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			logger.error("Ocorreu um erro inesperado ao tentar salvar o cliente.");
			return ResponseEntity.badRequest()
					.body("Ocorreu um erro inesperado ao tentar salvar o cliente. Motivo: " + e.getMessage());
		}
	}

	@DeleteMapping("/cliente/delete/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable(value = "id") Long id) {
		Cliente cliente = service.findById(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		service.delete(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/cliente/find/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable(value = "id") Long id) {
		Cliente cliente = service.findById(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(cliente);
	}

}
