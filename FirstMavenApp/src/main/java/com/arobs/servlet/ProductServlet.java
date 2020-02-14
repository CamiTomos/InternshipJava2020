package com.arobs.servlet;

import com.arobs.domain.Product;
import com.arobs.domain.ProductDTO;
import com.arobs.manager.ProductManager;
import com.arobs.manager.SaleManager;

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
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    ProductManager productManager = new ProductManager();
    SaleManager saleManager = new SaleManager();

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
        HttpSession session = req.getSession(false);
        if (session != null) {
            resp.setContentType("text/html");
            ServletContext servletContext = getServletContext();
            List<ProductDTO> products = productManager.findAllProducts();
            req.setAttribute("myProducts", productManager.getAllProductsForUser((String) session.getAttribute("userLoggedIn")));
            req.setAttribute("allProducts", products);
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/products.jsp");
            requestDispatcher.include(req, resp);
            resp.getWriter().println(session.getAttribute("userLoggedIn"));
        } else {
            resp.getWriter().println("You are not logged in");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            resp.setContentType("text/html");
            PrintWriter printWriter = resp.getWriter();
            String username = (String) session.getAttribute("userLoggedIn");
            String productName = req.getParameter("productName");
            int productQuantity = Integer.parseInt(req.getParameter("productQuantity"));
            saleManager.insertSaleForUser(username, productName, productQuantity);
            List<ProductDTO> products = productManager.findAllProducts();
            req.setAttribute("myProducts", productManager.getAllProductsForUser(username));
            req.setAttribute("allProducts", products);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/products.jsp");
            requestDispatcher.include(req, resp);
        }
        else{
            resp.getWriter().println("You are not logged in");
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession(false);
//        if (session != null) {
//            resp.setContentType("text/html");
//            PrintWriter printWriter = resp.getWriter();
//            String productName = req.getParameter("productName");
//            String productPrice = req.getParameter("productPrice");
//            Product productToBeAdded = new Product(productName, Double.parseDouble(productPrice));
//            String errorString = "";
//            if (localRepository.findProduct(productToBeAdded) != null) {
//                List<Product> myList=userMap.get(session.getAttribute("userLoggedIn"));
//                if(myList==null){
//                    myList=new ArrayList<>();
//                }
//                myList.add(productToBeAdded);
//                localRepository.addProductForUser((String) session.getAttribute("userLoggedIn"),userMap.get((String) session.getAttribute("userLoggedIn")));
//                System.out.println("My list size " + myList.size());
//            } else {
//                errorString += "Product does not exist in shop!";
//            }
//            req.setAttribute("myProducts", userMap.get(session.getAttribute("userLoggedIn")));
//            List<Product> products = localRepository.getProducts();
//            req.setAttribute("allProducts", products);
//            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/products.jsp");
//            requestDispatcher.include(req, resp);
//            printWriter.println(errorString);
//        } else {
//            resp.getWriter().println("You are not logged in");
//        }
//    }
}
