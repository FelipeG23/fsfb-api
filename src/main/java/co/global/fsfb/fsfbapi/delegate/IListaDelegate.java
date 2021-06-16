package co.global.fsfb.fsfbapi.delegate;

import co.global.fsfb.fsfbapi.dto.CitasAutorizadasDto;
import co.global.fsfb.fsfbapi.dto.EspeAndSubDTO;
import co.global.fsfb.fsfbapi.dto.ListaDto;

import java.util.List;

/**
 *
 * @author POLLO
 */
public interface IListaDelegate {

    List<ListaDto> getSedes();

    List<ListaDto> getEspecialidades();

    List<ListaDto> getSubEspecialidades();

    List<EspeAndSubDTO> getEspAndSub();

    List<ListaDto> getServicios();

    List<ListaDto> getConvenios();

    List<ListaDto> getPolizas();

    List<ListaDto> getMedicos();

    List<ListaDto> getMedicos2(String valor);

    List<CitasAutorizadasDto> getCitasAutorizadas();

    List<ListaDto> getMotivoNoAutoriza();

    List<ListaDto> getUbicacionSedes();

}
