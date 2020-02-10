package com.arobs.servlet;

import com.arobs.domain.Product;
import com.arobs.localRepository.LocalRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    LocalRepository localRepository = new LocalRepository();
    List<Product> myList = new ArrayList<>();

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
        resp.setContentType("text/html");
        ServletContext servletContext = getServletContext();
        List<Product> products = localRepository.getProducts();
        req.setAttribute("allProducts", products);
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/products.jsp");
        requestDispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        req.setAttribute("myProducts",myList);
        List<Product> products = localRepository.getProducts();
        req.setAttribute("allProducts",products);
        String productName = req.getParameter("productName");
        String productPrice = req.getParameter("productPrice");
        Product productToBeAdded = new Product(productName, Double.parseDouble(productPrice));
        String errorString="";
        if(localRepository.findProduct(productToBeAdded)!=null){
            myList.add(productToBeAdded);
        }
        else {
            errorString+="Product does not exist in shop!";
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/products.jsp");
        requestDispatcher.include(req, resp);
        printWriter.println(errorString);
        System.out.println("My list size " + myList.size());
    }
}
