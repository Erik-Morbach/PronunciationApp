package br.ifsul.services;

import br.ifsul.repositories.WordRepository;

public class WordService {

	private WordRepository wordRepository;
	
	public WordService(WordRepository wordRepository) {
		super();
		this.wordRepository = wordRepository;
	}

	public String findAll(String text) {
		if(text == null) {
			text = "";
		}
		
		return "";
	}
}
