package service;

import dao.MedikationDAO;
import model.Medikation;

import java.sql.SQLException;

public class MedikationSave implements Runnable {

    private Medikation medikation;
    private MedikationDAO dao;

    public MedikationSave(Medikation medikation, MedikationDAO dao) {
        this.medikation = medikation;
        this.dao = dao;
    }

    @Override
    public void run() {
        try {
            dao.save(medikation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


