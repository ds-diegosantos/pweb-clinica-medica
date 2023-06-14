package br.edu.ifba.provapweb.domain.dto.request;

import br.edu.ifba.provapweb.domain.enums.Especialidade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record MedicoCreateRequest(
		@NotNull(message = "CRM não pode ser nulo") String crm,
		@NotNull(message = "nome não pode ser nulo") String nome,
		@NotNull(message = "email não pode ser nulo") @Email String email,
		@NotNull(message = "telefone não pode ser nulo") String telefone,
		@NotNull(message = "especialidade não pode ser nulo") Especialidade especialidade,
		@NotNull(message = "O campo 'endereco' não pode ser nulo") EnderecoRequest endereco) {

	public MedicoCreateRequest {
		nome = nome.toUpperCase();
		crm = crm.toUpperCase();
		email = email.toUpperCase();
	}
}
