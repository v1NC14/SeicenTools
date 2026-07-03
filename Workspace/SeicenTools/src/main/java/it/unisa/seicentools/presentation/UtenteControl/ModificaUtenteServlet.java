package it.unisa.seicentools.presentation.UtenteControl;

import it.unisa.seicentools.application.profileMGMT.UserService;
import it.unisa.seicentools.application.profileMGMT.interfaces.IUserService;
import it.unisa.seicentools.models.Ruolo;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ModificaUtenteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        IUserService service = new UserService();
        Utente user = (Utente)session.getAttribute("utente");

        if(user != null){
            //dati che verranno presi da un form.
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");

            Utente utente = new Utente();
            utente.setNome(nome);
            utente.setEmail(email);
            utente.setRuolo(Ruolo.USER);
            try {
                if(service.updateUser(utente)){
                    request.setAttribute("errore", "Dati aggiornati con successo.");
                }
                else{
                    request.setAttribute("errore", "Errore durante l'aggiornamento.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("viewPath", "WEB-INF/views/gestioneUtenti.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/layout.jsp").forward(request, response);
        }else {
            request.setAttribute("errore", "/Utente non loggato");
            request.setAttribute("viewPath", "/WEB-INF/views/login.jsp");
            request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente admin = (Utente) session.getAttribute("utente");
        IUserService service = new UserService();

        if(admin != null){
            try {
                Utente user = service.getUser(Integer.parseInt(request.getParameter("id")));

                request.setAttribute("user", user);
                request.setAttribute("viewPath", "WEB-INF/views/modificaUtente.jsp");
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
