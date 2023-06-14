package br.edu.ifba.provapweb.domain.dto.response;

import br.edu.ifba.provapweb.domain.entity.Medico;
import br.edu.ifba.provapweb.domain.enums.Especialidade;

public record MedicoResponse(String nome, String email, String crm, Especialidade especialidade) {

	public MedicoResponse(Medico entity) {
		this(entity.getNome(), entity.getEmail(), entity.getCrm(), entity.getEspecialidade());
	}
}
