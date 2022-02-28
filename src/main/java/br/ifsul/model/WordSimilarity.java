package br.ifsul.model;

import br.ifsul.model.Word;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;
import java.util.TreeMap;

@Entity
@Getter
@Setter
public class WordSimilarity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Map.Entry<Word,Word> words;
    private int similarity;
}
