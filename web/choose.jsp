<%@ page import="com.delivery.entity.Cityscoordinate" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 22.05.21
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col">
            <div class="control-group" align = "center">
                <select class="form-select" aria-label="Default select example" id="citeFrom" name="cite" >
                    <option selected>Choose City</option>
                    <%
                        List<Cityscoordinate> cityscoordinatesFrom = (List<Cityscoordinate>) session.getAttribute("cityscoordinates");
                        for(int i = 0; null!= cityscoordinatesFrom && i < cityscoordinatesFrom.size(); i++) {
                            out.println("<option value=" + cityscoordinatesFrom.get(i).getId() + ">" + cityscoordinatesFrom.get(i).getName() + "</option>");
                        }
                    %>
                </select>
            </div>
            <%-- "2 of 3 -> 1 of 1"--%>
        </div>
        <div class="col">
            <div class="control-group" align = "center">
                <select class="form-select" aria-label="Default select example" id="citeTo" name="cite" >
                    <option selected>Choose City</option>
                    <%
                        List<Cityscoordinate> cityscoordinatesTo = (List<Cityscoordinate>) session.getAttribute("cityscoordinates");
                        for(int i = 0; null!= cityscoordinatesTo && i < cityscoordinatesTo.size(); i++) {
                            out.println("<option value=" + cityscoordinatesTo.get(i).getId() + ">" + cityscoordinatesTo.get(i).getName() + "</option>");
                        }
                    %>
                </select>
            </div>
            <%--"2 of 3 -> 1 of 2"--%>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            <div class="control-group" align = "center">
                <select class="form-select" aria-label="Default select example" id="TypeTo" name="TypeTo" >
                    <option selected>Choose Type</option>
                    <option value="1">Cargo</option>
                    <option value="2">Documents</option>
                </select>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-5">
            <div class="control-group">
                <!-- Count -->
                <label class="control-label"  for="count">Count</label>
                <div class="controls">
                    <input type="text" id="count" name="Count" placeholder="">
                </div>
            </div>
        </div>
        <div class="col-md-2">
            <div class="control-group">
                <!-- Weight -->
                <label class="control-label"  for="weight">Weight</label>
                <div class="controls">
                    <input type="text" id="weight" name="Weight" placeholder="" >
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-4 col-md-5">
            <div class="control-group">
                <!-- Length -->
                <label class="control-label"  for="length">Length</label>
                <div class="controls">
                    <input type="text" id="length" name="Length" placeholder="">
                </div>
            </div>
        </div>
        <div class="col-xs-4 col-md-5">
            <div class="control-group">
                <!-- Width -->
                <label class="control-label"  for="length">Width</label>
                <div class="controls">
                    <input type="text" id="width" name="Width" placeholder="">
                </div>
            </div>
        </div>
        <div class="col-xs-4 col-md-2">
            <div class="control-group">
                <!-- Height -->
                <label class="control-label"  for="height">Height</label>
                <div class="controls">
                    <input type="text" id="height" name="Height" placeholder="">
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-7">
        </div>
        <div class="col-xs-4 col-md-5">
            <div class="control-group">
                <!-- Height -->
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <label class="control-label"  for="height"></label>
            <div class="input-group-prepend">
                <span class="input-group-text" id="sentstatus_edit">Price:</span>
            </div>
        </div>
        <div class="col-md-3">
            <!-- Button -->
            <label class="control-label"  for="height"></label>
            <div class="controls">
                <button id="id_price_count" class="btn btn-default id_price_count control-group card panel-shadow panel panel-info">Get Price</button>
            </div>
        </div>
        <div class="col-md-3">
            <!-- Button -->
            <%
                if (session.getAttribute("username") != null){ %>
            <label class="control-label"  for="height"></label>
            <div class="controls">
                <button id="id_brought_count" class="btn btn-default id_price_count control-group card panel-shadow panel panel-info">Add to Cart</button>
            </div>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
