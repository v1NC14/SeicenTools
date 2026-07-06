import it.unisa.seicentools.application.productMGMT.commonProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IcommonProdService;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.persistence.DAOmodels.ProdottoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/*
* DA COMPLETARE - per ora va bene per provare
* */

@WebServlet(name = "HomepageServlet", value = "/homepage")
public class HomepageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IcommonProdService service = new commonProdService();

        try {
            List<Prodotto> random = service.getRandProd(8);
            List<Prodotto> banner = service.getRandProd(4);
            List<String> categorie = service.getCategorie();

            request.setAttribute("cardProducts", random);
            request.setAttribute("categorie", categorie);
            request.setAttribute("bannerProducts", banner);
            request.setAttribute("viewPath","homepage.jsp");
            request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
