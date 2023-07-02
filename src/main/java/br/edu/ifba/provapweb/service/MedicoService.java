package br.edu.ifba.provapweb.service;

import br.edu.ifba.provapweb.domain.entity.Endereco;
import br.edu.ifba.provapweb.domain.exceptions.ResourceBadRequestException;
import br.edu.ifba.provapweb.domain.exceptions.ResourceConflictException;
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
		if(medicoRepository.existsById(dto.crm()))
			throw new ResourceConflictException("Já existe outro médico com o crm informado!");

		Medico medico = medicoRepository.save(new Medico(dto));
		return new MedicoResponse(medico);
	}

	public Page<MedicoResponse> listarMedicos(Pageable pageable, boolean ativo) {
		return medicoRepository
				.findAllByAtivo(pageable,ativo)
				.map(MedicoResponse::new);
	}

	public Medico buscarPeloId(String crm){
		Medico medico = medicoRepository.findById(crm).orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com o CRM: " + crm));

		if(!medico.isAtivo())
			throw new ResourceNotFoundException("Médico inativo com o CRM: " + crm);

		return medico;
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

	public Void AtivarMedico(String crm){
		Medico medico =  medicoRepository.findById(crm).orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com o CRM: " + crm));
		medico.setAtivo(true);
		medicoRepository.save(medico);
		return null;
	}
}
