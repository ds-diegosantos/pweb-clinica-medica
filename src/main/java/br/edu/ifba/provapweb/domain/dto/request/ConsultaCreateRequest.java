package br.edu.ifba.provapweb.domain.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaCreateRequest(
		@NotBlank(message = "cpf não pode ser nulo ou vazio") String pacienteCpf,
		String medicoCrm,
		@NotNull(message ="dataHoraConsulta não pode ser nulo ou vazio" ) LocalDateTime dataHoraConsulta) {

	public ConsultaCreateRequest {
		medicoCrm = medicoCrm.toUpperCase();
	}
}
