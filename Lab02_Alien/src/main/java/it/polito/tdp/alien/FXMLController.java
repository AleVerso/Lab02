package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

import it.polito.tpd.model.AlienDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class FXMLController {

	private TreeMap<String, AlienDictionary> dizionario;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtParola;

	@FXML
	private Button btnTranslate;

	@FXML
	private Button btnReset;

	@FXML
	private TextArea txtRisultato;

	@FXML
	void doReset(ActionEvent event) {
		this.txtParola.clear();
		this.txtRisultato.clear();

	}

	@FXML
	void doTranslate(ActionEvent event) {

		String[] frase = this.txtParola.getText().split(" ");

		if (frase.length == 1) {
			String word = frase[0].toLowerCase();
			if (((this.findWildCard(word) != null))) {
				this.txtRisultato.setText(this.ricercaParola(this.findWildCard(word)).toString());
			} else if (word.matches("([a-z]*)")) {
				if (this.ricercaParola(word) != null) {
					this.txtRisultato.setText(this.ricercaParola(word).toString());
				} else {
					this.txtRisultato.setText("Parola non presente");
				}

			} else {
				this.txtRisultato.setText("Formato errato, inserire solo lettere");
				return;
			}

		}

		if (frase.length == 2) {
			String word = frase[0].toLowerCase();
			String translation = frase[1].toLowerCase();
			if (((word.matches("([a-z]*)")) && (translation.matches("([a-z]*)")))) {
				if (this.ricercaParola(word) != null) {
					this.ricercaParola(word).addTraduzione(translation);
					this.txtRisultato.setText(this.ricercaParola(word).toString());
				} else {
					AlienDictionary w = new AlienDictionary(word, translation);
					this.addParola(w);
					this.txtRisultato.setText(w.toString());
				}
			} else {
				this.txtRisultato.setText("Formato errato, inserire solo lettere");
				return;
			}
		}

		this.txtParola.clear();

	}

	public void addParola(AlienDictionary w) {
		this.dizionario.put(w.getWord(), w);

	}

	public AlienDictionary ricercaParola(String word) {

		if (dizionario.containsKey(word)) {
			return dizionario.get(word);
		} else {
			return null;
		}

	}

	public String findWildCard(String word) {

		int contatore = 0;

		if (word.contains("?")) {
			for (String w : this.dizionario.keySet()) {
				for (int i = 0; i < w.length(); i++) {
					if (w.codePointAt(i) == word.codePointAt(i)) {
						contatore++;
					}
					if (contatore == w.length() - 1) {
						return w;
					}

				}
			}
		}
		return null;
	}

	@FXML
	void initialize() {
		assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnTranslate != null : "fx:id=\"btnTranslate\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
		this.dizionario = new TreeMap<String, AlienDictionary>();

	}
}