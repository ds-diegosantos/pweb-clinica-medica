package br.edu.ifba.provapweb.service;

import java.util.List;

import br.edu.ifba.provapweb.domain.dto.request.ConsultaCancelamentoRequest;
import br.edu.ifba.provapweb.domain.dto.response.ConsultaCanceladaResponse;
import br.edu.ifba.provapweb.domain.dto.response.ConsultaResponse;
import br.edu.ifba.provapweb.domain.exceptions.ResourceBadRequestException;
import br.edu.ifba.provapweb.domain.validador.agendamento.ValidadorAgendamentoConsultar;
import br.edu.ifba.provapweb.domain.validador.cancelamento.ValidadorCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.ifba.provapweb.domain.dto.request.ConsultaCreateRequest;
import br.edu.ifba.provapweb.domain.entity.Consulta;
import br.edu.ifba.provapweb.domain.entity.Medico;
import br.edu.ifba.provapweb.domain.entity.Paciente;
import br.edu.ifba.provapweb.repository.ConsultaRepository;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private MedicoService medicoService;

	@Autowired
	private List<ValidadorAgendamentoConsultar> validadores;

	@Autowired
	private List<ValidadorCancelamentoConsulta> validadoresCancelamento;

	public ConsultaResponse cadastrarConsulta(ConsultaCreateRequest request) {

		Paciente paciente = pacienteService.buscarPeloId(request.pacienteCpf());
		Medico medico = adicionarMedico(request);

		validadores.forEach(v -> v.validar(request));

		Consulta entity = new Consulta(null, paciente, medico, request.dataHoraConsulta(),null);
		entity = consultaRepository.save(entity);
		return new ConsultaResponse(entity);
	}

	public Page<ConsultaResponse> listarConsulta(Pageable pageable) {
		return consultaRepository.findAllByMotivoCancelamentoIsNull(pageable).map(ConsultaResponse::new);
	}

	public Page<ConsultaCanceladaResponse> listarConsultaCancelada(Pageable pageable) {
		return consultaRepository.findAllByMotivoCancelamentoIsNotNull(pageable).map(ConsultaCanceladaResponse::new);
	}

	private Medico adicionarMedico(ConsultaCreateRequest request){
		if(!request.medicoCrm().isEmpty()){
			Medico medico = medicoService.buscarPeloId(request.medicoCrm());
		}
		return medicoService.MedicoDisponivelPelaData(request.dataHoraConsulta());
	}

	public Void cancelar(ConsultaCancelamentoRequest request) {
		if (!consultaRepository.existsById(request.consultaId())) {
			throw new ResourceBadRequestException("Id da consulta informado não existe!");
		}

		validadoresCancelamento.forEach(v -> v.validar(request));

		Consulta consulta = consultaRepository.getReferenceById(request.consultaId());
		consulta.setMotivoCancelamento(request.motivo());
		consultaRepository.save(consulta);
		return null;
	}

}
