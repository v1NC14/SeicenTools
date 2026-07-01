package it.unisa.seicentools.presentation.UtenteControl;

import it.unisa.seicentools.application.profileMGMT.UserService;
import it.unisa.seicentools.application.profileMGMT.interfaces.IUserService;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class RimuoviUtenteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        IUserService service = new UserService();
        Utente utente = (Utente) session.getAttribute("utente");
        Utente user = (Utente) request.getAttribute("utente");

        if(utente != null){
            try {
                if(service.deleteUser(user)){
                    request.setAttribute("errore", "Utente eliminato con successo."); //anche se non è un errore, chaimarlo così ci evita di gestire altre stringhe nelle jsp
                }
                else{
                    request.setAttribute("errore", "Errore durante l'eliminazione dell utente.");

                }
                request.getRequestDispatcher("/WEB-INF/jsp/layout.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            request.setAttribute("errore", "/Utente non loggato");
            request.setAttribute("viewPath", "/WEB-INF/views/login.jsp");
            request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
        }
    }
}
