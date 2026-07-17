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

        final int limit = 5;

        int page = 1;
        int offset;
        int actualPage = Integer.parseInt(request.getParameter("page"));

        if(actualPage > 1)
            page = actualPage;

        try {
            int numOrders = service.getNumOrders(utente.getId());
            int totalPages = (numOrders + limit - 1) / limit;
            if(page > 1){
                int n = page - 1;
                offset = n * limit;
            }else{
                offset = 1;
            }
            List<Ordine> lista = service.getOrdiniUtente(utente.getId());
            request.setAttribute("numOrders", numOrders);
            request.setAttribute("page", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("orders",lista);
            request.setAttribute("offset", offset);
            request.setAttribute("viewPath","ordiniUtente.jsp");
            request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}