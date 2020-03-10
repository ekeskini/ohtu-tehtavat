
package ohtu.intjoukkosovellus;

public class IntJoukko {


	private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
	private int[] luvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
	private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

	public IntJoukko() {
		luvut = new int[5];
		for (int i = 0; i < luvut.length; i++) {
			luvut[i] = 0;
		}
		alkioidenLkm = 0;
		this.kasvatuskoko = 5;
	}

	public IntJoukko(int kapasiteetti) {
		if (kapasiteetti < 0) {
			return;
		}
		luvut = new int[kapasiteetti];
		for (int i = 0; i < luvut.length; i++) {
			luvut[i] = 0;
		}
		alkioidenLkm = 0;
		this.kasvatuskoko = 5;

	}


	public IntJoukko(int kapasiteetti, int kasvatuskoko) {
		if (kapasiteetti < 0) {
			System.out.println("Kapasiteetin tulee olla positiivinen");
		}
		if (kasvatuskoko < 0) {
			System.out.println("Kasvatuskoon tulee olla positiivinen");
		}
		luvut = new int[kapasiteetti];
		for (int i = 0; i < luvut.length; i++) {
			luvut[i] = 0;
		}
		alkioidenLkm = 0;
		this.kasvatuskoko = kasvatuskoko;

	}

	public boolean lisaa(int luku) {
		if (alkioidenLkm == 0) {
			luvut[0] = luku;
			alkioidenLkm++;
			return true;
		} 
		if (!tarkistaKuuluukoLukuJoukkoon(luku)) {
			luvut[alkioidenLkm] = luku;
			alkioidenLkm++;
			if (alkioidenLkm % luvut.length == 0) {
				int[] taulukkoOld = luvut;
				luvut = new int[alkioidenLkm + kasvatuskoko];
				kopioiTaulukko(taulukkoOld, luvut);
			}
			return true;
		}
		return false;
	}
	public int haeIndeksi(int luku) {
		int index = -1;
		for (int i = 0; i < alkioidenLkm; i++) {
			if (luku == luvut[i]) {
				index = i;
				break;
			}
		}
		return index;
	}
	public boolean tarkistaKuuluukoLukuJoukkoon(int luku) {
		if (haeIndeksi(luku) == -1) {
			return false;
		}
		return true;
	}

	public boolean poista(int luku) {
		if (!tarkistaKuuluukoLukuJoukkoon(luku)) {
			return false;
		}
		int index = haeIndeksi(luku);
		int apumuuttuja = 0;


		for (int i = index; i < alkioidenLkm - 1; i++) {
			apumuuttuja = luvut[i];
			luvut[i] = luvut[i + 1];
			luvut[i + 1] = apumuuttuja;
		}
		alkioidenLkm--;
		return true;
	}

	private void kopioiTaulukko(int[] vanha, int[] uusi) {
		for (int i = 0; i < vanha.length; i++) {
			uusi[i] = vanha[i];
		}

	}

	public int getAlkioidenLkm() {
		return alkioidenLkm;
	}


	@Override
	public String toString() {
		if (alkioidenLkm == 0) {
			return "{}";
		} else if (alkioidenLkm == 1) {
			return "{" + luvut[0] + "}";
		} else {
			String taulukkoMerkkijonona = "{";
			for (int i = 0; i < alkioidenLkm - 1; i++) {
				taulukkoMerkkijonona += luvut[i];
				taulukkoMerkkijonona += ", ";
			}
			taulukkoMerkkijonona += luvut[alkioidenLkm - 1];
			taulukkoMerkkijonona += "}";
			return taulukkoMerkkijonona;
		}
	}

	public int[] toIntArray() {
		int[] taulu = new int[alkioidenLkm];
		for (int i = 0; i < taulu.length; i++) {
			taulu[i] = luvut[i];
		}
		return taulu;
	}


	public static IntJoukko yhdistaJoukot(IntJoukko alfa, IntJoukko beta) {
		IntJoukko uusiJoukko = new IntJoukko();
		int[] alfataulu = alfa.toIntArray();
		int[] betataulu = beta.toIntArray();
		for (int i = 0; i < alfataulu.length; i++) {
			uusiJoukko.lisaa(alfataulu[i]);
		}
		for (int i = 0; i < betataulu.length; i++) {
			uusiJoukko.lisaa(betataulu[i]);
		}
		return uusiJoukko;
	}

	public static IntJoukko leikkaus(IntJoukko alfa, IntJoukko beta) {
		IntJoukko uusiJoukko = new IntJoukko();
		int[] alfataulu = alfa.toIntArray();
		int[] betataulu = beta.toIntArray();
		for (int i = 0; i < alfataulu.length; i++) {
			for (int j = 0; j < betataulu.length; j++) {
				if (alfataulu[i] == betataulu[j]) {
					uusiJoukko.lisaa(betataulu[j]);
				}
			}
		}
		return uusiJoukko;

	}

	public static IntJoukko erotus (IntJoukko alfa, IntJoukko beta) {
		IntJoukko uusiJoukko = new IntJoukko();
		int[] alfataulu = alfa.toIntArray();
		int[] betataulu = beta.toIntArray();
		for (int i = 0; i < alfataulu.length; i++) {
			uusiJoukko.lisaa(alfataulu[i]);
		}
		for (int i = 0; i < betataulu.length; i++) {
			uusiJoukko.poista(betataulu[i]);
		}

		return uusiJoukko;
	}

}
