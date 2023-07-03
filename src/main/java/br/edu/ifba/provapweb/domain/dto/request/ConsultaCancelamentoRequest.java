package br.edu.ifba.provapweb.domain.dto.request;

import br.edu.ifba.provapweb.domain.enums.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record ConsultaCancelamentoRequest(
        @NotNull(message = "idConsulta não pode ser nulo") Long consultaId,

        @NotNull
        MotivoCancelamento motivo) {
}
