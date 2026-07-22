package it.unisa.seicentools.presentation.OrdineControl;

import it.unisa.seicentools.application.orderMGMT.OrderService;
import it.unisa.seicentools.application.orderMGMT.interfaces.IOrderService;
import it.unisa.seicentools.models.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name="OrdineServlet"  ,value="/ordine")
public class OrdineServlet  extends HttpServlet{

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        IOrderService orderService = new  OrderService();

        if(utente == null || utente.getRuolo() == Ruolo.GUEST){
            request.setAttribute("errore","Utente non loggato");
            response.sendRedirect(request.getContextPath()+"/login");
        }else{
            List<Carrello> carrello = (List<Carrello>) session.getAttribute("carrello");
            try {
                List<Prodotto> prodottiCarrello = orderService.cartToProds(carrello);
                request.setAttribute("prodotti", prodottiCarrello);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if(carrello == null || carrello.isEmpty()){
                response.sendRedirect(request.getContextPath()+"/show-carrello");
                return;
            }

            request.setAttribute("carrello", carrello);
            request.setAttribute("viewPath", "ordine.jsp");
            request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        IOrderService orderService = new OrderService();

        if(utente.getRuolo() == Ruolo.GUEST){
            request.setAttribute("errore", "Utente non loggato");
            response.sendRedirect(request.getContextPath()+"/homepage");
        }else{
            List<Carrello> carrelloSessione = (List<Carrello>) session.getAttribute("carrello");

            if(carrelloSessione == null || carrelloSessione.isEmpty()){
                response.sendRedirect(request.getContextPath()+"/show-carrello");
            }else{
                try {
                    Ordine tmpOrder = new Ordine();

                    tmpOrder.setId_utente(utente.getId());
                    tmpOrder.setTotale(orderService.getTotalFromCart(carrelloSessione));
                    tmpOrder.setQta(orderService.getTotQta(carrelloSessione));
                    tmpOrder.setNumCarta(request.getParameter("numCarta"));
                    tmpOrder.setDataCreazione(Timestamp.valueOf(LocalDateTime.now()));
                    tmpOrder.setIndirizzoConsegna(request.getParameter("indirizzoConsegna"));

                    if(orderService.creaOrdine(tmpOrder, carrelloSessione)){
                        request.setAttribute("errore", "Ordine effettuato correttamente");
                        response.sendRedirect(request.getContextPath()+"/ordini-utente");
                    }else{

                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
