import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.persistence.DAOmodels.UtenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class RimuoviUtenteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        Utente utente =request.getParameter("utente");

        boolean eliminato= UtenteDAO.rimuoviUtente(utente);
        if(eliminato){
            request.setAttribute("messaggio", "Utente eliminato con successo.");
            request.getRequestDispatcher("/WEB-INF/jsp/layout.jsp").forward(request, response);
        }
        else{
            request.setAttribute("errore", "Errore durante l'eliminazione dell utente.");
            request.getRequestDispatcher("/WEB-INF/jsp/layout.jsp").forward(request, response);
        }
    }
}
