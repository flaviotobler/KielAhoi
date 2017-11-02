
function loadMap() {

    console.log("part3");
    var mymap = L.map('mapbox').setView([47.607, 9.441], 10);

    L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
        maxZoom: 18,
        id: 'mapbox.streets',
        accessToken: 'pk.eyJ1IjoiZmxhdmlvdG9ibGVyIiwiYSI6ImNqOHBzdHMyaTBha3kzM3FvaGZ5NDZkNjUifQ.PR0rTpHVq86DojYZur9z0g'
    }).addTo(mymap);

    var greenIcon = new L.Icon({
        iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-green.png',
        shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [41, 41]
    });

    var redIcon = new L.Icon({
        iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png',
        shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [41, 41]
    });


    var req = new XMLHttpRequest();
    req.open("GET","ports",false)
    req.send();
    console.log(req.response);
    JSON.parse(req.responseText).forEach(function(port){
        console.log(document.getElementById("tiefgangInput").value);
        console.log(port.height);
        if(port.height - document.getElementById("tiefgangInput").value <= 0)
        {
            L.marker([port.lon, port.lat], {icon: redIcon}).addTo(mymap);
        }
        else
        {
            L.marker([port.lon, port.lat], {icon: greenIcon}).addTo(mymap);
        }
    })


}
