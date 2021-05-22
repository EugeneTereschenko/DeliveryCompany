<%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 19.05.21
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <script type="text/javascript" src="ownscript.js"></script>

    <%--action='addressdata'--%>
    <%--action='paymentdata'--%>

<%--    <script>
        $(function() {
           $("#id_checkout_login").click(function () {

               var firstName = $('#firstName').val();
               var lastName = $('#lastName').val();
               var username = $('#username').val();
               var email = $('#email').val();
               var address = $('#address').val();
               var phone = $('#phone').val();
               var country = $('#country').val();
               var state = $('#state').val();
               var zip = $('#zip').val();


               var sendFlagaddress = 0;
               var sendFlagcard = 0;

               $.ajax({
                   url: '/DeliveryCompany/addressdata',
                   type: 'POST',
                   data: {"firstName": firstName, "lastName": lastName, "username": username, "email": email, "address": address, "phone": phone, "country": country, "state": state, "zip": zip  },
                   success: function (data) {
                       console.log(data);

                       sendFlagaddress = 1;
                       // alert(data);

                   },
                   failure: function (data) {

                   }
               });

               //var paymentMethod = $('#paymentMethod').val();
               var paymentMethod;

               console.log()

               var credname = $('#cc-name').val();
               var crednumber = $('#cc-number').val();
               var credexpiration = $('#cc-expiration').val();
               var credcvv = $('#cc-cvv').val();


               if(document.getElementById('credit').checked) {
                   paymentMethod = "credit";
                   alert(paymentMethod);
               }else if(document.getElementById('debit').checked) {
                   paymentMethod = "debit";
                   alert(paymentMethod);
               }else if(document.getElementById('paypal').checked){
                   paymentMethod = "paypal";
                   alert(paymentMethod);
               }



               $.ajax({
                   url: '/DeliveryCompany/paymentdata',
                   type: 'POST',
                   data: {"paymentMethod": paymentMethod, "credname": credname, "crednumber": crednumber, "credexpiration": credexpiration, "credcvv": credcvv },
                   success: function (data) {
                       console.log(data);


                       sendFlagcard = 1;

                       // alert(data);

                   },
                   failure: function (data) {

                   }
               });

               var userId = getCookie("userid");


               $.ajax({
                   url: '/DeliveryCompany/updatedatacart',
                   type: 'POST',
                   data: {"userid":  userId},
                   success: function (data) {
                       console.log(data);

                       if ((sendFlagaddress == 1)&&(sendFlagcard == 1)) {
                           document.cookie =  "cartproc=0;";
                           window.location.replace("http://localhost:8080/DeliveryCompany/showdelivery");
                       }

                       // alert(data);

                   },
                   failure: function (data) {

                   }
               });


           });
        });
    </script>--%>


</head>
<body>
<table align="center"><tr><td>
    <div class="col-md-8 order-md-1">
        <h4 class="mb-3">Billing address</h4>
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="firstName">First name</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="" value="" required>
                    <div class="invalid-feedback">
                        Valid first name is required.
                    </div>
                </div>
                <div class="col-md-6 mb-3">
                    <label for="lastName">Last name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="" value="" required>
                    <div class="invalid-feedback">
                        Valid last name is required.
                    </div>
                </div>
            </div>

            <div class="mb-3">
                <label for="username">Username</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">@</span>
                    </div>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
                    <div class="invalid-feedback" style="width: 100%;">
                        Your username is required.
                    </div>
                </div>
            </div>

            <div class="mb-3">
                <label for="email">Email <span class="text-muted">(Optional)</span></label>
                <input type="email" class="form-control" id="email" name="email" placeholder="you@example.com">
                <div class="invalid-feedback">
                    Please enter a valid email address for shipping updates.
                </div>
            </div>

            <div class="mb-3">
                <label for="address">Address</label>
                <input type="text" class="form-control" name="address" id="address" placeholder="1234 Main St" required>
                <div class="invalid-feedback">
                    Please enter your shipping address.
                </div>
            </div>

            <div class="mb-3">
                <label for="phone">phone <span class="text-muted">(Optional)</span></label>
                <input type="text" class="form-control" name="phone" id="phone" placeholder="phone">
            </div>


            <div class="row">
                <div class="col-md-5 mb-3">
                    <label for="country">Country</label>
                    <select class="custom-select d-block w-100" id="country" name="country" required>
                        <option value="">Choose...</option>
                        <option>United States</option>
                        <option>Ukraine</option>
                    </select>
                    <div class="invalid-feedback">
                        Please select a valid country.
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="state">State</label>
                    <select class="custom-select d-block w-100" id="state" name="state" required>
                        <option value="">Choose...</option>
                        <option>California</option>
                        <option>Dnipro</option>
                    </select>
                    <div class="invalid-feedback">
                        Please provide a valid state.
                    </div>
                </div>
                <div class="col-md-3 mb-3">
                    <label for="zip">Zip</label>
                    <input type="text" class="form-control" id="zip" placeholder="" name="zip" required>
                    <div class="invalid-feedback">
                        Zip code required.
                    </div>
                </div>
            </div>
            <hr class="mb-4">
            <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" name="same-address" id="same-address">
                <label class="custom-control-label" for="same-address">Shipping address is the same as my billing address</label>
            </div>
            <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" name="save-info" id="save-info">
                <label class="custom-control-label" for="save-info">Save this information for next time</label>
            </div>
            <hr class="mb-4">
    </div>
</td></tr>
    <tr><td>
        <div class="col-md-8 order-md-1">
                <hr class="mb-4">
                <h4 class="mb-3">Payment</h4>
                <div class="d-block my-3" id="checkboxes">
                    <div class="custom-control custom-radio">
                        <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked required>
                        <label class="custom-control-label" for="credit">Credit card</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" required>
                        <label class="custom-control-label" for="debit">Debit card</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" required>
                        <label class="custom-control-label" for="paypal">Paypal</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="cc-name">Name on card</label>
                        <input type="text" class="form-control" id="cc-name" name="credname" placeholder="" required>
                        <small class="text-muted">Full name as displayed on card</small>
                        <div class="invalid-feedback">
                            Name on card is required
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="cc-number">Credit card number</label>
                        <input type="text" class="form-control" id="cc-number" name="crednumber" placeholder="" required>
                        <div class="invalid-feedback">
                            Credit card number is required
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 mb-3">
                        <label for="cc-expiration">Expiration</label>
                        <input type="text" class="form-control" id="cc-expiration" name="credexpiration" placeholder="" required>
                        <div class="invalid-feedback">
                            Expiration date required
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="cc-expiration">CVV</label>
                        <input type="text" class="form-control" id="cc-cvv" name="credcvv" placeholder="" required>
                        <div class="invalid-feedback">
                            Security code required
                        </div>
                    </div>
                </div>
                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block id_checkout_login" type="submit" id="id_checkout_login" >Continue to checkout</button>
        </div>
    </td></tr>
</table>
</body>
</html>
