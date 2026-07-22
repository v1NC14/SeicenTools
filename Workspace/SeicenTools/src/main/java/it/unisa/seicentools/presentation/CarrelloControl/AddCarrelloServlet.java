package it.unisa.seicentools.presentation.CarrelloControl;

import it.unisa.seicentools.application.productMGMT.commonProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IcommonProdService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name="AddCarrelloServlet" , value="/add-carrello")
public class AddCarrelloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente user = (Utente) session.getAttribute("utente");
        IcommonProdService prodottoService = new commonProdService();

        int qta = Integer.parseInt(req.getParameter("qta"));

        try{
            Prodotto tmpProdotto = prodottoService.getProdotto(Integer.parseInt(req.getParameter("id"))); // Prodotto da aggiungere
            if (tmpProdotto == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Prodotto non trovato in sessione.");
                return;
            }

            // Recupera il carrello dalla sessione, ora come List<Carrello>
            List<Carrello> carrelloSessione = (List<Carrello>) session.getAttribute("carrello");
            if (carrelloSessione == null) {
                carrelloSessione = new ArrayList<>();
            }

            boolean trovato=false;

            for(Carrello c : carrelloSessione){
                if(c.getId_prodotto() == tmpProdotto.getId()) {
                    c.setQta(c.getQta() + 1);
                    trovato = true;
                }
            }

            if(!trovato){
                Carrello newItem = new Carrello(user.getId(), tmpProdotto.getId(), qta);
                carrelloSessione.add(newItem);
            }

            // Aggiorna il carrello nella sessione
            session.setAttribute("carrello", carrelloSessione);
            session.setAttribute("cartMessage", "Prodotto aggiunto al carrello!");

            // Reindirizza alla pagina del prodotto o a una conferma
            resp.sendRedirect(req.getContextPath() + "/dettagli-prod?idPrd=" + tmpProdotto.getId());
        }catch(Exception e){
            // Gestione dell'errore, ad esempio reindirizzando a una pagina di errore
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'aggiunta al carrello: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
