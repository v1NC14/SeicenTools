
package it.unisa.seicentools;
import it.unisa.seicentools.application.accessMGMT.SessionService;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(name="OrdineServlet"  ,value="/ordine")
public class OrdineServlet  extends HttpServlet{

    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Ordine ordine = (Ordine)session.getAttribute("ordine");

        /*
        Qui dovresti utilizzare l'entità ProdottiOrdinati per risalire ai prodotti contenuti nell'ordine
        ricordati di settare come attributo in sessione una List<Prodotto> che verrà mostrata nella pagina ordine
        poi le piccolezze me le vedo io
        */
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
    }
}
