package jwd.zavrsni.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.zavrsni.model.Automobil;
import jwd.zavrsni.service.AutomobilService;
import jwd.zavrsni.service.KompanijaService;
import jwd.zavrsni.web.dto.AutomobilDTO;

@Component
public class AutomobilDTOToAutomobil implements Converter<AutomobilDTO, Automobil> {
	
	@Autowired
	private AutomobilService automobilService;
	
	@Autowired
	private KompanijaService kompanijaService;

	@Override
	public Automobil convert(AutomobilDTO source) {
		Automobil automobil;
		if(source.getId()==null){
			automobil = new Automobil();
			automobil.setKompanija(
					kompanijaService.findOne(
							source.getKompanijaId()));
		}else{
			automobil = automobilService.findOne(source.getId());
		}
		
		automobil.setModel(source.getModel());
		automobil.setRegistracija(source.getRegistracija());
		automobil.setGodiste(source.getGodiste());
		automobil.setPotrosnja(source.getPotrosnja());
		automobil.setIznajmljen(source.getIznajmljen());
		
		return automobil;
	}

}
