/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import entity.Person;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.PersonFacade;
import session.UserFacade;

/**
 *
 * @author artjo
 */
@WebServlet(name = "LoginController", urlPatterns = {
    "/createUser",
    "/login",
    "/logout"
})
public class LoginController extends HttpServlet {

    @EJB
    private PersonFacade personFacade;
    @EJB
    private UserFacade userFacade;

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
        response.setContentType("text/html;charset=UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/createUser":
                JsonReader jsonReader = Json.createReader(request.getReader());
                JsonObject jsonObject = jsonReader.readObject();
                String firstname = jsonObject.getString("firstname");
                String lastname = jsonObject.getString("lastname");
                String email = jsonObject.getString("email");
                String money = jsonObject.getString("money");
                String city = jsonObject.getString("city");
                String street = jsonObject.getString("street");
                String house = jsonObject.getString("house");
                String room = jsonObject.getString("room");
                String login = jsonObject.getString("login");
                String password = jsonObject.getString("password");
                Person person = null;
                User user = null;
                try {
                    person = new Person(firstname, lastname, email, city, street, house, room);
                    personFacade.create(person);
                    user = new User(login, password, "salts", true, person);
                    userFacade.create(user);
                } catch (Exception e) {
                    if (person != null && person.getId() != null) {
                        personFacade.remove(person);
                    }
                    if (user != null && user.getId() != null) {
                        userFacade.remove(user);
                    }
                }

                break;

            case "/login":

                break;

            case "/logout":

                break;

        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
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