package br.edu.ifba.provapweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.ifba.provapweb.domain.dto.request.MedicoCreateRequest;
import br.edu.ifba.provapweb.domain.dto.request.MedicoUpdateRequest;
import br.edu.ifba.provapweb.domain.dto.response.MedicoResponse;
import br.edu.ifba.provapweb.domain.entity.Medico;
import br.edu.ifba.provapweb.repository.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;

	public Void cadastrarMedico(MedicoCreateRequest dto) {
		medicoRepository.save(new Medico(dto));
		return null;
	}

	public Page<MedicoResponse> listarMedicos(Pageable pageable) {
		return medicoRepository
				.findAll(pageable)
				.map(MedicoResponse::new);
	}

	public Void deletarMedico(String crm) {
		medicoRepository
				.findById(crm)
				.ifPresent(medico -> {
					medico.setAtivo(false);
					medicoRepository.save(medico);
				});
		return null;
	}

	public MedicoResponse atualizarMedico(String crm, MedicoUpdateRequest request) {

		return medicoRepository
				.findById(crm.toUpperCase())
				.map(medico -> {
					medico.setNome(request.nome());
					medico.setTelefone(request.telefone());
					medicoRepository.save(medico);
					return new MedicoResponse(medico);
				})
				.orElse(null);
	}
}
