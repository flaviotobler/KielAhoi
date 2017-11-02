function loadSiteMap(){
    var req = new XMLHttpRequest();
    req.open("GET","portmap",false);
    req.send();
    console.log(req.response);
    document.getElementById("content").innerHTML = req.responseText;
    loadMap();
}

function loadSiteList(){
    var req = new XMLHttpRequest();
    req.open("GET","portlist",false);
    req.send();
    console.log(req.response);
    document.getElementById("content").innerHTML = req.responseText;
}

