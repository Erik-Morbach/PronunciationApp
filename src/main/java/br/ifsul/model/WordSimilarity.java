package br.ifsul.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter
public class WordSimilarity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Word w1;
    @ManyToOne
    private Word w2;
    private int similarity;

	public WordSimilarity(Word w1, Word w2, int similarity) {
		super();
		this.w1 = w1;
		this.w2 = w2;
		this.similarity = similarity;
	}
	
	public Word getOther(Word w) {
		return (w.getId()==w1.getId()? w2 : w1);
	}
}
