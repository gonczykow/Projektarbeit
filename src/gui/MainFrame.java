package gui;

import db.DatenbankAnbindung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Medikationsmodul");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(createMenuBar());
        add(createToolBar(), BorderLayout.NORTH);
        add(new MedikationPanel(), BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DatenbankAnbindung.close();
            }
        });
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();

        JMenu datei = new JMenu("Datei");
        JMenuItem beenden = new JMenuItem("Beenden");
        beenden.addActionListener(e -> System.exit(0));

        datei.add(beenden);
        bar.add(datei);

        return bar;
    }

    private JToolBar createToolBar() {
        JToolBar tb = new JToolBar();
        tb.add(new JButton("Neu"));
        tb.add(new JButton("Speichern"));
        tb.add(new JButton("Löschen"));
        return tb;
    }
}
