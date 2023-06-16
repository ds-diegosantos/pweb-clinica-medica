package br.edu.ifba.provapweb.service;

import br.edu.ifba.provapweb.domain.entity.Endereco;
import br.edu.ifba.provapweb.domain.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.ifba.provapweb.domain.dto.request.MedicoCreateRequest;
import br.edu.ifba.provapweb.domain.dto.request.MedicoUpdateRequest;
import br.edu.ifba.provapweb.domain.dto.response.MedicoResponse;
import br.edu.ifba.provapweb.domain.entity.Medico;
import br.edu.ifba.provapweb.repository.MedicoRepository;

import java.time.LocalDateTime;

@Service
public class MedicoService {
	@Autowired
	private MedicoRepository medicoRepository;

	public MedicoResponse cadastrarMedico(MedicoCreateRequest dto) {
		Medico medico = medicoRepository.save(new Medico(dto));
		return new MedicoResponse(medico);
	}

	public Page<MedicoResponse> listarMedicos(Pageable pageable) {
		return medicoRepository
				.findAllByAtivoTrue(pageable)
				.map(MedicoResponse::new);
	}

	public Medico buscarPeloId(String crm){
		return medicoRepository.findByCrmAndAtivoTrue(crm).orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com o CRM: " + crm));
	}

	public Medico MedicoDisponivelPelaData(LocalDateTime data){
		return medicoRepository.medicoAleatorioLivreNaData(data).orElseThrow(() -> new ResourceNotFoundException("nenhum médico disponivel para essa data: " + data));
	}

	public Void deletarMedico(String crm) {
		Medico medico = buscarPeloId(crm);
		medico.setAtivo(false);
		medicoRepository.save(medico);
		return null;
	}

	public MedicoResponse atualizarMedico(String crm, MedicoUpdateRequest request) {
		Medico medico = buscarPeloId(crm);
		medico.setNome(request.nome());
		medico.setTelefone(request.telefone());
		medico.setEndereco(new Endereco(request.endereco()));
		medicoRepository.save(medico);
		return new MedicoResponse(medico);
	}
}
