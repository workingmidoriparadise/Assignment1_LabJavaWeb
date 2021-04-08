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
public class SearchController extends HttpServlet {

    private static final String MEMBER = "portlets/member.jsp";
    private static final String GUEST = "portlets/index.jsp";
    private static final String ADMIN = "portlets/admin.jsp";
    private final static Logger LOG = Logger.getLogger(SearchController.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        String url = GUEST;
        if (role.equals("admin")) {
            url = ADMIN;
        } else if (role.equals("member")) {
            url = MEMBER;
        }

        float min, max;
        min = Float.MIN_VALUE;
        max = Float.MAX_VALUE;
        try {
            session.setAttribute("errorInput", false);
            String searchByName = request.getParameter("txtSearchByName");
            String fromPrice = request.getParameter("txtFromPrice");
            String toPrice = request.getParameter("txtToPrice");
            String searchByCategory = request.getParameter("txtSearchCategory");

            if (!fromPrice.equals("")) {
                try {
                    Float.parseFloat(fromPrice);
                } catch (Exception e) {
                    if (role.equals("admin")) {
                        session.setAttribute("adminErrorInput", true);
                    } else if (role.equals("member")) {
                        session.setAttribute("memberErrorInput", true);
                    } else {
                        session.setAttribute("errorInput", true);
                    }
                    request.getRequestDispatcher(url).forward(request, response);
                }
                min = Float.parseFloat(fromPrice);
            } else {
                fromPrice = "" + min;
            }
            if (!toPrice.equals("")) {
                try {
                    Float.parseFloat(toPrice);
                } catch (Exception e) {
                    if (role.equals("admin")) {
                        session.setAttribute("adminErrorInput", true);
                    } else if (role.equals("member")) {
                        session.setAttribute("memberErrorInput", true);
                    } else {
                        session.setAttribute("errorInput", true);
                    }
                    request.getRequestDispatcher(url).forward(request, response);
                }
                max = Float.parseFloat(toPrice);
            } else {
                toPrice = "" + max;
            }

            SearchDTO searchDTO = new SearchDTO();
            searchDTO.setName(searchByName);
            searchDTO.setCategory(searchByCategory);
            searchDTO.setFromPrice(min);
            searchDTO.setToPrice(max);

            int index = Integer.parseInt(request.getParameter("page"));
            session.setAttribute("page", request.getParameter("page"));
            if (index != 1) {
                index = (index - 1) * 6;
            } else {
                index = 0;
            }
            ProductDAO dao = new ProductDAO();
            ArrayList<ProductDTO> listSearched = null;
            listSearched = dao.searchForUpdate(searchDTO, index);
            session.setAttribute("listSearched", listSearched);
            int pageCount = dao.count(searchDTO);
            if (pageCount % 6 != 0) {
                pageCount = pageCount / 6 + 1;
            } else {
                pageCount = pageCount / 6;
            }
            session.setAttribute("pageCount", pageCount);
        } catch (Exception e) {
            LOG.error("Error at SearchController: " + e.getMessage());
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
