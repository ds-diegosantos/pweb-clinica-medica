package br.edu.ifba.provapweb.domain.entity;

import java.time.LocalDateTime;

import br.edu.ifba.provapweb.domain.enums.MotivoCancelamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Paciente paciente;
	@ManyToOne
	private Medico medico;
	private LocalDateTime data;

	@Enumerated(EnumType.STRING)
	private MotivoCancelamento motivoCancelamento;

}
