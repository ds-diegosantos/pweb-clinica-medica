package br.edu.ifba.provapweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ifba.provapweb.domain.entity.Medico;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, String> {
    @Query(value = "SELECT m FROM medico m WHERE m.ativo = TRUE AND m.id NOT IN (SELECT c.medico.id FROM Consulta c WHERE c.data = :data and c.motivoCancelamento is null) ORDER BY RAND() FETCH FIRST 1 ROW ONLY")
    Optional<Medico> medicoAleatorioLivreNaData(@Param("data") LocalDateTime data);

    Page<Medico> findAllByAtivo(Pageable pageable, boolean ativo);
}
