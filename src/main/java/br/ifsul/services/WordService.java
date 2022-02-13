package br.ifsul.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import br.ifsul.models.Word;
import br.ifsul.repositories.WordRepository;

public class WordService {
	private class WordComparator implements Comparator<Word>{
		private String source;
		public void setSource(String source) { this.source = source; }

		private HashMap<String, Integer> memoization;
		private Integer compute(String s) {
			return memoization.computeIfAbsent(s, new Function<String, Integer>() {
				@Override
				public Integer apply(String t) {
					final String minimum = (t.length()< source.length()?t:source);
					final String maximum = (t.length()>=source.length()?t:source);
					int error = Integer.MAX_VALUE;
					int minLen = minimum.length();
					int maxLen = maximum.length();
					for(int offset=0;offset+minLen<=maxLen;offset++) {
						int currentError = 0;
						for(int i=0;i<minLen;i++) 
							currentError += Math.min(1,
													Math.abs(
														maximum.charAt(i+offset) - minimum.charAt(i) // retorna a diferença 
																									// entre os caracteres
														) // valor absoluto da diferença
													); // se a diferença for 0 currentError não é alterada
													   // caso contrário currentError é somado em 1
						error = Math.min(error,currentError);
					}
					return error; // Menor erro entre todos os offsets possíveis
				}
			});
		}
		@Override
		public int compare(Word w1, Word w2) {
			return Integer.compare(compute(w1.getText()), compute(w2.getText()));
		}
	};

	private WordRepository wordRepository;
	private Random randomGenerator;
	private WordComparator comparator;
	
	public WordService(WordRepository wordRepository) {
		super();
		this.wordRepository = wordRepository;
		this.randomGenerator = new Random();
	}
	
	public Word getRandom() {
		Long maxIndex = this.wordRepository.findMaxIndex();
		Long wordIndex = this.randomGenerator.nextLong(maxIndex);
		return this.wordRepository.findWithIndexGraterThan(wordIndex);
	}
	
	public List<Word> getSimilar(Word w){
		List<Word> response = (List<Word>) this.wordRepository.findAll();
		response.sort(this.comparator);
		return response;
	}

	public String findAll(String text) {
		if(text == null) {
			text = "";
		}
		
		return "";
	}
}
