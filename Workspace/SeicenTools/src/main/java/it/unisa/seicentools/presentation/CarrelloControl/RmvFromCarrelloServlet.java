package it.unisa.seicentools.presentation.CarrelloControl;

import it.unisa.seicentools.application.productMGMT.UserProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IUserProdService;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RmvFromCartServlet", value="rmv-cart")
public class RmvFromCarrelloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        IUserProdService service = new UserProdService();
        if (utente != null) {
            try {
                List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
                int idPrd = Integer.parseInt(req.getParameter("id"));

                if(service.rmvFromCarrello(utente.getId(), idPrd))
                    carrello.remove(idPrd);


                session.setAttribute("carrello", carrello);
                resp.sendRedirect(req.getContextPath() + "/show-carrello");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
