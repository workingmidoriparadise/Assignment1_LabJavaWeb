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
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class MainController extends HttpServlet {
    private static final String ERROR = "portlets/error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String SEARCH = "SearchController";
    private static final String FIRST_UPDATE = "FirstUpdateController";
    private static final String DELETE = "DeleteController";
    private static final String UPDATE = "UpdateController";
    private static final String ADD_TO_CART = "AddToCartController";
    private static final String LOGOUT = "LogoutController";
    private static final String UPDATE_CART = "UpdateCartController";
    private static final String DELETE_CART  = "DeleteCartController";
    private static final String CONFIRM_CART = "ConfirmCartController";
    private static final String BACK_HOME = "portlets/index.jsp";
    private static final String GO_LOGIN = "portlets/login.jsp";
    private static final String CONTINUE_SHOPPING = "portlets/member.jsp";
    private static final String VIEW_CART = "portlets/view.jsp";
    private static final String SHOPPING_HISTORY = "ShoppingHistoryController";
    private static final String GO_CREATE_PRODUCT = "portlets/createProduct.jsp";
    private static final String CREATE_PRODUCT = "CreateProductController";
    private static final String GOOGLE_LOGIN = "CheckGoogleLogin";
    private final static Logger LOG = Logger.getLogger(MainController.class);
    private static final String BACK_HOME_ADMIN = "portlets/admin.jsp";
    private static final String BACK_HOME_MEMBER = "portlets/member.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if(action.equals("Login")){
                url = LOGIN;
            } if(action.equals("Search")){
                url = SEARCH;
            } if(action.equals("FirstUpdate")){
                url = FIRST_UPDATE;
            } if(action.equals("Delete")){
                url = DELETE;
            } if(action.equals("Update")){
                url = UPDATE;
            } if(action.equals("Add to cart")){
                url = ADD_TO_CART;
            } if(action.equals("Logout")){
                url = LOGOUT;
            } if(action.equals("UpdateCart")){
                url = UPDATE_CART;
            } if(action.equals("DeleteCart")){
                url = DELETE_CART;
            } if(action.equals("ConfirmCart")){
                url = CONFIRM_CART;
            } if(action.equals("BackHome")){
                url = BACK_HOME;
            } if(action.equals("GoLogin")){
                url = GO_LOGIN;
            } if(action.equals("ContinueShopping")){
                url = CONTINUE_SHOPPING;
            } if(action.equals("ViewCart")){
                url = VIEW_CART;
            } if(action.equals("ViewOrderHistory")){
                url = SHOPPING_HISTORY;
            } if(action.equals("GoCreateProduct")){
                url = GO_CREATE_PRODUCT;
            } if(action.equals("CreateProduct")){
                url = CREATE_PRODUCT;
            } if(action.equals("GoogleLogin")){
                url = GOOGLE_LOGIN;
            } if(action.equals("BackHomeAdmin")){
                url = BACK_HOME_ADMIN;
            } if(action.equals("BackHomeMember")){
                url = BACK_HOME_MEMBER;
            }
            else {
                request.setAttribute("ERROR", "Your action is invalid");
            }
        } catch (Exception e) {
            LOG.error("Error at MainController: " + e.getMessage());
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
