package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KPS{
	//Pelimuodosta riippuen peli on joko normaalilla tai parannetulla tekoälyllä toteutettu, erillistä luokkaa paremmalle tekoälylle ei tarvita
	protected KPSTekoaly(Scanner scanner, TuomariInterface tuomari, Pelimuoto pelimuoto) {
    	this.scanner = scanner;
    	this.tuomari = tuomari;
    	this.pelimuoto = pelimuoto;
    }
   
	@Override
	public String annaSiirto() {
		return pelimuoto.annaSiirto();
	}

	@Override
	public String pelimuotoSiirto() {
		String tokanSiirto = annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        return tokanSiirto;
	}
}