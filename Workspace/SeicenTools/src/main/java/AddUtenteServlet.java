import it.unisa.seicentools.models.Utente;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        DatabaseMetaData DatabaseConnection;
        try (Connection conn = DatabaseConnection.getConnection()) {

            String sql = "INSERT INTO Utenti (username, password) VALUES (?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.getWriter().write("Utente creato con successo");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Errore nella creazione dell'utente");
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Errore: " + e.getMessage());
        }
    }


    @Override
    private void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.sendRedirect(request.getContextPath() + "/gestisciUtente");
        request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
    }
}
