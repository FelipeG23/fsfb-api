package co.global.fsfb.fsfbapi.controllers;

import co.global.fsfb.fsfbapi.delegate.IListaDelegate;
import co.global.fsfb.fsfbapi.dto.CitasAutorizadasDto;
import co.global.fsfb.fsfbapi.dto.ListaDto;
import co.global.fsfb.fsfbapi.models.CaCitasGestionadas;
import co.global.fsfb.fsfbapi.repositories.ICitaRepository.PruebaCita;
import co.global.fsfb.fsfbapi.services.impl.ListaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author POLLO
 */
@RestController
@RequestMapping("listas")
public class ListasController {

    private static final Logger LOG = Logger.getLogger(ListasController.class.getName());

    @Autowired
    private IListaDelegate listaDelegate;

    @Autowired
    ListaService listaService;

    @GetMapping("/citasAuto")
    public ResponseEntity<List<PruebaCita>> paginas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        //Page<PruebaCita> citas = listaService.paginas(PageRequest.of(page, size), null);
        int init = page * size;
        int end = init + size - 1;
        System.out.println("init --> " + init);
        System.out.println("end --> " + end);
        List<PruebaCita> citas = listaService.paginas(init, end);
        return new ResponseEntity<List<PruebaCita>>(citas, HttpStatus.OK);
    }

    @GetMapping("/sedes")
    public ResponseEntity<List<ListaDto>> getSedes() {
        return ResponseEntity.ok(listaDelegate.getSedes());
    }

    @GetMapping("/especialidades")
    public ResponseEntity<List<ListaDto>> getEspecialidades() {
        return ResponseEntity.ok(listaDelegate.getEspecialidades());
    }

    @GetMapping("/subespecialidades")
    public ResponseEntity<List<ListaDto>> getSubEspecialidades() {
        return ResponseEntity.ok(listaDelegate.getSubEspecialidades());
    }

    @GetMapping("/citasAutorizadas")
    public ResponseEntity<List<CitasAutorizadasDto>> getCitasAutorizadas() {
        return ResponseEntity.ok(listaDelegate.getCitasAutorizadas());
    }

    @GetMapping("/servicios")
    public ResponseEntity<List<ListaDto>> getServicios() {
        return ResponseEntity.ok(listaDelegate.getServicios());
    }

    @GetMapping("/convenios")
    public ResponseEntity<List<ListaDto>> getConvenios() {
        System.out.println("entro");
        return ResponseEntity.ok(listaDelegate.getConvenios());
    }

    @GetMapping("/polizas")
    public ResponseEntity<List<ListaDto>> getPolizas() {
        return ResponseEntity.ok(listaDelegate.getPolizas());
    }

    @PostMapping("/medicosv2")
    public ResponseEntity<List<ListaDto>> getMedicos2() {
        LOG.log(Level.INFO, "INICIO - CONSULTA MEDICOSV2");
        ResponseEntity<List<ListaDto>> lista = ResponseEntity.ok(listaDelegate.getMedicos());
         LOG.log(Level.INFO, "FIN - CONSULTA MEDICOSV2");
        return lista;
    }

    @GetMapping("/medicos")
    public ResponseEntity<List<ListaDto>> getMedicos() {

        ResponseEntity<List<ListaDto>> lista = ResponseEntity.ok(listaDelegate.getMedicos());

        return lista;
    }

    @GetMapping("/motivoNoAutoriza")
    public ResponseEntity<List<ListaDto>> getMotivoNoAutoriza() {
        return ResponseEntity.ok(listaDelegate.getMotivoNoAutoriza());
    }

    @GetMapping("/ubicacionSedes")
    public ResponseEntity<List<ListaDto>> getUbicacionSedes() {
        return ResponseEntity.ok(listaDelegate.getUbicacionSedes());
    }

}
