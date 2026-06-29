package it.unisa.seicentools.presentation.UtenteControl;

import it.unisa.seicentools.application.profileMGMT.UserService;
import it.unisa.seicentools.application.profileMGMT.interfaces.IUserService;
import it.unisa.seicentools.models.Ruolo;
import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.persistence.DAOmodels.UtenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class ModificaUtenteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        IUserService service = new UserService();

        //dati che verranno presi da un form.
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        Ruolo ruolo = Ruolo.valueOf(request.getParameter("ruolo"));

        Utente utente = new Utente();
        utente.setNome(nome);
        utente.setEmail(email);
        utente.setRuolo(ruolo);

        try {
            if(service.updateUser(utente)){
                session.setAttribute("utente", utente);
                request.setAttribute("messaggio", "Dati aggiornati con successo.");
            }
            else{
                request.setAttribute("errore", "Errore durante l'aggiornamento.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/layout.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/gestisciUtente");
        request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
    }
}
