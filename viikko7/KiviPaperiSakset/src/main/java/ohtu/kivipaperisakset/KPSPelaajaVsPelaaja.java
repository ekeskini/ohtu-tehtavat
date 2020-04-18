package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KPS{

    protected KPSPelaajaVsPelaaja(Scanner scanner) {
    	this.scanner = scanner;
    	this.pelimuoto = new Kaksinpeli();
    	this.tuomari = new Tuomari();
    }

	@Override
	public String annaSiirto() {
		return scanner.nextLine();
	}

	@Override
	public String pelimuotoSiirto() {
		System.out.print("Toisen pelaajan siirto: ");
        return annaSiirto();
	}
}