

package it.unisa.seicentools;
import it.unisa.seicentools.models.Prodotto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


@WebServlet(name="AddCarrelloServlet" , value="/add-carrello")
public class AddCarrelloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(session.getAttribute("user") != null){
            try{
                List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");

                Prodotto tmp = (Prodotto) session.getAttribute("prodotto");
                carrello.add(tmp);

                CarrelloDAO cartDAO = new CarrelloDAO();

                cartDAO.add(carrello, tmp);

                //String psAdd = "INSERT INTO carrelloutente (id_utente, id_podotto) VALUES(?, ?)";"

                session.setAttribute("carrello", carrello);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
