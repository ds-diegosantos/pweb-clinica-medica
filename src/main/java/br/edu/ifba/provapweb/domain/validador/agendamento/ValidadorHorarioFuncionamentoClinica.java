package br.edu.ifba.provapweb.domain.validador.agendamento;

import br.edu.ifba.provapweb.domain.dto.request.ConsultaCreateRequest;
import br.edu.ifba.provapweb.domain.exceptions.ResourceBadRequestException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsultar{
    @Override
    public void validar(ConsultaCreateRequest request) {
        Boolean domingo = request.dataHoraConsulta().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        Boolean horario = request.dataHoraConsulta().getHour() < 7 || request.dataHoraConsulta().getHour() > 18;

        if(domingo || horario){
            throw new ResourceBadRequestException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
