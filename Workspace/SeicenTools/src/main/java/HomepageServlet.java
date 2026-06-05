
package it.unisa.seicentools;


import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "HomepageServlet", value = "/homepage")
public class HomepageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            request.setAttribute("error", "Utente non loggato");
            request.setAttribute("viewPath", "/WEB-INF/views/login.jsp");
            request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
            return;
        }

        switch (utente.getRuolo()) {
            case GUEST://lista prodotti da cui ricavare le categorie
                TaskGuestService guestService = new TaskGuestServiceImpl();
                Guest guesttmp = (Guest) utente;
                try {
                    List<Task> taskList = guestService.getAllTaskGuest(guesttmp);
                    request.setAttribute("taskList", taskList);


                } catch (SQLException e) {
                    request.setAttribute("error", "Errore durante il caricamento della homepage");
                    session.setAttribute("viewPath", "/WEB-INF/views/login.jsp");
                    response.sendRedirect(request.getContextPath() + "/DispatcherServlet");
                }

                session.setAttribute("utente", utente);

                break;

            case USER://guest+sessione+ordineService+prodottiOrdinati
                TaskUserService userService = new TaskUserServiceImpl();
                User usertmp = (User) utente;
                try {
                    List<Task> taskList = userService.getAllTaskSup(usertmp);
                    request.setAttribute("taskList", taskList);
                    System.out.println("Ho caricato i task user: " + taskList.size() + taskList.get(0).getIstruzioni());

                } catch (SQLException e) {
                    request.setAttribute("error", "Errore durante il caricamento della homepage");
                    request.setAttribute("viewPath", "/WEB-INF/views/login.jsp");
                    request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
                }

                session.setAttribute("utente", utente);
                break;

            case ADMIN://utente+sessione+service+ProdottoService
                AdminService gestoreService = new AdminServiceImpl();
                try {
                    List<Utente> userList = adminService.getAllAccount();
                    request.setAttribute("userList", userList);
                } catch (SQLException e) {
                    request.setAttribute("error", "Errore durante il caricamento della homepage");
                    request.setAttribute("viewPath", "/WEB-INF/views/login.jsp");
                    request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
                }

                session.setAttribute("utente", utente);
                break;
        }


        //gestire il passaggio della jsp per quanto riguarda i button nelle jsp
        request.setAttribute("viewPath", "//WEB-INF/views/homepage.jsp");
        request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}