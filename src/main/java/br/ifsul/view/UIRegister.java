package br.ifsul.view;

import br.ifsul.controller.Controller;
import br.ifsul.model.Phrase;
import br.ifsul.model.Word;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UIRegister extends JFrame{
    private JPanel panel;
    private JButton finishRegisterBt;
    private JTextField wordTextField;
    private JTextField pronunciationTextfield;
    private JTextField phrase1TextField;
    private JTextField phrase2TextField;
    private JTextField phrase3TextField;

    public UIRegister(Controller controller){
        this.setTitle("Registrar");
        this.setSize(800,400);
        this.add(this.panel);

        finishRegisterBt.addActionListener(e -> {
            if(!validateFields()) {
                showError();
                return;
            }
            Word word = new Word();
            word.setText(wordTextField.getText());
            word.setPronunciation(pronunciationTextfield.getText());
            controller.registerWord(word);
            List<Phrase> phrases = new ArrayList<Phrase>();
            for(JTextField currentField: new JTextField[]{phrase1TextField, phrase2TextField, phrase3TextField}){
                Phrase phrase = new Phrase();
                phrase.setText(currentField.getText());
                phrase.setWord(word);
                controller.registerPhrase(phrase);
                phrases.add(phrase);
            }
            word.setPhrases(phrases);
        });
    }
    public void showError(){
        System.out.println("Error");
    }
    private boolean validateFields(){
        for(String current: new String[]{wordTextField.getText(), pronunciationTextfield.getText(),
                                         phrase1TextField.getText(),phrase2TextField.getText(),
                                         phrase3TextField.getText()}){
            if(current.length()==0) return false;
        }
        return true;
    }

}
