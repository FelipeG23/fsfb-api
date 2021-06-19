package co.global.fsfb.fsfbapi.delegate.impl;

import co.global.fsfb.fsfbapi.constants.QueryConst;
import co.global.fsfb.fsfbapi.delegate.IListaDelegate;
import co.global.fsfb.fsfbapi.dto.CitasAutorizadasDto;
import co.global.fsfb.fsfbapi.dto.EspeAndSubDTO;
import co.global.fsfb.fsfbapi.dto.ListaDto;
import co.global.fsfb.fsfbapi.services.IListaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author POLLO
 */
@Service
@Slf4j
public class ListaDelegate implements IListaDelegate {
    
    @Autowired
    private IListaService listaService;

    @Override
    public List<ListaDto> getSedes() {
        return listaService.getList(QueryConst.Listas.CONSULTAR_SEDES);
    }

    @Override
    public List<ListaDto> getEspecialidades() {
        return listaService.getList(QueryConst.Listas.CONSULTAR_ESPECIALIDADES);
    }

    @Override
    public List<ListaDto> getSubEspecialidades() {
        return listaService.getList(QueryConst.Listas.CONSULTAR_SUBESPECIALIDADES);
    }

    @Override
    public List<EspeAndSubDTO> getEspAndSub() {
        return listaService.getEspAndSub();
    }

    @Override
    public List<ListaDto> getServicios() {
        return listaService.getList(QueryConst.Listas.CONSULTAR_SERVICIOS);
    }

    @Override
    public List<ListaDto> getConvenios() {
        return listaService.getList(QueryConst.Listas.CONSULTAR_CONVENIOS);
    }
    
    @Override
    public List<ListaDto> getPolizas() {
        return listaService.getList(QueryConst.Listas.CONSULTAR_POLIZAS);
    }
    
    @Override
    public List<CitasAutorizadasDto> getCitasAutorizadas() {
        return listaService.getLista(QueryConst.Listas.CONSULTAR_CITASAUTORIZADAS);
    }

    @Override
    public List<ListaDto> getMedicos() {
        return listaService.getList(QueryConst.Listas.CONSULTAR_MEDICOS);
    }

    @Override
    public List<ListaDto> getMotivoNoAutoriza() {
        return listaService.getList(QueryConst.Listas.CONSULTAR_MOTIVOS_NO_AUTORIZA);
    }
    
    @Override
    public List<ListaDto> getUbicacionSedes() {
        return listaService.getList(QueryConst.Listas.CONSULTA_UBICACION_SEDES);
    }

    @Override
    public List<ListaDto> getMedicos2(String valor) {
        String query = QueryConst.Listas.CONSULTAR_MEDICOS_LIKE +valor+ "%' ORDER BY NOMBRE " ;
        
        return listaService.consultarMedicos(query);
    }

}
