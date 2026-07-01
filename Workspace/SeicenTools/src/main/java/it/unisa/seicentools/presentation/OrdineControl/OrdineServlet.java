package it.unisa.seicentools.presentation.OrdineControl;

import it.unisa.seicentools.application.productMGMT.UserProdService;
import it.unisa.seicentools.application.productMGMT.commonProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IUserProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IcommonProdService;
import it.unisa.seicentools.models.Ordine;
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


@WebServlet(name="OrdineServlet"  ,value="/ordine")
public class OrdineServlet  extends HttpServlet{

    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        IUserProdService service = new UserProdService();

        List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");

        if (utente != null) {
            try {
                String numCarta = req.getParameter("numCarta");
                String indirizzoConsegna = req.getParameter("indirizzoConsegna");

                Ordine order = service.cartToOrder(carrello, utente.getId(), numCarta, indirizzoConsegna);

                if (order != null) {
                    req.setAttribute("esito", "Ordine effettuato");
                    session.removeAttribute("carrello");
                } else {
                    req.setAttribute("esito", "Si è verificato un problema, ordine interrotto");
                }
                req.setAttribute("viewPath", "/WEB-INF/views/homepage.jsp");
                req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            req.setAttribute("errore", "/Utente non loggato");
            req.setAttribute("viewPath", "/WEB-INF/views/login.jsp");
            req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        IcommonProdService service = new commonProdService();
        Utente utente =  (Utente) session.getAttribute("utente");

        if(utente != null){
            try {
                List<Prodotto> lista = service.getProdByUtente(utente.getId());

                session.setAttribute("carrello",lista); //rieseguo la scansione del carrello per sicurezza
                request.setAttribute("viewPath", "/WEB-INF/views/ordine.jsp");
                request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            request.setAttribute("errore", "/Utente non loggato");
            request.setAttribute("viewPath", "/WEB-INF/views/login.jsp");
            request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
        }
    }
}
