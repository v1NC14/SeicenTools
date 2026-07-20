package it.unisa.seicentools.presentation.UtenteControl;

import it.unisa.seicentools.application.profileMGMT.UserService;
import it.unisa.seicentools.application.profileMGMT.interfaces.IUserService;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RimuoviUtenteServlet", value="/rmv-utente")
public class RimuoviUtenteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        IUserService service = new UserService();
        Utente utente = (Utente) session.getAttribute("utente");
        int idUser = Integer.parseInt(request.getParameter("id"));
        Utente user = null;

        if(utente != null){
            try {
                user = service.getUser(idUser);

                if(!user.equals(utente)){
                    try {
                        if(service.deleteUser(user)){
                            request.setAttribute("errore", "Utente eliminato con successo."); //anche se non è un errore, chiamarlo così ci evita di gestire altre stringhe nelle jsp
                        }
                        else{
                            request.setAttribute("errore", "Errore durante l'eliminazione dell utente.");
                        }
                        response.sendRedirect(request.getContextPath()+"/gestisci-utente");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                else{
                    request.setAttribute("errore", "Impossibile eliminare utente in sessione.");
                    response.sendRedirect(request.getContextPath()+"/gestisci-utente");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            request.setAttribute("errore", "Utente non loggato");
            request.setAttribute("viewPath", "login.jsp");
            request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
