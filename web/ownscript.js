function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

$(document).ready(function(){
    // view user

    $("#idusersitemy").click(function () {
        console.log("test");
        window.location.replace("http://localhost:8080/showusers");
    });




    // shop cart
    console.log("TEst");

    $("#TextBoxesGroup").on("click", ".iterdeletecost", function () {
        console.log(this.id);

        var id = this.id

        $.ajax({
            url: '/removedelivery',
            type: 'POST',
            data: {"cartid": this.id },
            success: function (data) {
                console.log(data);

                console.log(id);
                $("#TextBoxDiv" + id).remove();

                // alert(data);

            },
            failure: function (data) {

            }
        });

    });

    $("#TextBoxesGroup").on("click", ".iterdowncost", function () {
        console.log(this.id);

        var id = this.id


        var textcount = parseInt($('#textcount' + id).text());

        textcount = textcount - 1;


        $.ajax({
            url: '/updatenumdelivery',
            type: 'POST',
            data: {"cartid": id, "count": textcount },
            success: function (data) {
                console.log(data);

                $('#textcount' + id).text(textcount);
                // alert(data);

            },
            failure: function (data) {

            }
        });

    });



    $("#TextBoxesGroup").on("click", ".itercost", function () {
        console.log(this.id);

        var id = this.id


        var textcount = parseInt($('#textcount' + id).text());
        textcount = textcount + 1;


        $.ajax({
            url: '/updatenumdelivery',
            type: 'POST',
            data: {"cartid": id, "count": textcount },
            success: function (data) {
                console.log(data);

                $('#textcount' + id).text(textcount);
                // alert(data);

            },
            failure: function (data) {

            }
        });

    });




    $(".pdfreport").click(function () {

        alert(this.id);
        console.log("test");


        var url = "http://localhost:8080/createpdfdoc?cartid=" + this.id;

        //window.open.replace("http://localhost:8080/bookstore/createpdfdoc?cartid=" + );
        window.open(url, '_blank');
    });

    $("#id_button").click(function () {
        // alert("help me");
        window.location.replace("http://localhost:8080/logout");

    });

    $("#id_proceed_payment").click(function () {
        //document.cookie =  "cartid=" + data + ";";
        document.cookie =  "cartproc=1;";
        window.location.replace("http://localhost:8080/showdelivery");
    });
    // shop cart

    // checkoutform

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
            url: '/addressdata',
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
            url: '/paymentdata',
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
            url: '/updatedatacart',
            type: 'POST',
            data: {"userid":  userId},
            success: function (data) {
                console.log(data);

                if ((sendFlagaddress == 1)&&(sendFlagcard == 1)) {
                    document.cookie =  "cartproc=0;";
                    window.location.replace("http://localhost:8080/showdelivery");
                }

                // alert(data);

            },
            failure: function (data) {

            }
        });

    });

    // registration

    $("#id_delivery_register").click(function () {


        var text1 = $('#username').val();
        var text2 = $('#email').val();
        var text3 = $('#password').val();
        var text4 = $('#password_confirm').val();
        var text5 = "off";


        if($('#flexCheckDefault').prop('checked')){
            text5 = "on";
        }
        var text6 = $('#lang').val();

        $.ajax({
            url: '/registration',
            type: 'POST',
            data: {"username": text1, "email": text2, "password": text3, "password_confirm": text4, "lang": text6},
            success: function (data) {

                if (data != "stop") {

                    /*
                                            if (text5 == "on") {

                                                $.ajax({
                                                    url: '/bookstore/sendmailuserregister',
                                                    type: 'POST',
                                                    data: {"email": text2},
                                                    success: function (data) {


                                                    },
                                                    failure: function (data) {

                                                    }
                                                });
                                            }*/



                    window.location.replace("http://localhost:8080/showdelivery");
                }

            },
            failure: function (data) {

            }
        });

    });

    // login


    $(".id_delivery_login").click(function () {
        var text1 = $('#username').val();
        var text2 = $('#password').val();
        var text3 = $('#lang').val();

        $.ajax({
            url: '/authentication',
            type: 'POST',
            data: {"login": text1, "pass": text2, "lang": text3},
            success: function (data) {
                console.log(data);

                // alert(data);

                if (data != "stop") {
                    window.location.replace("http://localhost:8080/showdelivery");
                }

            },
            failure: function (data) {

            }
        });

    });


    // index



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
            url: '/putdelivery',
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
                        .append($('<div class="d-flex flex-row align-items-center qty">').html('<button id=' + data.cartid + ' class="btn btn-default iterdowncost" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-left-fill" viewBox="0 0 16 16">\n' +
                            '  <path d="m3.86 8.753 5.482 4.796c.646.566 1.658.106 1.658-.753V3.204a1 1 0 0 0-1.659-.753l-5.48 4.796a1 1 0 0 0 0 1.506z"/>\n' +
                            '</svg></button>'))
                        .append($('<div class="d-flex flex-row align-items-center qty">').html('<h5 id="textcount' + data.cartid + '" class="text-grey mt-1 mr-1 ml-1">' + count + '</h5>'))
                        .append($('<div class="d-flex flex-row align-items-center qty">').html('<button id=' + data.cartid + ' class="btn btn-default itercost" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16">\n' +
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
            url: '/countdelivery',
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