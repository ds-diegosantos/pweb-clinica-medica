package br.edu.ifba.provapweb.domain.validador.agendamento;

import br.edu.ifba.provapweb.domain.dto.request.ConsultaCreateRequest;
import br.edu.ifba.provapweb.domain.exceptions.ResourceBadRequestException;
import br.edu.ifba.provapweb.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorPacienteConsultaMesmoDia implements ValidadorAgendamentoConsultar{

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(ConsultaCreateRequest request) {
        LocalDateTime primeiroHorario = request.dataHoraConsulta().withHour(7);
        LocalDateTime ultimoHorario = request.dataHoraConsulta().withHour(18);
        Boolean pacientePossuiOutraConsultaNoDia = repository.existsByPacienteCpfAndDataBetween(request.pacienteCpf(),primeiroHorario,ultimoHorario);

        if(pacientePossuiOutraConsultaNoDia){
            throw new ResourceBadRequestException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}
