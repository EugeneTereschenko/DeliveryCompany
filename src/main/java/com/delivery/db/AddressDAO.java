package com.delivery.db;



import com.delivery.entity.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class AddressDAO {


    private static final String URL = "jdbc:mysql://localhost:3306/db_delivery" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_ADDRESS_BY_USER_ID = "SELECT * FROM addresses where user_id = (?)";
    private static final String SQL_FIND_ADDRESS_BY_ID = "SELECT * FROM addresses where id = (?)";
    private static final String SQL_INSERT_ADDRESS = "INSERT INTO addresses (address_type, first_name, last_name, address, city, zip, country, phone, user_id, created_at, update_at) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

    private static final String SQL_UPDATE_ADDRESS_DELIVERY_ID = "UPDATE addresses SET delivery_id = (?) where user_id = (?)";
    private static final String SQL_UPDATE_ADDRESS_DELIVERY_ID_BY_ID = "UPDATE addresses SET delivery_id = (?) where id = (?) and user_id = (?)";

    //private static final String SQL_INSERT_USER = "INSERT INTO users (email, name, encrypted_password) VALUES (?, ?, ?)";


    private static final Logger logger = LogManager.getLogger(AddressDAO.class);


    /**
     *
     * @param prstatement
     * @return
     */

    public Address getAddressParam(PreparedStatement prstatement){
        Address address = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                address = new Address();
                address.setId(result.getInt("id"));
                address.setAddress_type(result.getString("address_type"));
                address.setFirst_name(result.getString("first_name"));
                address.setLast_name(result.getString("last_name"));
                address.setAddress(result.getString("address"));
                address.setCity(result.getString("city"));
                address.setZip(result.getInt("zip"));
                address.setAddress(result.getString("country"));
                address.setPhone(result.getString("phone"));
                address.setUser_id(result.getInt("user_id"));
                address.setDelivery_id(result.getInt("delivery_id"));
            } else {

                return null;
            }
        } catch (SQLException | NullPointerException e) {
            logger.info("error find user address" + e);
        }
        return address;

    }

    /**
     *
     * @param addressId
     * @return
     * @throws ClassNotFoundException
     */


    public Address checkAddressByUserId(int addressId) throws ClassNotFoundException {
        Address address = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_ADDRESS_BY_USER_ID)) {
            if (con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
            prstatement.setInt(1, addressId);
            address = getAddressParam(prstatement);
            if (address == null) {
                logger.info("address is null");
                con.rollback();
                return null;
            }
        } catch (SQLException e) {
            logger.info("error find user address" + e);
        }
        return address;
    }


    /**
     *
     * @param addressId
     * @return
     * @throws ClassNotFoundException
     */

    public Address checkAddressById(int addressId) throws ClassNotFoundException {

        Address address = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_ADDRESS_BY_ID)) {
            if (con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
            prstatement.setInt(1, addressId);
            address = getAddressParam(prstatement);
            if (address == null) {
                logger.info("address is null");
                con.rollback();
                return null;
            }
        } catch (SQLException e) {
            logger.info("error find user address" + e);
        }
        return address;
    }


    /**
     *
     * @param address_type
     * @param first_name
     * @param last_name
     * @param addressTo
     * @param city
     * @param zip
     * @param country
     * @param phone
     * @param user_id
     * @return
     * @throws ClassNotFoundException
     */
    public Address insertAddress(String address_type, String first_name, String last_name, String addressTo, String city, int zip, String country, String phone, int user_id) throws ClassNotFoundException {
        Address address = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS)) {
            prstatement.setString(1, address_type);
            prstatement.setString(2, first_name);
            prstatement.setString(3, last_name);
            prstatement.setString(4, addressTo);
            prstatement.setString(5, city);
            prstatement.setInt(6, zip);
            prstatement.setString(7, country);
            prstatement.setString(8, phone);
            prstatement.setInt(9, user_id);

            if (prstatement.executeUpdate() > 0) {
                ResultSet result = prstatement.getGeneratedKeys();
                address = new Address();
                if (result.next()) {
                    address.setId(result.getInt(1));
                }
            }
            return address;
        } catch (SQLException e) {
            logger.info(e);
        }

        return null;
    }

    /**
     *
     * @param user_id
     * @param order_id
     * @return
     * @throws ClassNotFoundException
     */

    public Boolean updateDeliveryId(int user_id, int order_id) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_UPDATE_ADDRESS_DELIVERY_ID)) {
            prstatement.setInt(1, order_id);
            prstatement.setInt(2, user_id);

            return prstatement.executeUpdate() > 0;

        } catch (SQLException e) {
            logger.info(e);
        }

        return null;
    }

    /**
     *
     * @param delivery_id
     * @param address_id
     * @param user_id
     * @return
     * @throws ClassNotFoundException
     */

    public Boolean updateDeliveryById(int delivery_id, int address_id, int user_id ) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_UPDATE_ADDRESS_DELIVERY_ID_BY_ID)) {
            prstatement.setInt(1, delivery_id);
            prstatement.setInt(2, address_id);
            prstatement.setInt(3, user_id);
            if (prstatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.info(e);
        }
        return false;
    }


}
