package it.unisa.seicentools.presentation.ProdottoControl;

import it.unisa.seicentools.application.productMGMT.commonProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IcommonProdService;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

@WebServlet(name="DettagliProdotto", value="/dettagli-prod")
public class DettagliProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        IcommonProdService service = new commonProdService();
        int idPrd = Integer.parseInt(req.getParameter("id"));

        try {
            Prodotto tmp = service.getProdotto(idPrd);

            req.setAttribute("prodotto", tmp);
            req.setAttribute("viewPath", "productDetail.jsp");
            req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
