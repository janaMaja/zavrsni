package jwd.zavrsni.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.html.HTMLTitleElement;

import jwd.zavrsni.model.Automobil;
import jwd.zavrsni.model.Najam;
import jwd.zavrsni.service.AutomobilService;
import jwd.zavrsni.service.NajamService;
import jwd.zavrsni.support.AutomobilDTOToAutomobil;
import jwd.zavrsni.support.AutomobilToAutomobilDTO;
import jwd.zavrsni.support.NajamToNajamDTO;
import jwd.zavrsni.web.dto.AutomobilDTO;
import jwd.zavrsni.web.dto.NajamDTO;

@RestController
@RequestMapping("/api/automobili")
public class ApiAutomobilController {
	
	@Autowired
	private AutomobilService automobilService;
	
	@Autowired
	private AutomobilToAutomobilDTO toAutomobilDTO;
	
	@Autowired
	private AutomobilDTOToAutomobil toAutomobil;
	
	@Autowired 
	private NajamService najamService;
	
	@Autowired
	private NajamToNajamDTO toNajamDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<AutomobilDTO>> get(
			@RequestParam(required=false) String model,
			@RequestParam(required=false) Integer godiste,
			@RequestParam(required=false) Double potrosnja,
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Automobil> automobilPages;
		
		if(model != null || godiste != null || potrosnja != null) {
			automobilPages = automobilService.pretraga(model, godiste, potrosnja, pageNum);
		}
		else {
			automobilPages = automobilService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(automobilPages.getTotalPages()) );
		
		return new ResponseEntity<>(toAutomobilDTO.convert(automobilPages.getContent()), headers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}")
	public ResponseEntity<AutomobilDTO> get(@PathVariable Long id){
		Automobil automobil = automobilService.findOne(id);
		
		if(automobil==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toAutomobilDTO.convert(automobil), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<AutomobilDTO> delete(@PathVariable Long id){
		automobilService.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<AutomobilDTO> add(@Validated @RequestBody AutomobilDTO noviAutomobil){
		Automobil automobil = toAutomobil.convert(noviAutomobil);
		automobilService.save(automobil);
		
		return new ResponseEntity<>(toAutomobilDTO.convert(automobil), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<AutomobilDTO> edit(@PathVariable Long id, @RequestBody AutomobilDTO izmenjenAutomobil){
		if(!id.equals(izmenjenAutomobil.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Automobil automobil = toAutomobil.convert(izmenjenAutomobil);
		automobilService.save(automobil);
		
		return new ResponseEntity<>(toAutomobilDTO.convert(automobil), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}/najam")
	public ResponseEntity<NajamDTO> najam(@PathVariable Long id){
		
		Najam najam = najamService.iznajmiAuto(id);
		if(najam != null) {
		return new ResponseEntity<NajamDTO>(toNajamDTO.convert(najam), HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ExceptionHandler
	public ResponseEntity<Void> validationHandler(
					DataIntegrityViolationException e){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
