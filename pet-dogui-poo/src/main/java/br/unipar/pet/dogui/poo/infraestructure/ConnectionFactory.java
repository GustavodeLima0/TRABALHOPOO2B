package br.unipar.pet.dogui.poo.infraestructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    

    public Connection getConnection() throws SQLException  {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/TRABALHO_POO",
                "postgres", 
                "Gu12112005"
        );
    }
    
}
