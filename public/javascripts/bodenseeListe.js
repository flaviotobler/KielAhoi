function loadList(){
    var req = new XMLHttpRequest();
    req.open("GET","ports",false)
    req.send();
    console.log(req.response);
    JSON.parse(req.responseText).forEach(function(port){
        if(port.height - document.getElementById("tiefgangInput").value <= 0)
        {
            document.getElementById(port.id).style.backgroundColor = "red";
        }
        else
        {
            document.getElementById(port.id).style.backgroundColor = "green";
        }
    })
}