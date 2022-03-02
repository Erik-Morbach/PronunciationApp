package br.ifsul.view;

import br.ifsul.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class UIRegister extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JButton adicionarNovaFraseButton;
    private JButton concluirCadastroButton;
    private List<JTextField> phrases;
    private JPanel panel;
    private JScrollPane phrasesContainer;
    private JPanel phrasesPanel;

    private Controller controller;

    public UIRegister(Controller controller) {
        super("Registrar");
        this.setSize(600,200);
        this.add(this.panel);
        this.controller = controller;
        this.phrases = new ArrayList<JTextField>();
        this.phrasesPanel = new JPanel();
        this.phrasesPanel.setLayout(new BoxLayout(this.phrasesPanel, BoxLayout.PAGE_AXIS));
        this.phrasesContainer.validate();
        adicionarNovaFraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField phrase = new JTextField("Testando");
                phrasesPanel.add(phrase);
                phrasesPanel.validate();
                phrases.add(phrase);
            }
        });
        concluirCadastroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
