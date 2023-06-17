package br.edu.ifba.provapweb.domain.dto.request;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoRequest(
		@NotBlank(message = "O campo 'logradouro' não pode ser nulo ou vazio") String logradouro,
		String numero,
		String complemento,
		@NotBlank(message = "O campo 'bairro' não pode ser nulo ou vazio") String bairro,
		@NotBlank(message = "O campo 'cidade' não pode ser nulo ou vazio") String cidade,
		@NotBlank(message = "O campo 'uf' não pode ser nulo ou vazio") String uf,
		@NotBlank(message = "O campo 'cep' não pode ser nulo ou vazio") String cep
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
