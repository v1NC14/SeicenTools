package it.unisa.seicentools.presentation.UtenteControl;

import it.unisa.seicentools.application.profileMGMT.UserService;
import it.unisa.seicentools.application.profileMGMT.interfaces.IUserService;
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

@WebServlet(name="GestisciUtenteServlet", value="/gestisciUtente")
public class GestisciUtenteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        IUserService service = new UserService();

        if (utente != null) {
            try {
                List<Utente> utentiRegistrati = service.getAllUtenti();

                session.setAttribute("listaUtenti", utentiRegistrati);
                resp.sendRedirect(req.getContextPath()+"gestioneUtenti.jsp");
                req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
            } catch (SQLException e) {

                throw new RuntimeException(e);
            }
        }else{
            req.setAttribute("errore", "/Utente non loggato");
            req.setAttribute("viewPath", "/WEB-INF/views/login.jsp");
            req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
