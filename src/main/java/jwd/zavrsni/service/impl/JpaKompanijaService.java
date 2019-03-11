package jwd.zavrsni.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.zavrsni.model.Kompanija;
import jwd.zavrsni.repository.KompanijaRepository;
import jwd.zavrsni.service.KompanijaService;

@Service
@Transactional
public class JpaKompanijaService implements KompanijaService {
	
	@Autowired
	private KompanijaRepository kompanijaRepository;

	@Override
	public List<Kompanija> findAll() {
		// TODO Auto-generated method stub
		return kompanijaRepository.findAll();
	}

	@Override
	public Kompanija findOne(Long id) {
		// TODO Auto-generated method stub
		return kompanijaRepository.findOne(id);
	}

	@Override
	public void save(Kompanija kompanija) {
		kompanijaRepository.save(kompanija);
	}

	@Override
	public void remoce(Long id) {
		kompanijaRepository.delete(id);
	}

}
