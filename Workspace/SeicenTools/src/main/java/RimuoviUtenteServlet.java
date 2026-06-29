import it.unisa.seicentools.application.productMGMT.UserService;
import it.unisa.seicentools.application.productMGMT.interfaces.IUserService;
import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.persistence.DAOmodels.UtenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class RimuoviUtenteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("utente");
        IUserService service = new UserService();

        if(user!= null){
            try{
                service.deleteUser(user);

                session.setAttribute("user",user);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}
