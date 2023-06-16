package br.edu.ifba.provapweb.repository;

import br.edu.ifba.provapweb.domain.entity.Medico;
import br.edu.ifba.provapweb.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.provapweb.domain.entity.Consulta;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByPacienteCpfAndDataBetween(String cpf, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByMedicoCrmAndData(String crm, LocalDateTime data);
}
