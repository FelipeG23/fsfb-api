package co.global.fsfb.fsfbapi.services;

import co.global.fsfb.fsfbapi.dto.CitasAutorizadasDto;
import co.global.fsfb.fsfbapi.dto.ListaDto;

import java.util.List;

/**
 *
 * @author POLLO
 */
public interface IListaService {

    List<ListaDto> getList(String query);
    
    List<ListaDto> consultarMedicos(String query);
    
    List<CitasAutorizadasDto> getLista(String query);

    
}
