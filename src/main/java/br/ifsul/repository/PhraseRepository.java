package br.ifsul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ifsul.model.Phrase;

public interface PhraseRepository extends JpaRepository<Phrase, Long>{
    @Query("SELECT p FROM Phrase p WHERE p.word.id=?1")
	public List<Phrase> findByWordId(Long wordId);
}
