/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khamdd.daos.ProductDAO;
import khamdd.dtos.ProductDTO;
import khamdd.dtos.UpdateProductHistoryDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class UpdateController extends HttpServlet {

    private static final String SUCCESS = "FirstUpdateController";
    private static final String FAILED = "portlets/error.jsp";
    private final static Logger LOG = Logger.getLogger(UpdateController.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAILED;
        try {
            String productName = request.getParameter("txtUpdateProductName");
            String price = request.getParameter("txtUpdatePrice");
            float updatePrice = Float.parseFloat(price);
            String status = request.getParameter("status");
            String category = request.getParameter("txtUpdateCategory");
            String quantity = request.getParameter("txtUpdateQuantity");
            int updateQuantity = Integer.parseInt(quantity);
            System.out.println(updateQuantity);
            String image = request.getParameter("txtUpdateImage");
            String productID = request.getParameter("txtProductID");
            

            ProductDTO dto = new ProductDTO(productID, productName, image, category, updatePrice, updateQuantity, Integer.parseInt(status));
            ProductDAO dao = new ProductDAO();
            if (dao.updateProduct(dto) == true) {
                url = SUCCESS;
                UpdateProductHistoryDTO updateDto = new UpdateProductHistoryDTO();
                updateDto.setProductID(productID);
                updateDto.setAction("Update Product ID " +productID);
                Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                updateDto.setUpdateDate(date);
                dao.recordUpdateProductHistory(updateDto);
            } else {
                request.setAttribute("error", "Update failed");
            }

        } catch (Exception e) {
            LOG.error("Error at UpdateController: " + e.getMessage());
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
