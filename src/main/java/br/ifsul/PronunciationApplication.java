package br.ifsul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ifsul.model.Word;
import br.ifsul.service.WordService;


@SpringBootApplication
public class PronunciationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PronunciationApplication.class, args);
	}
	
	@Bean
	public WordService testRandomAndSimilar(WordService service) {
		Word myWord = service.getRandom();
		System.out.println(myWord);
		System.out.println(service.getSimilar(myWord));
		return service;
	}
	

}
