package com.example.pinguiapp;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Conexion {
    private final String dirIp = "mysql://urhzabcaylbcx2t7:D9ln66kQMBArEt4wMcU8@bhy07o9l8zzptvw9aad1-mysql.services.clever-cloud.com:3306/bhy07o9l8zzptvw9aad1"; // ubicación del servidor
    private final String usr = "urhzabcaylbcx2t7";
    private final String pass = "D9ln66kQMBArEt4wMcU8";
    private final String bd = "bhy07o9l8zzptvw9aad1";
    private final String pto = "3306";
    private Connection con = null;

    public Conexion() {
        // Constructor vacío
    }

    public Connection conectar() {
        try {
            // Cargar el DRIVER
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL8
            // Hacer la cadena de conexión
            con = DriverManager.getConnection("jdbc:mysql://" + dirIp + ":" + pto + "/" + bd, usr, pass);
            System.out.println("Conectado...");
        } catch (ClassNotFoundException ex) {
            System.out.println("No se pudo cargar el DRIVER: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error en BD: " + ex.getMessage());
        }
        return con;
    }

    public void desconectar() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Desconectado...");
            } catch (SQLException ex) {
                System.out.println("Error al desconectar: " + ex.getMessage());
            }
        }
    }

    public boolean loguearUsuario(String username, String password) {
        String query = "SELECT*FROM usuarios WHERE username ";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                return BCrypt.checkpw(password, storedHashedPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}




