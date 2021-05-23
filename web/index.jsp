<%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 16.05.21
  Time: 07:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.delivery.StoreDeliveryServlet" %>
<%@ page import="com.delivery.entity.Rate" %>
<%@ page import="java.util.List" %>
<%@ page import="com.delivery.entity.Cityscoordinate" %>
<html>
  <head>
    <title>DeliveryCompany</title>

      <script type="text/javascript" src="ownscript.js"></script>
    <script
            src="https://code.jquery.com/jquery-1.12.4.min.js"
            integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

      <link rel="stylesheet" href="./style/routestyle.css">

  </head>
  <body>

  <div class="container">
    <div class="row">
      <div class="col-md-6">



            <%

          int cartproc = 0;
          Cookie[] cookies = request.getCookies();
          for (int i = 0; i < cookies.length; i++) {
                    String name = cookies[i].getName();
                    String valueID = cookies[i].getValue();
                    if (name.equals("cartproc")) {
                        cartproc = Integer.parseInt(valueID);
                    }

                }

                System.out.println(cartproc);

          if (cartproc == 1) { %>
          <jsp:include page="checkoutform.jsp" />
         <% } %>


      <%--"1 of 1"--%>
      </div>
      <div class="col-md-6">
          <%
              String username = (String) session.getAttribute("username");
              if (username == null){ %>
                <jsp:include page="login.jsp" />
             <% } else { %>
                <jsp:include page="shopcart.jsp" />
                <%
              }
          %>
<%--          <c:out logInfo="${session.getAttribute("username")}" />
          <c:if "${logInfo == null}">
          <jsp:include page="login.jsp" />
          </c:if>--%>
      </div>
    </div>
    <div class="row">
      <div class="col-md-4">

          <%
              if (cartproc == 0) { %>
          <jsp:include page="rate.jsp" />
          <% } %>

      </div>
      <%--"2 of 1"--%>
      <div class="col-md-3">
          <%--2 of 2--%>
      </div>
      <div class="col-md-5">
          <%--2 of 3--%>
              <%
                  if (cartproc == 0) { %>
              <jsp:include page="choose.jsp" />
              <% } %>
          <%--"2 of 3"--%>
          <%--<jsp:include page="shopcart.jsp" />--%>
      </div>
    </div>
  </div>

  </body>
</html>
