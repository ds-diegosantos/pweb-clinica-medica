package br.edu.ifba.provapweb.domain.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicoUpdateRequest(
		@NotBlank(message = "nome não pode ser nulo ou vazio")String nome,
		@NotBlank(message = "telefone não pode ser nulo ou vazio")String telefone,
		@NotNull @Valid EnderecoRequest endereco) {

	public MedicoUpdateRequest {
		nome = nome.toUpperCase();
	}
}
