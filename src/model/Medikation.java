package model;

/**
 * Repräsentiert eine Medikation.
 */
public class Medikation {

    private int id;
    private Patient patient;
    private Medikament medikament;
    private String dosierung;

    public Medikation(int id, Patient patient, Medikament medikament, String dosierung) {
        this.id = id;
        this.patient = patient;
        this.medikament = medikament;
        this.dosierung = dosierung;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Medikament getMedikament() {
        return medikament;
    }

    public String getDosierung() {
        return dosierung;
    }
}
