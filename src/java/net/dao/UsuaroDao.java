package net.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.models.Usuario;

public class UsuaroDao {
    dbConnection conn;

    public UsuaroDao(dbConnection conn) {
        this.conn = conn;
    }
    
    public Usuario login(String user, String password){
        String sql = "SELECT * FROM usuario WHERE username=? AND password=md5(?) limit 1";
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);  
            ps.setString(1, user);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
         
            Usuario usuario = new Usuario(0);
            while(rs.next()){
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setPerfil(rs.getString("perfil"));
                usuario.setEstado(rs.getString("estado"));
            
            }
            return usuario;
        } catch (SQLException ex){
            System.out.println("Error SQL al loguearce ==> " + ex);
            return null; 
        }
    }
     
}
