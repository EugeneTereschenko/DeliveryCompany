package com.delivery.db;

import com.delivery.entity.Cityscoordinate;
import com.delivery.entity.Rate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatedeliveryDAO {
    static final String URL = "jdbc:mysql://localhost:3306/db_delivery" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_RATE_BY_WEIGHT = "SELECT * FROM ratedeliver where weight = (?)";
    private static final String SQL_FIND_RATE_BY_ID = "SELECT * FROM ratedeliver where id = (?)";


    private static final String SQL_FIND_CITYCOORDINATE = "SELECT * FROM cityscoordinates where name = (?)";

    private static final String SQL_FIND_CITYCOORDINATE_BY_ID = "SELECT * FROM cityscoordinates where id = (?)";

    private static final String SQL_FIND_ALL_RATES = "SELECT * FROM ratedeliver";
    private static final String SQL_FIND_ALL_CITYSCOORDINATES = "SELECT * FROM cityscoordinates";

    private static final String SQL_SELECT_RATES_BY_WEIGHT = "SELECT * from ratedeliver where distancefrom < ? and distanceto >= ? and weight >= ? limit 1;";

    private static final Logger logger = LogManager.getLogger(RatedeliveryDAO.class);


    public Rate getRateParam(PreparedStatement prstatement){
        Rate rate = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                rate = new Rate();
                rate.setId(result.getInt("id"));
                rate.setWeight(result.getDouble("weight"));
                rate.setCost(result.getDouble("cost"));
                rate.setDistancefrom(result.getInt("distancefrom"));
                rate.setDistanceto(result.getInt("distanceto"));
                rate.setCreate_at(result.getDate("created_at"));
                rate.setUpdate_at(result.getDate("update_at"));
                //book.setCreated_at(result.getDate("created_at"));
                //book.setUpdate_at(result.getDate("update-at"));
            }
        } catch (SQLException e) {
            logger.info("error find rate" + e);
        }
        return rate;

    }


    public Cityscoordinate getCitecoordinateParam(PreparedStatement prstatement){
        Cityscoordinate cityscoordinate = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                cityscoordinate = new Cityscoordinate();
                cityscoordinate.setId(result.getInt("id"));
                cityscoordinate.setName(result.getString("name"));
                cityscoordinate.setName_local(result.getString("name_local"));
                cityscoordinate.setLatitude(result.getInt("latitude"));
                cityscoordinate.setLongitude(result.getInt("longitude"));;

            }
        } catch (SQLException e) {
            logger.info("error find coordinate" + e);
        }
        return cityscoordinate;

    }


    public Cityscoordinate checkCitesCoordinateById(int id) throws ClassNotFoundException {

        Cityscoordinate cityscoordinate = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_CITYCOORDINATE_BY_ID)) {
            prstatement.setInt(1, id);

            cityscoordinate = getCitecoordinateParam(prstatement);

        } catch (SQLException e) {
            logger.info("error find coordinate" + e);
        }

        return cityscoordinate;

    }


    public Cityscoordinate checkCitescoordinate(String name) throws ClassNotFoundException {

        Cityscoordinate cityscoordinate = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_CITYCOORDINATE)) {
            prstatement.setString(1, name);

            cityscoordinate = getCitecoordinateParam(prstatement);

        } catch (SQLException e) {
            logger.info("error find coordinate" + e);
        }

        return cityscoordinate;

    }


    public List<Cityscoordinate> findAllCitescoordinates() throws ClassNotFoundException {

        List<Cityscoordinate> cityscoordinates = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); Statement statement = con.createStatement(); ResultSet result = statement.executeQuery(SQL_FIND_ALL_CITYSCOORDINATES)) {
            while (result.next()) {
                Cityscoordinate cityscoordinate = new Cityscoordinate();
                cityscoordinate.setId(result.getInt("id"));
                cityscoordinate.setName(result.getString("name"));
                cityscoordinate.setName_local(result.getString("name_local"));
                cityscoordinate.setLatitude(result.getInt("latitude"));
                cityscoordinate.setLongitude(result.getInt("longitude"));
                cityscoordinates.add(cityscoordinate);
            }
        } catch (SQLException e) {
            logger.info("" + e);
        }
        return cityscoordinates;
    }

    //SQL_SELECT_RATES_BY_WEIGHT


    public Rate checkRate(int distanceFrom, int distanceTo , double weight) throws ClassNotFoundException {

        Rate rate = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_SELECT_RATES_BY_WEIGHT)) {
            prstatement.setInt(1, distanceFrom);
            prstatement.setInt(2, distanceTo);
            prstatement.setDouble(3, weight);

            rate = getRateParam(prstatement);

        } catch (SQLException e) {
            logger.info("error find rate" + e);
        }

        return rate;

    }


    public List<Rate> findAllRates() throws ClassNotFoundException {

        List<Rate> rates = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); Statement statement = con.createStatement(); ResultSet result = statement.executeQuery(SQL_FIND_ALL_RATES)) {
            while (result.next()) {
                Rate rate = new Rate();
                rate.setId(result.getInt("id"));
                rate.setWeight(result.getDouble("weight"));
                rate.setCost(result.getDouble("cost"));
                rate.setDistancefrom(result.getInt("distancefrom"));
                rate.setDistanceto(result.getInt("distanceto"));
                rate.setCreate_at(result.getDate("created_at"));
                rate.setUpdate_at(result.getDate("update_at"));
                rates.add(rate);
            }
        } catch (SQLException e) {
            logger.info("" + e);
        }
        return rates;
    }


}
