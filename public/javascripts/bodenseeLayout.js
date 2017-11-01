function loadSiteMap(){
    var req = new XMLHttpRequest();
    req.open("GET","portmap",false);
    req.send();
}

function loadSiteList(){
    var req = new XMLHttpRequest();
    req.open("GET","portlist",false);
    req.send();
}

