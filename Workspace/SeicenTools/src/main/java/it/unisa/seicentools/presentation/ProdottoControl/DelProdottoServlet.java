package it.unisa.seicentools.presentation.ProdottoControl;

import it.unisa.seicentools.application.productMGMT.AdminProdService;
import it.unisa.seicentools.application.productMGMT.ImgService;
import it.unisa.seicentools.application.productMGMT.commonProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IAdminProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IcommonProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.ImgServiceInterface;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="DelProdotto", value="/del-Prod")
public class DelProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        Utente utente = (Utente)session.getAttribute("utente");

        if(utente != null){

            IAdminProdService adminPService = new AdminProdService();
            ImgServiceInterface imgService = new ImgService();
            IcommonProdService  commonPService = new commonProdService();
            int idPrd = Integer.parseInt(req.getParameter("id"));
            String basePath = req.getServletContext().getRealPath("");

            try {
                Prodotto tmp = commonPService.getProdotto(idPrd);

                if(adminPService.deleteProdotto(tmp) && imgService.deleteImage(basePath, tmp.getImgPath())){
                    req.setAttribute("errore", "Prodotto eliminato con successo");
                }else{
                    req.setAttribute("errore", "Problemi con l'eliminazione del prodotto");
                }

                req.setAttribute("viewPath", "/WEB-INF/views/homepage.jsp");
                req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            req.setAttribute("errore", "Utente non loggato");
            req.setAttribute("viewPath", "/WEB-INF/views/login.jsp");
            req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
        }
    }
}
