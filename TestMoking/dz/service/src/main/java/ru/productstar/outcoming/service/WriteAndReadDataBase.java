package ru.productstar.outcoming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class WriteAndReadDataBase implements DBInterface {

    private final DataSource dataSource;

    @Autowired
    public WriteAndReadDataBase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void writeWordToDB(int id, String word) {
        try (Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO words_table (word_id, word) VALUES (?, ?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, word);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getWordById(int id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM words_table WHERE word_id = ?")) {
            preparedStatement.setInt(1, id);
            try(
                    ResultSet wordById  = preparedStatement.executeQuery();) {
                if(!wordById.next()) {
                    throw new RuntimeException(String.format("Word with id %d was not found", id));
                }
                return wordById.getString(2);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Пусто!");
        }
    }
}






