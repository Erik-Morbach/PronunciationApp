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
    private JLabel Phrases;
    private JTextArea phrasesTextField;
    private JLabel SimilarWords;
    private JTextField similarWordField1;
    private JTextField similarWordField2;
    private JTextField similarWordField3;

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
            similarWordField1.setText("");
            similarWordField2.setText("");
            similarWordField3.setText("");

            Long wordId = wordListComponent.getSelectedValue().getId();
            Word word = controller.findWordById(wordId);
            List<Word> similarWords = word.getSimilarWords();
            String phraseText = word.getPhrasesString() == null ? "Essa palavra não possui frases" : word.getPhrasesString();
            phrasesTextField.setText(phraseText);
            for (int i = 0; i < similarWords.size(); i++) {
                switch (i) {
                    case 0:
                        similarWordField1.setText(similarWords.get(0).toString());
                    break;
                    case 1:
                        similarWordField2.setText(similarWords.get(1).toString());
                    break;
                    case 2:
                        similarWordField3.setText(similarWords.get(2).toString());
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
