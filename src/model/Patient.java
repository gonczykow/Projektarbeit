package model;

/**
 * Repräsentiert einen Patienten.
 */
public class Patient {

    private int id;
    private String vorname;
    private String nachname;

    public Patient(int id, String vorname, String nachname) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public int getId() {
        return id;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }
}
