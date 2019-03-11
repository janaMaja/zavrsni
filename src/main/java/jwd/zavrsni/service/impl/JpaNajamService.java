package jwd.zavrsni.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.zavrsni.model.Automobil;
import jwd.zavrsni.model.Najam;
import jwd.zavrsni.repository.AutomobilRepository;
import jwd.zavrsni.repository.NajamRepository;
import jwd.zavrsni.service.NajamService;
import jwd.zavrsni.support.NajamToNajamDTO;

@Service
@Transactional
public class JpaNajamService implements NajamService{
	
	@Autowired
	private NajamRepository najamRepository;
	@Autowired
	private AutomobilRepository automobilRepository;

	@Override
	public Najam iznajmiAuto(Long automobilId) {
		// TODO Auto-generated method stub
		if(automobilId == null) {
			throw new IllegalArgumentException("Id automobila ne smije biti null");
		}
		Automobil automobil = automobilRepository.findOne(automobilId);
		if(automobil == null) {
			throw new IllegalArgumentException("Ne postoji automobil sa prosljedjenim id");
		}
		
		Najam najam = new Najam();
		if(!automobil.getIznajmljen()) {
			automobil.setIznajmljen(true);
			najam.setAutomobil(automobil);
			najamRepository.save(najam);
			automobilRepository.save(automobil);
			return najam;
		}
		return null;
	}

}
