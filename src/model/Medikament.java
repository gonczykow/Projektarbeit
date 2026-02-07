package model;

/**
 * Repräsentiert ein Medikament.
 */
public class Medikament {

    private int id;
    private String name;
    private String wirkstoff;

    public Medikament(int id, String name, String wirkstoff) {
        this.id = id;
        this.name = name;
        this.wirkstoff = wirkstoff;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWirkstoff() {
        return wirkstoff;
    }
}
