<%@ page import="com.delivery.entity.Rate" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 22.05.21
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div class="card panel-shadow panel panel-info" style="width: 600px;">
    <div class="card-header">Rate</div>
    <div class="panel panel-info table-responsive panel-shadow">
        <table class="table card-table table-success table-striped" id="idRateTable"><tbody>
        <tr><td>Distance km</td><td>Weight by kg</td><td>cost $</td></tr>
        <%
            List<Rate> rate = (List<Rate>) session.getAttribute("rates");
            for(int i=0; null!=rate && i < rate.size(); i++) {
                out.println("<tr><td>" + rate.get(i).getDistancefrom() + " - " + rate.get(i).getDistanceto() + "</td>" +
                        "<td>" + rate.get(i).getWeight() + "</td>" +
                        "<td>" + rate.get(i).getCost() + "</td>" +
                        "</tr>");
            }
        %>
        </tbody></table>
    </div>
</div>
</div>
</body>
</html>
