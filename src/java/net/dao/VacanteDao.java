package net.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import net.models.Vacante;

public class VacanteDao {
    dbConnection conn;

    public VacanteDao(dbConnection conn) {
        this.conn = conn;
    }
    
    public boolean insert(Vacante vacante){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String sql = "INSERT INTO vacante VALUES(?,?,?,?,?)";
        
        try{
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setInt(1, vacante.getId());
            ps.setString(2, format.format(vacante.getFechaPublicacion()));
            ps.setString(3, vacante.getNombre());
            ps.setString(4, vacante.getDescripcion());
            ps.setString(5, vacante.getDetalle());
            ps.executeUpdate();
            
            return true;
        } catch (Exception ex){
            return false;
        }
    }
    
    public List<Vacante> getUltimas(){
        String sql = "SELECT * FROM vacante ORDER BY id desc limit 3";
        try {
            PreparedStatement psG = conn.getConnection().prepareStatement(sql);
            ResultSet rs = psG.executeQuery();
            List<Vacante> list = new LinkedList<>();
            
            while(rs.next()){
                Vacante vac = new Vacante(rs.getInt(1));
                vac.setFechaPublicacion(rs.getDate(2));
                vac.setNombre(rs.getString(3));
                vac.setDescripcion(rs.getString(4));
                vac.setDetalle(rs.getString(5));
                list.add(vac);
            }
            
            return list;
        } catch (SQLException ex) {
            System.out.println("Error en la obtencion de datos ==> " + ex);
            return null;
        }
    }
    
    public Vacante getVacanteId(int idVacante){
        String sql = "SELECT * FROM vacante WHERE id = ? limit 1";
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setInt(1, idVacante);
            System.out.println("id getVacanteId ==> " + sql);
            ResultSet rs = ps.executeQuery();
            Vacante vac = new Vacante(0);
            while(rs.next()){
                vac.setId(rs.getInt("id"));
                vac.setFechaPublicacion(rs.getDate("fechaPublicacion"));
                vac.setNombre(rs.getString("nombre"));
                vac.setDescripcion(rs.getString("descripcion"));
                vac.setDetalle(rs.getString("detalle"));
            }
            return vac;
        } catch(SQLException ex){
            System.err.println("Error el recuperar la vancate por id ==> " + ex.getMessage());
            return null;
        }      
    }
    
    public List<Vacante> getAllVacantes(){
        String sql = "SELECT * FROM vacante ORDER BY ID DESC";
        try {
            
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Vacante> lista = new LinkedList<>();
            while(rs.next()){
                Vacante vac = new Vacante(rs.getInt("id"));
                vac.setId(rs.getInt("id"));         
                vac.setFechaPublicacion(rs.getDate("fechaPublicacion"));
                vac.setNombre(rs.getString("nombre"));
                vac.setDescripcion(rs.getString("descripcion"));
                vac.setDetalle(rs.getString("detalle"));
                lista.add(vac);
            }
            return lista;
        } catch(SQLException ex){
            System.out.println("Error al recuperar todas las vacantes ==> " + ex);
            return null;
        }
    }
    
    public List<Vacante> getByQuery(String query){
        String sql = "SELECT * FROM vacante WHERE (descripcion like ? or nombre like ?) order by id desc";
        try{
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            ResultSet rs = ps.executeQuery();
            List<Vacante> lista = new LinkedList<>();
            while(rs.next()){
                Vacante vac = new Vacante(rs.getInt("id"));
                vac.setFechaPublicacion(rs.getDate("fechaPublicacion"));
                vac.setNombre(rs.getString("nombre"));
                vac.setDescripcion(rs.getString("descripcion"));
                vac.setDetalle(rs.getString("detalle"));
                lista.add(vac);
            }
            
            return lista;
        } catch (SQLException ex){
            System.err.println("Error al buscar vacantes por id ==> "+ ex);
            return null;
        }
    }
    
    public boolean deleteEmployee(int id){
        String sql = "DELETE FROM vacante WHERE id=?";
        
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch(SQLException ex){
            System.out.println("Ocurrio un error en la eliminacion ==> " + ex);
            return false;
        }
    }
    
    
}
