package jwd.zavrsni.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.zavrsni.model.Najam;
import jwd.zavrsni.web.dto.NajamDTO;

@Component
public class NajamToNajamDTO implements Converter<Najam, NajamDTO>{

	@Override
	public NajamDTO convert(Najam najam) {
		NajamDTO najamDTO = new NajamDTO();
		najamDTO.setId(najam.getId());
		return najamDTO;
	}

}
