package it.unisa.seicentools;
import it.unisa.seicentools.application.accessMGMT.SessionService;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="Loginservlet", value="/LoginServlet")
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
               switch(utente.getRuolo){
                   case USER:
                       session.setAttribute("utente",utente);
                       response.sendRedirect(request.getContextPath()+"/homepage");
                       break;

                   case ADMIN:
                       session.setAttribute("utente",utente);
                       response.sendRedirect(request.getContextPath()+"/homepage");
                       break;
               }
           }
           else{
               response.sendRedirect(request.getContextPath()+"/homepage");
               request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request,response);
           }
       }
   }



}
