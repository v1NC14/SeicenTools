package it.unisa.seicentools.presentation.ProdottoControl;

import it.unisa.seicentools.application.productMGMT.AdminProdService;
import it.unisa.seicentools.application.productMGMT.ImgService;
import it.unisa.seicentools.application.productMGMT.interfaces.IAdminProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.ImgServiceInterface;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(name="CreaProdotto", value="/crea-prod")
public class CreaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        Utente utente = (Utente)session.getAttribute("utente");

        if(utente != null){
            IAdminProdService service = new AdminProdService();
            ImgServiceInterface imgService = new ImgService();
            Prodotto tmp = new Prodotto();

            Part img = req.getPart("img");

            // path reale della cartella imgs/products
            String uploadPath = req.getServletContext().getRealPath("/imgs/products");

            imgService.imgMGMT(uploadPath, img);

            tmp.setNome(req.getParameter("nome"));
            tmp.setCategoria(req.getParameter("categoria"));
            tmp.setDescrizione(req.getParameter("descrizione"));
            tmp.setPrezzo(BigDecimal.valueOf(Float.parseFloat(req.getParameter("prezzo"))));
            tmp.setImgPath(imgService.saveImage(img, uploadPath));
            tmp.setDisponibilita(Integer.parseInt(req.getParameter("disponibilita")));

            try{
                if(service.creaProdotto(tmp)){
                    req.setAttribute("errore", "Prodotto creato con successo");
                    req.setAttribute("viewPath", "/WEB-INF/views/productDetail.jsp");
                }else{
                    req.setAttribute("errore", "Errore durante la registrazione del prodotto");
                    req.setAttribute("viewPath", "/WEB-INF/views/homepage.jsp");
                }

                req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }else{
            req.setAttribute("errore", "/Utente non loggato");
            req.setAttribute("viewPath", "/WEB-INF/views/login.jsp");
            req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
        }
    }
}
