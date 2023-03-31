package modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Usuario {
    //-------------Variables------------
    public int id;
    public String usuario;
    public String contrasenia;


    //-----------Constructor-----------
    public Usuario(int id, String usuario, String contrasenia){
        this.id = id;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }


    public Usuario() {
        // TODO Auto-generated constructor stub
    }

    /*
     * METODO BUSCAR EL USUARIO
     */
    public Usuario getUsuario(Connection conect) {
        ArrayList<Usuario> listMaterial = new ArrayList<Usuario>();
        Usuario usuario = new Usuario();
        String sqlSentence = "SELECT * FROM USUARIO_ACCESO WHERE id_usuario =" + 1;//Unicamente el usuario administrador
        try {
            Statement stmt = conect.createStatement();
            ResultSet request = stmt.executeQuery(sqlSentence);
            while(request.next()) {
                usuario.setId(request.getInt(1));
                usuario.setUsuario(request.getString(2));
                usuario.setContrasenia(request.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
    //Metodo que valida las credenciales con la BD - True Si no False
    public Boolean login(Usuario us, Connection conect){
        Usuario usuario = this.getUsuario(conect);
        String contrasenia = convertSHA256(us.getContrasenia());
        //COmpara el nombre y contraseña digitados con la BD
        if(us != null && usuario.getContrasenia().equals(contrasenia)&&usuario.getUsuario().equals(us.getUsuario())) {
            return true;
        }
        return false;
    }
    //Método que cifra la contraseña en SHA256
    public String convertSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /*
     * METODOS GET_SETTERS
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
