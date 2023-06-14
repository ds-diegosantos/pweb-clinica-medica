package br.edu.ifba.provapweb.domain.dto.request;

public record PacienteUpdateRequest(String nome, String telefone) {

	public PacienteUpdateRequest {
		nome = nome.toUpperCase();
	}
}
