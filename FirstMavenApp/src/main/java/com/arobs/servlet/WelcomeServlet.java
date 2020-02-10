package com.arobs.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "WelcomeServlet", urlPatterns = "/welcome")
public class WelcomeServlet extends HttpServlet {

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher welcomeFileDispatcher = servletContext.getRequestDispatcher("/jsp/welcome.jsp");
        welcomeFileDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String parameterValue = req.getParameter("page");
        if (parameterValue.equals("Login")) {
            resp.sendRedirect(req.getContextPath()+"/login");
            System.out.println("Redirected to Login");
        }
        if (parameterValue.equals("Register")) {
//            add redirect to register page
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
//            requestDispatcher.forward(req, resp);
            System.out.println("Redirected to Register");
        }

    }
}
