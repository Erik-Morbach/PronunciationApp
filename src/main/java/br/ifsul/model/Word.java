package br.ifsul.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Word {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String text;
	private String pronunciation;
	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "word",
			cascade = CascadeType.REMOVE,
			orphanRemoval = true
	)
	private List<Phrase> phrases;

	@Transient
	private List<Word> similarWords;

	public String toString() {
		return text + " [ " + pronunciation + " ]";
	}
}
