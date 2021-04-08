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
public class FirstUpdateController extends HttpServlet {

    private final static Logger LOG = Logger.getLogger(FirstUpdateController.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String updatePage = request.getParameter("updatePage");
            if (updatePage == null) {
                updatePage = "1";
            }
            HttpSession session = request.getSession();
            int page = Integer.parseInt(updatePage);
            if (page != 1) {
                page = (page - 1) * 6;
            }
            ArrayList<ProductDTO> listUpdate = new ArrayList<>();
            ProductDAO dao = new ProductDAO();
            SearchDTO dto = new SearchDTO("", "", Float.MIN_VALUE, Float.MAX_VALUE);
            int pageCount = dao.count(dto);
            if (pageCount % 6 != 0) {
                pageCount = pageCount / 6 + 1;
            } else {
                pageCount = pageCount / 6;
            }
            session.setAttribute("pageCount", pageCount);
            session.setAttribute("updatePage", updatePage);
            listUpdate = dao.searchForUpdate(dto, page);
            session.setAttribute("listUpdate", listUpdate);
        } catch (Exception e) {
            LOG.error("Error at FirstUpdateController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("portlets/update.jsp").forward(request, response);
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
