package br.edu.ifba.provapweb.repository;

import br.edu.ifba.provapweb.domain.entity.Consulta;
import br.edu.ifba.provapweb.domain.entity.Medico;
import br.edu.ifba.provapweb.domain.util.MotivoCancelamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsBymotivoCancelamentoNullAndPacienteCpfAndDataBetween(String cpf, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByMedicoCrmAndDataAndMotivoCancelamentoIsNull(String crm, LocalDateTime data);

    Page<Consulta> findAllByMotivoCancelamentoIsNull(Pageable pageable);

    Page<Consulta> findAllByMotivoCancelamentoIsNotNull(Pageable pageable);
}
