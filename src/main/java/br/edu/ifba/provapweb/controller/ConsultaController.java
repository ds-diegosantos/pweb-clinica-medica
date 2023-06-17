package br.edu.ifba.provapweb.controller;

import br.edu.ifba.provapweb.domain.dto.request.ConsultaCancelamentoRequest;
import br.edu.ifba.provapweb.domain.dto.response.ConsultaResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping
	@Transactional
	public ResponseEntity<ConsultaResponse> postConsulta(@RequestBody @Valid ConsultaCreateRequest request) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(consultaService.cadastrarConsulta(request));
	}

	@DeleteMapping
	public ResponseEntity cancelar(@RequestBody @Valid ConsultaCancelamentoRequest request) {
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(consultaService.cancelar(request));
	}

}
