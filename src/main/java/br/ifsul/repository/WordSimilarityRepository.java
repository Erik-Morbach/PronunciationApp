package br.ifsul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ifsul.model.WordSimilarity;

public interface WordSimilarityRepository extends JpaRepository<WordSimilarity, Long>{
	
	@Query(value="SELECT CASE WHEN ws.w1_id=?1 THEN ws.w2_id ELSE ws.w1_id END "
			   + "FROM word_similarity ws "
			   + "WHERE ws.w1_id=?1 or ws.w2_id=?1 "
			   + "ORDER BY ws.similarity ASC "
			   + "LIMIT ?2",
		   nativeQuery=true)
	List<Long> findSimilarWords(Long wordId, int quantity);

}
