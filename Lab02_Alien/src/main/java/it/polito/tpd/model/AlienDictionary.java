package it.polito.tpd.model;

import java.util.ArrayList;
import java.util.List;

public class AlienDictionary {

	String word;

	List<String> parole = new ArrayList<>();

	public AlienDictionary(String word, String traduzione) {
		this.word = word;
		this.parole.add(traduzione);

	}

	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}

	public void addTraduzione(String traduzione) {
		if (!this.parole.contains(traduzione))
			this.parole.add(traduzione);
	}

	@Override
	public String toString() {
		String s = "La traduzione di " + word +" è: ";
		for (String t : parole)
			s += t +" " ;
		return s;
	}

}
