package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {
	public Summa(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Sovelluslogiikka sovellus) {
		super(tuloskentta, syotekentta, plus, miinus, nollaa, sovellus);
	}
	@Override
	public void suorita() {
		super.talletaAlkuArvo();
		sovellus.plus(Integer.valueOf(syotekentta.getText()));
		super.asetaTulos();
		super.nollaaJaTarkista();
	}
	@Override
	public void peru() {
		sovellus.setTulos(super.getAlkuArvo());
		super.asetaTulos();
		super.nollaaJaTarkista();
	}

}
