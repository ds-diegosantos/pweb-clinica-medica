package br.edu.ifba.provapweb.domain.validador.agendamento;

import br.edu.ifba.provapweb.domain.dto.request.ConsultaCreateRequest;

public interface ValidadorAgendamentoConsultar {
    void validar(ConsultaCreateRequest request);
}
