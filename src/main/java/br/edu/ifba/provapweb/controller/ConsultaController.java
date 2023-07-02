package br.edu.ifba.provapweb.controller;

import br.edu.ifba.provapweb.domain.dto.request.ConsultaCancelamentoRequest;
import br.edu.ifba.provapweb.domain.dto.response.ConsultaCanceladaResponse;
import br.edu.ifba.provapweb.domain.dto.response.ConsultaResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifba.provapweb.domain.dto.request.ConsultaCreateRequest;
import br.edu.ifba.provapweb.service.ConsultaService;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;

	@GetMapping
	public ResponseEntity<Page<ConsultaResponse>> listaConsulta(
			@PageableDefault(page = 0, size = 10, sort = "data", direction = Sort.Direction.DESC) Pageable pageable
	){
		return ResponseEntity.ok(consultaService.listarConsulta(pageable));
	}

	@GetMapping("cancelado")
	public ResponseEntity<Page<ConsultaCanceladaResponse>> listaConsultaCancelada(
			@PageableDefault(page = 0, size = 10, sort = "data", direction = Sort.Direction.DESC) Pageable pageable
	){
		return ResponseEntity.ok(consultaService.listarConsultaCancelada(pageable));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ConsultaResponse> postConsulta(@RequestBody @Valid ConsultaCreateRequest request) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(consultaService.cadastrarConsulta(request));
	}

	@DeleteMapping
	@Transactional
	public ResponseEntity cancelar(@RequestBody @Valid ConsultaCancelamentoRequest request) {
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(consultaService.cancelar(request));
	}

}
