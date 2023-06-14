package br.edu.ifba.provapweb.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record PacienteCreateRequest(
		@NotNull String cpf,
		@NotNull String nome,
		@NotNull @Email String email,
		@NotNull String telefone
) {

	public PacienteCreateRequest {
		nome = nome.toUpperCase();
		email = email.toUpperCase();
	}
}
