<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<script type="text/javascript" src="../../js/libs/jquery-1.7.1.js"></script>
<link rel="stylesheet" href="../../styles/style.css"
	type="text/css" />
<title>Politica</title>
</head>
<body style="margin-left: 0;">
<div id="fb-root"></div>
<script>
	//Script para cargar el preloading
	
</script>
<script>
	(function() {
		var e = document.createElement('script'); e.async = true;
        e.src = document.location.protocol + '//connect.facebook.net/en_US/all.js';
        document.getElementById('fb-root').appendChild(e);
    }());
	window.fbAsyncInit = function() {
	    FB.init({
	      appId      : '<%= request.getAttribute("facebook_api_key")%>', // App ID [PARAMETRIZAR]
	      status     : true, // check login status
	      cookie     : true, // enable cookies to allow the server to access the session
	      xfbml      : true,  // parse XFBML
	      oauth		 : true
	    });
	    FB.Event.subscribe('auth.authResponseChange', function(response){
	    }); 
	  	FB.Event.subscribe('auth.login', function(response){
		}); 
	 	 FB.Event.subscribe('auth.logout', function(response){
		}); 
	  	FB.getLoginStatus(function(response){
	  		FB.api('/me', function(response) {
	  			var domTitle = $('#hello');
	  			$('#loadingPanel').hide();
	  			domTitle.text("Hola " + response.name);
	  			domTitle.show(); 
	  			
	  			/* FB.api('/' + response.id + '/friends', function(response){
	  				FB.api('/' + response.data[0].id + '/friends', function(response){
	  					alert('friends de friends' + response.data);
	  				});
	  			}); */
	  		});
		  //new UserAuthenticationEvent(response.authResponse);
	  	});
	    // Additional initialization code here
	  };
	  // Load the SDK Asynchronously
	  (function(d){
	     var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
	     if (d.getElementById(id)) {return;}
	     js = d.createElement('script'); js.id = id; js.async = true;
	     js.src = "//connect.facebook.net/en_US/all.js";
	     ref.parentNode.insertBefore(js, ref);
	   }(document));
	  $('#hello').hide();
</script>
	<div id=loadingPanel class="loading">
		<img src="../../assets/loading.gif">
	</div>
	<h1 id="hello"></h1>
</body>
</html>