package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    	KPSTehdas peliTehdas = new KPSTehdas(scanner);
    	
        while (true) {
            System.out.println(valintaOhje());
            
            String vastaus = scanner.nextLine();
            System.out.println(ohje());
            if (vastaus.endsWith("a")) {	
                KPS kaksinpeli = peliTehdas.luoKPSPelaajaVsPelaajapeli();
                kaksinpeli.pelaa();
            } else if (vastaus.endsWith("b")) {    
                KPS yksinpeli = peliTehdas.luoKPSTekoalypeli();
                yksinpeli.pelaa();
            } else if (vastaus.endsWith("c")) {
                 KPS pahaYksinpeli = peliTehdas.luoKPSParempiTekoalypeli();
                pahaYksinpeli.pelaa();
            } else {
                break;
            }

        }

    }
    private static String valintaOhje() {
		return "\nValitse pelataanko"
                + "\n (a) ihmistä vastaan "
                + "\n (b) tekoälyä vastaan"
                + "\n (c) parannettua tekoälyä vastaan"
                + "\nmuilla valinnoilla lopetataan";
		
	}
	public static String ohje() {
    	return "peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s";		
    }
}
