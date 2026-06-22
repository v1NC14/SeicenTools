import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.persistence.DAOmodels.ProdottoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "RmvFromCartServlet", value="rmv-cart")
public class RmvFromCarrelloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("utente") != null) {
            try {
                Carrello carrello = (Carrello) session.getAttribute("carrello");
                int idPrd = Integer.parseInt(req.getParameter("id"));

                CarrelloDAO cartDAO = new CarrelloDAO();
                ProdottoDAO prodottoDAO = new ProdottoDAO();

                Prodotto tmp =  prodottoDAO.getProdottoById(idPrd);

                cartDAO.remove(carrello, tmp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
