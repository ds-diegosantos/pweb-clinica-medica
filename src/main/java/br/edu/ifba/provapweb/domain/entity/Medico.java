package br.edu.ifba.provapweb.domain.entity;

import br.edu.ifba.provapweb.domain.dto.request.MedicoCreateRequest;
import br.edu.ifba.provapweb.domain.enums.Especialidade;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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
	private String crm;
	private String nome;
	private String email;
	private String telefone;
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	private boolean ativo;

	public Medico(MedicoCreateRequest dto) {
		this(dto.crm(), dto.nome(), dto.email(), dto.telefone(), dto.especialidade());
	}

	public Medico(String crm, String nome, String email, String telefone,
								Especialidade especialidade) {
		this(crm, nome, email, telefone, especialidade, true);
	}
}
