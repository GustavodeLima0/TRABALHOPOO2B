package br.unipar.pet.dogui.poo.infraestructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/TRABALHO-POO";
        String user = "postgres";
        String password = "12112005";
        return DriverManager.getConnection(url, user, password);
    }
}
