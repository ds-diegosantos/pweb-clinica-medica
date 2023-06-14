package br.edu.ifba.provapweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.ifba.provapweb.domain.dto.request.PacienteCreateRequest;
import br.edu.ifba.provapweb.domain.dto.request.PacienteUpdateRequest;
import br.edu.ifba.provapweb.domain.dto.response.PacienteResponse;
import br.edu.ifba.provapweb.domain.entity.Paciente;
import br.edu.ifba.provapweb.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;

	public Void cadastrarPaciente(PacienteCreateRequest request) {
		pacienteRepository.save(new Paciente(request));
		return null;
	}

	public Page<PacienteResponse> listarPacientes(Pageable pageable) {
		return pacienteRepository
				.findAll(pageable)
				.map(PacienteResponse::new);
	}

	public Void deletarPaciente(String cpf) {
		pacienteRepository
				.findById(cpf)
				.ifPresent(paciente -> {
					paciente.setAtivo(false);
					pacienteRepository.save(paciente);
				});
		return null;
	}

	public PacienteResponse atualizarPaciente(String cpf, PacienteUpdateRequest request) {
		return pacienteRepository
				.findById(cpf)
				.map(paciente -> {
					paciente.setNome(request.nome());
					paciente.setTelefone(request.telefone());
					pacienteRepository.save(paciente);
					return new PacienteResponse(paciente);
				})
				.orElse(null);
	}
}
