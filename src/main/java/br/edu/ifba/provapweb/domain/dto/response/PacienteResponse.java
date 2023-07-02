package br.edu.ifba.provapweb.domain.dto.response;

import br.edu.ifba.provapweb.domain.entity.Paciente;

public record PacienteResponse(Long id, String cpf, String nome, String email, String telefone, EnderecoResponse endereco) {

	public PacienteResponse(Paciente entity) {
		this(entity.getId(), entity.getCpf(), entity.getNome(), entity.getEmail(),entity.getTelefone(), new EnderecoResponse(entity.getEndereco()));
	}
}
