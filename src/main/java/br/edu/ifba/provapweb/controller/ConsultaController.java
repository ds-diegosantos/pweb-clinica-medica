package br.edu.ifba.provapweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.provapweb.domain.dto.request.ConsultaCreateRequest;
import br.edu.ifba.provapweb.service.ConsultaService;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;

	@PostMapping
	public ResponseEntity<Void> postConsulta(@RequestBody ConsultaCreateRequest request) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(consultaService.cadastrarConsulta(request));
	}


}
