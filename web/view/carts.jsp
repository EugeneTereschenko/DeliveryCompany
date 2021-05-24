<%@ page import="java.util.Properties" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="com.delivery.entity.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="com.delivery.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 24.05.21
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Order</title>
    <link rel="stylesheet" href="shopstyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-1.12.4.min.js"
            integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>


<script type="text/javascript">

    $(document).ready(function() {




        $("#ordercarts").on('click', '.pdfreport', function (e) {


            var url = "http://localhost:8080/createpdfdoc?cartid=" + this.id;

            window.open(url, '_blank');
        });

        $("#ordercarts").on('click', '.confirm', function(e) {
            var cart = {};

            cart.cartid = this.id;
            cart.confirm = "confirm";

            $.ajax({
                url: '/updateorder',
                type: 'POST',
                data: 'idorder=' + JSON.stringify(cart),
                dataType: 'JSON',
                beforeSend: function (x) {
                    if (x && x.overrideMimeType) {
                        x.overrideMimeType("application/j-son;charset=UTF-8");
                    }
                },
                success: function (data) {
                    console.log(data);


                },
                failure: function (data) {
                    console.log(data);

                }
            });

        });



        $("#ordercarts").on('click', '.canceled', function(e) {

            var cart = {};

            console.log("help me")

            cart.cartid = this.id;
            cart.confirm = "cancel";

            $.ajax({
                url: '/updateorder',
                type: 'POST',
                data: 'idorder=' + JSON.stringify(cart),
                dataType: 'JSON',
                beforeSend: function (x) {
                    if (x && x.overrideMimeType) {
                        x.overrideMimeType("application/j-son;charset=UTF-8");
                    }
                },
                success: function (data) {
                    // alert('success'+data);
                    console.log(data);


                },
                failure: function (data) {
                    console.log(data);
                    // alert(data);

                }
            });

        });


        $("#ordercarts").on('click', '.complete', function(e) {
            // alert("help me")
            // alert(this.id);
            var cart = {};

            cart.cartid = this.id;
            cart.confirm = "complete";

            $.ajax({
                url: '/updateorder',
                type: 'POST',
                data: 'idorder=' + JSON.stringify(cart),
                dataType: 'JSON',
                beforeSend: function (x) {
                    if (x && x.overrideMimeType) {
                        x.overrideMimeType("application/j-son;charset=UTF-8");
                    }
                },
                success: function (data) {
                    // alert('success'+data);
                    console.log(data);


                },
                failure: function (data) {
                    console.log(data);
                    // alert(data);

                }
            });

        });


        $("#id_button").click(function () {
            window.location.replace("http://localhost:8080/logout");
        });

        $("#id_button_back").click(function () {
            window.location.replace("http://localhost:8080/showdelivery");
        });


        $("#idusersitemy").click(function () {
            window.location.replace("http://localhost:8080/showusers");
        });



    });
</script>
<h1 align="center">Delivery Company</h1>

<%


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

    String exit = properties.getProperty("fieldExit");
    String usersitemy = properties.getProperty("fieldUser");




    String back = properties.getProperty("fieldBack");
    String role = (String) session.getAttribute("roleid");

    List<Cart> cart = (List<Cart>) session.getAttribute("viewcarts");
    List<User> user = (List<User>) session.getAttribute("viewcartusers");


    out.println("<div class=\"container\">");
    out.println("<div class=\"row\">");
    out.println("<div class=\"col\">");

    out.println("</div>");
    out.println("<div class=\"col\">");
    out.println("<ul class=\"nav nav-pills\">");
    out.println("<li role=\"presentation\" class=\"active\"><button type=\"button\" class=\"btn btn-primary btn-sm\" id=\"id_button\">" + exit +  "</button></li>");
    out.println("<li role=\"presentation\" class=\"active\"><button type=\"button\" class=\"btn btn-primary btn-sm\" id=\"id_button_back\">" + back +  "</button></li>");
    if (role.equals("administrator") || role.equals("manager")) {
        out.println("<li role=\"presentation\" class=\"active\"><input type=\"button\" class=\"btn btn-primary newclass btn-sm\" id=\"idusersitemy\" value=\"" + usersitemy + "\" /></li>");
    }
    out.println("</ul>");
    out.println("</div>");
    out.println("<div class=\"col\">");

    out.println("</div>");
    out.println("</div>");
    out.println("<div class=\"row\">");
    out.println("<div class=\"col\">");
    out.println("</div>");
    out.println("<div class=\"col\">");
    out.println("<table class=\"table card-table table-success table-striped\" id=\"ordercarts\" width = \"800\"><tbody>");
    out.println("<tr><td>cart_id</td><td>status</td><td>user_id</td><td width =\"300\">Books</td><td colspan =\"4\">Price</td></tr>");
    for(int i=0; null!=cart && i < cart.size(); i++) {
        out.println("<tr><td>" + cart.get(i).getId() + "</td><td>" + cart.get(i).getCheckout_step() + "</td><td>" + user.get(i).getEmail() + "</td><td>" + cart.get(i).getTotal_price() + " $</td>");
        out.println("<td><button id=\"" + cart.get(i).getId() + "\" class=\"btn btn-primary confirm booksnewview btn-block\">Confirm</button></td>");
        out.println("<td><button id=\"" + cart.get(i).getId() + "\" class=\"btn btn-primary complete booksnewedit btn-block\">Complete</button></td>");
        out.println("<td><button id=\"" + cart.get(i).getId() + "\" class=\"btn btn-primary canceled booksnewdelete btn-block\">Canceled</button></td>");
        out.println("<td><button id=\"" + cart.get(i).getId() + "\" class=\"btn btn-primary pdfreport booksnewdelete btn-block\">Create PDF report</button></td></tr>");
    }
    out.println("</tbody></table>");
    out.println("</div>");
    out.println("<div class=\"col\">");

    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
%>

</body>
</html>

