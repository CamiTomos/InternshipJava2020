package com.arobs.servlet;

import com.arobs.manager.UserManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserManager userManager=new UserManager();
    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext=getServletContext();
        RequestDispatcher requestDispatcher=servletContext.getRequestDispatcher("/jsp/login.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        User user=new User(username,password);

        if (userManager.getUserByCredentials(username,password)!=null) {
            System.out.println("DA");
            HttpSession session=req.getSession();
            session.setAttribute("userLoggedIn",username);
            resp.sendRedirect(req.getContextPath()+"/products");
        } else {
            System.out.println("NU");
            printWriter.println("Login failed! Username or password is incorrect!");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/login.jsp");
            requestDispatcher.include(req, resp);
        }
    }

}
