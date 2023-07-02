package br.edu.ifba.provapweb.domain.dto.response;

import br.edu.ifba.provapweb.domain.entity.Medico;
import br.edu.ifba.provapweb.domain.enums.Especialidade;

public record MedicoResponse(Long id,String nome, String email, String crm, Especialidade especialidade, String telefone, EnderecoResponse endereco) {

	public MedicoResponse(Medico entity) {
		this(entity.getId(), entity.getNome(), entity.getEmail(), entity.getCrm(), entity.getEspecialidade(), entity.getTelefone(), new EnderecoResponse(entity.getEndereco()));
	}
}
