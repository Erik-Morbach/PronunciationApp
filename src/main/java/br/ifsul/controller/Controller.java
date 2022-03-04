package br.ifsul.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import br.ifsul.model.Phrase;
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
		return newWord;
	}

	// USAR NA TELA DE PALAVRA ALEATÓRIA
	public Word getRandomWord() {
		Long maxIndex = this.wordRepository.findMaxIndex();
		Long wordIndex = randomGenerator.nextLong(maxIndex+1);
		return this.wordRepository.findWithIndexGraterThan(wordIndex);
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
		Word word = this.wordRepository.findById(wordId).get();
		List<Phrase> phrases = this.phraseRepository.findByWordId(wordId);
		word.setPhrases(phrases);
		List<WordSimilarity> wordSimilarities = this.wordSimilarityRepository.findByWordId(wordId);
		if(wordSimilarities == null)
			return word;
		ArrayList similarWordIds = new ArrayList<Long>();
		for (WordSimilarity wordSimilarity : wordSimilarities) {
			similarWordIds.add(wordSimilarity.getW1Id());
		}
		List<Word> similarWords = this.wordRepository.findAllById(similarWordIds);
		word.setSimilarWords(similarWords);
		return word;
	}
}

//	public List<Word> findSimilarWords(Word w, int quantity){
//		if(w==null) return null;
//		List<Long> ids = this.wordSimilarityRepository.findSimilarWords(w.getId(), quantity);
//		return this.wordRepository.findAllById(ids);
//	}