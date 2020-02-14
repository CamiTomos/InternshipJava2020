package com.arobs.servlet;

import com.arobs.domain.User;
import com.arobs.localRepository.LocalRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    LocalRepository localRepository=new LocalRepository();
    // Aici o sa fac update pe user
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
        RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher("/jsp/user.jsp");
        requestDispatcher.include(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        User userToUpdate=new User(username,password);
        User result=localRepository.updateUser(userToUpdate);
        System.out.println(result.toString());
        if(result!=null){
            resp.sendRedirect(req.getContextPath()+"/products");
        }
        else {
            RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher("/jsp/user.jsp");
            requestDispatcher.include(req,resp);
            PrintWriter printWriter=resp.getWriter();
            printWriter.println("Wrong username");
        }
    }
}
