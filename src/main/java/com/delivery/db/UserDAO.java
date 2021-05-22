package com.delivery.db;

import com.delivery.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


public class UserDAO{



    private static final String URL = "jdbc:mysql://localhost:3306/db_delivery" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_USER = "SELECT * FROM users where email = (?) and encrypted_password = (?)";
    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users where email = (?)";
    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users where id = (?)";
    private static final String SQL_INSERT_USER = "INSERT INTO users (email, name, remember_created_at, current_sign_in_at, encrypted_password, uid) VALUES (?, ?, NOW(), NOW(), ?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE users set email = (?), name = (?), remember_created_at = (?)," +
            "encrypted_password = (?), uid = (?) where id = (?)";
    private static final String SQL_DELETE_USER = "DELETE FROM users where id = (?)";
    //private static final String SQL_INSERT_USER = "INSERT INTO users (email, name, encrypted_password) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_USER_CURRENT_TIME  = "UPDATE users set current_sign_in_at = NOW() where id = (?)";
    private static final String SQL_UPDATE_USER_CURRENT_IMAGE = "UPDATE users set image = (?) where id = (?)";

    private static final Logger logger = LogManager.getLogger(UserDAO.class);

    /**
     *
     * @param prstatement
     * @return
     */


    public User getUserParam(PreparedStatement prstatement){
        User user = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setEmail(result.getString("email"));
                user.setUid(result.getString("uid"));
                user.setName(result.getString("name"));
                user.setImage(result.getString("image"));
                user.setCurrent_sign_in_at(result.getString("current_sign_in_at"));
                user.setRemember_created_at(result.getString("remember_created_at"));
                // user.setEncrypted_password(result.getString("encrypted_password"));
            } else {
                logger.info("You are not valid");

                return null;
            }
        } catch (SQLException | NullPointerException e) {
            logger.info("error find user" + e);
        }
        return user;

    }

    /**
     *
     * @param email
     * @return
     * @throws ClassNotFoundException
     */

    public User checkLogin(String email) throws ClassNotFoundException {

        User user = null;
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {

            if(con.getAutoCommit()) {
                con.setAutoCommit(false);
            }

            prstatement.setString(1, email);

            user = getUserParam(prstatement);

            if (user == null){
                logger.info("user is null");
                con.rollback();
                return null;
            }

        } catch (SQLException e) {
            logger.info("error find user" + e);
        }

        return user;

    }

    /**
     *
     * @param email
     * @param encrypted_password
     * @return
     * @throws ClassNotFoundException
     */

    public User checkLoginandPassword(String email, String encrypted_password) throws ClassNotFoundException {

        User userCheckLogin=checkLogin(email);

        if (userCheckLogin == null) {
            return null;
        }

        if (userCheckLogin != null) {

            User user = null;
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_USER)) {

                if (con.getAutoCommit()) {
                    con.setAutoCommit(false);
                }

                prstatement.setString(1, email);
                prstatement.setString(2, encrypted_password);

                user = getUserParam(prstatement);

                if (user == null) {
                    con.rollback();
                    return null;
                }

            } catch (SQLException e) {
                logger.info("error find user" + e);
            }

            return user;
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     * @throws ClassNotFoundException
     */

    public User checkUserbyId(int id) throws ClassNotFoundException {
        User user = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_USER_BY_ID)) {
            prstatement.setInt(1, id);
            user = getUserParam(prstatement);
        } catch (SQLException e) {
            logger.info("error find user" + e);
        }
        return user;

    }

    /**
     *
     * @param username
     * @param email
     * @param password
     * @param password_confirm
     * @param role
     * @return
     * @throws ClassNotFoundException
     */

    public Boolean inputUser(String username, String email, String password, String password_confirm, String role) throws ClassNotFoundException {
        logger.info(username + " dd " + email + " dd " + password + " rr " + role);
        User login = null;
        login = checkLogin(email);

        User user = null;
        user = checkLoginandPassword(email, password);

        if (user == null && login == null) {

            if (password.equals(password_confirm)) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
                    prstatement.setString(1, email);
                    prstatement.setString(2, username);
                    prstatement.setString(3, password);
                    prstatement.setString(4, role);
                    if (prstatement.executeUpdate() > 0) {
                        ResultSet result = prstatement.getGeneratedKeys();
                        return true;
                    }

                } catch (SQLException e) {
                    logger.info((Supplier<String>) e);
                }
            }
        }

        return false;
    }

    /**
     *
     * @param image
     * @param id
     * @return
     * @throws ClassNotFoundException
     */

    public Boolean updatecurrentimageUser(String image, int id) throws ClassNotFoundException {
        //System.out.println(" dd " + image + " dd " + id + " rr ");

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_UPDATE_USER_CURRENT_IMAGE)) {

            prstatement.setString(1, image);
            prstatement.setInt(2, id);
            if (prstatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            logger.info((Supplier<String>) e);
        }


        return null;
    }

    /**
     *
     * @param iduser
     * @return
     * @throws ClassNotFoundException
     */

    public Boolean updatecurrentsignUser(int iduser) throws ClassNotFoundException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_UPDATE_USER_CURRENT_TIME)) {

            prstatement.setInt(1, iduser);
            if (prstatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            logger.info((Supplier<String>) e);
        }


        return null;
    }

    /**
     *
     * @param username
     * @param email
     * @param password
     * @param password_confirm
     * @param uid
     * @param iduser
     * @param remembercreatedat
     * @return
     * @throws ClassNotFoundException
     */

    public User updateUser(String username, String email, String password, String password_confirm, String uid, int iduser, String remembercreatedat) throws ClassNotFoundException {

        User user = null;
        if (password.equals(password_confirm)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_UPDATE_USER, Statement.RETURN_GENERATED_KEYS)) {
                prstatement.setString(1, email);
                prstatement.setString(2, username);
                prstatement.setString(3, remembercreatedat);
                prstatement.setString(4, password);
                prstatement.setString(5, uid);
                prstatement.setInt(6, iduser);
                if (prstatement.executeUpdate() > 0) {
                    ResultSet result = prstatement.getGeneratedKeys();
                    user = new User();
                    if (result.next()) {
                        user.setId(result.getInt("id"));

                    }
                }
                return user;
            } catch (SQLException e) {
                logger.info("" + e);
            }
        }

        return null;
    }


    /**
     *
     * @param userid
     * @return
     * @throws ClassNotFoundException
     */

    public boolean deleteUser(int userid)  throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_DELETE_USER)) {
            prstatement.setInt(1, userid);
            if (prstatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            logger.info((Supplier<String>) e);
        }

        return false;
    }

    /**
     *
     * @return
     * @throws ClassNotFoundException
     */

    public List<User> findAllUsers() throws ClassNotFoundException {

        List<User> users = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); Statement statement = con.createStatement(); ResultSet result = statement.executeQuery(SQL_FIND_ALL_USERS)) {
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setEmail(result.getString("email"));
                user.setName(result.getString("name"));
                user.setUid(result.getString("uid"));
                user.setRemember_created_at(result.getString("remember_created_at"));
                user.setCurrent_sign_in_at(result.getString("current_sign_in_at"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.info((Supplier<String>) e);
        }
        return users;
    }
}
