package co.global.fsfb.fsfbapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.global.fsfb.fsfbapi.models.CaGestionAutorizacion;

public interface ICaGestionAutorizacionRepository extends JpaRepository<CaGestionAutorizacion, Long> {
	
	CaGestionAutorizacion findByPomIdPrestOrdm(long pomIdPrestOrdm);

}
