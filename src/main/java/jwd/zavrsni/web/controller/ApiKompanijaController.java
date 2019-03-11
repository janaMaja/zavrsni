package jwd.zavrsni.web.controller;

import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.zavrsni.model.Automobil;
import jwd.zavrsni.model.Kompanija;
import jwd.zavrsni.service.AutomobilService;
import jwd.zavrsni.service.KompanijaService;
import jwd.zavrsni.support.AutomobilToAutomobilDTO;
import jwd.zavrsni.support.KompanijaToKompanijaDTO;
import jwd.zavrsni.web.dto.AutomobilDTO;
import jwd.zavrsni.web.dto.KompanijaDTO;

@RestController
@RequestMapping(value="/api/kompanije")
public class ApiKompanijaController {
	
	@Autowired
	private KompanijaService kompanijaService;
	
	@Autowired
	private AutomobilService automobilService;
	
	@Autowired
	private KompanijaToKompanijaDTO toDTO;
	
	@Autowired
	private AutomobilToAutomobilDTO toAutomobilDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<KompanijaDTO>> get(){
		return new ResponseEntity<>(toDTO.convert(kompanijaService.findAll()), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<KompanijaDTO> get(@PathVariable Long id){
		Kompanija kompanija = kompanijaService.findOne(id);
		if(kompanija == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<KompanijaDTO>(toDTO.convert(kompanija), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{kompanijaId}/automobili")
	public ResponseEntity<List<AutomobilDTO>> getn(@PathVariable Long kompanijaId){
		List<Automobil> automobili = automobilService.findByKompanijaId(kompanijaId);
		if(automobili == null) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toAutomobilDTO.convert(automobili), HttpStatus.OK);
	}
}
