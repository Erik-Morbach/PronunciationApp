package br.ifsul.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ifsul.models.Word;

@Repository
public interface WordRepository extends CrudRepository<Word, Long>{
	@Query("SELECT MAX(w.id) FROM Word w")
	public Long findMaxIndex();
	
	@Query("SELECT * FROM Word w WHERE w.id>=?1 "
			+ "ORDER BY w.id "
			+ "LIMIT 1")
	public Word findWithIndexGraterThan(Long index);
}
