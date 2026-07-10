package it.unisa.seicentools.presentation.AccessControl;

import it.unisa.seicentools.application.productMGMT.commonProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IAdminProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IcommonProdService;
import it.unisa.seicentools.application.profileMGMT.UserService;
import it.unisa.seicentools.application.profileMGMT.interfaces.IUserService;
import it.unisa.seicentools.models.Ruolo;
import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.persistence.DAOmodels.CarrelloDAO;
import it.unisa.seicentools.persistence.interfaces.ICarrelloDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="logoutServlet", value="/logout")
public class logoutServlet  extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente)session.getAttribute("utente");
        IcommonProdService service = new commonProdService();

        if (session != null) {
            if(utente.getRuolo().equals(Ruolo.GUEST)) {
                try {
                    service.cancellaCarrello(utente.getId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            session.setAttribute("utente", null);
            session.invalidate();//invalida la sessione.
        }
        request.setAttribute("error", "logout effettuato");
        request.setAttribute("viewPath", "login.jsp");
        request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
