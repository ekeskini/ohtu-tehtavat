package ohtu.kivipaperisakset;

import java.util.Scanner;

import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPS{
    protected KPSParempiTekoaly(Scanner scanner) {
    	this.scanner = scanner;
    	this.tuomari = new Tuomari();
    	this.pelimuoto = new TekoalyParannettu(20);
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
