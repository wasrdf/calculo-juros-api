package br.com.dbc.dbc.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dbc.dbc.model.Cliente;
import br.com.dbc.dbc.model.Endereco;
import br.com.dbc.dbc.model.Risco;
import br.com.dbc.dbc.repository.ClienteRepository;
import br.com.dbc.dbc.repository.EnderecoRepository;
import br.com.dbc.dbc.service.ClientService;

@Service
public class ClienteServiceImpl implements ClientService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public void save(Cliente client) {

		/**
		 * VERIFICA EM QUAL RISCO O CLIENTE SE ENQUADRA. SE RENDIMENTO MENSAL
		 * <= 200 = C > 2000 AND <= 8000 = B SE NAO A
		 **/

		if (client.getRendimentoMensal().compareTo(new BigDecimal(2000)) <= 0) {
			client.setRisco(Risco.C);
		} else if ((client.getRendimentoMensal().compareTo(new BigDecimal(2000)) > 0)
				&& (client.getRendimentoMensal().compareTo(new BigDecimal(8000)) <= 0)) {
			client.setRisco(Risco.B);
		} else {
			client.setRisco(Risco.A);
		}

		// Salva o cliente e retorna o codigo dele.
		Cliente c = clienteRepository.save(client);

		// percorre a lista de enderecos.
		for (Endereco e : client.getEndereco()) {
			// seta o codigo do cliente pro endereco
			e.setCodigoCliente(c.getCodigo());
			// salva o endereço
			enderecoRepository.save(e);
		}

	}

	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente findById(Long id) {
		return clienteRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		clienteRepository.delete(id);
	}

}
