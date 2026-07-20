package it.unisa.seicentools.presentation.UtenteControl;

import it.unisa.seicentools.application.productMGMT.commonProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IcommonProdService;
import it.unisa.seicentools.application.profileMGMT.UserService;
import it.unisa.seicentools.application.profileMGMT.interfaces.IUserService;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="GestisciUtenteServlet", value="/gestisci-utente")
public class GestisciUtenteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Utente admin = (Utente) session.getAttribute("utente");
        IUserService service = new UserService();

        if(admin != null){
            try {
                List<Utente> listaUtenti = service.getAllUser();

                listaUtenti.removeIf(utente -> admin.getId() == utente.getId());

                session.setAttribute("listaUtenti",listaUtenti);
                request.setAttribute("viewPath", "gestioneUtenti.jsp");
                request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            request.setAttribute("errore", "Utente non loggato");
            request.setAttribute("viewPath", "login.jsp");
            request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
        }
    }
}
