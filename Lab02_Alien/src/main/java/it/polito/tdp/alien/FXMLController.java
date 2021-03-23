package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tpd.model.AlienDictionary;
import it.polito.tpd.model.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class FXMLController {

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

	AlienDictionary dizionario;

	@FXML
	void doReset(ActionEvent event) {
		this.txtParola.clear();
		this.txtRisultato.clear();

	}

	@FXML
	void doTranslate(ActionEvent event) {

		String[] frase = this.txtParola.getText().split(" ");
		String alienWord = frase[0];
		String translation = frase[1];
		String risultato = "";
		Word parola = new Word(alienWord, translation);

		if (this.dizionario.getElenco().isEmpty()) {
			dizionario.addParola(parola);
		}

		for (Word w : this.dizionario.getElenco()) {
			if (w.getParola().equals(alienWord)) {
				risultato += w.toString() + "\n";
			} else {
				dizionario.addParola(parola);
				risultato += parola.toString()+"\n";
			}
		}
        this.txtParola.clear();
		this.txtRisultato.setText(risultato);

	}

	@FXML
	void initialize() {
		assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnTranslate != null : "fx:id=\"btnTranslate\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
		dizionario = new AlienDictionary();

	}
}