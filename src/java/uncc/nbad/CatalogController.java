
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package uncc.nbad;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.owasp.esapi.ESAPI;

/**
 *
 * @author saikr
 */
public class CatalogController extends HttpServlet {

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
        try {
            itemDB DB    = new itemDB();
            List<Item> items = DB.getAllItems();

            // getItemsFromDB
            HttpSession session = request.getSession();
            Userprofile profile = (Userprofile) session.getAttribute("currentProfile");

            if (request.getParameterMap().containsKey("itemCode")) {
                String itemcode = ESAPI.validator()
                                       .getValidInput("replace ME with validation context",
                                                      request.getParameter("itemCode"),
                                                      "HTTPParameterValue",
                                                      200,
                                                      false);
        
                Item  item  = DB.getItem(itemcode);
                String itemStatus = DB.getItemStatus(itemcode);

                request.setAttribute("item", item);
                request.setAttribute("itemStatus", itemStatus);
                getServletContext().getRequestDispatcher("/item.jsp").forward(request, response);
            } else if (request.getParameterMap().containsKey("catelogCategory")) {
            
                String category = ESAPI.validator()
                                       .getValidInput("replace ME with validation context",
                                                      request.getParameter("catelogCategory"),
                                                      "HTTPParameterValue",
                                                      200,
                                                      false);
                List<Item> category1_items = new ArrayList<Item>();;
                List<Item> category2_items = new ArrayList<Item>();;

                if (category.equalsIgnoreCase("Technology")) {
                    for (Item item : items) {
                        if (item.getCategory().equalsIgnoreCase("Technology")) {
                            category1_items.add(item);
                        }
                    }

                    if (session.getAttribute("theUser") == null) {
                        request.setAttribute("items", category1_items);
                    } else {
                        session.setAttribute("items", Utility.excludeUserItems(profile, category1_items));
                    }
                } else {
                    for (Item item : items) {
                        if (item.getCategory().equalsIgnoreCase("Science Fiction")) {
                            category2_items.add(item);
                        }
                    }

                    if (session.getAttribute("theUser") == null) {
                        request.setAttribute("items", category2_items);
                    } else {
                        session.setAttribute("items", Utility.excludeUserItems(profile, category2_items));
                    }
                }

                getServletContext().getRequestDispatcher("/categories.jsp").forward(request, response);
            } else {
                if (session.getAttribute("theUser") == null) {
                    request.setAttribute("items", items);
                } else {
                    session.setAttribute("items", Utility.excludeUserItems(profile, items));
                }
            }

            getServletContext().getRequestDispatcher("/categories.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet requests
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet itemDBServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet itemDBServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }    // </editor-fold>
}

