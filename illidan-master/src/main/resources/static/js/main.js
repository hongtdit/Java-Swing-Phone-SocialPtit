
$(document).ready(function() {
    AOS.init({
        duration: 800,
        easing: 'slide'
    });

    (function($) {

        "use strict";

        $(window).stellar({
            responsive: true,
            parallaxBackgrounds: true,
            parallaxElements: true,
            horizontalScrolling: false,
            hideDistantElements: false,
            scrollProperty: 'scroll',
            horizontalOffset: 0,
            verticalOffset: 0
        });


        var fullHeight = function() {

            $('.js-fullheight').css('height', $(window).height());
            $(window).resize(function(){
                $('.js-fullheight').css('height', $(window).height());
            });

        };
        fullHeight();

        // loader
        var loader = function() {
            setTimeout(function() {
                if($('#ftco-loader').length > 0) {
                    $('#ftco-loader').removeClass('show');
                }
            }, 1);
        };
        loader();


        $('nav .dropdown').hover(function(){
            var $this = $(this);
            // 	 timer;
            // clearTimeout(timer);
            $this.addClass('show');
            $this.find('> a').attr('aria-expanded', true);
            // $this.find('.dropdown-menu').addClass('animated-fast fadeInUp show');
            $this.find('.dropdown-menu').addClass('show');
        }, function(){
            var $this = $(this);
            // timer;
            // timer = setTimeout(function(){
            $this.removeClass('show');
            $this.find('> a').attr('aria-expanded', false);
            // $this.find('.dropdown-menu').removeClass('animated-fast fadeInUp show');
            $this.find('.dropdown-menu').removeClass('show');
            // }, 100);
        });

        // scroll
        var scrollWindow = function() {
            $(window).scroll(function(){
                var $w = $(this),
                    st = $w.scrollTop(),
                    navbar = $('.ftco_navbar'),
                    sd = $('.js-scroll-wrap');

                if (st > 150) {
                    if ( !navbar.hasClass('scrolled') ) {
                        navbar.addClass('scrolled');
                    }
                }
                if (st < 150) {
                    if ( navbar.hasClass('scrolled') ) {
                        navbar.removeClass('scrolled sleep');
                    }
                }
                if ( st > 350 ) {
                    if ( !navbar.hasClass('awake') ) {
                        navbar.addClass('awake');
                    }

                    if(sd.length > 0) {
                        sd.addClass('sleep');
                    }
                }
                if ( st < 350 ) {
                    if ( navbar.hasClass('awake') ) {
                        navbar.removeClass('awake');
                        navbar.addClass('sleep');
                    }
                    if(sd.length > 0) {
                        sd.removeClass('sleep');
                    }
                }
            });
        };
        scrollWindow();


    })(jQuery);

    $('.tab-content').hide();
    $('#list-question ul li a').click(function (event) {
        event.preventDefault();
        var content = $(this).attr('href');
        // $(content).slideToggle(500);
        var class_name = $(content).parent().attr('class');
        if(class_name !== "active") {
            $(content).slideToggle(500);
            $(this).parent().addClass("active")
            $(this).find('i').addClass("flipping");
        } else {
            $(content).slideToggle(500);
            $(this).parent().removeClass("active")
            $(this).find('i').removeClass("flipping");
        }
    });

    $(".item_title").on('click', function(event) {
        // Make sure this.hash has a value before overriding default behavior
        if (this.hash !== "") {
            // Prevent default anchor click behavior
            event.preventDefault();
            // Store hash
            var hash = this.hash;
            // Using jQuery's animate() method to add smooth page scroll
            // The optional number (800) specifies the number of milliseconds it takes to scroll to the specified area
            $('html, body').animate({
                scrollTop: $(hash).offset().top
            }, 800, function(){
                // Add hash (#) to URL when done scrolling (default click behavior)
                window.location.hash = hash;
            });
        } // End if
    });

});


