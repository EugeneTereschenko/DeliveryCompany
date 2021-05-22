package com.delivery;

import com.delivery.db.UserDAO;
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
import java.util.List;
import java.util.Properties;

@WebServlet(name = "UserViewServlet")
public class UserViewServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(UserViewServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch(action) {
            case "/insertuser":
                try {
                    insertuser(request, response);
                } catch (ClassNotFoundException | JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "/showoneuser":
                try {
                    showOneUser(request, response);
                } catch (ClassNotFoundException | JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "/updateuser":
                try {
                    updateuser(request, response);
                } catch (ClassNotFoundException | JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "/deleteuser":
                try {
                    deleteuser(request, response);
                } catch (ClassNotFoundException | JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getServletPath();


        switch(action) {
            case "/showusers":
                try {
                    showusers(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }


    }

    /**
     *
     * @param request
     * @param response
     * @throws ClassNotFoundException
     * @throws IOException
     */

    private void insertuser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, JSONException {
        String temp = request.getParameter("user");

        logger.info(temp);


        JSONObject myJsonObject = new JSONObject(temp);



        String emailuser = myJsonObject.getString("emailuser");
        String nameuser = myJsonObject.getString("nameuser");
        String roleuser = myJsonObject.getString("roleuser");
        String password = myJsonObject.getString("password");
        String confirmpassword = myJsonObject.getString("confirmpassword");



        UserDAO userDAO = new UserDAO();
        Boolean flag = userDAO.inputUser(nameuser, emailuser, password, confirmpassword, roleuser);

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
        if (flag != null) {

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



    /**
     *
     * @param request
     * @param response
     * @throws ClassNotFoundException
     * @throws IOException
     */

    private void showOneUser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, JSONException {
        String iduser = request.getParameter("iduser");

        logger.info(" iduser " + iduser);
        User user = new User();
        UserDAO userDAO = new UserDAO();
        user = userDAO.checkUserbyId(Integer.parseInt(iduser));
        logger.info(" userDao get id " + user.getId() + " userDao get email " + user.getEmail());
        JSONObject json = new JSONObject();
        json.put("userid", user.getId());
        json.put("emailid", user.getEmail());
        json.put("nameid", user.getName());
        json.put("roleid", user.getUid());
        json.put("remembid", user.getRemember_created_at());
        json.put("currentid", user.getCurrent_sign_in_at());

        logger.info(json);
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }


    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void updateuser(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, JSONException {
        String temp = request.getParameter("user");

        logger.info("updateuser");

        logger.info(temp);

        JSONObject myJsonObject = new JSONObject(temp);

        String usid = myJsonObject.getString("userid");
        String name = myJsonObject.getString("name");
        String email = myJsonObject.getString("email");
        String role = myJsonObject.getString("role");
        String passwd = myJsonObject.getString("passwd");
        String rememberuser = myJsonObject.getString("rememberuser");
        //String currentuser = myJsonObject.getString("currentuser");



        UserDAO userDAO = new UserDAO();
        User user = new User();


        if (role.equals("1")){
            role = "administrator";
        } else if (role.equals("2")){
            role = "manager";
        } if (role.equals("3")){
            role = "user";
        }


        user = userDAO.updateUser(name, email, passwd, passwd, role, Integer.parseInt(usid), rememberuser);


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
        if (user != null) {

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

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ClassNotFoundException
     */

    private void deleteuser(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, JSONException {
        String temp = request.getParameter("iduser");
        logger.info("deleteuser");

        UserDAO userDAO = new UserDAO();
        boolean flag = userDAO.deleteUser(Integer.parseInt(temp));


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


    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     * @throws ClassNotFoundException
     */

    private void showusers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession();
        List<User> viewusers = userDAO.findAllUsers();
        session.setAttribute("viewusers", viewusers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./view/users.jsp");
        dispatcher.forward(request, response);
    }

}
