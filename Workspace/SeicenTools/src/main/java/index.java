import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class index  extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path="/WEB-INF/jsp/index.jsp";
        try{
            req.setAttribute("viewPath","WEB-INF/views/login.jsp");
            req.getRequestDispatcher("WEB-INF/views/layout.jsp").forward(req,resp);
        }
        catch(ServletException e){
            throw new RuntimeException(e);
        }
    }
}
