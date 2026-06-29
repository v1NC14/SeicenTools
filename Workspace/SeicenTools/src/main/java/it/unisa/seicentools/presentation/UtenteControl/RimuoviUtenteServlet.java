package it.unisa.seicentools.presentation.UtenteControl;

import it.unisa.seicentools.application.profileMGMT.UserService;
import it.unisa.seicentools.application.profileMGMT.interfaces.IUserService;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class RimuoviUtenteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IUserService service = new UserService();

        Utente user = (Utente) request.getAttribute("utente");

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
    }
}
