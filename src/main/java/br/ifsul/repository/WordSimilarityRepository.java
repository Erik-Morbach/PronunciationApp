package br.ifsul.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import br.ifsul.model.Word;
import br.ifsul.model.WordSimilarity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WordSimilarityRepository extends PagingAndSortingRepository<WordSimilarity, Long> {

	@Query("SELECT CASE WHEN ws.w1=?1 THEN ws.w2.id ELSE ws.w1.id END "
		 + "FROM WordSimilarity ws "
		 + "WHERE ws.w1=?1 or ws.w2=?1 "
		 + "ORDER BY ws.similarity ASC")
	List<Long> findSimilarWords(Word word, Pageable pageable);
}
