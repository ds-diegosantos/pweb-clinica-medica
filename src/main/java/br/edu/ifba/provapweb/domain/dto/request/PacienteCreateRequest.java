package br.edu.ifba.provapweb.domain.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PacienteCreateRequest(
		@NotBlank(message = "cpf não pode ser nulo ou vazio") String cpf,
		@NotBlank(message = "nome não pode ser nulo ou vazio") String nome,
		@NotBlank(message = "Email não pode ser nulo ou vazio") @Email String email,
		@NotBlank(message = "telefone não pode ser nulo ou vazio") String telefone,

		@NotNull(message = "O campo 'endereco' não pode ser nulo") @Valid EnderecoRequest endereco
) {

	public PacienteCreateRequest {
		nome = nome.toUpperCase();
	}
}
