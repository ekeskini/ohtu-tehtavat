package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTehdas {
	private Scanner scanner;
	
	public KPSTehdas(Scanner scanner) {
		this.scanner = scanner;
	}
	public KPS luoKPSTekoalypeli() {
		return new KPSTekoaly(scanner);
	}
	public KPS luoKPSParempiTekoalypeli() {
		return new KPSParempiTekoaly(scanner);
	}
	public KPS luoKPSPelaajaVsPelaajapeli() {
		return new KPSPelaajaVsPelaaja(scanner);
	}
}
