package com.delivery;

import com.delivery.db.AddressDAO;
import com.delivery.db.CardDAO;
import com.delivery.db.CartDAO;
import com.delivery.entity.Address;
import com.delivery.entity.Card;
import com.delivery.entity.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserInputServlet")
public class CheckOutServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(CheckOutServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getServletPath();

        switch (action) {
            case "/addressdata":
                try {
                    addressdata(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/paymentdata":
                try {
                    paymentdata(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/updatedatacart":
                updatedatacart(request, response);
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private void addressdata(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String addressTo = request.getParameter("address");
        String phone = request.getParameter("phone");
        String country = request.getParameter("country");
        String state = request.getParameter("state");
        String sameaddress = request.getParameter("same-address");
        String saveinfo = request.getParameter("save-info");
        String zip = request.getParameter("zip");


        if (email != null) {
            int userId = 0;
            int cartId = 0;

            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                String name = cookies[i].getName();
                String valueID = cookies[i].getValue();
                if (name.equals("userid")) {
                    userId = Integer.parseInt(valueID);
                }
                if (name.equals("cartid")) {
                    cartId = Integer.parseInt(valueID);
                }
            }

            AddressDAO addressDAO = new AddressDAO();
            Address address = new Address();
            address = addressDAO.insertAddress("shipping", firstName, lastName, addressTo, state, Integer.parseInt(zip), country, phone, userId);

/*            CartDAO cartDAO = new CartDAO();

            cartDAO.updateCart(cartId,"delivery");*/

            Cookie cookie = new Cookie("addressid", Integer.toString(address.getId()));
            cookie.setMaxAge(1800);
            response.addCookie(cookie);

            HttpSession session = request.getSession();
            session.setAttribute("addressid", Integer.toString(address.getId()));


            PrintWriter out = response.getWriter();
            out.print(userId);
            out.flush();
            out.close();

        }


    }


    private void paymentdata(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {


        String paymentMethod = request.getParameter("paymentMethod");
        String ccname = request.getParameter("credname");
        String ccnumber = request.getParameter("crednumber");
        String ccexpiration = request.getParameter("credexpiration");
        String cccvv = request.getParameter("credcvv");

        logger.info(" paymentMethod " + paymentMethod + " ccname " + ccname + " ccnumber " + ccnumber + " ccexpiration " + ccexpiration + " cccvv " + cccvv);

        if (ccname != null && ccnumber != null) {

            int userId = 0;
            int cartId = 0;

            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                String name = cookies[i].getName();
                String valueID = cookies[i].getValue();
                if (name.equals("userid")) {
                    userId = Integer.parseInt(valueID);
                }
                if (name.equals("cartid")) {
                    cartId = Integer.parseInt(valueID);
                }
            }


/*
            Cart cart = new Cart();
            CartDAO cartDAO = new CartDAO();
            cart = cartDAO.checkCartByStep(cartId, "payment");
            cartDAO.updateCart(cartId, "complete");*/

            Card card = new Card();
            CardDAO cardDAO = new CardDAO();

            card = CardDAO.insertCard(userId, ccexpiration, Integer.parseInt(cccvv), ccname, ccnumber);
            PrintWriter out = response.getWriter();
            out.print(userId);
            out.flush();
            out.close();

/*            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher("./checkout/complete.jsp");
            requestDispatcher.forward(request, response);*/
        } else {
/*            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher("./checkout/payment.jsp");
            requestDispatcher.forward(request, response);*/
        }

    }



    private void updatedatacart(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");

        CartDAO cartDAO = new CartDAO();

        try {
            cartDAO.updateCartStepByUserId(Integer.parseInt(userid), "complete");

            HttpSession session = request.getSession();
            List<Cart> carts = new ArrayList<>();
            carts  = cartDAO.findallDeliverValueByUserID(Integer.parseInt(userid));
            session.setAttribute("carts", carts);

            PrintWriter out = response.getWriter();
            out.print(userid);
            out.flush();
            out.close();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }


}