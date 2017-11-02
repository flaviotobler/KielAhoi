function loadSiteMap(){
    console.log("part2");
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
    loadList();
}

function tiefgangControll(){
    console.log("part1");
    if(document.getElementById("mapbox") == undefined){
        loadSiteList();
    }
    else{
        loadSiteMap();
    }
}

function hallo(){
    console.log("hallo");
}
