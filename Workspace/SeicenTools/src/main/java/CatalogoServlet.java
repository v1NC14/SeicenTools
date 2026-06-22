import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.persistence.DAOmodels.ProdottoDAO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class CatalogoServlet extends HttpServlet {

    @WebServlet(name="CatalogoServlet", value="7catalogo")
    private void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Prodotto> catalogo = (List<Prodotto>) session.getAttribute("catalogo");

        String filtro = req.getParameter("filtro");
        try {
            ProdottoDAO dao = new ProdottoDAO();
            List<Prodotto> lista = dao.getProdottoByCategoria(filtro);

            session.setAttribute("listaProdotti",lista);
            req.setAttribute("viewPath", "/WEB-INF/views/catalogo.jsp");
            req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
        }
        catch (Exception e) {}
    }
}
