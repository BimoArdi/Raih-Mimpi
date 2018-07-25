jQuery(document).ready(function($) {

    // Banner Slider Function
    $('.charity-banner').slick({
      dots: false,
      infinite: true,
      prevArrow: "<span class='slick-arrow-left'><i class='fa fa-angle-left'></i></span>",
      nextArrow: "<span class='slick-arrow-right'><i class='fa fa-angle-right'></i></span>",
      speed: 1000,
      autoplay: true,
      autoplaySpeed: 2000,
      slidesToShow: 1,
      slidesToScroll: 1,
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1,
            infinite: true,
          }
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1
          }
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1
          }
        }
      ]
    });
    // Partner Slider Function
    $('.charity-partner-slider').slick({
      dots: false,
      infinite: true,
      prevArrow: "<span class='slick-arrow-left'><i class='fa fa-angle-left'></i></span>",
      nextArrow: "<span class='slick-arrow-right'><i class='fa fa-angle-right'></i></span>",
      speed: 1000,
      autoplay: true,
      autoplaySpeed: 2000,
      slidesToShow: 4,
      slidesToScroll: 1,
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesToShow: 2,
            slidesToScroll: 1,
            infinite: true,
          }
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 2,
            slidesToScroll: 1
          }
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1
          }
        }
      ]
    });

    //***************************
    // Click to Top Button
    //***************************
    jQuery('.back-top').on("click", function() {
        jQuery('html, body').animate({
            scrollTop: 0
        }, 800);
        return false;
    });
    //***************************
    // Countdown Function
    //***************************
      var austDay = new Date();
      austDay = new Date(austDay.getFullYear() + 2, 1 - 1, -308);
      jQuery('#charity-countdown').countdown({
          until: austDay
      });
      jQuery('#year').text(austDay.getFullYear());
    //***************************
    // CartToggle Function
    //***************************
    jQuery('.charity-header-options i.fa-opencart').on("click", function(){
          jQuery('.charity-cart-box').fadeToggle('slow');
          return false;
      });
    jQuery('html').on("click", function() { jQuery(".charity-cart-box").fadeOut(); });

    //***************************
    // Jplayer Function
    //***************************
    var myPlayer = new jPlayerPlaylist({
        jPlayer: "#jquery_jplayer_16",
        cssSelectorAncestor: "#jp_container_16"
    }, [
        //php writePlaylist('Y',' (!!duration!!)'); 
        //php writePlaylist('Y',"<span style='color:#828282'> (!!duration!!)</span>");
        {
            title: "Dj kozak-promo mix soundwave <span>05:30</span>",
            mp3: "http://www.jplayer.org/audio/mp3/Miaow-03-Lentement.mp3",
            oga: "http://www.jplayer.org/audio/ogg/Miaow-02-Hidden.ogg",
            album_buy_mp3: "sdas",
            url: "sdas",
            layrics: "sdas",
        }, {
            title: "Shot me Down (feat. Skylar Grey) <span>04:00</span>",
            mp3: "http://www.jplayer.org/audio/mp3/Miaow-03-Lentement.mp3",
            oga: "http://www.jplayer.org/audio/ogg/Miaow-02-Hidden.ogg",
            album_buy_mp3: "sdas",
            url: "sdas",
            layrics: "sdas",
        }, {
            title: "I'll Keep Loving you (feat. Birdy) <span>03:00</span>",
            mp3: "http://www.jplayer.org/audio/mp3/Miaow-03-Lentement.mp3",
            oga: "http://www.jplayer.org/audio/ogg/Miaow-02-Hidden.ogg",
            album_buy_mp3: "sdas",
            url: "sdas",
            layrics: "sdas",
        }, {
            title: "The Hum (Short Edit) <span>04:30</span>",
            mp3: "http://www.jplayer.org/audio/mp3/Miaow-03-Lentement.mp3",
            oga: "http://www.jplayer.org/audio/ogg/Miaow-02-Hidden.ogg",
            album_buy_mp3: "sdas",
            url: "sdas",
            layrics: "sdas",
        },
    ],
     {
       
        playlistOptions: { loopOnPrevious: true, },
        loop: true,
        swfPath: "images/jplayer.swf",
        supplied: "mp3, oga",
        wmode: "window",
        useStateClassSkin: true,
        autoBlur: false,
        preload: 'auto',
        preload: 'metadata',
        smoothPlayBar: true,
        audioFullScreen: true,
        keyEnabled: true,
    });


});

// Multi-Toggle Navigation
jQuery(function ($) {
    'use strict';

    $('body').addClass('js');
    var $menu = $('#menu'),
            $menulink = $('.menu-link');
    $(".charity-navigation li").each(function () {
        var each_li = $(this);
        if (each_li.find('ul').length > 0) {
            each_li.append("<span class='has-subnav'><i class='fa fa-angle-down'></i></span>");
        }
    });
    var $menuTrigger = $('.has-subnav');

    $menulink.on("click", function (e) {
        e.preventDefault();
        $menulink.toggleClass('active');
        $menu.toggleClass('active');
    });

    $menuTrigger.on("click", function (e) {
        e.preventDefault();
        var $this = $(this);
        $this.parent('li').find('ul').toggleClass('active');
    });

});
jQuery(window).resize(function(){
    'use strict';
    var $ = jQuery;
    var $menu = $('#menu'),
    $menulink = $('.menu-link');
    $menu.removeClass('active');
    $menulink.removeClass('active');
});

jQuery('.charity-cause-progressbar').progressBar({
    shadow : false,
    percentage : true,
    animation : true,
});

// Counter
var a = 0;
$(window).scroll(function() {

  var oTop = $('#counter').offset().top - window.innerHeight;
  if (a == 0 && $(window).scrollTop() > oTop) {
    $('.counter-value').each(function() {
      var $this = $(this),
        countTo = $this.attr('data-count');
      $({
        countNum: $this.text()
      }).animate({
          countNum: countTo
        },

        {

          duration: 5000,
          easing: 'swing',
          step: function() {
            $this.text(Math.floor(this.countNum));
          },
          complete: function() {
            $this.text(this.countNum);
            //alert('finished');
          }

        });
    });
    a = 1;
  }

});

// When the window has finished loading create our google map below
google.maps.event.addDomListener(window, 'load', init);

function init() {
    // Basic options for a simple Google Map
    // For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions
    var mapOptions = {
        // How zoomed in you want the map to start at (always required)
        zoom: 11,

        // The latitude and longitude to center the map (always required)
        center: new google.maps.LatLng(40.6700, -73.9400), // New York

        // How you would like to style the map. 
        // This is where you would paste any style found on Snazzy Maps.
        styles: [{"featureType":"administrative","elementType":"labels.text.fill","stylers":[{"color":"#444444"}]},{"featureType":"landscape","elementType":"all","stylers":[{"color":"#f2f2f2"}]},{"featureType":"poi","elementType":"all","stylers":[{"visibility":"off"}]},{"featureType":"road","elementType":"all","stylers":[{"saturation":-100},{"lightness":45}]},{"featureType":"road.highway","elementType":"all","stylers":[{"visibility":"simplified"}]},{"featureType":"road.arterial","elementType":"labels.icon","stylers":[{"visibility":"off"}]},{"featureType":"transit","elementType":"all","stylers":[{"visibility":"off"}]},{"featureType":"water","elementType":"all","stylers":[{"color":"#46bcec"},{"visibility":"on"}]}]
    };

    // Get the HTML DOM element that will contain your map 
    // We are using a div with id="map" seen below in the <body>
    var mapElement = document.getElementById('map');

    // Create the Google Map using our element and options defined above
    var map = new google.maps.Map(mapElement, mapOptions);

    // Let's also add a marker while we're at it
    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(40.6700, -73.9400),
        map: map,
        title: 'Snazzy!'
    });
}

 function displaytransfer (){
    var rp = $("#currency").val();
    rp = rp.replace("Rp ","");
    rp = rp.replace(".","");
    rp = rp.replace(".","");
    var donasi =parseInt(rp);
    var unik = parseInt(Math.random()*1000);
    var sponosr;
    sponosr=("Silahkan Transfer Ke Rekening Sejumlah Rp. "+(donasi+unik));
    document.getElementById("Donasi").innerHTML=sponosr;
 }
 
 function displayTanpaRp(){
	 var rp = $("#currency").val();
	    rp = rp.replace("Rp ","");
	    rp = rp.replace(".","");
	    rp = rp.replace(".","");
	    var donasi =parseInt(rp);
	    document.getElementById('jmlSponsor').value=donasi;
 }


 function openCity(evt, cityName) {
     var i, tabcontent, tablinks;
     tabcontent = document.getElementsByClassName("tabcontent");
     for (i = 0; i < tabcontent.length; i++) {
         tabcontent[i].style.display = "none";
     }
     tablinks = document.getElementsByClassName("tablinks");
     for (i = 0; i < tablinks.length; i++) {
         tablinks[i].className = tablinks[i].className.replace(" active", "");
     }
     document.getElementById(cityName).style.display = "block";
     evt.currentTarget.className += " active";
 }
 