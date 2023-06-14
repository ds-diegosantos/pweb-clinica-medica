package br.edu.ifba.provapweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.provapweb.domain.entity.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
