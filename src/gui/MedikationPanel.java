package gui;

import model.*;
import service.MedikationService;

import javax.swing.*;
import java.awt.*;

public class MedikationPanel extends JPanel {

    private JTextField dosierungField = new JTextField(10);
    private JButton speichernBtn = new JButton("Speichern");
    private JButton löschenBtn = new JButton("Löschen");

    private JTable table;
    private MedikationTableModel tableModel = new MedikationTableModel();

    private MedikationService service = new MedikationService();

    public MedikationPanel() {
        setLayout(new BorderLayout());

        // Formular
        JPanel form = new JPanel();
        form.add(new JLabel("Dosierung:"));
        form.add(dosierungField);
        form.add(speichernBtn);
        form.add(löschenBtn);

        // Tabelle
        table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);

        add(form, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        speichernBtn.addActionListener(e -> speichern());
        löschenBtn.addActionListener(e -> löschen());

        laden();
    }

    private void laden() {
        service.findAllAsync(medikationen -> {
            tableModel.setDaten(medikationen);
        });
    }

    private void speichern() {
        if (!dosierungField.getText().matches("\\d+(mg|ml)")) {
            JOptionPane.showMessageDialog(this, "Ungültige Dosierung!");
            return;
        }

        Patient p = new Patient(1, "Max", "Mustermann");
        Medikament m = new Medikament(1, "Aspirin", "ASS");

        Medikation medikation =
                new Medikation(0, p, m, dosierungField.getText());

        service.saveAsync(
                medikation,
                () -> {
                    laden();
                    dosierungField.setText("");
                },
                () -> JOptionPane.showMessageDialog(
                        this,
                        "Datenbankfehler",
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE)
        );
    }

    private void löschen() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bitte Datensatz auswählen");
            return;
        }

        Medikation m = tableModel.getMedikationAt(row);
        service.deleteAsync(
                m.getId(),
                this::laden,
                () -> JOptionPane.showMessageDialog(
                        this,
                        "Fehler beim Löschen",
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE)
        );
    }
}
