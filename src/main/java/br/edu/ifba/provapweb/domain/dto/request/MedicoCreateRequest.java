package br.edu.ifba.provapweb.domain.dto.request;

import br.edu.ifba.provapweb.domain.enums.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicoCreateRequest(
		@NotBlank(message = "CRM não pode ser nulo ou vazio") String crm,
		@NotBlank(message = "nome não pode ser nulo ou vazio") String nome,
		@NotBlank(message = "email não pode ser nulo ou vazio") @Email String email,
		@NotBlank(message = "telefone não pode ser nulo ou vazio") String telefone,
		@NotNull(message = "especialidade não pode ser nulo") Especialidade especialidade,
		@NotNull(message = "O campo 'endereco' não pode ser nulo") @Valid EnderecoRequest endereco) {

	public MedicoCreateRequest {
		nome = nome.toUpperCase();
		crm = crm.toUpperCase();
	}
}
