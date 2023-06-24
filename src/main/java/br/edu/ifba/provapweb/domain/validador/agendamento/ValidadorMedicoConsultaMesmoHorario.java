package br.edu.ifba.provapweb.domain.validador.agendamento;

import br.edu.ifba.provapweb.domain.dto.request.ConsultaCreateRequest;
import br.edu.ifba.provapweb.domain.exceptions.ResourceBadRequestException;
import br.edu.ifba.provapweb.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConsultaMesmoHorario implements ValidadorAgendamentoConsultar{

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(ConsultaCreateRequest request) {
        if(repository.existsByMedicoCrmAndDataAndMotivoCancelamentoIsNull(request.medicoCrm(),request.dataHoraConsulta())){
            throw new ResourceBadRequestException("Médico ja possui outra consulta agendada nesse mesmo horário");
        }
    }
}
