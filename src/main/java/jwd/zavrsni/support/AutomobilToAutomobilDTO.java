package jwd.zavrsni.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.zavrsni.model.Automobil;
import jwd.zavrsni.web.dto.AutomobilDTO;

@Component
public class AutomobilToAutomobilDTO implements Converter<Automobil, AutomobilDTO> {

	@Override
	public AutomobilDTO convert(Automobil source) {
		// TODO Auto-generated method stub
		AutomobilDTO automobilDTO = new AutomobilDTO();
		
		automobilDTO.setId(source.getId());
		automobilDTO.setModel(source.getModel());
		automobilDTO.setRegistracija(source.getRegistracija());
		automobilDTO.setGodiste(source.getGodiste());
		automobilDTO.setPotrosnja(source.getPotrosnja());
		automobilDTO.setIznajmljen(source.getIznajmljen());
		automobilDTO.setKompanijaId(source.getKompanija().getId());
		automobilDTO.setKompanijaNaziv(source.getKompanija().getNaziv());
		
		return automobilDTO;
	}
	
	public List<AutomobilDTO> convert(List<Automobil> automobili){
		List<AutomobilDTO> ret = new ArrayList<>();
		
		for(Automobil a : automobili) {
			ret.add(convert(a));
		}
		
		return ret;
	}
	
}
