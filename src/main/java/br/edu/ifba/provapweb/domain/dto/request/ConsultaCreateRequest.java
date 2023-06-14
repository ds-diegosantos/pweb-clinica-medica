package br.edu.ifba.provapweb.domain.dto.request;

import java.time.LocalDateTime;

public record ConsultaCreateRequest(String pacienteCpf, String medicoCrm,
																		LocalDateTime dataHoraConsulta) {

	public ConsultaCreateRequest {
		medicoCrm = medicoCrm.toUpperCase();
	}
}
