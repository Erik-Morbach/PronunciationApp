package br.ifsul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ifsul.model.Word;

public interface WordRepository extends JpaRepository<Word, Long>{
	@Query("SELECT MAX(w.id) FROM Word w")
	public Long findMaxIndex();

	@Query(value="SELECT * FROM word w WHERE w.id>=?1 "
			+ "ORDER BY w.id "
			+ "LIMIT 1", 
			nativeQuery=true)
	public Word findWithIndexGraterThan(Long index);
}
