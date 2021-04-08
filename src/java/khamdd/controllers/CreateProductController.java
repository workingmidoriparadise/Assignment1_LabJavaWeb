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
import khamdd.daos.ProductDAO;
import khamdd.dtos.CreateErrorObj;
import khamdd.dtos.ProductDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class CreateProductController extends HttpServlet {

    private static final String SUCCESS = "portlets/createProduct.jsp";
    private static final String ERROR = "portlets/error.jsp";
    private static final String INVALID = "portlets/createProduct.jsp";
    private final static Logger LOG = Logger.getLogger(CreateProductController.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean check = true;
        try {
            String productID = request.getParameter("txtProductID");
            String productName = request.getParameter("txtProductName");
            String price = request.getParameter("txtPrice");
            String description = request.getParameter("txtDescription");
            String category = request.getParameter("txtCategory");
            String quantity = request.getParameter("txtQuantity");
            String image = request.getParameter("txtImage");
            CreateErrorObj createError = new CreateErrorObj();
            ProductDAO dao = new ProductDAO();

            if (productID.equals("")) {
                createError.setErrorProductID("ProductID can't be blank");
                check = false;
            }
            if (productName.equals("")) {
                createError.setErrorProductName("Product Name can't be blank");
                check = false;
            }
            if (price.equals("")) {
                createError.setErrorPrice("Price can't be blank");
                check = false;
            }
            if (description.equals("")) {
                description = "";
            }
            if (quantity.equals("")) {
                createError.setErrorQuantity("Quantity can't be blank");
                check = false;
            }
            if (image.equals("")) {
                createError.setErrorImage("Image can't be blank");
                check = false;
            }
            try {
                Float.parseFloat(price);
            } catch (Exception e) {
                createError.setErrorPrice("Your price is invalid");
                check = false;
            }
            try {
                Integer.parseInt(quantity);
            } catch (Exception e) {
                createError.setErrorQuantity("Your quantity is invalid");
                check = false;
            }

            if(dao.checkExistProductID(productID) == true){
                createError.setErrorProductID("Product ID is existed");
                check = false;
            }
            if (check == true) {
                category = dao.getCategoryIDByCategoryName(category);
                ProductDTO dto = new ProductDTO(productID, productName, image, description, category, Float.parseFloat(price), Integer.parseInt(quantity));
                if (dao.insertProduct(dto)) {
                    url = SUCCESS;
                    request.setAttribute("INSERTSUCCESS", "Insert Successfully");
                } else {
                    request.setAttribute("ERROR", "Insert failed");
                }
            } else {
                request.setAttribute("INVALID", createError);
                url = INVALID;
            }

        } catch (Exception e) {
            LOG.error("Error at CreateProductController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
