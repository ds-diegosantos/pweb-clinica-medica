package br.edu.ifba.provapweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.provapweb.domain.entity.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String> {

}
