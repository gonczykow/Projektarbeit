package dao;

import db.DatenbankAnbindung;
import model.Medikation;
import model.Patient;
import model.Medikament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO für Medikationen.
 */
public class MedikationDAO {

    /**
     * Speichert eine Medikation in der Datenbank.
     */
    public void save(Medikation medikation) throws SQLException {
        String sql = "INSERT INTO medikation (patient_id, medikament_id, dosierung) VALUES (?, ?, ?)";

        Connection con = DatenbankAnbindung.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, medikation.getPatient().getId());
        ps.setInt(2, medikation.getMedikament().getId());
        ps.setString(3, medikation.getDosierung());

        ps.executeUpdate();
    }

    /**
     * Löscht eine Medikation aus der Datenbank.
     */
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM medikation WHERE id = ?";
        Connection con = DatenbankAnbindung.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    /**
     * Aktualisiert eine Medikation in der Datenbank.
     */
    public void update(Medikation m) throws SQLException {
        String sql = """
        UPDATE medikation
        SET dosierung = ?
        WHERE id = ?
    """;

        PreparedStatement ps = DatenbankAnbindung
                .getConnection()
                .prepareStatement(sql);

        ps.setString(1, m.getDosierung());
        ps.setInt(2, m.getId());
        ps.executeUpdate();
    }

    /**
     * Lädt alle Medikationen.
     */
    public List<Medikation> findAll() throws SQLException {

        List<Medikation> result = new ArrayList<>();

        String sql = """
        SELECT m.id, m.dosierung,
               p.id AS pid, p.vorname, p.nachname,
               me.id AS mid, me.name, me.wirkstoff
        FROM medikation m
        JOIN patient p ON m.patient_id = p.id
        JOIN medikament me ON m.medikament_id = me.id
        """;

        Connection con = DatenbankAnbindung.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Patient p = new Patient(
                    rs.getInt("pid"),
                    rs.getString("vorname"),
                    rs.getString("nachname")
            );

            Medikament me = new Medikament(
                    rs.getInt("mid"),
                    rs.getString("name"),
                    rs.getString("wirkstoff")
            );

            Medikation m = new Medikation(
                    rs.getInt("id"),
                    p,
                    me,
                    rs.getString("dosierung")
            );

            result.add(m);
        }

        return result;
    }
}
