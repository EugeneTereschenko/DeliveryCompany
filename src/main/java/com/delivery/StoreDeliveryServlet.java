package com.delivery;

import com.delivery.count.RateDeliveryCount;
import com.delivery.db.CartDAO;
import com.delivery.db.RatedeliveryDAO;
import com.delivery.entity.Cityscoordinate;
import com.delivery.entity.Rate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StoreDeliveryServlet")
public class StoreDeliveryServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(StoreDeliveryServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();


        switch(action)
        {
            case "/countdelivery":
                countDelivery((HttpServletRequest) request, response);
                break;
            case "/putdelivery":
                try {
                    putDelivery((HttpServletRequest) request, response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "/removedelivery":
                removeDelivery((HttpServletRequest) request, response);
                break;
            case "/updatenumdelivery":
                updatenumDelivery((HttpServletRequest) request, response);
                break;
/*            case "/showdelivery":
                showDelivery((HttpServletRequest) request, response);
                break;*/
            default:
                break;
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();

        switch(action)
        {
            case "/showdelivery":
                showDelivery((HttpServletRequest) request, response);
                break;
            default:
                break;
        }

    }

    private void removeDelivery(HttpServletRequest request, HttpServletResponse response) {

        String temp = request.getParameter("cartid");
        CartDAO cartDAO = new CartDAO();

        try {
        Boolean flag = cartDAO.deleteCartById(Integer.parseInt(temp));


            PrintWriter out = null;
            out = response.getWriter();

            out.print(flag);
            out.flush();
            out.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void putDelivery(HttpServletRequest request, HttpServletResponse response) throws JSONException {
        String temp = request.getParameter("delivery");

        logger.info(temp);

        RateDeliveryCount rateDeliveryCount = new RateDeliveryCount();

        int cartid = rateDeliveryCount.putRate(temp);


        JSONObject json = new JSONObject();
        json.put("cartid", cartid);
        json.put("distanceBetween", (int) rateDeliveryCount.countRate(temp));
        json.put("time", "00:00");
        json.put("shippingprice", (int) rateDeliveryCount.shippingprice);
        json.put("namecityFrom", rateDeliveryCount.citysFrom.getName());
        json.put("namecityTo", rateDeliveryCount.citysTo.getName());

        response.setContentType("application/json; charset=UTF-8");
        try {
            PrintWriter out = null;
            out = response.getWriter();

            out.print(json);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void countDelivery(HttpServletRequest request, HttpServletResponse response) {
        String temp = request.getParameter("delivery");

        logger.info(temp);

        RateDeliveryCount rateDeliveryCount = new RateDeliveryCount();

        double num = rateDeliveryCount.countRate(temp);

        response.setContentType("application/json; charset=UTF-8");
        try {
            PrintWriter out = null;
            out = response.getWriter();

            out.print(num);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void updatenumDelivery(HttpServletRequest request, HttpServletResponse response) {

        String cartid = request.getParameter("cartid");
        String count = request.getParameter("count");
        CartDAO cartDAO = new CartDAO();

        try {
            Boolean flag = cartDAO.updateCartCount(Integer.parseInt(cartid), Integer.parseInt(count));


            PrintWriter out = null;
            out = response.getWriter();

            out.print(flag);
            out.flush();
            out.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showDelivery(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        RatedeliveryDAO ratedeliveryDAO = new RatedeliveryDAO();

        HttpSession session = request.getSession();
        try {
            List<Rate> rates = ratedeliveryDAO.findAllRates();
            session.setAttribute("rates", rates);
            logger.info("Complete");
            List<Cityscoordinate> cityscoordinates = ratedeliveryDAO.findAllCitescoordinates();
            session.setAttribute("cityscoordinates", cityscoordinates);
            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(request, response);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            logger.info("" + e);
        }
    }

}
