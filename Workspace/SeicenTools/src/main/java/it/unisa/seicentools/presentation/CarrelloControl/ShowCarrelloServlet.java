package it.unisa.seicentools.presentation.CarrelloControl;

import it.unisa.seicentools.models.Carrello;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="ShowCarrelloServlet" , value="/show-carrello")
public class ShowCarrelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try{
            List<Carrello> carrelloSessione = (List<Carrello>) session.getAttribute("carrello");
            if (carrelloSessione == null) {
                carrelloSessione = new ArrayList<>();
                session.setAttribute("carrello", carrelloSessione);
            }

            session.setAttribute("carrello", carrelloSessione);
            req.setAttribute("viewPath", "carrello.jsp");
            req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
        }catch(Exception e){
            throw new  RuntimeException(e);
        }
    }
}