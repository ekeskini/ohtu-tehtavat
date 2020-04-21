package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPS {
	protected Pelimuoto pelimuoto;
	protected Scanner scanner;
	protected TuomariInterface tuomari;
	
	public abstract String annaSiirto();
	//Tämä on pelimuodosta riippuvainen siirto. Kaksinpelissä pyydetään syötettä, tekoäly palauttaa oman siirron
	public abstract String pelimuotoSiirto();
	
	public void pelaa() {
		
        System.out.print("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = scanner.nextLine();
        String tokanSiirto = pelimuotoSiirto();


        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            System.out.print("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();

            tokanSiirto = pelimuotoSiirto();
            pelimuoto.asetaSiirto(ekanSiirto);
        }
        
        pelinLopetus();
    }	
	
	
	public static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
	
	public void pelinLopetus() {
		System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
	}
	
}


