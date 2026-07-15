package it.unisa.seicentools.presentation.OrdineControl;

import it.unisa.seicentools.application.productMGMT.UserProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IUserProdService;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name="OrdiniUtenteServlet"  ,value="/ordini-utente")
public class OridiniUtenteServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        IUserProdService service = new UserProdService();
        int idUtente=utente.getId();
        /*int orders=Integer.parseInt(request.getParameter("totalOrders"));
        int offset= Integer.parseInt(request.getParameter("offset"));
        int limit= Integer.parseInt(request.getParameter("limit"));*/

        request.setAttribute("orders", orders);
        request.setAttribute("totalPages",orders/limit);
        request.setAttribute("currentPage", 0);
        List<Ordine> lista = service.getOrdiniUtente(idUtente);



        request.setAttribute("viewPath", "ordiniUtente.jsp");
        request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
    }

}
