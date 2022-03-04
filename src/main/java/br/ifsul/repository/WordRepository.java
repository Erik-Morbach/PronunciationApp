package br.ifsul.repository;

import java.util.List;

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

	@Query("SELECT w.id, w.text FROM Word w WHERE w.text LIKE %?1%")
	public List<Word> searchByText(String word);
}
