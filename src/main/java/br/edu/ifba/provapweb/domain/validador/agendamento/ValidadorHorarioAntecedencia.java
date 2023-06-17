package br.edu.ifba.provapweb.domain.validador.agendamento;

import br.edu.ifba.provapweb.domain.dto.request.ConsultaCreateRequest;
import br.edu.ifba.provapweb.domain.exceptions.ResourceBadRequestException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsultar{
    @Override
    public void validar(ConsultaCreateRequest request) {
        LocalDateTime now = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(now, request.dataHoraConsulta()).toMinutes();

        if(diferencaEmMinutos < 30){
            throw new ResourceBadRequestException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
