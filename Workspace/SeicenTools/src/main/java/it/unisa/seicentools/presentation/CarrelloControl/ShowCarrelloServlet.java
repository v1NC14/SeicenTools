package it.unisa.seicentools.presentation.CarrelloControl;

import it.unisa.seicentools.application.productMGMT.UserProdService;
import it.unisa.seicentools.application.productMGMT.commonProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IUserProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IcommonProdService;
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


@WebServlet(name="ShowCarrelloServlet" , value="/show-carrello")
public class ShowCarrelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente user = (Utente) session.getAttribute("utente");
        IcommonProdService service = new commonProdService();


        try{
            List<Prodotto> carrello = service.getProdByUtente(user.getId());

            session.setAttribute("prodottiUtente", carrello);
            req.setAttribute("viewPath", "carrello.jsp");
            req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
        }catch(Exception e){
            throw new  RuntimeException(e);
        }
    }
}
