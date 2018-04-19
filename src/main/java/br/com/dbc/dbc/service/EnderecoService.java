package br.com.dbc.dbc.service;

import br.com.dbc.dbc.model.Endereco;

public interface EnderecoService {
	Endereco findById(Long codigo);
	
	void salvar(Endereco endereco);
	
	void deletar(Long codigo);
	
}
