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
    private Controller controller;

    public UIRegister(Controller controller) {
        super("Registrar");
        this.setSize(600,200);
        this.controller = controller;
    }
}
