package br.edu.ifba.provapweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.provapweb.domain.entity.Paciente;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivo(Pageable pageable, boolean ativo);

    Optional<Paciente> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
