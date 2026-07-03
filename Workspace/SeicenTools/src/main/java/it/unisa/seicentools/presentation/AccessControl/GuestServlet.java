package it.unisa.seicentools.presentation.AccessControl;

import it.unisa.seicentools.application.accessMGMT.SessionService;
import it.unisa.seicentools.models.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class GuestServlet extends HttpServlet {
/*
* si deve inserire in sessione un oggetto Utente temporaneo con id temporaneo
* */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionService sessionService = new SessionService();


        Utente utente = sessionService.doGuest();
        HttpSession session = request.getSession();

        if(utente != null){
            session.setAttribute("utente", utente);
            request.setAttribute("viewPath", "/WEB-INF/views/homepage.jsp");
            request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
        }
        else{
            request.setAttribute("error", "Problemi con accesso come guest");
            request.setAttribute("viewPath", "/WEB-INF/views/login.jsp");
            request.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(request, response);
        }
    }

}
