package jwd.zavrsni.service;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.zavrsni.model.Automobil;

public interface AutomobilService {
	Page<Automobil> findAll(int pageNum);
	Automobil findOne(Long id);
	void save(Automobil automobil);
	void remove(Long id);
	
	List<Automobil> findByKompanijaId(Long kompanijaId);
	
	Page<Automobil> pretraga(
			@Param("model") String model, 
			@Param("godiste") Integer godiste,
			@Param("potrosnja") Double potrosnja, 
			int page);
}
