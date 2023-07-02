package br.edu.ifba.provapweb.domain.entity;

import br.edu.ifba.provapweb.domain.dto.request.MedicoCreateRequest;
import br.edu.ifba.provapweb.domain.enums.Especialidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "medico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String crm;
	private String nome;
	private String email;
	private String telefone;
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	@Embedded
	private Endereco endereco;
	private boolean ativo;

	public Medico(MedicoCreateRequest dto) {
		this(null,dto.crm(), dto.nome(), dto.email(), dto.telefone(), dto.especialidade(),new Endereco(dto.endereco()),true);
	}
}
