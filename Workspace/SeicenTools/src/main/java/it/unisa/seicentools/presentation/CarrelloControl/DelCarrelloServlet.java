package it.unisa.seicentools.presentation.CarrelloControl;

import it.unisa.seicentools.application.productMGMT.UserProdService;
import it.unisa.seicentools.application.productMGMT.commonProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IUserProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IcommonProdService;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "DelCartServlet", value="del-cart")
public class DelCarrelloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente utente = (Utente)session.getAttribute("utente");
        IcommonProdService service = new commonProdService();

         try {
             if(service.cancellaCarrello(utente.getId()))
                 session.removeAttribute("carrello");

             req.setAttribute("viewPath", "/WEB-INF/views/carrello.jsp");
             req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
         }catch(Exception e){
             e.printStackTrace();
         }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
