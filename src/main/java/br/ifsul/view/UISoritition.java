package br.ifsul.view;

import br.ifsul.controller.Controller;
import br.ifsul.model.Word;

import javax.swing.*;
import java.util.List;

public class UISoritition extends JFrame {
    private Controller controller;
    private JPanel jPanel;
    private JButton sortButton;
    private JLabel wordLabel;
    private JTextArea phrasesTextArea;
    private JTextField similarWordTextField1;
    private JTextField similarWordTextField2;
    private JTextField similarWordTextField3;
    private JLabel PhrasesLabel;
    private JLabel SimilarWords;

    public UISoritition(Controller controller){
        this.controller = controller;
        this.initialize();
        this.sortButton.addActionListener(e -> {
            Word word = controller.getRandomWord();
            if(word == null)
                wordLabel.setText("Desculpe mas você não possuí nenhuma palavra cadastrada");
            else setSortedWord(word);
        });
    }

    private void setSortedWord(Word word) {
        this.wordLabel.setText(word.toString());
        similarWordTextField1.setText("");
        similarWordTextField2.setText("");
        similarWordTextField3.setText("");

        List<Word> similarWords = word.getSimilarWords();
        String phraseText = word.getPhrasesString() == null ? "Essa palavra não possui frases" : word.getPhrasesString();
        phrasesTextArea.setText(phraseText);
        for (int i = 0; i < similarWords.size(); i++) {
            switch (i) {
                case 0:
                    similarWordTextField1.setText(similarWords.get(0).toString());
                    break;
                case 1:
                    similarWordTextField2.setText(similarWords.get(1).toString());
                    break;
                case 2:
                    similarWordTextField3.setText(similarWords.get(2).toString());
                    break;
            }
        }
    }

    private void initialize() {
        this.setTitle("Sorteio");
        this.setSize(800,400);
        this.add(this.jPanel);
    }
}
