package it.unisa.seicentools.presentation.UtenteControl;

import it.unisa.seicentools.application.profileMGMT.UserService;
import it.unisa.seicentools.application.profileMGMT.interfaces.IUserService;
import it.unisa.seicentools.models.Ruolo;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name="AddUtenteServlet", value="/add-utente")
public class AddUtenteServlet  extends HttpServlet {


    //si deve cambiare la servlet capendo bene cosa fare nel doget e cosa fare nel dopost, per ora lascio così - 13:39 01/07/2026
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        IUserService service = new UserService();
        Utente user = (Utente)session.getAttribute("utente");

        if(user!=null){
            //dati che verranno presi da un form.
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            Ruolo ruolo = Ruolo.valueOf(request.getParameter("ruolo"));
            String password = request.getParameter("password");

            Utente utente = new Utente();
            utente.setNome(nome);
            utente.setEmail(email);
            utente.setRuolo(ruolo);

            try {
                if (service.addUser(utente, password)) {
                    request.setAttribute("messaggio", "Utente aggiuto con successo.");
                    request.setAttribute("viewPath", "gestioneUtenti.jsp");

                } else {
                    request.setAttribute("errore", "Errore durante la registrazione del nuovo utente.");
                    request.setAttribute("viewPath", "homepage.jsp");
                }
                request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }else {
            request.setAttribute("errore", "/Utente non loggato");
            request.setAttribute("viewPath", "login.jsp");
            request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/gestisciUtente");
        request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
    }
}
