package it.unisa.seicentools.presentation.ProdottoControl;

import it.unisa.seicentools.application.productMGMT.AdminProdService;
import it.unisa.seicentools.application.productMGMT.ImgService;
import it.unisa.seicentools.application.productMGMT.commonProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IAdminProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IcommonProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.ImgServiceInterface;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.models.Ruolo;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 10 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)

@WebServlet(name="ModificaProdotto", value="/mod-prod")
public class ModificaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente utente = (Utente)session.getAttribute("utente");
        int idPrd = Integer.parseInt(req.getParameter("id"));

        if(utente != null){
            IcommonProdService service = new commonProdService();

            try {
                Prodotto tmp = service.getProdotto(idPrd);
                List<String> categorie = service.getCategorie();

                categorie.add("placeholder");
                req.setAttribute("categorie", categorie);
                req.setAttribute("prodotto", tmp);
                req.setAttribute("viewPath", "modificaProdotto.jsp");
                req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            req.setAttribute("errore", "/Utente non loggato");
            req.setAttribute("viewPath", "login.jsp");
            req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        Utente utente = (Utente)session.getAttribute("utente");

        if(utente != null && utente.getRuolo() == Ruolo.ADMIN){
            try{
                IAdminProdService adminService = new AdminProdService();
                IcommonProdService prodottoService = new  commonProdService();
                ImgServiceInterface imgService = new ImgService();
                Prodotto tmp = prodottoService.getProdotto(Integer.parseInt(req.getParameter("idPrd")));

                Part img = req.getPart("img");

                // path reale della cartella imgs/products
                String uploadPath = req.getServletContext().getRealPath("/imgs/products");

                //imgService.imgMGMT(uploadPath, img);

                tmp.setNome(req.getParameter("nome"));
                tmp.setCategoria(req.getParameter("categoria"));
                tmp.setDescrizione(req.getParameter("descrizione"));
                tmp.setPrezzo(BigDecimal.valueOf(Float.parseFloat(req.getParameter("prezzo")))); //String basePath = req.getServletContext().getRealPath("");
                tmp.setImgPath(imgService.saveImage(img, uploadPath)); //basePath
                tmp.setDisponibilita(Integer.parseInt(req.getParameter("disponibilita")));

                if(adminService.updateProdotto(tmp)){
                    req.setAttribute("errore", "Prodotto aggiornato con successo");
                    resp.sendRedirect(req.getContextPath()+"/dettagli-prod?idPrd=" + tmp.getId());
                }else{
                    req.setAttribute("errore", "Errore durante l'aggiornamento del prodotto");
                    resp.sendRedirect(req.getContextPath()+"/homepage");
                }
            }catch(SQLException e){
                e.printStackTrace();
                //throw new SQLException(e);
            }
        }else{
            req.setAttribute("errore", "Utente non loggato");
            resp.sendRedirect(req.getContextPath()+"/login");
        }
    }
}
