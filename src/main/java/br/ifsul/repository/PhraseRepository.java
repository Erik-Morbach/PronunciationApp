package br.ifsul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifsul.model.Phrase;

public interface PhraseRepository extends JpaRepository<Phrase, Long>{

}
