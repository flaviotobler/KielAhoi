/**
 * Created by flavi on 09.10.2017.
 */



var mymap = L.map('mapbox').setView([51.505, -0.09], 13);

L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox.streets',
    accessToken: 'pk.eyJ1IjoiZmxhdmlvdG9ibGVyIiwiYSI6ImNqOHBzdHMyaTBha3kzM3FvaGZ5NDZkNjUifQ.PR0rTpHVq86DojYZur9z0g'
}).addTo(mymap);



function tiefgangsenden()
{
    var tiefgang = document.getElementById('tiefgangInput').value
    var url = "localhost:9000/haefen/" + tiefgang;
    window.open(url);
}

function sendTiefgang(){
    var xhttp = new XMLHttpRequest();
    var tiefgang = document.getElementById('tiefgangInput').value;
    route = routes.controllers.WidgetController.heightControll(tiefgang);
    xhttp.open(route.method, route.url);
    xhttp.onreadystatechange = function(){
      document.getElementByID("container").innerHTML = xhttp.responseText;
    };
    xhttp.send();
}