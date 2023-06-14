package br.edu.ifba.provapweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.edu.ifba.provapweb.domain.dto.request.MedicoCreateRequest;
import br.edu.ifba.provapweb.domain.dto.request.MedicoUpdateRequest;
import br.edu.ifba.provapweb.domain.dto.response.MedicoResponse;
import br.edu.ifba.provapweb.service.MedicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/medico")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;

	@PostMapping
	public ResponseEntity<Void> postMedico(@RequestBody @Valid MedicoCreateRequest dto) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(medicoService.cadastrarMedico(dto));
	}

	@GetMapping
	public ResponseEntity<Page<MedicoResponse>> getMedicos(
			@PageableDefault(sort = {"nome"}, page = 0, size = 10) Pageable pageable) {
		return ResponseEntity.ok(medicoService.listarMedicos(pageable));
	}

	@DeleteMapping("/{crm}")
	@Transactional
	public ResponseEntity<Void> deleteMedico(@PathVariable String crm) {
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(medicoService.deletarMedico(crm));
	}

	@PutMapping("/{crm}")
	@Transactional
	public ResponseEntity<MedicoResponse> putMedico(@PathVariable String crm,
																									@RequestBody MedicoUpdateRequest request) {
		return ResponseEntity.ok(medicoService.atualizarMedico(crm.toUpperCase(), request));
	}
}
