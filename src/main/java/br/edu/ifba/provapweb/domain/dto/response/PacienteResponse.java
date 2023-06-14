package br.edu.ifba.provapweb.domain.dto.response;

import br.edu.ifba.provapweb.domain.entity.Paciente;

public record PacienteResponse(String cpf, String nome, String email) {

	public PacienteResponse(Paciente entity) {
		this(entity.getCpf(), entity.getNome(), entity.getEmail());
	}
}
