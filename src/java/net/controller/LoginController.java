package net.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.dao.UsuaroDao;
import net.dao.dbConnection;
import net.models.Usuario;

public class LoginController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
       
        String msg = "";
        HttpSession session = request.getSession();
                
        switch(accion){
            case "admin": //valida si el usuario ya tiene secion abierta 
                if(session.getAttribute("usuario") == null){
                    msg = "deve iniciar secion";
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                }
            break;
            case "logout": //cerrar la secion del administrador 
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/home");
            break;
            case "addvacante": //agregar nueva vacante desde menu admin
                request.getRequestDispatcher("frmvacante.jsp").forward(request, response);
            break;
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //obtencion de datos de formulario login
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        String msg = "";
        
        //Recuperar instancia del objeto HttpSession
        HttpSession session = request.getSession();
        
        dbConnection conn = new dbConnection();
        UsuaroDao userDao = new UsuaroDao(conn);
        Usuario user = userDao.login(username, password);
        conn.closeConn();
        
        if(user.getId() > 0){
            session.setAttribute("usuario", user);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } else {
            msg = "El usuario no existe ";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
