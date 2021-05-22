package com.delivery.db;

import com.delivery.entity.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class CardDAO {



    static final String URL = "jdbc:mysql://localhost:3306/db_delivery" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_CARD = "SELECT * FROM cards where user_id = (?)";
    private static final String SQL_INSERT_CARD = "INSERT INTO cards (created_at, update_at, user_id, expiration_month_year, cvv, name, card_number) VALUES (NOW(), NOW(), ?, ?, ?, ?, ?)";
    //private static final String SQL_UPDATE_CARD = "UPDATE carts set  where id = (?)";

    private static final Logger logger = LogManager.getLogger(CardDAO.class);


    /**
     *
     * @param prstatement
     * @return
     */
    public static Card getCardParam(PreparedStatement prstatement){
        Card card = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                card = new Card();
                card.setCard_number(result.getString("card_number"));
                card.setName(result.getString("name"));
                card.setCvv(result.getInt("cvv"));
                card.setExpiration_month_year(result.getString("expiration_month_year"));
                card.setUser_id(result.getInt("user_id"));
            } else {
                logger.info("You are not valid");

                return null;
            }
        } catch (SQLException | NullPointerException e) {
            logger.info("error find user" + e);
        }
        return card;

    }

    /**
     *
     * @param user_id
     * @return
     * @throws ClassNotFoundException
     */


    public static Card checkCardByUserId(int user_id) throws ClassNotFoundException {

        Card card = null;
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_CARD)) {

            if(con.getAutoCommit()) {
                con.setAutoCommit(false);
            }

            prstatement.setInt(1, user_id);

            card = getCardParam(prstatement);

            if (card == null){
                logger.info("card is null");
                con.rollback();
                return null;
            }

        } catch (SQLException e) {
            logger.info("error find card" + e);
        }

        return card;

    }

    /**
     *
     * @param user_id
     * @param expiration_month_year
     * @param cvv
     * @param name
     * @param card_number
     * @return
     * @throws ClassNotFoundException
     */



    public static Card insertCard(int user_id, String expiration_month_year, int cvv, String name, String card_number) throws ClassNotFoundException {
        Card card = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_INSERT_CARD, Statement.RETURN_GENERATED_KEYS)) {
            prstatement.setInt(1, user_id);
            prstatement.setString(2, expiration_month_year);
            prstatement.setInt(3, cvv);
            prstatement.setString(4, name);
            prstatement.setString(5, card_number);
            if (prstatement.executeUpdate() > 0) {
                ResultSet result = prstatement.getGeneratedKeys();
                card = new Card();
                if (result.next()) {
                    card.setUser_id(result.getInt(1));
                }
            }
            return card;
        } catch (SQLException e) {
            logger.info("" + e);
        }

        return null;
    }



}
