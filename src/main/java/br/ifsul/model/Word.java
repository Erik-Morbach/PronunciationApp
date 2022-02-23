package br.ifsul.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Word {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String text;
	private String pronunciation;
	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "word",
			cascade = CascadeType.PERSIST,
			orphanRemoval = true
	)
	private List<Phrase> phrases;
	
	public String toString() {
		return text + " ~= " + pronunciation;
	}
}
