package it.unisa.seicentools;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="logoutServlet", value="/LogoutServlet")
public class logoutServlet  extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();//invalida la sessione.
        }
        request.setAttribute("error", "logout effettuato");
        request.setAttribute("viewPath", "WEB-INF/views/logout.jsp");
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw ServletException,  IOException{
            doPost(request, response);
        }
    }
}
