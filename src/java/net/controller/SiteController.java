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

public class SiteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        dbConnection conn = new dbConnection();
        VacanteDao vac = new VacanteDao(conn);
        List<Vacante> lista = vac.getUltimas();
        conn.closeConn();
        request.setAttribute("vacantes", lista);
        request.getRequestDispatcher("index.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
