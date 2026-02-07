package gui;

import model.Medikation;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MedikationTableModel extends AbstractTableModel {
    private List<Medikation> daten = new ArrayList<>();

    private final String[] spalten = {
            "ID",
            "Patient",
            "Medikament",
            "Dosierung"
    };

    public void setDaten(List<Medikation> daten) {
        this.daten = daten;
        fireTableDataChanged();
    }

    public Medikation getMedikationAt(int row) {
        return daten.get(row);
    }

    @Override
    public int getRowCount() {
        return daten.size();
    }

    @Override
    public int getColumnCount() {
        return spalten.length;
    }

    @Override
    public String getColumnName(int column) {
        return spalten[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Medikation m = daten.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> m.getId();
            case 1 -> m.getPatient().getNachname();
            case 2 -> m.getMedikament().getName();
            case 3 -> m.getDosierung();
            default -> "";
        };
    }
}
