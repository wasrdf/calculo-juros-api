package br.com.dbc.dbc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dbc.dbc.model.Endereco;
import br.com.dbc.dbc.repository.EnderecoRepository;
import br.com.dbc.dbc.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository repository;
	
	@Override
	public Endereco findById(Long codigo) {
		return repository.findOne(codigo);
	}

	@Override
	public void salvar(Endereco endereco) {
		repository.save(endereco);
	}

	@Override
	public void deletar(Long codigo) {
		repository.delete(codigo);
	}

}
