package br.edu.ifba.provapweb.domain.dto.request;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;

public record EnderecoRequest(
		@NotNull(message = "O campo 'logradouro' não pode ser nulo") String logradouro,
		String numero,
		String complemento,
		@NotNull(message = "O campo 'bairro' não pode ser nulo") String bairro,
		@NotNull(message = "O campo 'cidade' não pode ser nulo") String cidade,
		@NotNull(message = "O campo 'uf' não pode ser nulo") String uf,
		@NotNull(message = "O campo 'cep' não pode ser nulo") String cep
) {

	public EnderecoRequest {
		logradouro = logradouro.toUpperCase();
		numero = numero.toUpperCase();
		if (Objects.nonNull(complemento)) {
			complemento = complemento.toUpperCase();
		}
		bairro = bairro.toUpperCase();
		cidade = cidade.toUpperCase();
		uf = uf.toUpperCase();
	}
}
