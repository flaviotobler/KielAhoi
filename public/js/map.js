var map = L.map('mapbox').setView([47.607, 9.441], 10);

// Karten-Layer
L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox.streets',
    accessToken: 'pk.eyJ1IjoiZmxhdmlvdG9ibGVyIiwiYSI6ImNqOHBzdHMyaTBha3kzM3FvaGZ5NDZkNjUifQ.PR0rTpHVq86DojYZur9z0g'
}).addTo(map);

// leerer geoJSON-Layer erstellen
var portLayer = L.geoJSON(geojsonFeature, {
    onEachFeature: onEachFeature,
    pointToLayer: function (feature, latlng) {
        return L.circleMarker(latlng, style(feature));
    }
}).addTo(map);

// Daten zu geoJSON-Layer hinzufügen
portLayer.addData(geojsonFeature);


// Popup-Informationen hinzufügen
function onEachFeature(feature, layer) {
    if (feature.properties && feature.properties.wasserUnterKiel) {
        layer.bindPopup(feature.properties.name + ": " + feature.properties.wasserUnterKiel+" Meter");
    }
}

// Punkte einfärben, abhängig von wasserUnterKiel
function style(feature) {
    var fillColor;
    if (feature.properties.wasserUnterKiel < 0.3) {
        fillColor = '#ff6e2b';  // orange
    } else {
        fillColor = '#65ff00';  // gruen
    }
    return {
        radius: 8,
        weight: 0.1,
        fillColor: fillColor,
        fillOpacity: 0.8
    };  
}