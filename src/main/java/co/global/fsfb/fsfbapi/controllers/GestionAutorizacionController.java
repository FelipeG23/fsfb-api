package co.global.fsfb.fsfbapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.global.fsfb.fsfbapi.dto.GestionAutorizacionCitaDto;
import co.global.fsfb.fsfbapi.services.IAutorizacionCitaService;

@RestController
@RequestMapping("gestionAutorizacion")
public class GestionAutorizacionController {

	@Autowired
	IAutorizacionCitaService iAutorizacionCitaService;

	@GetMapping("/{pacNumero}")
	public ResponseEntity<GestionAutorizacionCitaDto> getGestionAutorizacionCita(@PathVariable long pacNumero) {
		GestionAutorizacionCitaDto ca = iAutorizacionCitaService.consultarAutorizacionCita(pacNumero);
		if (ca == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(ca);

	}

}
