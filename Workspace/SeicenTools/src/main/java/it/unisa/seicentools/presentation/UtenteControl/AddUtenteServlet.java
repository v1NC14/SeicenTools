import it.unisa.seicentools.models.Ruolo;
import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.persistence.DAOmodels.UtenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;


@WebServlet(name="AddUtenteServlet", value="/addUtente")
public class AddUtenteServlet  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        boolean aggiornamentoDati= UtenteDAO.registraUtente(utente);
        if(aggiornamentoDati){
            session.setAttribute("utente", utente);
            request.setAttribute("messaggio", "Utente aggiuto con successo.");
            request.getRequestDispatcher("/WEB-INF/jsp/layout.jsp").forward(request, response);
        }
        else{
            request.setAttribute("errore", "Errore durante la registrazione del nuovo utente.");
            request.getRequestDispatcher("/WEB-INF/jsp/layout.jsp").forward(request, response);
        }


    }


    @Override
    private void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.sendRedirect(request.getContextPath() + "/gestisciUtente");
        request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
    }
}
