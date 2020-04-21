package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTehdas {
	private Scanner scanner;
	
	public KPSTehdas(Scanner scanner) {
		this.scanner = scanner;
	}
	public KPS luoKPSTekoalypeli() {
		return new KPSTekoaly(scanner, new Tuomari(), new TekoalyNormaali());
	}
	public KPS luoKPSParempiTekoalypeli() {
		return new KPSTekoaly(scanner, new Tuomari(), new TekoalyParannettu(20));
	}
	public KPS luoKPSPelaajaVsPelaajapeli() {
		return new KPSPelaajaVsPelaaja(scanner);
	}
}
