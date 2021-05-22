package com.delivery.count;

import com.delivery.db.CartDAO;
import com.delivery.db.RatedeliveryDAO;
import com.delivery.entity.Cart;
import com.delivery.entity.Cityscoordinate;
import com.delivery.entity.Rate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

public class RateDeliveryCount {



    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(RateDeliveryCount.class);

    private static Rate rate;
    public static Cityscoordinate citysFrom;
    public static Cityscoordinate citysTo;

    private static int count;

    public static double distanceBetween;
    private static String userId;
    public static double totalprice;
    public static double shippingprice;

    private static String checkoutstep;

    private static String typeOfde;
    private static int weight;
    private static int length;
    private static int width;
    private static int height;



    public RateDeliveryCount(){

    }

    public static double distance(double lat1, double lon1, double lat2, double lon2){
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;

        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        double r = 6371;


        logger.info(c * r);

        return (c * r);

    }

    public static double countRate(String temp){

        try {
            JSONObject myJsonObject = new JSONObject(temp);


            RatedeliveryDAO ratedeliveryDAO = new RatedeliveryDAO();

            citysFrom = new Cityscoordinate();
            citysTo = new Cityscoordinate();
            rate = new Rate();

            citysFrom.setId(myJsonObject.getInt("cityFrom"));
            citysTo.setId(myJsonObject.getInt("cityTo"));
            typeOfde = myJsonObject.getString("typeOfdeli");
            count =  myJsonObject.getInt("count");
            weight = myJsonObject.getInt("weight");
            length = myJsonObject.getInt("length");
            width = myJsonObject.getInt("width");
            height = myJsonObject.getInt("height");


            citysFrom = ratedeliveryDAO.checkCitesCoordinateById(citysFrom.getId());
            citysTo = ratedeliveryDAO.checkCitesCoordinateById(citysTo.getId());

            logger.info(citysFrom.getLatitude());
            logger.info(citysFrom.getLongitude());



            logger.info(citysTo.getLatitude());
            logger.info(citysTo.getLongitude());

            System.out.println(citysFrom.getLatitude() + " " + citysTo.getLatitude()  + " " + citysFrom.getLongitude() + " " + citysTo.getLongitude());

            distanceBetween = distance(citysFrom.getLatitude(), citysFrom.getLongitude(), citysTo.getLatitude(), citysTo.getLongitude());

            return distanceBetween;

        } catch (JSONException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }



    public int putRate(String temp){


        try {
            JSONObject myJsonObject = new JSONObject(temp);


            RatedeliveryDAO ratedeliveryDAO = new RatedeliveryDAO();

            citysFrom = new Cityscoordinate();
            citysTo = new Cityscoordinate();
            rate = new Rate();

            citysFrom.setId(myJsonObject.getInt("cityFrom"));
            citysTo.setId(myJsonObject.getInt("cityTo"));


            citysFrom = ratedeliveryDAO.checkCitesCoordinateById(citysFrom.getId());
            citysTo = ratedeliveryDAO.checkCitesCoordinateById(citysTo.getId());
            distanceBetween = distance(citysFrom.getLatitude(), citysFrom.getLongitude(), citysTo.getLatitude(), citysTo.getLongitude());




            userId = myJsonObject.getString("userId");


            checkoutstep = myJsonObject.getString("checkoutstep");

            typeOfde = myJsonObject.getString("typeOfdeli");
            count =  myJsonObject.getInt("count");
            weight = myJsonObject.getInt("weight");
            length = myJsonObject.getInt("length");
            width = myJsonObject.getInt("width");
            height = myJsonObject.getInt("height");



            totalprice = distanceBetween * 0.12;
            shippingprice = totalprice + 100;



            CartDAO cartDAO = new CartDAO();

            Cart cart = new Cart();

            cart = cartDAO.insertCart(Integer.parseInt(userId), (int) totalprice, (int) shippingprice, checkoutstep, citysFrom.getName(), citysTo.getName(), typeOfde, count, weight, length, width, height, (int) distanceBetween);


            return cart.getId();


        } catch (JSONException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }

    }

}
