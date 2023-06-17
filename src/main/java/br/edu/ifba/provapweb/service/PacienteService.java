package br.edu.ifba.provapweb.service;

import br.edu.ifba.provapweb.domain.entity.Endereco;
import br.edu.ifba.provapweb.domain.exceptions.ResourceNotFoundException;
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

	public PacienteResponse cadastrarPaciente(PacienteCreateRequest request) {
		Paciente paciente = pacienteRepository.save(new Paciente(request));
		return new PacienteResponse(paciente);
	}

	public Page<PacienteResponse> listarPacientes(Pageable pageable) {
		return pacienteRepository
				.findAllByAtivoTrue(pageable)
				.map(PacienteResponse::new);
	}

	public Paciente buscarPeloId(String cpf){
		return pacienteRepository.findByCpfAndAtivoTrue(cpf).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com o CPF: " + cpf));
	}

	public Void deletarPaciente(String cpf) {
		Paciente paciente = buscarPeloId(cpf);
		paciente.setAtivo(false);
		pacienteRepository.save(paciente);
		return null;
	}

	public PacienteResponse atualizarPaciente(String cpf, PacienteUpdateRequest request) {
		Paciente paciente = buscarPeloId(cpf);
		paciente.setNome(request.nome());
		paciente.setTelefone(request.telefone());
		paciente.setEndereco(new Endereco(request.endereco()));
		pacienteRepository.save(paciente);
		return new PacienteResponse(paciente);
	}
}
