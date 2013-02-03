<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<script type="text/javascript" src="../../js/libs/jquery-1.7.1.js"></script>
<script type="text/javascript" src="../../js/libs/json2.js"></script>
<link rel="stylesheet" href="../../styles/style.css"
	type="text/css" />
<title>Politica</title>
</head>
<body style="margin-left: 0;">
<div id="fb-root"></div>
<script>
	var user = JSON.parse('<%= request.getAttribute("user") %>');	
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
	  			var body = $('#body');
	  			$('#loadingPanel').hide();
	  			domTitle.text("Hola " + response.name);
	  			body.show(); 
	  			
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
</script>
<script>
function testNewElections(){
	var size = $("#electionsSize").val();
	var life = $("#life").val();
	$.ajax({
		type : 'POST',
		url : '../election/new/' + size + '/' + life,
		dataType : 'json',
		contentType : 'application/json',
		data: JSON.stringify(user),
		success: function(election){}
	});
}

function testAvailableElections(){
	$.ajax({
		type : 'GET',
		url : '../election/available',
		dataType : 'json',
		contentType : 'application/json',
		success: function(elections){
			var container = $('#electionsContainer');
			container.empty();
			for(var i=0;i<elections.length;i++){
				container.append("<div>"+elections[i].id+"</div>");
			}
		}
	});
}

function testMyAvailableElections(){
	$.ajax({
		type : 'POST',
		url : '../election/myAvailable',
		dataType : 'json',
		contentType : 'application/json',
		data: JSON.stringify(user),
		success: function(elections){
			var container = $('#myElectionsContainer');
			container.empty();
			for(var i=0;i<elections.length;i++){
				container.append("<div>"+elections[i].id+"</div>");
			}
		}
	});
}
</script>
	<div id=loadingPanel class="loading">
		<img src="../../assets/loading.gif">
	</div>
	<div id="body">
		<h1 id="hello"></h1>
		<div>
			<h2>Nuevas Elecciones</h2>
			<label>Candidatos:</label>
			<select id="electionsSize">
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
			</select>
			<label>Duración en semanas:</label>
			<select id="life">
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="10">10</option>
				<option value="12">12</option>
				<option value="15">15</option>
				<option value="20">20</option>
			</select>
			<label>Fecha de Inicio:</label>
			<input id="initDate" type="date" />
			<button onclick="testNewElections()">Prueba Nuevas Elecciones</button>
		</div>
		<h2>Search Available Elections</h2>
		<div>
			<button onclick="testAvailableElections()">Search</button>
			<div id="electionsContainer"></div>
		</div>
		<h2>Search My Available Elections</h2>
		<div>
			<button onclick="testMyAvailableElections()">Search</button>
			<div id="myElectionsContainer"></div>
		</div>
	</div>
</body>
<script>
$('#body').hide();
</script>
</html>