package br.ifsul.view;

import br.ifsul.controller.Controller;
import br.ifsul.model.Word;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UISearch extends JFrame {
    private Controller controller;
    private JTextField searchTermField;
    private JPanel panel1;
    private JList wordListComponent;
    private JButton searchButton;

    private List<String> wordList;

    public UISearch(Controller controller){
        this.controller = controller;
        this.setTitle("Procurar");
        this.setSize(800,400);
        this.add(this.panel1);
        this.wordListComponent = new JList();
//        this.wordList = new ArrayList<>();

        this.searchButton.addActionListener(e -> {
            String searchTerm = searchTermField.getText();
            List<Word> wordResults = controller.searchWords(searchTerm);
            System.out.println(wordResults);
            DefaultListModel model = new DefaultListModel();
            if(wordResults.size() == 0)
                model.addElement("Nenhum resultado encontrado");
            for(Word word: wordResults)
                model.addElement(word.toString());
            wordListComponent.setModel(model);
        });
    }
}
