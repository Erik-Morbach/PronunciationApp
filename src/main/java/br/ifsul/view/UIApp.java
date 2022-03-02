package br.ifsul.view;

import br.ifsul.controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIApp extends JFrame{
    private JPanel panel;
    private JButton telaCadastroButton;
    private JButton telaPesquisaButton;
    private JButton telaSorteioButton;
    private Controller controller;

    public UIApp(Controller controller) {
        super("Pronuncia!");
        this.setSize(400,400);
        this.add(this.panel);
        this.controller = controller;
        telaCadastroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIRegister screen = new UIRegister(controller);
                screen.setVisible(true);
            }
        });
        telaPesquisaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        telaSorteioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
