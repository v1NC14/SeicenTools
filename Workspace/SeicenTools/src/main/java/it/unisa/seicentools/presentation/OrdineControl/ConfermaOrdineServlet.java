package it.unisa.seicentools.presentation.OrdineControl;

import it.unisa.seicentools.application.orderMGMT.OrderService;
import it.unisa.seicentools.application.orderMGMT.interfaces.IOrderService;
import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.persistence.DAOmodels.OrdineDAO;
import it.unisa.seicentools.persistence.interfaces.IOrdineDAO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


@WebServlet(name="ConfermaOrdineSerlvet", value="/conferma-ordine")
public class ConfermaOrdineServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        List<Carrello> carrello = (List<Carrello>) session.getAttribute("carrello");
        IOrderService orderService = new OrderService();
        IOrdineDAO orderDAO = new OrdineDAO();

        try {
            int qtaTotale = orderService.getTotQta(carrello);
            BigDecimal total = orderService.getTotalFromCart(carrello);

            Ordine ordine = new Ordine();
            ordine.setId_utente(utente.getId());
            ordine.setTotale(total);
            ordine.setQta(qtaTotale);
            ordine.setNumCarta(request.getParameter("numCarta"));
            ordine.setIndirizzoConsegna(request.getParameter("indirizzo"));

            if (orderDAO.creaOrdine(ordine, carrello)) {
                session.removeAttribute("carrello");
                response.sendRedirect(request.getContextPath() + "/ordini-utente");
            } else {
                request.setAttribute("errore", "Problemi con la conferma dell'ordine");
                response.sendRedirect(request.getContextPath() + "/show-carrello");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}