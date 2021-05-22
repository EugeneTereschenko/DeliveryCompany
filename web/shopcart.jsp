<%@ page import="com.delivery.entity.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="com.delivery.count.RateDeliveryCount" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.nio.charset.StandardCharsets" %><%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 19.05.21
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="ownscript.js"></script>
    <link rel="stylesheet" href="./style/routestyle.css">

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

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
%>

<div class="container mt-5 mb-5">
    <div class="d-flex justify-content-center row">
        <div class="col-md-10">
            <div class="p-2">
                <h4>Shopping cart</h4>
                <div class="d-flex flex-row align-items-center pull-right"><span class="mr-1">Sort by:</span><span class="mr-1 font-weight-bold">Price</span><i class="fa fa-angle-down"></i><i class="icon-user"></i>
                    <span class="caret"><p><%=session.getAttribute("username")%></p></span><button type="button" class="btn btn-sm control-group card panel-shadow panel panel-info " id="id_button">exit</button>
                    <%
                    String username = (String) session.getAttribute("username");
                    String role = (String) session.getAttribute("roleid");
                        if (role.equals("administrator")) {
                            out.println("<input type=\"button\" class=\"btn btn-primary newclass list-group-item-action btn-sm\" id=\"idusersitemy\" value=\"" + usersitemy + "\" />");
                        }

                    %>
                </div>
            </div>
            <div id='TextBoxesGroup'>
                 <div id="TextBoxDiv1">
                <%-- <label>Textbox #1 : </label><input type='text' id='textbox1' name='textbox1' />--%>
                 </div>
                <%
                    List<Cart> carts = (List<Cart>) session.getAttribute("carts");
                    for(int i=0; null!=carts && i < carts.size(); i++) {


                out.println("<div id=\"TextBoxDiv" + carts.get(i).getId() + "\">");
                %>
                    <div class="d-flex flex-row justify-content-between align-items-center p-2 bg-white mt-4 px-3 rounded">
                    <div class="d-flex flex-column align-items-center product-details"><span class="font-weight-bold"><% out.println(carts.get(i).getCityFrom() + " " + carts.get(i).getCityTo());  %></span>
                        <div class="d-flex flex-row product-desc">
                            <div class="size mr-1"><span class="text-grey">Distance:</span><span class="font-weight-bold"><% out.println(carts.get(i).getDistance() + " " + "km"); %></span></div>
                            <div class="color"><span class="text-grey">Time:</span><span class="font-weight-bold">17:00-23:00</span></div>
                        </div>
                    </div>
                    <div class="d-flex flex-row align-items-center qty"><button id="<% out.print(carts.get(i).getId()); %>" class="btn btn-default iterdowncost" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-left-fill" viewBox="0 0 16 16">
                        <path d="m3.86 8.753 5.482 4.796c.646.566 1.658.106 1.658-.753V3.204a1 1 0 0 0-1.659-.753l-5.48 4.796a1 1 0 0 0 0 1.506z"/>\n' +
                        </svg></button>
                        <% out.print("<h5 id=\"textcount" + carts.get(i).getId() + "\"  class=\"text-grey mt-1 mr-1 ml-1\">"); %>
                        <% out.println(carts.get(i).getCount());  %></h5><button id="<% out.print(carts.get(i).getId()); %>" class="btn btn-default itercost" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"><path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/>
                        </svg></button>
                    </div>
                    <div>
                        <h5 class="text-grey"><% out.println((int) carts.get(i).getTotal_price());  %>$</h5>
                    </div>
                    <div class="d-flex align-items-center"><button id="<% out.print(carts.get(i).getId()); %>" class="btn btn-primary iterdeletecost" rel="tooltip" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                        <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                    </svg></button></div>
                    <div class="d-flex align-items-center">
                    <% if (carts.get(i).getCheckout_step().equals("complete")) {
                        out.println("<button id=\"" + carts.get(i).getId() + "\" class=\"btn btn-primary pdfreport btn-block\" >Create PDF report</button>");
                    } %>
                    </div>
                    </div>
                </div>
                  <%  } %>
            </div>


            <%--$("#removeButton").click(function () {
            if (counter == 1) {
            alert("No more textbox to remove");
            return false;
            }
            counter--;
            $("#TextBoxDiv" + counter).remove();
            });--%>

<%--            <div class="d-flex flex-row justify-content-between align-items-center p-2 bg-white mt-4 px-3 rounded">
                <div class="mr-1"><img class="rounded" src="https://i.imgur.com/XiFJkhI.jpg" width="70"></div>
                <div class="d-flex flex-column align-items-center product-details"><span class="font-weight-bold">Basic T-shirt</span>
                    <div class="d-flex flex-row product-desc">
                        <div class="size mr-1"><span class="text-grey">Size:</span><span class="font-weight-bold">&nbsp;M</span></div>
                        <div class="color"><span class="text-grey">Color:</span><span class="font-weight-bold">&nbsp;Grey</span></div>
                    </div>
                </div>
                <div class="d-flex flex-row align-items-center qty"><i class="fa fa-minus text-danger"></i>
                    <h5 class="text-grey mt-1 mr-1 ml-1">2</h5><i class="fa fa-plus text-success"></i>
                </div>
                <div>
                    <h5 class="text-grey">$20.00</h5>
                </div>
                <div class="d-flex align-items-center"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                    <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                </svg></div>
            </div>--%>
            <div class="d-flex flex-row align-items-center mt-3 p-2 bg-white rounded"><input type="text" class="form-control border-0 gift-card" placeholder="discount code/gift card"><button class="btn btn-outline-warning btn-sm ml-2" type="button">Apply</button></div>
            <div class="d-flex flex-row align-items-center mt-3 p-2 bg-white rounded"><button class="btn btn-warning btn-block btn-lg ml-2 pay-button" id="id_proceed_payment" type="button">Proceed to Pay</button></div>
        </div>
    </div>
</div>
</body>
</html>
