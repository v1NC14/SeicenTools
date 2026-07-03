package it.unisa.seicentools.presentation.AccessControl;

import it.unisa.seicentools.application.accessMGMT.SessionService;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="Loginservlet", value="/login")
public class LoginServlet  extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String username = request.getParameter("username");
       String password = request.getParameter("password");
       SessionService sessionService = new SessionService();

       if(sessionService.login(username, password)){
           Utente utente = sessionService.getUtente(username);
           HttpSession session = request.getSession();
           
           if(utente != null){
               session.setAttribute("utente",utente);
               request.setAttribute("viewPath", "homepage.jsp");
               request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
           }
           else{
               request.setAttribute("error", "Utente non loggato, effettua il login");
               request.setAttribute("viewPath", "login.jsp");
               request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
           }
       }
   }
}
