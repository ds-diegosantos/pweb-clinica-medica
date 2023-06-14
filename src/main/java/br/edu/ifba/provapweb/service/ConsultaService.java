package br.edu.ifba.provapweb.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifba.provapweb.domain.dto.request.ConsultaCreateRequest;
import br.edu.ifba.provapweb.domain.entity.Consulta;
import br.edu.ifba.provapweb.domain.entity.Medico;
import br.edu.ifba.provapweb.domain.entity.Paciente;
import br.edu.ifba.provapweb.repository.ConsultaRepository;
import br.edu.ifba.provapweb.repository.MedicoRepository;
import br.edu.ifba.provapweb.repository.PacienteRepository;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	public Void cadastrarConsulta(ConsultaCreateRequest request) {

		LocalDateTime time = request.dataHoraConsulta();
		if (!isValidTime(time) || !isValidDay(time)) {
			return null;
		}

		Optional<Paciente> optionalPaciente = pacienteRepository.findById(request.pacienteCpf());
		if (optionalPaciente.isEmpty() || !optionalPaciente
				.get()
				.isAtivo()) {
			return null;
		}
		Paciente paciente = optionalPaciente.get();

		Optional<Medico> optionalMedico = medicoRepository.findById(request.medicoCrm());
		if (optionalMedico.isEmpty() || !optionalMedico
				.get()
				.isAtivo()) {
			return null;
		}
		Medico medico = optionalMedico.get();

		Consulta entity = new Consulta(null, paciente, medico, time);
		consultaRepository.save(entity);
		return null;
	}

	private boolean isValidDay(LocalDateTime time) {
		return time.getDayOfWeek() == DayOfWeek.SUNDAY;
	}

	private boolean isValidTime(LocalDateTime request) {
		return request.getHour() < 7 ||
				request.getHour() > 19;
	}
}
