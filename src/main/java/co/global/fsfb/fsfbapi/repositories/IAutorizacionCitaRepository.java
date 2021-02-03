package co.global.fsfb.fsfbapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.global.fsfb.fsfbapi.models.CaGestionAutorizacionCitas;

public interface IAutorizacionCitaRepository extends JpaRepository<CaGestionAutorizacionCitas, Long> {
	
	CaGestionAutorizacionCitas findByCgIdCitaNumero(long idCita);

}
