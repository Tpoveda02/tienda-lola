package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import modelo.*;
import vista.Login;

public class Controlador {
    static Connection conect;

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/TIENDA_LOLA?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "root";
            conect = DriverManager.getConnection(url, usuario, contraseña);
            if(conect!=null) {
                System.out.println("Conexión establecida con Mysql");
            }
        }catch(ClassNotFoundException e) {
            System.out.println("Error al cargar el Driver");
            e.printStackTrace();
        }catch(SQLException e) {
            System.out.println("Error con la BD");
            e.printStackTrace();
        }

        return conect;
    }

    public Boolean login(String usuario, String contrasenia) {
        Usuario us = new Usuario(1,usuario,contrasenia);
        return us.login(us,getConexion());
    }


    public static void main(String args[]) {
        Controlador init = new Controlador();
        Login view = new Login();
        view.setVisible(true);
    }
}
