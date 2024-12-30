package es.ulpgc.dis.Control;

import es.ulpgc.dis.View.MainFrame;
import es.ulpgc.dis.io.Readers.SQLiteTitleReader;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class GetRandomTitleCommand implements Command{
    private final MainFrame mainFrame;
    private final File dbFile;

    public GetRandomTitleCommand(MainFrame mainFrame, File dbFile) {
        this.mainFrame = mainFrame;
        this.dbFile = dbFile;
    }

    @Override
    public void execute() throws Exception {
        try (SQLiteTitleReader reader = new SQLiteTitleReader(dbFile)){
            String title = reader.getRandomTitle();
            mainFrame.getTextArea().setText(title);
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }
}
