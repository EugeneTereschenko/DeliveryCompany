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
<%--
  <script>



     $(function() {



         $("#id_brought_count").click(function () {


             var delivery = {};

             var userId = getCookie("userid");
             var checkoutstep = "confirm";

             var cityFrom = $('#citeFrom').val();
             var cityTo = $('#citeTo').val();
             var typeOfdeli = $('#TypeTo').val();
             var count = $('#count').val();
             var weight = $('#weight').val();
             var length = $('#length').val();
             var width = $('#width').val();
             var height = $('#height').val();

             delivery.userId = userId;
             delivery.checkoutstep = checkoutstep;
             delivery.cityFrom = cityFrom;
             delivery.cityTo = cityTo;
             delivery.typeOfdeli = typeOfdeli;
             delivery.count = count;
             delivery.weight = weight;
             delivery.length = length;
             delivery.width = width;
             delivery.height = height;


             console.log(delivery);



             $.ajax({
                 url: '/DeliveryCompany/putdelivery',
                 type: 'POST',
                 data: 'delivery=' + JSON.stringify(delivery),
                 dataType: 'JSON',
                 beforeSend: function (x) {
                     if (x && x.overrideMimeType) {
                         x.overrideMimeType("application/j-son;charset=UTF-8");
                     }
                 },
                 success: function (data) {
                     // alert('success'+data);
                     console.log(data);
                     // $("#sentstatus_edit").html(data.deliveryprice.toString());
                     //$("#sentstatus_edit").html(data.toString());


                     $('<div/>',{'id':'TextBoxDiv' + data.cartid })
                         .html($('<div class="d-flex flex-row justify-content-between align-items-center p-2 bg-white mt-4 px-3 rounded">')
                             .html($('<div class="d-flex flex-column align-items-center product-details">')
                                 .html($('<span class="font-weight-bold">' + data.namecityFrom + ' ' + data.namecityTo + '</span>'))
                                    .append($('<div class="d-flex flex-row product-desc">')
                                        .html($('<div class="size mr-1">')
                                            .html('<span class="text-grey">Distance:</span><span class="font-weight-bold">' + data.distanceBetween + 'km</span>'))
                                            .append($('<div class="color">')
                                                .html('<span class="text-grey">Time:</span><span class="font-weight-bold">17:00-23:00</span>')
                                        )))
                                   .append($('<div class="d-flex flex-row align-items-center qty">').html('<button id=" + data.cartid + " class="btn btn-default iterdowncost" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-left-fill" viewBox="0 0 16 16">\n' +
                                       '  <path d="m3.86 8.753 5.482 4.796c.646.566 1.658.106 1.658-.753V3.204a1 1 0 0 0-1.659-.753l-5.48 4.796a1 1 0 0 0 0 1.506z"/>\n' +
                                       '</svg></button>'))
                                 .append($('<div class="d-flex flex-row align-items-center qty">').html('<h5 class="text-grey mt-1 mr-1 ml-1">' + count + '</h5>'))
                                 .append($('<div class="d-flex flex-row align-items-center qty">').html('<button id=" + data.cartid + " class="btn btn-default itercost" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16">\n' +
                                     '  <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/>\n' +
                                     '</svg></button>'))
                                 .append($('<div>').html('<h5 class="text-grey">' + data.shippingprice + '$</h5>'))
                                 .append($('<div class="d-flex align-items-center">').html('<button id=' + data.cartid + ' class="btn btn-primary iterdeletecost" rel="tooltip" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">\n' +
                     '                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>\n' +
                     '                    <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>\n' +
                     '                </svg></button>'))
                             )
                         .appendTo( '#TextBoxesGroup' )



                 },
                 failure: function (data) {
                     console.log(data);
                     // alert(data);
                   //  $("#sentstatus_edit").html("error");
                 }
             });


         });


       $("#id_price_count").click(function () {

           var delivery = {};

            var cityFrom = $('#citeFrom').val();
            var cityTo = $('#citeTo').val();
            var typeOfdeli = $('#TypeTo').val();
            var count = $('#count').val();
            var weight = $('#weight').val();
            var length = $('#length').val();
            var width = $('#width').val();
            var height = $('#height').val();


            delivery.cityFrom = cityFrom;
            delivery.cityTo = cityTo;
            delivery.typeOfdeli = typeOfdeli;
            delivery.count = count;
            delivery.weight = weight;
            delivery.length = length;
            delivery.width = width;
            delivery.height = height;


            console.log(delivery);



           $.ajax({
               url: '/DeliveryCompany/countdelivery',
               type: 'POST',
               data: 'delivery=' + JSON.stringify(delivery),
               dataType: 'JSON',
               beforeSend: function (x) {
                   if (x && x.overrideMimeType) {
                       x.overrideMimeType("application/j-son;charset=UTF-8");
                   }
               },
               success: function (data) {
                   // alert('success'+data);
                   console.log(data);
                  // $("#sentstatus_edit").html(data.deliveryprice.toString());
                   $("#sentstatus_edit").html(data.toString());

               },
               failure: function (data) {
                   console.log(data);
                   // alert(data);
                   $("#sentstatus_edit").html("error");
               }
           });


       });
     });
  </script>--%>


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
    <div class="row">
        <div class="col-md-6">
          <%--  3 of 1--%>
        </div>
        <div class="col-md-3">
           <%-- 3 of 2--%>
        </div>
        <div class="col-md-2">
          <%--  3 of 3--%>
        </div>
    </div>
  </div>

  </body>
</html>
