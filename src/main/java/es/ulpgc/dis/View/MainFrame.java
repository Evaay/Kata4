package es.ulpgc.dis.View;

import es.ulpgc.dis.Control.Command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final JFreeHistogramDisplay histogramDisplay;
    private final Map<String, Command> commands;
    private JTextArea textArea = new JTextArea(1, 20);

    public MainFrame() throws HeadlessException {
        this.setTitle("Kata 3");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(histogramDisplay = staticsPanel());
        this.add(BorderLayout.NORTH, toolbar());
        this.commands = new HashMap<>();
    }

    public void put(String name, Command command) {
        commands.put(name, command);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    private Component toolbar() {
        JPanel panel = new JPanel();
        textArea.setEditable(false);
        panel.add(toggle());
        panel.add(textArea);
        return panel;
    }

    private JButton toggle() {
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    commands.get("toggle").execute();
                    commands.get("getRandomTitle").execute();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return button;
    }

    private JFreeHistogramDisplay staticsPanel() {
        return new JFreeHistogramDisplay();
    }

    public HistogramDisplay histogramDisplay() {
        return histogramDisplay;
    }
}
