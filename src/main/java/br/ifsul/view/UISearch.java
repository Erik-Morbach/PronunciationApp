package br.ifsul.view;

import br.ifsul.controller.Controller;
import br.ifsul.model.Word;
import br.ifsul.model.WordSimilarity;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UISearch extends JFrame {
    private JTextField searchTermField;
    private JPanel panel1;
    private JList<Word> wordListComponent;
    private JButton searchButton;
    private JTextArea phrasesTextArea;
    private JTextField similarWordTextField1;
    private JTextField similarWordTextField2;
    private JTextField similarWordTextField3;
    private JLabel PhrasesLabel;
    private JLabel SimilarWordsLabel;

    private List<Word> wordList;

    public UISearch(Controller controller){
        this.initialize();
        this.searchButton.addActionListener(e -> {
            String searchTerm = searchTermField.getText();
            wordList = controller.searchWords(searchTerm);
            DefaultListModel<Word> model = new DefaultListModel<>();
            for(Word word: wordList)
                model.addElement(word);
            wordListComponent.setModel(model);
        });
        wordListComponent.addListSelectionListener(e -> {
            similarWordTextField1.setText("");
            similarWordTextField2.setText("");
            similarWordTextField3.setText("");

            Long wordId = wordListComponent.getSelectedValue().getId();
            Word word = controller.findWordById(wordId);
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
        });
    }

    public void initialize() {
        this.setTitle("Procurar");
        this.setSize(800,400);
        this.add(this.panel1);
        this.wordList = new ArrayList<>();
    }
}
