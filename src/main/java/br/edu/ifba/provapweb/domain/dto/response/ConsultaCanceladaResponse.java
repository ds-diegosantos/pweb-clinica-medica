package br.edu.ifba.provapweb.domain.dto.response;

import br.edu.ifba.provapweb.domain.entity.Consulta;
import br.edu.ifba.provapweb.domain.enums.MotivoCancelamento;

import java.time.LocalDateTime;

public record ConsultaCanceladaResponse(Long id, MedicoResponse medico, PacienteResponse paciente,
                                        MotivoCancelamento motivoCancelamento , LocalDateTime data){

    public ConsultaCanceladaResponse(Consulta consulta) {
        this(consulta.getId(),new MedicoResponse(consulta.getMedico()), new PacienteResponse(consulta.getPaciente()),consulta.getMotivoCancelamento(),consulta.getData());
    }
}
