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
import khamdd.dtos.UpdateProductHistoryDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class DeleteController extends HttpServlet {

    private final static Logger LOG = Logger.getLogger(DeleteController.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String[] listProductID = request.getParameterValues("txtDeleteCB");
            ProductDAO dao = new ProductDAO();
            UpdateProductHistoryDTO dto = null;
            dao.deleteProduct(listProductID);

            for (String s : listProductID) {
                dto = new UpdateProductHistoryDTO();
                dto.setProductID(s);
                dto.setAction("Delete Product ID " + s);
                Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                dto.setUpdateDate(date);
                dao.recordUpdateProductHistory(dto);
            }
        } catch (Exception e) {
            LOG.error("Error at DeleteController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("FirstUpdateController").forward(request, response);
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
