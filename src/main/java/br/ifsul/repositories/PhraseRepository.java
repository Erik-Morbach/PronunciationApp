package br.ifsul.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.ifsul.models.Phrase;

@Repository
public interface PhraseRepository extends CrudRepository<Phrase, Long> {
}
