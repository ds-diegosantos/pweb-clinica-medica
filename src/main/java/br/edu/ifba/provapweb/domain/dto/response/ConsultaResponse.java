package br.edu.ifba.provapweb.domain.dto.response;

import br.edu.ifba.provapweb.domain.entity.Consulta;
import br.edu.ifba.provapweb.domain.entity.Medico;
import br.edu.ifba.provapweb.domain.entity.Paciente;
import br.edu.ifba.provapweb.repository.MedicoRepository;

import java.time.LocalDateTime;

public record ConsultaResponse (Long id, MedicoResponse medico, PacienteResponse paciente, LocalDateTime data){

    public ConsultaResponse(Consulta consulta) {
        this(consulta.getId(),new MedicoResponse(consulta.getMedico()), new PacienteResponse(consulta.getPaciente()),consulta.getData());
    }
}
