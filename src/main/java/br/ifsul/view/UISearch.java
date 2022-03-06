package br.ifsul.view;

import br.ifsul.controller.Controller;
import br.ifsul.model.Word;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UISearch extends JFrame {
    private JTextField searchTermField;
    private JPanel panel1;
    private JList<Word> wordListComponent;
    private JButton searchButton;

    private List<Word> wordList;

    public UISearch(Controller controller){
        this.setTitle("Procurar");
        this.setSize(800,400);
        this.add(this.panel1);
        this.wordList = new ArrayList<>();

        this.searchButton.addActionListener(e -> {
            String searchTerm = searchTermField.getText();
            wordList = controller.searchWords(searchTerm);
            System.out.println(wordList);
            DefaultListModel<Word> model = new DefaultListModel<>();
            for(Word word: wordList)
                model.addElement(word);
            wordListComponent.setModel(model);

        });
        wordListComponent.addListSelectionListener(e ->{
            // Não ta funcionando direito, mas da pra utilizar coisas nesse estilo
            System.out.println(wordList.get(e.getFirstIndex()));
        });
    }
}
