package br.ifsul.comparator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Function;

import br.ifsul.model.Word;


public class WordComparator implements Comparator<Word> {
    private String source;
    private HashMap<String, Integer> memoization;
    public void setSource(String source) { 
    	this.memoization = new HashMap<String,Integer>();
    	this.source = source; 
	}

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
                    for(int i=0;i<minLen;i++) {
                    	int charError = Math.abs(maximum.charAt(i+offset) - minimum.charAt(i));
                    	charError = Math.min(1,charError);
                        currentError +=  charError;
                        // dado o valor absoluto da diferença entre os caracteres
                        // se a diferença for 0 currentError não é alterada
						// caso contrário currentError é somado em 1
                    }
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
}
