package service;

import dao.MedikationDAO;
import model.Medikation;

import javax.swing.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * Service-Klasse für Medikation.
 */
public class MedikationService {

    private MedikationDAO dao = new MedikationDAO();

    public void saveAsync(Medikation medikation,
                          Runnable onSuccess,
                          Runnable onError) {

        Runnable task = () -> {
            try {
                new MedikationSave(medikation, dao).run();

                // Erfolg → GUI-Callback
                SwingUtilities.invokeLater(onSuccess);

            } catch (Exception e) {

                // Fehler → GUI-Callback
                SwingUtilities.invokeLater(onError);
            }
        };

        new Thread(task).start();
    }

    public void findAllAsync(Consumer<List<Medikation>> onSuccess) {
        new Thread(() -> {
            try {
                List<Medikation> list = dao.findAll();
                SwingUtilities.invokeLater(() -> onSuccess.accept(list));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void deleteAsync(int id, Runnable onSuccess, Runnable onError) {
        new Thread(() -> {
            try {
                dao.delete(id);
                SwingUtilities.invokeLater(onSuccess);
            } catch (Exception e) {
                SwingUtilities.invokeLater(onError);
            }
        }).start();
    }

}
