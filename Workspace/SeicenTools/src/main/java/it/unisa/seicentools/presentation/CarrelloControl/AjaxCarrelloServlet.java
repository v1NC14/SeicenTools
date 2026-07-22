package it.unisa.seicentools.presentation.CarrelloControl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unisa.seicentools.application.productMGMT.commonProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IcommonProdService;
import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "AjaxCarrelloServlet", value = "/api/carrello")
public class AjaxCarrelloServlet extends HttpServlet {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        List<Carrello> carrelloSessione = (List<Carrello>) session.getAttribute("carrello");
        if (carrelloSessione == null) {
            carrelloSessione = new ArrayList<>();
        }

        // Per inviare i dettagli completi del prodotto insieme alla quantità
        // Creiamo una lista di Map per la risposta JSON
        List<Map<String, Object>> carrelloJSON = new ArrayList<>();
        BigDecimal totaleCarrello = BigDecimal.ZERO;
        IcommonProdService service = new commonProdService();

        try {
            for (Carrello c : carrelloSessione) {
                Prodotto prodotto = service.getProdotto(c.getId_prodotto());
                if (prodotto != null) {
                    Map<String, Object> Map = new HashMap<>();
                    Map.put("id", prodotto.getId());
                    Map.put("nome", prodotto.getNome());
                    Map.put("prezzo", prodotto.getPrezzo());
                    Map.put("imgPath", prodotto.getImgPath());
                    Map.put("qta", c.getQta());

                    BigDecimal subtotal = prodotto.getPrezzo().multiply(new BigDecimal(c.getQta()));
                    Map.put("subtotal", subtotal);
                    totaleCarrello = totaleCarrello.add(subtotal);

                    carrelloJSON.add(Map);
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson(Map.of("error", "Errore nel recupero dei dettagli del carrello: " + e.getMessage())));
            return;
        }

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("carrelloJSON", carrelloJSON);
        responseData.put("tot", totaleCarrello);
        responseData.put("numProdotti", carrelloSessione.size());

        response.getWriter().write(gson.toJson(responseData));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        IcommonProdService prodottoService = new commonProdService();
        List<Carrello> carrelloSessione = (List<Carrello>) session.getAttribute("carrello");
        if (carrelloSessione == null) {
            carrelloSessione = new ArrayList<>();
        }

        try {
            String action = request.getParameter("action");
            int idPrd = Integer.parseInt(request.getParameter("idPrd"));
            Prodotto tmpProdotto = prodottoService.getProdotto(idPrd);

            boolean trovato=false;

            for(Carrello c : carrelloSessione){
                if(c.getId_prodotto() == tmpProdotto.getId()) {
                    switch (action){
                        case "rmv":
                            carrelloSessione.remove(c);
                            break;

                        case "piu":
                            c.setQta(c.getQta() + 1);
                            break;

                        case "meno":
                            if(c.getQta() > 1)
                                c.setQta(c.getQta() - 1);
                            else if(c.getQta() <= 1)
                                carrelloSessione.remove(c);
                            break;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        session.setAttribute("carrello", carrelloSessione);

        // Reindirizza al doGet per restituire lo stato aggiornato del carrello
        doGet(request, response);
    }
}
