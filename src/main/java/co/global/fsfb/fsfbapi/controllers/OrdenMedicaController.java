package co.global.fsfb.fsfbapi.controllers;

import co.global.fsfb.fsfbapi.delegate.IOrdenMedicaDelegate;
import co.global.fsfb.fsfbapi.dto.ConsultaOrdenMedicaDto;
import co.global.fsfb.fsfbapi.dto.OrdenMedicaDto;
import co.global.fsfb.fsfbapi.dto.ResultadoOrdenMedicaDto;
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
@RequestMapping("OrdenMedica")
public class OrdenMedicaController {
    
    @Autowired
    private IOrdenMedicaDelegate ordenMedicaDelegate;
    
    @GetMapping("/{pacPacRut}")
    public ResponseEntity<OrdenMedicaDto> getOrdenMedica(@PathVariable("pacPacRut") String pacPacRut) {
        return ResponseEntity.ok(ordenMedicaDelegate.getOrdenMedica(pacPacRut));
    }
    
    @PostMapping
    public ResponseEntity<OrdenMedicaDto> createOrdenMedica(@RequestBody @Valid @NotNull OrdenMedicaDto ordenMedicaDto) {
        return ResponseEntity.ok(ordenMedicaDelegate.createOrdenMedica(ordenMedicaDto));
    }

    @PostMapping("/getOrdenes")
    public ResponseEntity<List<ResultadoOrdenMedicaDto>> getOrdenes(@RequestBody @Valid @NotNull ConsultaOrdenMedicaDto consultaOrdenMedicaDto) {
        return ResponseEntity.ok(ordenMedicaDelegate.getOrdenesMedicas(consultaOrdenMedicaDto));
    }
    @PostMapping("/getOrdenesPaginate/{page}")
    public ResponseEntity<List<ResultadoOrdenMedicaDto>> 
        getOrdenesPaginate(@RequestBody @Valid @NotNull 
                ConsultaOrdenMedicaDto consultaOrdenMedicaDto, 
                @PathVariable int page) {
        return ResponseEntity.ok(ordenMedicaDelegate.getOrdenesMedicasPaginate(
                consultaOrdenMedicaDto, page));
    }
    @GetMapping("/validarGestionContinuidad/{ormIdOrdmNumero}")
    public ResponseEntity<Boolean> validarGestionContinuidadOrden(@PathVariable long ormIdOrdmNumero) {
        return ResponseEntity.ok(ordenMedicaDelegate.validarGestionContinuidad(ormIdOrdmNumero));
    }

}
