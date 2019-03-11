package jwd.zavrsni;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.zavrsni.model.Automobil;
import jwd.zavrsni.model.Kompanija;
import jwd.zavrsni.service.AutomobilService;
import jwd.zavrsni.service.KompanijaService;

@Component
public class TestData {

	@Autowired
	private KompanijaService kompanijaService;
	
	@Autowired
	private AutomobilService automobilService;
	
	@PostConstruct
	public void init() {
	
		Kompanija kompanija1 = new Kompanija();
		kompanija1.setNaziv("SuRent");
		kompanija1.setAdresa("Adresa SuRent");
		kompanija1.setTelefon("066/2545-545");
		kompanijaService.save(kompanija1);
		
		Kompanija kompanija2 = new Kompanija();
		kompanija2.setNaziv("NsRent");
		kompanija2.setAdresa("Adresa NsRent");
		kompanija2.setTelefon("066/2545-775");
		kompanijaService.save(kompanija2);
		
		Automobil auto1 = new Automobil();
		auto1.setModel("Nissan Prairie");
		auto1.setRegistracija("SU-123-fd");
		auto1.setGodiste(1991);
		auto1.setPotrosnja(10.00);
		auto1.setKompanija(kompanija1);
		automobilService.save(auto1);
		
		Automobil auto2 = new Automobil();
		auto2.setModel("Nissan Micra");
		auto2.setRegistracija("Ns-021-nm");
		auto2.setGodiste(2007);
		auto2.setPotrosnja(8.00);
		auto2.setKompanija(kompanija2);
		automobilService.save(auto2);
		
		Automobil auto3 = new Automobil();
		auto3.setModel("Tesla Roadster");
		auto3.setRegistracija("SU-111-aa");
		auto3.setGodiste(1991);
		auto3.setPotrosnja(0.00);
		auto3.setKompanija(kompanija1);
		automobilService.save(auto3);
	}
}
