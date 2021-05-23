package com.delivery;

import com.delivery.db.CartDAO;
import com.delivery.db.UserDAO;
import com.delivery.entity.Cart;
import com.delivery.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AuthenticationServlet")
public class AuthenticationServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(AuthenticationServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getServletPath();

        switch (action) {
            case "/authentication":
                loginUser((HttpServletRequest) request, response);
                break;
            case "/registration":
                try {
                    registration(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException {


        String email = request.getParameter("login");
        String encrypted_password = request.getParameter("pass");

        String language = request.getParameter("lang");


        logger.info(" email " + email + " encrypted_password " + encrypted_password + "test input or enter" + " choose language " + language);

        UserDAO userDAO = new UserDAO();

        CartDAO cartDAO = new CartDAO();

        try {
            User user = userDAO.checkLoginandPassword(email, encrypted_password);

            String destPage = "index.jsp";

            if (user != null) {
                HttpSession session = request.getSession();
                List<Cart> carts = new ArrayList<>();
                carts  = cartDAO.findallDeliverValueByUserID(user.getId());
                session.setAttribute("carts", carts);
                session.setAttribute("username", user.getEmail());
                session.setAttribute("userid", user.getId());
                session.setAttribute("roleid", user.getUid());
                // session.setAttribute("imageiduser", user.getImage());


                logger.info("user.get " + user.getId());

                userDAO.updatecurrentsignUser(user.getId());

                if (language.equals("1")) {
                    session.setAttribute("idlocal", "en");
                }
                if (language.equals("2")) {
                    session.setAttribute("idlocal", "ru");
                    response.setContentType("text/html; charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");

                } else {
                    session.setAttribute("idlocal", "en");
                }

                String str = Integer.toString(user.getId());
                Cookie cookie = new Cookie("userid", str);
                Cookie cookie2 = new Cookie("imageiduser", user.getImage());
                cookie.setMaxAge(1800);
                response.addCookie(cookie);
                response.addCookie(cookie2);


                logger.info(str);

                //response.setContentType("text/plain");
                PrintWriter out = response.getWriter();
                out.print(str);
                out.flush();
                out.close();

            } else {
                response.setContentType("text/plain");
                PrintWriter out = response.getWriter();
                out.print("stop");
                out.flush();
                out.close();
            }

        } catch (Exception ex) {
            throw new ServletException(ex);
        }

    }



    private void registration(HttpServletRequest request, HttpServletResponse response) throws ServletException, ClassNotFoundException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password_confirm = request.getParameter("password_confirm");
        String language = request.getParameter("lang");

        logger.info(" username " + username + " email " + email + " password " + password + " password_confirm " + password_confirm);

        UserDAO userDAO = new UserDAO();
        CartDAO cartDAO = new CartDAO();

        Boolean flag = userDAO.inputUser(username, email, password, password_confirm, "user");
        User user = userDAO.checkLoginandPassword(email, password_confirm);
        try {
            String destPage = "index.jsp";
            if (user != null) {
                HttpSession session = request.getSession();

                List<Cart> carts = new ArrayList<>();
                carts  = cartDAO.findallDeliverValueByUserID(user.getId());
                session.setAttribute("carts", carts);
                session.setAttribute("username", user.getEmail());
                session.setAttribute("userid", user.getId());
                session.setAttribute("roleid", user.getUid());
                //session.setAttribute("imageiduser", user.getImage());

                if (language.equals("1")) {
                    session.setAttribute("idlocal", "en");
                }
                if (language.equals("2")) {
                    session.setAttribute("idlocal", "ru");
                } else {
                    session.setAttribute("idlocal", "en");
                }
                response.setContentType("text/html; charset=UTF-8");
                response.setCharacterEncoding("UTF-8");


                String str = Integer.toString(user.getId());
                Cookie cookie = new Cookie("userid", str);
                Cookie cookie2 = new Cookie("imageiduser", user.getImage());
                cookie.setMaxAge(1800);
                response.addCookie(cookie);
                response.addCookie(cookie2);

                PrintWriter out = response.getWriter();
                out.print(str);
                out.flush();
                out.close();

            } else {


                response.setContentType("text/plain");
                PrintWriter out = response.getWriter();
                out.print("stop");
                out.flush();
                out.close();

            }


        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();


        switch (action) {
            case "/logout":
                logoutUser(request, response);
                break;
            default:
                break;
        }
    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {


            session.removeAttribute("userid");
            session.removeAttribute("username");


            ServletContext servletContext = getServletContext();


            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }

    }
}