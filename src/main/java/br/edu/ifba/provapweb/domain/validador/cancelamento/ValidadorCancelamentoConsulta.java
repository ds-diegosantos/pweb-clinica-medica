package br.edu.ifba.provapweb.domain.validador.cancelamento;

import br.edu.ifba.provapweb.domain.dto.request.ConsultaCancelamentoRequest;

public interface ValidadorCancelamentoConsulta {
    void validar(ConsultaCancelamentoRequest request);
}
