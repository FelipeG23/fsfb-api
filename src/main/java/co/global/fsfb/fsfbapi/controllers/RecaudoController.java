/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.global.fsfb.fsfbapi.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.global.fsfb.fsfbapi.delegate.IRecaudoDelegate;
import co.global.fsfb.fsfbapi.dto.ConsultaValoresDTO;
import co.global.fsfb.fsfbapi.dto.ResultadoValoresDTO;

/**
 *
 * @author GIOVANNI-PC
 */
@RestController
@RequestMapping("recaudo")
public class RecaudoController {

	@Autowired
	private IRecaudoDelegate iRecaudoDelegate;

	@PostMapping("/consultaValoresPrestaciones")
	public ResponseEntity<?> consultaValoresPrestaciones(
			@RequestBody @Valid @NotNull ConsultaValoresDTO consultaValoresDTO) {

		if (consultaValoresDTO.getAnio() == null) {
			return ResponseEntity.badRequest().body("Campo anio obligatorio");
		}
		if (consultaValoresDTO.getCodigoConvenio() == null || consultaValoresDTO.getCodigoConvenio().isEmpty()) {
			return ResponseEntity.badRequest().body("Campo codigoConvenio obligatorio");
		}

		if (consultaValoresDTO.getCodigoPrestacion() == null || consultaValoresDTO.getCodigoPrestacion().isEmpty()) {
			return ResponseEntity.badRequest().body("Campo codigoPrestacion obligatorio");
		}

		List<ResultadoValoresDTO> resultadoValoresDTOs = iRecaudoDelegate
				.consultaValoresPrestaciones(consultaValoresDTO);

		if (resultadoValoresDTOs != null && !resultadoValoresDTOs.isEmpty()) {
			return ResponseEntity.ok(resultadoValoresDTOs);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
}
