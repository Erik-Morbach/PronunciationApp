package br.ifsul.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ifsul.models.Word;

@Repository
public interface WordRepository extends CrudRepository<Word, Long>{

}
