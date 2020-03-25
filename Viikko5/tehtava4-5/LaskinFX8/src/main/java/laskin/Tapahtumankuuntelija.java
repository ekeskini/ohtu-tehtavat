package laskin;

import java.util.HashMap;
import java.util.Map;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {
	private Button undo;
	private Sovelluslogiikka sovellus;

	private Map<Button, Komento> komennot;
	private Komento edellinen;

	public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
		this.undo = undo;
		this.sovellus = new Sovelluslogiikka();

		komennot = new HashMap<>();
		komennot.put(plus, new Summa(tuloskentta, syotekentta, plus, miinus, nollaa, sovellus));
		komennot.put(miinus, new Erotus(tuloskentta, syotekentta, plus, miinus, nollaa, sovellus));
		komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta, plus, miinus, nollaa, sovellus));
	}

	@Override
	public void handle(Event event) {
		if ( event.getTarget() != undo ) {
			Komento komento = komennot.get((Button)event.getTarget());
			komento.suorita();
			edellinen = komento;
			this.undo.disableProperty().set(false);
		} else {
			edellinen.peru();
			this.undo.disableProperty().set(true);
			edellinen = null;
		}                  
	}

}
