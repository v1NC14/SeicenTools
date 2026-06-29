import it.unisa.seicentools.application.productMGMT.UserProdService;
import it.unisa.seicentools.application.productMGMT.UserService;
import it.unisa.seicentools.application.productMGMT.interfaces.IUserProdService;
import it.unisa.seicentools.application.productMGMT.interfaces.IUserService;
import it.unisa.seicentools.models.Ruolo;
import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.persistence.DAOmodels.UtenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;


@WebServlet(name="AddUtenteServlet", value="/addUtente")
public class AddUtenteServlet  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("utente");
        String password = request.getParameter("password");
        IUserService service = new UserService();

        if(user!= null){
            try{
                service.addUser(user,password);

                session.setAttribute("user",user);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

    }


    @Override
    private void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.sendRedirect(request.getContextPath() + "/gestisciUtente");
        request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
    }
}
