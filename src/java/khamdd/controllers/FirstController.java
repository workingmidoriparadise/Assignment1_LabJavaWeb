/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khamdd.daos.ProductDAO;
import khamdd.dtos.ProductDTO;
import khamdd.dtos.SearchDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class FirstController extends HttpServlet {

    private final static Logger LOG = Logger.getLogger(FirstController.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            session.setAttribute("role", "guest");
            ProductDAO dao = new ProductDAO();
            SearchDTO searchDTO = new SearchDTO("", "", Float.MIN_VALUE, Float.MAX_VALUE);
            ArrayList<ProductDTO> listSearched = dao.searchForUpdate(searchDTO, 1);
            session.setAttribute("listSearched", listSearched);
            if (session.getAttribute("page") == null) {
                session.setAttribute("page", 1);
            }
            session.setAttribute("listCategory", dao.getListCategory());
            int page = dao.count(searchDTO);
            if (page % 6 == 0) {
                page = page / 6;
            } else {
                page = page / 6 + 1;
            }
            session.setAttribute("pageCount", page);
        } catch (Exception e) {
            LOG.error("Error at FirstController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("portlets/index.jsp").forward(request, response);
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
