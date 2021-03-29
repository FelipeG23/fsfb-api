package co.global.fsfb.fsfbapi.controllers;

import co.global.fsfb.fsfbapi.delegate.ICitaDelegate;
import co.global.fsfb.fsfbapi.dto.ConsultaCitasDto;
import co.global.fsfb.fsfbapi.dto.ResultadoCitaDto;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author POLLO
 */
@RestController
@RequestMapping("citas")
public class CitaController {

    @Autowired
    private ICitaDelegate citaDelegate;

    @PostMapping("/citas")
    public ResponseEntity<List<ResultadoCitaDto>> citas(
            @RequestBody @Valid @NotNull ConsultaCitasDto consultaCitasDto) {
        return ResponseEntity.ok(citaDelegate.consultarCitas(consultaCitasDto));
    }

    @PostMapping("/consultarCitas")
    public ResponseEntity<List<ResultadoCitaDto>> consultarCitas(
            @RequestBody @Valid @NotNull ConsultaCitasDto consultaCitasDto) {
        return ResponseEntity.ok(citaDelegate.consultarCitas(consultaCitasDto));
    }
    
    @PostMapping("/consultarCitas2")
    public ResponseEntity<List<ResultadoCitaDto>> consultarCitas2(
            @RequestBody @Valid @NotNull ConsultaCitasDto consultaCitasDto) {
        return ResponseEntity.ok(citaDelegate.consultarCitas2(consultaCitasDto));
    }

    @PostMapping("/porautorizar")
    public ResponseEntity<List<ResultadoCitaDto>> consultarCitasPorAutorizar(
            @RequestBody @Valid @NotNull ConsultaCitasDto consultaCitasDto) {
        return ResponseEntity.ok(citaDelegate.consultarCitasPorAutorizar(consultaCitasDto));
    }

    @PutMapping("/{id}/update-asistencia")
    public ResponseEntity updateAsistencia(@PathVariable("id") Long citaId, @RequestBody @NotNull String estado) {
        citaDelegate.updateAsistencia(citaId, estado);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/byId")
    public ResponseEntity updateAsistencia(@RequestBody @Valid @NotNull ConsultaCitasDto consultaCitasDto) {
        return ResponseEntity.ok(citaDelegate.consultarCitaPorId(consultaCitasDto));
    }

}
