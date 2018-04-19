package br.com.dbc.dbc.service;

import java.util.List;

import br.com.dbc.dbc.model.Cliente;

public interface ClientService {
	
	void save(Cliente client);
	
	List<Cliente> findAll();
	
	Cliente findById(Long id);
	
	void delete(Long id);
	
	
}
