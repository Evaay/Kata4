package es.ulpgc.dis.io.Readers;

import es.ulpgc.dis.Model.Title;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;

public class SQLiteTitleReader implements TitleReader, AutoCloseable{
    private final Connection connection;
    private final PreparedStatement selectTitlePreparedStatement;
    private final PreparedStatement selectRandomTitlePreparedStatement;

    private static final String selectTitleStatement = "SELECT * FROM titles";
    private static final String selectRandomTitleStatement = "SELECT * FROM titles ORDER BY RANDOM() LIMIT 1";

    public SQLiteTitleReader(File dbFile) throws IOException {
        try {
            this.connection = openConnection(dbFile);
            this.selectTitlePreparedStatement = preparedStatement(selectTitleStatement);
            this.selectRandomTitlePreparedStatement = preparedStatement(selectRandomTitleStatement);
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    public String getRandomTitle() throws SQLException {
        return selectRandomTitlePreparedStatement.executeQuery().getString(3);
    }

    private Connection openConnection(File dbFile) throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
    }

    private PreparedStatement preparedStatement(String statement) throws SQLException {
        return connection.prepareStatement(statement);
    }

    @Override
    public Iterator<Title> read() throws IOException {
        return new Iterator<>() {
            final ResultSet resultSet;

            {
                try {
                    resultSet = selectTitlePreparedStatement.executeQuery();
                } catch (SQLException e) {
                    throw new IOException(e);
                }
            }

            @Override
            public boolean hasNext() {
                try {
                    return resultSet.next();
                } catch (SQLException e) {
                    return false;
                }
            }

            @Override
            public Title next() {
                try {
                    return new Title(resultSet.getString(1),
                            Title.TitleType.valueOf(resultSet.getString(2)),
                            resultSet.getString(3));
                } catch (SQLException e) {
                    return null;
                }
            }
        };
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
