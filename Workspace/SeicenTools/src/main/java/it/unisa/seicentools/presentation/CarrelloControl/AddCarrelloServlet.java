

package it.unisa.seicentools.presentation.CarrelloControl;
import it.unisa.seicentools.application.productMGMT.UserProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IUserProdService;
import it.unisa.seicentools.models.Carrello;
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


@WebServlet(name="AddCarrelloServlet" , value="/add-carrello")
public class AddCarrelloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente user = (Utente) session.getAttribute("utente");
        int qta = Integer.parseInt(req.getParameter("qta"));
        IUserProdService service = new UserProdService();

        if(user != null){
            try{
                Prodotto tmp = (Prodotto) session.getAttribute("prodotto");
                List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");

                Carrello cart = new Carrello(); cart.setId_prodotto(tmp.getId()); cart.setId_utente(user.getId());

                if(service.aggiungiAlCarrello(cart,qta)){
                    carrello.add(tmp);
                }

                session.setAttribute("carrello", carrello);
                resp.sendRedirect(req.getContextPath() + "/dettagliProd?id=" + tmp.getId());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
