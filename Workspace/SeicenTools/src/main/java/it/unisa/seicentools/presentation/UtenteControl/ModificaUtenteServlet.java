package it.unisa.seicentools.presentation.UtenteControl;

import it.unisa.seicentools.models.Ruolo;
import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.persistence.DAOmodels.UtenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ModificaUtenteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //dati che verranno presi da un form.
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        Ruolo ruolo = request.getParameter("ruolo");
        String password = request.getParameter("password");


        Utente utente = new Utente();
        utente.setNome(nome);
        utente.setEmail(email);
        utente.setRuolo(ruolo);
        utente.setHashpwd(password);

        boolean aggiornamentoDati= UtenteDAO.updateUtente(utente);
        if(aggiornamentoDati){
            session.setAttribute("utente", utente);
            request.setAttribute("messaggio", "Dati aggiornati con successo.");
            request.getRequestDispatcher("/WEB-INF/jsp/layout.jsp").forward(request, response);
        }
        else{
            request.setAttribute("errore", "Errore durante l'aggiornamento.");
            request.getRequestDispatcher("/WEB-INF/jsp/layout.jsp").forward(request, response);
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/gestisciUtente");
        request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
    }
}
