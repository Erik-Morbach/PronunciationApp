package br.ifsul.controller;

import java.util.List;
import java.util.Random;

import br.ifsul.repository.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifsul.comparator.WordComparator;
import br.ifsul.model.Word;
import br.ifsul.model.WordSimilarity;
import br.ifsul.repository.WordRepository;
import br.ifsul.repository.WordSimilarityRepository;

@Service
public class Controller {
	static private final Random randomGenerator = new Random();
	static private final WordComparator comparator = new WordComparator();

	@Autowired
	private WordRepository wordRepository;
	@SuppressWarnings("unused")
	@Autowired
	private PhraseRepository phraseRepository;
	@Autowired
	private WordSimilarityRepository wordSimilarityRepository;
	
	public List<Word> findAllWords(){
		return this.wordRepository.findAll();
	}

	public Word registerWord(Word w) {
		// Realizar operação em tabela de similaridades
		List<Word> response = this.wordRepository.findAll();
		comparator.setSource(w.getText());
		response.sort(comparator);
		w = this.wordRepository.save(w);
		for(int i=0;i<Math.min(3,response.size());i++) {
			WordSimilarity newSimilarity = new WordSimilarity(w,response.get(i),
															  comparator.computeSimilarity(response.get(i).getText()));
			this.wordSimilarityRepository.save(newSimilarity);
		}
		return w;
	}

	public Word getRandom() {
		Long maxIndex = this.wordRepository.findMaxIndex();
		Long wordIndex = randomGenerator.nextLong(maxIndex+1);
		return this.wordRepository.findWithIndexGraterThan(wordIndex);
	}
	
	public List<Word> findSimilarWords(Word w, int quantity){
		if(w==null) return null;
		List<Long> ids = this.wordSimilarityRepository.findSimilarWords(w.getId(), quantity);
		return this.wordRepository.findAllById(ids);
	}
	public List<Word> findOccurrence(String w){
		return this.wordRepository.findOccurrence(w);
	}
}
