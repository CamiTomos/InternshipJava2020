package com.arobs.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "FirstServlet", urlPatterns = "/test-jsp", initParams = {
        @WebInitParam(name = "fName", value = "fValue")
})
public class FirstServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = getInitParameter("fName");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("First Name: " + firstName);

        List<String> names = new ArrayList<String>();
        names.add("Ana");
        names.add("Dan");
        names.add("Kate");
        names.add("Jess");
        names.add("Tessa");

        for (String name : names
        ) {
            printWriter.print(name + ",");
        }
        printWriter.close();

    }
}
