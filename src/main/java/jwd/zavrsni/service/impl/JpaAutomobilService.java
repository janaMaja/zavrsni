package jwd.zavrsni.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.zavrsni.model.Automobil;
import jwd.zavrsni.repository.AutomobilRepository;
import jwd.zavrsni.service.AutomobilService;

@Service
@Transactional
public class JpaAutomobilService implements AutomobilService {
	
	@Autowired
	private  AutomobilRepository automobilRepository;

//	@Override
//	public List<Automobil> findAll() {
//		// TODO Auto-generated method stub
//		return automobilRepository.findAll();
//	}

	@Override
	public Automobil findOne(Long id) {
		// TODO Auto-generated method stub
		return automobilRepository.findOne(id);
	}

	@Override
	public void save(Automobil automobil) {
		// TODO Auto-generated method stub
		automobilRepository.save(automobil);
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		automobilRepository.delete(id);
	}

	@Override
	public List<Automobil> findByKompanijaId(Long kompanijaId) {
		// TODO Auto-generated method stub
		return automobilRepository.findByKompanijaId(kompanijaId);
	}

	@Override
	public Page<Automobil> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return automobilRepository.findAll(new PageRequest(pageNum, 3));
	}

	@Override
	public Page<Automobil> pretraga(String model, Integer godiste, Double potrosnja, int page) {
		// TODO Auto-generated method stub
		if(model != null) {
			model = "%" + model + "%";
		}
		return automobilRepository.pretraga(model, godiste, potrosnja, new PageRequest(page, 3));
	}

}
