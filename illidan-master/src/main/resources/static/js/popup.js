$(document).ready(function () {
    function payment(name, description, price) {
        $('.price-pay').click(function(){
            var email = $(this).parent().parent().find('.email').val()
            var url_app = $(this).parent().parent().find('.url_app').val()
            var key_words = $(this).parent().parent().find('.key_words').val()

            if(email.trim().length < 1 || url_app.trim().length < 1 || key_words.trim().length < 1) {
                // var margin = $('.email').css('margin-left');
                // $('.input_hidden').css("margin-left",margin);
                $('.input_hidden').html("Please fill out the fields")
                return;
            }
            var dataObject = {}
            dataObject.name = name
            dataObject.email = email
            dataObject.urlApp = url_app
            dataObject.description = description
            dataObject.keyWords = key_words
            dataObject.price = price

            var data = JSON.stringify(dataObject)
            // console.log(data)
            $.ajax({
                type: 'POST',
                url: "/pay",
                headers: {  'Access-Control-Allow-Origin': 'https://www.sandbox.paypal.com' },
                crossDomain: true,
                crossOrigin: true,
                contentType: "application/json; charset=utf-8",
                data: data,

                success: function (data) {
                    // console.log("SUCCESS: " + data);
                    location.href = data;
                }
                // error: function (jqXhr, textStatus, errorMessage) {
                //     console.log("FAILED : ", errorMessage);
                // }
            });
            // }
            var $this = $(this);
            $this.button('loading');
        });
    }
    $(window).load(function () {
        $(".trigger_popup").click(function(){
            var name = $(this).parent().find('.name').text()
            var description = $(this).parent().find('.description').text()
            var price = $(this).parent().find('.price').text()
            $('.hover_bkgr').show();
            payment(name, description, price);
        });
        $('.popupCloseButton').click(function(){
            $('.hover_bkgr').hide();
        });
        $(".trigger_popup2").click(function(){
            var name = $(this).parent().find('.name').text()
            var description = $(this).parent().find('.description').text()
            var price = $(this).parent().find('.price').text()
            $('.hover_bkgr2').show();
            payment(name, description, price);
        });
        $('.popupCloseButton2').click(function(){
            $('.hover_bkgr2').hide();
        });

    });
});