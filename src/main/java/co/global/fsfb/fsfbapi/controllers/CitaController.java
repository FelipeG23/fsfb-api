package co.global.fsfb.fsfbapi.controllers;

import co.global.fsfb.fsfbapi.delegate.ICitaDelegate;
import co.global.fsfb.fsfbapi.dto.ConsultaCitasDto;
import co.global.fsfb.fsfbapi.dto.ResultadoCitaDto;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

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
    @PostMapping("/consultarCitasPaginate/{page}")
    public ResponseEntity<List<ResultadoCitaDto>> consultarCitasPaginate(
            @RequestBody @Valid @NotNull ConsultaCitasDto consultaCitasDto,
            @PathVariable int page) {
        return ResponseEntity.ok(citaDelegate.consultarCitasPaginate(
                consultaCitasDto, page));
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
    
    @PostMapping("/porautorizarPaginate")
    
    public ResponseEntity<List<ResultadoCitaDto>> porautorizarPaginate(
            
            @RequestBody @Valid @NotNull ConsultaCitasDto consultaCitasDto) {
        
        return ResponseEntity.ok(citaDelegate.consultarCitasPorAutorizarPaginate(consultaCitasDto));
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
    
    @PostMapping("/cambioconvenio")
    public ResponseEntity cambioConvenio(@RequestBody @Valid @NotNull 
            String citaConvenio) {
        //TODO REVISAR CAMBIO CONVENIO EN citaservice
            Integer citaId = 0, IdConvenios = 0;
            try {
                JSONObject obj = new JSONObject(citaConvenio);
                citaId = obj.getInt("idCita");
                IdConvenios = obj.getInt("IdConvenios");
            } catch (Exception e) {
                System.out.println("no se pudo decodificar JSON Object: " + 
                        citaConvenio);
            }
        citaDelegate.cambioConvenio(citaId, IdConvenios );
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("citaId", citaId);
        response.put("IdConvenios", IdConvenios);
        return ResponseEntity.ok(response);
    }

}
