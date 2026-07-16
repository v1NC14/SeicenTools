package it.unisa.seicentools.presentation.OrdineControl;

import it.unisa.seicentools.application.orderMGMT.OrderService;
import it.unisa.seicentools.application.orderMGMT.interfaces.IOrderService;
import it.unisa.seicentools.application.productMGMT.UserProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IUserProdService;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name="OrdiniUtenteServlet"  ,value="/ordini-utente")
public class OridiniUtenteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        IOrderService service = new OrderService();

       //int offset= Integer.parseInt(request.getParameter("offset"));  //non si calcola così
        final int limit = 5;


        try {
            int numOrders = service.getNumOrders(utente.getId());
            List<Ordine> lista = service.getOrdiniUtente(utente.getId());
            request.setAttribute("orders", numOrders);
            request.setAttribute("totalPages",numOrders/limit);
            request.setAttribute("currentPage", 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        request.setAttribute("viewPath", "ordiniUtente.jsp");
        request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
    }

}
