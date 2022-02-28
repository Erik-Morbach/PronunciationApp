package br.ifsul.controller;

import java.util.List;
import java.util.Random;

import br.ifsul.repository.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifsul.comparator.WordComparator;
import br.ifsul.model.Word;
import br.ifsul.repository.WordRepository;

@Service
public class Controller {
	static private final Random randomGenerator = new Random();
	static private final WordComparator comparator = new WordComparator();

	@Autowired
	private WordRepository wordRepository;
	@Autowired
	private PhraseRepository phraseRepository;

	public Word save(Word w) {
		// Realizar operação em tabela de similaridades
		return this.wordRepository.save(w);
	}

	public Word getRandom() {
		Long maxIndex = this.wordRepository.findMaxIndex();
		Long wordIndex = randomGenerator.nextLong(maxIndex+1);
		return this.wordRepository.findWithIndexGraterThan(wordIndex);
	}
	
	public List<Word> getSimilar(Word w, int quantity){
		if(w==null) return null;
		List<Word> response = (List<Word>) this.wordRepository.findAll();
		comparator.setSource(w.getText());
		response.sort(comparator);
		quantity = Math.min(quantity, response.size());
		return response.subList(0, quantity);
	}
	public List<Word> findOccurrence(String w){
		return this.wordRepository.findOccurrence(w);
	}
}
