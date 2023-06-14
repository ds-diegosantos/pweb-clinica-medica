package br.edu.ifba.provapweb.domain.dto.request;

public record MedicoUpdateRequest(String nome, String telefone) {

	public MedicoUpdateRequest {
		nome = nome.toUpperCase();
	}
}
