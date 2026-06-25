package it.unisa.seicentools;
import it.unisa.seicentools.application.accessMGMT.SessionService;
import it.unisa.seicentools.application.productMGMT.commonProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IcommonProdService;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.persistence.DAOmodels.ProdottoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

@WebServlet(name="DettagliProdotto", value="/dettagliProd")
public class DettagliProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        IcommonProdService service = new commonProdService();
        int idPrd = Integer.parseInt(req.getParameter("id"));

        try {
            Prodotto tmp = service.getProdotto(idPrd);

            req.setAttribute("prodotto", tmp);
            req.setAttribute("viewPath", "/WEB-INF/views/productDetail.jsp");
            req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException{
        doGet(req, resp);
    }

}
