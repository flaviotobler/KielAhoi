/**
 * Created by flavi on 09.10.2017.
 */







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