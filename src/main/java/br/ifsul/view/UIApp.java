package br.ifsul.view;

import br.ifsul.controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIApp extends JFrame{
    private JPanel panel;
    private JButton screenRegisterBt;
    private JButton screenSearchBt;
    private JButton screenSortWordBt;

    public UIApp(Controller controller) {
        super("Pronuncia!");
        this.setSize(400,400);
        this.add(this.panel);
        this.screenRegisterBt.addActionListener(e -> {
            UIRegister screen = new UIRegister(controller);
            screen.setVisible(true);
        });
        this.screenSearchBt.addActionListener(e -> {
            UISearch screen = new UISearch(controller);
            screen.setVisible(true);
        });
        this.screenSortWordBt.addActionListener(e -> {
            UISoritition screen = new UISoritition(controller);
            screen.setVisible(true);
        });
    }
}
