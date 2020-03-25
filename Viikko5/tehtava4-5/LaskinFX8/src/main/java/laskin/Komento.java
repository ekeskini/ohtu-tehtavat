package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {
	protected TextField tuloskentta;
	protected TextField syotekentta;
	protected Button plus;
	protected Button miinus;
	protected Button nollaa;
	protected Sovelluslogiikka sovellus;
	protected Integer alkuarvo;
	
	public Komento(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Sovelluslogiikka sovellus) {
		this.tuloskentta = tuloskentta;
		this.syotekentta = syotekentta;
		this.plus = plus;
		this.miinus = miinus;
		this.nollaa = nollaa;
		this.sovellus = sovellus;
		this.alkuarvo = 0;
	}
	public abstract void suorita();
	
	public abstract void peru();
	
	public void asetaTulos() {
		int laskunTulos = getTulos();
		tuloskentta.setText("" + laskunTulos);
		syotekentta.setText("");
	}
	
	public void nollaaJaTarkista() {
		if (getTulos() == 0) {
			nollaa.disableProperty().set(true);
		} else {
			nollaa.disableProperty().set(false);
		}
	
	}
	public Integer getTulos() {
		return sovellus.tulos();
	}
	public void talletaAlkuArvo() {
		this.alkuarvo = getTulos();
	}
	public Integer getAlkuArvo() {
		return this.alkuarvo;
	}
	
}
