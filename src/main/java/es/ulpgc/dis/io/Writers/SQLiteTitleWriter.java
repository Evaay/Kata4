package es.ulpgc.dis.io.Writers;

import es.ulpgc.dis.Model.Title;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLiteTitleWriter implements TitleWriter, AutoCloseable{
    private final Connection connection;
    private final PreparedStatement insertTitlePreparedStatement;
    private static final String createTitleTableStatement = """
            CREATE TABLE IF NOT EXISTS titles (
                    id TEXT PRIMARY KEY,
                    titleType TEXT NOT NULL,
                    primaryTitle TEXT NOT NULL
            )
            """;
    private static final String insertTitleStatement = """
            INSERT INTO titles(id, titleType, primaryTitle) VALUES (?,?,?)
            """;

    public SQLiteTitleWriter(File dbFile) throws IOException {
        try {
            this.connection = openConnection(dbFile);
            this.stopAutoCommit();
            this.createTable();
            this.insertTitlePreparedStatement = preparedStatement(insertTitleStatement);
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    private PreparedStatement preparedStatement(String statement) throws SQLException {
        return connection.prepareStatement(statement);
    }

    private void createTable() throws SQLException {
        connection.createStatement().execute(createTitleTableStatement);
    }

    private void stopAutoCommit() throws SQLException {
        connection.setAutoCommit(false);
    }

    private Connection openConnection(File dbFile) throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:"+ dbFile.getAbsolutePath());
    }

    @Override
    public void write(Title title) throws IOException {
        try {
            insertTitlePreparedStatement.setString(1, title.id());
            insertTitlePreparedStatement.setString(2, title.titleType().name());
            insertTitlePreparedStatement.setString(3, title.primaryTitle());
            insertTitlePreparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void close() throws Exception {
        connection.commit();
        connection.close();
    }
}
