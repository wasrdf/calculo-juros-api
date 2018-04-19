package br.com.dbc.dbc.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dbc.dbc.model.Cliente;
import br.com.dbc.dbc.model.Emprestimo;
import br.com.dbc.dbc.model.Risco;
import br.com.dbc.dbc.service.ClientService;

@RestController
@RequestMapping("/v1/api")
public class SimulacaoController {

	@Autowired
	private ClientService service;

	@PostMapping("/simulacao/simular")
	public BigDecimal getById(@Valid @RequestBody Emprestimo emprestimo) {
		
		Cliente cliente = service.findById(emprestimo.getCodigoCliente());
		if (cliente.getRisco().equals(Risco.A)) {
			//1.9
			BigDecimal valor = emprestimo.getValor();//valor do emprestimo
			BigDecimal duracao = new BigDecimal(emprestimo.getDuracao());//duracao
			BigDecimal juros = valor.multiply(new BigDecimal(1.9)).divide(new BigDecimal(100)).multiply(duracao);//juros valor x taxa de juros x duracao
			return valor.add(juros);			
		} else if (cliente.getRisco().equals(Risco.B)) {
			//5
			BigDecimal valor = emprestimo.getValor();
			BigDecimal duracao = new BigDecimal(emprestimo.getDuracao());
			BigDecimal juros = valor.multiply(new BigDecimal(5)).divide(new BigDecimal(100)).multiply(duracao);
			
			return valor.add(juros);
		} else {
			//10
			BigDecimal valor = emprestimo.getValor();
			BigDecimal duracao = new BigDecimal(emprestimo.getDuracao());
			BigDecimal juros = valor.multiply(new BigDecimal(10)).divide(new BigDecimal(100)).multiply(duracao);
			
			return valor.add(juros);
		}

	}

}
