package net.controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.util.List;
import net.dao.VacanteDao;
import net.dao.dbConnection;
import net.models.Vacante;

public class VacanteController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if(accion.equals("ver")){
            this.verDetalle(request, response);
        }
        
        if(accion.equals("getall")){
            this.verTodas(request, response);
        }
        
        if(accion.equals("delete")){
            this.delete(request, response);
        }
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //obtener datos del formulario
        String nombreParam = request.getParameter("nombre");
        String descripcionParam = request.getParameter("descripcion");
        String detalleParam = request.getParameter("detalle");
        //java instancia vacante con los datos del formulario
        Vacante vacante = new Vacante(0);
        vacante.setNombre(nombreParam);
        vacante.setDescripcion(descripcionParam);
        vacante.setDetalle(detalleParam);
        
        System.out.println(vacante);
        //almacenar los datos en la DB
        dbConnection conn = new dbConnection();
        VacanteDao Vdao = new VacanteDao(conn);
        boolean dataInsert = Vdao.insert(vacante);
        
        String msj;
        if(dataInsert){
            msj = "La vacante fue agregada correctamente";
        } else {
            msj = "Error al crear la vacante";
        }
        conn.closeConn();
        request.setAttribute("message", msj);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
     
    }
    
    
    public void verDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idVacante = Integer.parseInt(request.getParameter("id"));
        dbConnection conn = new dbConnection();
        VacanteDao vacDao = new VacanteDao(conn);
        Vacante vacante = vacDao.getVacanteId(idVacante);
        conn.closeConn();
        
        request.setAttribute("vacante", vacante);
        request.getRequestDispatcher("/detalle.jsp").forward(request, response);
    }
    
    public void verTodas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dbConnection conn = new dbConnection();
        VacanteDao vacanteDao = new VacanteDao(conn);
        List<Vacante> lista = vacanteDao.getAllVacantes();
        System.out.println("==> lista de datos getAllVacantes desde vacanteController");
            System.out.println(lista);
        conn.closeConn();
        
        request.setAttribute("listVacante", lista);
        request.getRequestDispatcher("/vacantes.jsp").forward(request, response);
    }
    
    //llamar al metodo eliminar
    public void delete(HttpServletRequest request, HttpServletResponse response)  
             throws ServletException, IOException {
        int idDelete = Integer.parseInt(request.getParameter("id"));
        dbConnection conn = new dbConnection();
        VacanteDao vacDao = new VacanteDao(conn);
        boolean successfulRemoval = vacDao.deleteEmployee(idDelete);
        conn.closeConn();
        String mensaje = "";
        
        if(successfulRemoval){
            mensaje = "Vacante eliminada con exito";
        } else {
            mensaje = "Error al eliminar la vacante";
        }
        
        request.setAttribute("message", mensaje);
        request.getRequestDispatcher("message.jsp").forward(request, response);
    }
}
