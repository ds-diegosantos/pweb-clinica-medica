package br.edu.ifba.provapweb.domain.validador.cancelamento;

import br.edu.ifba.provapweb.domain.dto.request.ConsultaCancelamentoRequest;
import br.edu.ifba.provapweb.domain.entity.Consulta;
import br.edu.ifba.provapweb.domain.exceptions.ResourceBadRequestException;
import br.edu.ifba.provapweb.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorCancelamentoConsulta")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoConsulta {

    @Autowired
    private ConsultaRepository repository;


    @Override
    public void validar(ConsultaCancelamentoRequest request) {
        Consulta consulta = repository.getReferenceById(request.consultaId());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ResourceBadRequestException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}
