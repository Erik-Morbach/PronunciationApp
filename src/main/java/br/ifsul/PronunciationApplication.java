package br.ifsul;

import br.ifsul.controller.Controller;
import br.ifsul.model.Word;
import br.ifsul.view.UIApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.Clock;


@SpringBootApplication
public class PronunciationApplication {
	@Autowired 
	private Controller controller;

	public static void main(String[] args) {
		SpringApplicationBuilder sab = new SpringApplicationBuilder(PronunciationApplication.class);
		sab.headless(false);
		ConfigurableApplicationContext cac = sab.run(args);
		PronunciationApplication app = (PronunciationApplication) cac.getBean("pronunciationApplication");
		UIApp view = new UIApp(app.controller);
		view.setVisible(true);
	}
	
	@Bean
	Controller testSimilars(Controller controller) {
		for(Word w: controller.searchWords("")) {
			controller.populateWord(w);
			System.out.println(w);
			System.out.println(w.getSimilarWords());
			System.out.println("--------------------");
		}
		return controller;
	}
	//
	//	//@Bean
	//	Controller test(Controller controller) {
	//		Scanner sc = new Scanner(System.in);
	//		System.out.print("Quantity: ");
	//		int quantity = sc.nextInt();
	//		while(quantity-->0) {
	//			System.out.print("Word Pronun: ");
	//			String wordText = sc.next();
	//			String pronun = sc.next();
	//			Word nw = new Word();
	//			nw.setText(wordText);
	//			nw.setPronunciation(pronun);
	//			controller.registerWord(nw);
	//			System.out.println(nw.getSimilarWords());
	//		}
	//		sc.close();
	//		return controller;
	//	}

	//@Bean
	//	public Controller testRandomAndSimilar(Controller controller) {
	//		Word myWord = controller.getRandomWord();
	//		System.out.println(myWord);
	//
	//		System.out.println(myWord.getSimilarWords());
	//		return controller;
	//	}

}
