package br.ifsul;

import br.ifsul.controller.Controller;
import br.ifsul.view.UIApp;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import br.ifsul.model.Word;


@SpringBootApplication
public class PronunciationApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder sab = new SpringApplicationBuilder(PronunciationApplication.class);
		sab.headless(false);
		ConfigurableApplicationContext cac = sab.run(args);
		PronunciationApplication app = (PronunciationApplication) cac.getBean("pronunciationApplication");
		UIApp ui = new UIApp();
	}
	
	@Bean
	public Controller testRandomAndSimilar(Controller controller) {
		Word myWord = controller.getRandom();
		System.out.println(myWord);

		System.out.println(controller.getSimilar(myWord,3));
		return controller;
	}
	
	//@Bean 
	Controller testFindOccurrence(Controller controller) {
		System.out.println(controller.findOccurrence("ar"));
		return controller;
	}
	

}
