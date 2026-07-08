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
               session.removeAttribute("utente"); //cancello per sicurezza l'utente in sessione prima di assegnarlo
               session.setAttribute("utente",utente);
               response.sendRedirect(request.getContextPath() + "/homepage");
           }
       }else {
           request.setAttribute("error", "Login o Password invalide");
           doGet(request, response);
       }
   }

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
           request.setAttribute("viewPath","login.jsp");
           request.getRequestDispatcher("WEB-INF/views/layout.jsp").forward(request,response);
       }
       catch(ServletException e){
           throw new RuntimeException(e);
       }
   }
}
