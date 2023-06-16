package br.edu.ifba.provapweb.controller;

import br.edu.ifba.provapweb.domain.dto.response.MedicoResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.provapweb.domain.dto.request.PacienteCreateRequest;
import br.edu.ifba.provapweb.domain.dto.request.PacienteUpdateRequest;
import br.edu.ifba.provapweb.domain.dto.response.PacienteResponse;
import br.edu.ifba.provapweb.service.PacienteService;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;

	@PostMapping
	@Transactional
	public ResponseEntity<PacienteResponse> postPaciente(@RequestBody @Valid PacienteCreateRequest request, UriComponentsBuilder uriBuilder) {

		var paciente = pacienteService.cadastrarPaciente(request);
		var uri = uriBuilder.path("/paciente/{crm}").buildAndExpand(paciente.cpf()).toUri();
		return ResponseEntity
				.created(uri)
				.body(paciente);
	}

	@GetMapping
	public ResponseEntity<Page<PacienteResponse>> getPacientes(
			@PageableDefault(page = 0, size = 10, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok(pacienteService.listarPacientes(pageable));
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<PacienteResponse> getPaciente(@PathVariable String cpf) {
		return ResponseEntity
				.ok(new PacienteResponse(pacienteService.buscarPeloId(cpf)));
	}

	@DeleteMapping("/{cpf}")
	@Transactional
	public ResponseEntity<Void> deletePaciente(@PathVariable String cpf) {
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(pacienteService.deletarPaciente(cpf));
	}

	@PutMapping("/{cpf}")
	@Transactional
	public ResponseEntity<PacienteResponse> putPaciente(@PathVariable String cpf,@RequestBody @Valid PacienteUpdateRequest request) {
		return ResponseEntity.ok(pacienteService.atualizarPaciente(cpf, request));
	}

}
