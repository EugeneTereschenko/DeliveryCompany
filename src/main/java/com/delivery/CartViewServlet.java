package com.delivery;

import com.delivery.db.CardDAO;
import com.delivery.db.CartDAO;
import com.delivery.db.UserDAO;
import com.delivery.entity.Cart;
import com.delivery.entity.User;
import com.itextpdf.text.DocumentException;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "CartViewServlet")
public class CartViewServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(CartViewServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();


        if (action.equals("/updateorder")){
            try {
                updateorder(request, response);
            } catch (ClassNotFoundException  | JSONException e) {
                e.printStackTrace();
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();


        if (action.equals("/showcarts")){
            try {
                showcart(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }




    /**
     *
     * @param request
     * @param response
     * @throws ClassNotFoundException
     * @throws ServletException
     * @throws IOException
     * @throws DocumentException
     */
    private void showcart(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {

        logger.info("text put");

        HttpSession session = request.getSession();

        CartDAO cartDAO = new CartDAO();
        List<Cart> viewcarts = cartDAO.findAllCarts();
        UserDAO userDAO = new UserDAO();
        User user = new User();
        List<User> viewcartusers = new ArrayList<>();

        for (int i = 0; i < viewcarts.size(); i++){
            user = userDAO.checkUserbyId(viewcarts.get(i).getUser_id());
            viewcartusers.add(user);
        }


        session.setAttribute("viewcarts", viewcarts);
        session.setAttribute("viewcartusers", viewcartusers);


        RequestDispatcher dispatcher = request.getRequestDispatcher("./view/carts.jsp");
        dispatcher.forward(request, response);
    }



    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void updateorder(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, JSONException {
        String temp = request.getParameter("idorder");

        logger.info(temp);

        JSONObject myJsonObject = new JSONObject(temp);

        String cartid = myJsonObject.getString("cartid");
        String name = myJsonObject.getString("confirm");


        CartDAO cartDAO = new CartDAO();
        Boolean flag;
        flag = cartDAO.updateCart(Integer.parseInt(cartid), name);

        HttpSession session = request.getSession(true);
        String setLocal = (String) session.getAttribute("idlocal");
        String fileproper = null;
        if (setLocal.equals("ru")){
            fileproper = "app_ru.properties";
        } else {
            fileproper = "app_en.properties";
        }

        Properties properties = new Properties();
        InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(fileproper);
        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        properties.load(reader);

        String addToTrue = properties.getProperty("fieldSuccess");
        String addToFalse = properties.getProperty("fieldError");
        JSONObject jsonMain = new JSONObject();
        if (flag) {

            jsonMain.put("flagid", "true");
            jsonMain.put("localid", addToTrue);
        } else {
            jsonMain.put("flagid", "flag");
            jsonMain.put("localid", addToFalse);
        }

        //response.setContentType("text/plain");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonMain);
        out.flush();
        out.close();

    }

}
