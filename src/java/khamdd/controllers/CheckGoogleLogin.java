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
import khamdd.daos.RegistrationDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class CheckGoogleLogin extends HttpServlet {
    private final static Logger LOG = Logger.getLogger(CheckGoogleLogin.class);
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            response.setContentType("text/html;charset=UTF-8");
            String id = (String) request.getAttribute("id");
            String email = (String) request.getAttribute("email");
            
            RegistrationDAO dao = new RegistrationDAO();
            boolean check = dao.checkExistUserID(id);
            if(check){
                session.setAttribute("role", "member");
                session.setAttribute("fullname", email);
                session.setAttribute("userID", id);
            } else {
                dao.createGGAccount(id, "member", email, email);
                session.setAttribute("role", "member");
                session.setAttribute("fullname", email);
                session.setAttribute("userID", id);
            }
        } catch (Exception e) {
            LOG.error("Error at CheckGoogleLogin: " +e.getMessage());
        } finally {
            request.getRequestDispatcher("portlets/member.jsp").forward(request, response);
        }
        //VanVTT10@fe.edu.vn
        
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
