package br.ifsul;

import br.ifsul.controller.Controller;

import java.util.Scanner;

import br.ifsul.view.UIApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import br.ifsul.model.Word;


@SpringBootApplication
public class PronunciationApplication {
	@Autowired 
	private Controller controller;

	public static void main(String[] args) {
		SpringApplicationBuilder sab = new SpringApplicationBuilder(PronunciationApplication.class);
		sab.headless(false);
		ConfigurableApplicationContext cac = sab.run(args);
		@SuppressWarnings("unused")
		PronunciationApplication app = (PronunciationApplication) cac.getBean("pronunciationApplication");
		UIApp view = new UIApp(app.controller);
		view.setVisible(true);
	}
	
	//@Bean
	Controller testSimilars(Controller controller) {
		for(Word w: controller.findAllWords()) {
			System.out.println(w);
			System.out.println(controller.findSimilarWords(w, 3));
			System.out.println("--------------------");
		}
		return controller;
	}
	
	//@Bean 
	Controller test(Controller controller) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Quantity: ");
		int quantity = sc.nextInt();
		while(quantity-->0) {
			System.out.print("Word Pronun: ");
			String wordText = sc.next();
			String pronun = sc.next();
			Word nw = new Word();
			nw.setText(wordText);
			nw.setPronunciation(pronun);
			controller.registerWord(nw);
			System.out.println(controller.findSimilarWords(nw, 3));
		}
		sc.close();
		return controller;
	}

	//@Bean
	public Controller testRandomAndSimilar(Controller controller) {
		Word myWord = controller.getRandom();
		System.out.println(myWord);

		System.out.println(controller.findSimilarWords(myWord,3));
		return controller;
	}
	
	//@Bean 
	Controller testFindOccurrence(Controller controller) {
		System.out.println(controller.findOccurrence("ar"));
		return controller;
	}
	

}
