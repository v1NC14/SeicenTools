package it.unisa.seicentools;
import it.unisa.seicentools.application.accessMGMT.SessionService;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

@WebServlet(name="DettagliProdotto", value="/dettagliProd")
public class DettagliProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Prodotto prod = (Prodotto)session.getAttribute("prodotto");


        /*
         * qui va bene ma ricorda che ogni volta che ci troviamo in una pagina dove si può visualizzare un prodotto
         * (quindi, ordine, homepage, catalogo, carrello, ...) si deve rimandare alla pagina dettaglio con lo specifico
         * id del prodotto
         */

    }

}
