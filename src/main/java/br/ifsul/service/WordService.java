package br.ifsul.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifsul.comparator.WordComparator;
import br.ifsul.model.Word;
import br.ifsul.repository.WordRepository;

@Service
public class WordService {
	static private final Random randomGenerator = new Random();
	static private final WordComparator comparator = new WordComparator();

	@Autowired
	private WordRepository wordRepository;

	public Word getRandom() {
		Long maxIndex = this.wordRepository.findMaxIndex();
		Long wordIndex = randomGenerator.nextLong(maxIndex+1);
		return this.wordRepository.findWithIndexGraterThan(wordIndex);
	}
	
	public List<Word> getSimilar(Word w){
		if(w==null) return null;
		List<Word> response = (List<Word>) this.wordRepository.findAll();
		comparator.setSource(w.getText());
		response.sort(comparator);
		return response.subList(0, 3);
	}
}
