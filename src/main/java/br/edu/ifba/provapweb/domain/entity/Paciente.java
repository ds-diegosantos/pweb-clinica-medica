package br.edu.ifba.provapweb.domain.entity;

import br.edu.ifba.provapweb.domain.dto.request.PacienteCreateRequest;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "paciente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

	@Id
	private String cpf;
	private String nome;
	private String email;
	private String telefone;
	@Embedded
	private Endereco endereco;
	private boolean ativo;

	public Paciente(PacienteCreateRequest request) {
		this(request.cpf(), request.nome(), request.email(), request.telefone(), new Endereco(request.endereco()));
	}

	public Paciente(String cpf, String nome, String email, String telefone, Endereco endereco) {
		this(cpf, nome, email, telefone,endereco ,true);
	}
}
