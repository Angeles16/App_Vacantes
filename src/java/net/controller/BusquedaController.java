package net.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import net.dao.VacanteDao;
import net.dao.dbConnection;
import net.models.Vacante;

public class BusquedaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String buscar = request.getParameter("query");
        dbConnection conn = new dbConnection();
        VacanteDao vacanteDao = new VacanteDao(conn);
        List<Vacante> lista = vacanteDao.getByQuery(buscar);
        System.out.println("Lista ==> ");
            System.out.println(lista);
        conn.closeConn();
        request.setAttribute("listVacante", lista);
        request.getRequestDispatcher("vacantes.jsp").forward(request, response);
        
    }


}
