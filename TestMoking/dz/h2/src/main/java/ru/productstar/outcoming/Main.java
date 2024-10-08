package ru.productstar.outcoming;

import org.h2.jdbcx.JdbcDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws IOException {
        // run H2
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:./db");
        jdbcDataSource.setUser("user");
        jdbcDataSource.setPassword("password");

        try (InputStream inputStream = Main.class.getResource("/initial.sql").openStream()) {
            String sql = new String(inputStream.readAllBytes());
            try (
                    Connection connection = jdbcDataSource.getConnection();
                    Statement statement = connection.createStatement();) {

                statement.executeUpdate(sql);


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }





}
