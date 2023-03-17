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
     * METODO BUSCAR
     */
    public Usuario getUsuario(Connection conect) {
        ArrayList<Usuario> listMaterial = new ArrayList<Usuario>();
        Usuario usuario = new Usuario();
        String sqlSentence = "SELECT * FROM USUARIO_ACCESO WHERE id_usuario =" + 1;
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

    public Boolean login(Usuario us, Connection conect){
        Usuario usuario = this.getUsuario(conect);
        String contrasenia = convertSHA256(us.getContrasenia());
        if(us != null && us.getContrasenia().equals(contrasenia)) {
            return true;
        }
        return false;
    }

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
