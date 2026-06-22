

package it.unisa.seicentools;
import it.unisa.seicentools.application.accessMGMT.SessionService;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.swing.*;
import java.io.IOException;
import java.util.List;


@WebServlet(name="CarrelloServlet" , value="/carrello")
public class CarrelloServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");

        /*
        * ti ho creato le entità per il carrello e la relativa assocciazione N-1 / 1-N quindi adesso ti devi solo
        * ricavare la lista dei prodotti dalle associazioni con le tabelle
        * */
    }

}
