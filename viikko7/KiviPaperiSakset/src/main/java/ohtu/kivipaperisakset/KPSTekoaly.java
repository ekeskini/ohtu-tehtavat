package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KPS{
	protected KPSTekoaly(Scanner scanner) {
    	this.scanner = scanner;
    	this.tuomari = new Tuomari();
    	this.pelimuoto = new TekoalyNormaali();
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