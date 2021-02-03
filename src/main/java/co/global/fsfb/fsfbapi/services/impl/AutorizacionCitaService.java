package co.global.fsfb.fsfbapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.global.fsfb.fsfbapi.dto.GestionAutorizacionCitaDto;
import co.global.fsfb.fsfbapi.models.CaGestionAutorizacionCitas;
import co.global.fsfb.fsfbapi.repositories.IAutorizacionCitaRepository;
import co.global.fsfb.fsfbapi.services.IAutorizacionCitaService;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AutorizacionCitaService implements IAutorizacionCitaService {
	
	@Autowired
	IAutorizacionCitaRepository iAutorizacionCitaRepository;

	@Override
	public GestionAutorizacionCitaDto consultarAutorizacionCita(long idCita) {
		// TODO Auto-generated method stub
		CaGestionAutorizacionCitas ca = iAutorizacionCitaRepository.findByCgIdCitaNumero(idCita);
		if(ca != null) {
			GestionAutorizacionCitaDto g = new GestionAutorizacionCitaDto();
			g.setCgIdCitaNumero(ca.getCgIdCitaNumero());
			g.setGauAutorizaServ(ca.getGauAutorizaServ());
			g.setGauCodigoAutorizacion(ca.getGauCodigoAutorizacion());
			g.setGauCostoConvenio(ca.getGauCostoConvenio());
			g.setGauCostoPac(ca.getGauCostoPac());
			g.setGauFechaAutorizacion(ca.getGauFechaAutorizacion());
			g.setGauFechaProceso(ca.getGauFechaProceso());
			g.setGauFechaVencimientoAutorizacion(ca.getGauFechaVencimientoAutorizacion());
			g.setGauNombreAutorizador(ca.getGauNombreAutorizador());
			g.setGauTelefonoAutorizador(ca.getGauTelefonoAutorizador());
			g.setGauValorPrestacion(ca.getGauValorPrestacion());
			g.setGauVigenciaAutorizacion(ca.getGauVigenciaAutorizacion());
			g.setOgaDescripcion(ca.getOgaDescripcion());
			return g;
		}
		return null;
	}

}
