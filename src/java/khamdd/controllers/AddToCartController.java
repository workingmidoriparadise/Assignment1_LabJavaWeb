/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khamdd.dtos.CartObj;
import khamdd.dtos.MyOrderDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class AddToCartController extends HttpServlet {

    private final static Logger LOG = Logger.getLogger(AddToCartController.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            CartObj cart = (CartObj) session.getAttribute("cart");
            String productID = request.getParameter("txtAddProductID");
            String productName = request.getParameter("txtAddProductName");
            String price = request.getParameter("txtAddPrice");
            String image = request.getParameter("txtAddImage");

            if (cart == null) {
                cart = new CartObj(session.getAttribute("fullname").toString());
            }
            MyOrderDTO dto = new MyOrderDTO(productID, productName, image, Float.parseFloat(price));
            dto.setQuantity(1);
            cart.addCart(dto);
            session.setAttribute("cart", cart);
        } catch (Exception e) {
            LOG.error("Error at AddToCartController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("portlets/member.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
