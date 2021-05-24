package com.delivery.db;

import com.delivery.entity.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {


    static final String URL = "jdbc:mysql://localhost:3306/db_delivery" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_CART_BY_ID = "SELECT * FROM carts where id = (?)";
    private static final String SQL_FIND_CART_WITH_CHECKOUT_STEP = "SELECT * FROM carts where id = (?) AND checkout_step = (?)";
    private static final String SQL_INSERT_CART = "INSERT INTO carts (created_at, update_at, user_id, total_price, shipping_price, checkout_step, cityFrom, cityTo, typeDeliver, count, weight, length, width, height, distance) VALUES (NOW(), NOW(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_CART = "UPDATE carts set checkout_step = (?) where id = (?)";
    private static final String SQL_UPDATE_CART_COUPON = "UPDATE carts set coupon = (?) where id = (?)";
    private static final String SQL_UPDATE_CART_COUNT = "UPDATE carts set count = (?) where id = (?)";
    private static final String SQL_UPDATE_CART_STEP_BY_USERID = "UPDATE carts set checkout_step = (?) where user_id = (?)";
    private static final String SQL_FIND_ALL_CARTS = "SELECT * FROM carts";
    private static final String SQL_SELECT_CARTS_BY_USER_ID ="SELECT * FROM carts where user_id = (?)";
    private static final String SQL_DELETE_FROM_CARTS_BY_ID = "DELETE FROM carts where id = (?)";


    private static final Logger logger = LogManager.getLogger(CartDAO.class);



/**
     *
     * @param prstatement
     * @return
     */

    public Cart getCartParam(PreparedStatement prstatement){
        Cart cart = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                cart = new Cart();
                cart.setId(result.getInt("id"));
                cart.setTotal_price(result.getDouble("total_price"));
                cart.setUser_id(result.getInt("user_id"));
                cart.setShipping_price(result.getDouble("shipping_price"));
                cart.setCoupon(result.getInt("coupon"));
                cart.setCheckout_step(result.getString("checkout_step"));
                cart.setCityFrom(result.getString("cityFrom"));
                cart.setCityTo(result.getString("cityTo"));
                cart.setTypeDeliver(result.getString("typeDeliver"));
                cart.setCount(result.getInt("count"));
                cart.setWeight(result.getInt("weight"));
                cart.setLength(result.getInt("length"));
                cart.setWidth(result.getInt("width"));
                cart.setHeight(result.getInt("height"));
                cart.setDistance(result.getInt("distance"));
            } else {
                logger.info("Cart is not valid");
                return null;
            }
        } catch (SQLException | NullPointerException e) {
            logger.info("error find cart" + e);
        }
        return cart;
    }



    /**
     *
     * @param id

     * @return
     * @throws ClassNotFoundException
     */


    public Cart checkCartById(int id) throws ClassNotFoundException {

        Cart cart = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_CART_BY_ID)) {
            if(con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
            prstatement.setInt(1, id);
            cart = getCartParam(prstatement);
            if (cart == null){
                con.rollback();
            }
        } catch (SQLException e) {
            logger.info("error find cart" + e);
        }
        return cart;
    }


/**
     *
     * @param id
     * @param checkout_step
     * @return
     * @throws ClassNotFoundException
     */


    public Cart checkCartByStep(int id, String checkout_step) throws ClassNotFoundException {

        Cart cart = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_CART_WITH_CHECKOUT_STEP)) {
            if(con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
            prstatement.setInt(1, id);
            prstatement.setString(2, checkout_step);
            cart = getCartParam(prstatement);
            if (cart == null){
                con.rollback();
            }
        } catch (SQLException e) {
            logger.info("error find cart" + e);
        }
        return cart;
    }


/**
     *
     * @param user_id
     * @param total_price
     * @param total_price
     * @param checkout_step
     * @return
     * @throws ClassNotFoundException
     */
//int user_id, double total_price, double shipping_price, String checkout_step, String cityFrom, String cityTo, String typeDeliver, int count, int weight, int length, int width, int height, int distance

    public Cart insertCart(Cart cart) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_INSERT_CART, Statement.RETURN_GENERATED_KEYS)) {
            prstatement.setInt(1, cart.getUser_id());
            prstatement.setDouble(2, cart.getTotal_price());
            prstatement.setDouble(3, cart.getShipping_price());
            prstatement.setString(4, cart.getCheckout_step());
            prstatement.setString(5, cart.getCityFrom());
            prstatement.setString(6, cart.getCityTo());
            prstatement.setString(7, cart.getTypeDeliver());
            prstatement.setInt(8, cart.getCount());
            prstatement.setInt(9, cart.getWeight());
            prstatement.setInt(10, cart.getLength());
            prstatement.setInt(11, cart.getWeight());
            prstatement.setInt(12, cart.getHeight());
            prstatement.setInt(13, cart.getDistance());
            if (prstatement.executeUpdate() > 0) {
                ResultSet result = prstatement.getGeneratedKeys();
                cart = new Cart();
                if (result.next()) {
                    cart.setId(result.getInt(1));
                }
            }
            return cart;
        } catch (SQLException e) {
            logger.info("" + e);
        }
        return null;
    }



/**
     *
     * @param checkout_step
     * @return
     * @throws ClassNotFoundException
     */

    public Boolean updateCartStepByUserId(int userid, String checkout_step) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_UPDATE_CART_STEP_BY_USERID)) {
            prstatement.setString(1, checkout_step);
            prstatement.setInt(2, userid);

            if (prstatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            logger.info("" + e);
        }
        return false;
    }




    /**
     *
     * @param cartid
     * @return
     * @throws ClassNotFoundException
     */

    public Boolean deleteCartById(int cartid) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_DELETE_FROM_CARTS_BY_ID)) {
            prstatement.setInt(1, cartid);

            if (prstatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            logger.info("" + e);
        }
        return false;
    }


    /**
     *
     * @param id
     * @param checkout_step
     * @return
     * @throws ClassNotFoundException
     */

    public Boolean updateCart(int id, String checkout_step) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_UPDATE_CART)) {
            prstatement.setString(1, checkout_step);
            prstatement.setInt(2, id);
            if (prstatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            logger.info("" + e);
        }
        return false;
    }



/**
     *
     * @param id
     * @param coupon
     * @return
     * @throws ClassNotFoundException
     */

    public Boolean updateCartCoupon(int id, int coupon) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_UPDATE_CART_COUPON, Statement.RETURN_GENERATED_KEYS)) {
            prstatement.setInt(1, coupon);
            prstatement.setInt(2, id);
            if (prstatement.executeUpdate() > 0) {
                ResultSet result = prstatement.getGeneratedKeys();
                return true;
            }

        } catch (SQLException e) {
            logger.info("" + e);
        }
        return false;
    }



    /**
     *
     * @param id
     * @param count
     * @return
     * @throws ClassNotFoundException
     */

    public Boolean updateCartCount(int id, int count) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_UPDATE_CART_COUNT, Statement.RETURN_GENERATED_KEYS)) {
            prstatement.setInt(1, count);
            prstatement.setInt(2, id);
            if (prstatement.executeUpdate() > 0) {
                ResultSet result = prstatement.getGeneratedKeys();
                return true;
            }

        } catch (SQLException e) {
            logger.info("" + e);
        }
        return false;
    }



/**
     *
     * @return
     * @throws ClassNotFoundException
     */

    public List<Cart> findAllCarts() throws ClassNotFoundException {

        List<Cart> carts = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); Statement statement = con.createStatement(); ResultSet result = statement.executeQuery(SQL_FIND_ALL_CARTS)) {
            while (result.next()) {
                Cart cart = new Cart();
                cart.setId(result.getInt("id"));
                cart.setTotal_price(result.getDouble("total_price"));
                cart.setUser_id(result.getInt("user_id"));
                cart.setTotal_price(result.getDouble("shipping_price"));
                cart.setCoupon(result.getInt("coupon"));
                cart.setCheckout_step(result.getString("checkout_step"));
                cart.setCityFrom(result.getString("cityFrom"));
                cart.setCityTo(result.getString("cityTo"));
                cart.setTypeDeliver(result.getString("typeDeliver"));
                cart.setCount(result.getInt("count"));
                cart.setWeight(result.getInt("weight"));
                cart.setLength(result.getInt("length"));
                cart.setWidth(result.getInt("width"));
                cart.setHeight(result.getInt("height"));
                cart.setDistance(result.getInt("distance"));
                carts.add(cart);
            }
        } catch (SQLException e) {
            logger.info("" + e);
        }
        return carts;
    }



/**
     *
     * @param user_id
     * @return
     * @throws ClassNotFoundException
     */



    public static List<Cart> findallDeliverValueByUserID(int user_id) throws ClassNotFoundException {

        List<Cart> carts = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_SELECT_CARTS_BY_USER_ID)) {
            prstatement.setInt(1, user_id);

            ResultSet result = prstatement.executeQuery();
            while (result.next()) {
                Cart cart = new Cart();
                cart.setId(result.getInt("id"));
                cart.setTotal_price(result.getDouble("total_price"));
                cart.setUser_id(result.getInt("user_id"));
                cart.setTotal_price(result.getDouble("shipping_price"));
                cart.setCoupon(result.getInt("coupon"));
                cart.setCheckout_step(result.getString("checkout_step"));
                cart.setCityFrom(result.getString("cityFrom"));
                cart.setCityTo(result.getString("cityTo"));
                cart.setTypeDeliver(result.getString("typeDeliver"));
                cart.setCount(result.getInt("count"));
                cart.setWeight(result.getInt("weight"));
                cart.setLength(result.getInt("length"));
                cart.setWidth(result.getInt("width"));
                cart.setHeight(result.getInt("height"));
                cart.setDistance(result.getInt("distance"));
                carts.add(cart);
            }
        } catch (SQLException e) {
            logger.info( ""  + e);
        }
        return carts;
    }

}
