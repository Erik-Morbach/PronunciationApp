package br.ifsul.controller;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import br.ifsul.model.Phrase;
import br.ifsul.repository.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	@Autowired
	private PhraseRepository phraseRepository;
	@Autowired
	private WordSimilarityRepository wordSimilarityRepository;

	// USAR NA TELA CADASTRO
	public Word registerWord(Word w) {
		List<Word> response = this.wordRepository.findAll();
		comparator.setSource(w.getText());
		response.sort(comparator);
		Word newWord = this.wordRepository.save(w);
		for(int i=0;i<Math.min(3,response.size());i++) {
			Integer similarity = comparator.computeSimilarity(response.get(i).getText());
			WordSimilarity newSimilarity = new WordSimilarity(w,response.get(i), similarity);
			this.wordSimilarityRepository.save(newSimilarity);
		}
		return this.populateWord(newWord);
	}

	public Phrase registerPhrase(Phrase phrase){
		return this.phraseRepository.save(phrase);
	}
	List<Word> findSimilarWords(Word word){
		Pageable page = PageRequest.of(0,3);
		List<Long> lis=  (this.wordSimilarityRepository.findSimilarWords(word,page));
		return this.wordRepository.findAllById(lis);
	}
	
	public Word populateWord(Word w) {
		if(w==null) return null;
		w.setPhrases(this.phraseRepository.findByWordId(w.getId()));
		w.setSimilarWords(this.findSimilarWords(w));
		return w;
	}

	// USAR NA TELA DE PALAVRA ALEATÓRIA
	public Word getRandomWord() {
		Long maxIndex = this.wordRepository.findMaxIndex();
		Long wordIndex = randomGenerator.nextLong(maxIndex+1);
		Word w = this.wordRepository.findWithIndexGraterThan(wordIndex);
		return this.populateWord(w);
	}

	// USAR NA TELA DE PESQUISA
	public List<Word> searchWords(String text){
		if(text == null)
			return null;
		return this.wordRepository.searchByText(text);
	}
	
	// USAR NA TELA DE DETALHES
	public Word findWordById(Long wordId) {
		if(wordId == null)
			return null;
		Optional<Word> opt = this.wordRepository.findById(wordId);
		return opt.map(this::populateWord).orElse(null);
	}
}