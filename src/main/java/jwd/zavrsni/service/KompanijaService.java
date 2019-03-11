package jwd.zavrsni.service;

import java.util.List;

import jwd.zavrsni.model.Kompanija;

public interface KompanijaService {
	List<Kompanija> findAll();
	Kompanija findOne(Long id);
	void save(Kompanija kompanija);
	void remoce(Long id);
}
