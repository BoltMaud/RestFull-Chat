window.onload = function(){

    var url = window.location.href;
    var params = getUrlParams(url);

    var pseudo= params["username"];
    $('#pseudo').text(pseudo);
    getlistsaloon();
}
function addSaloon(){
    var name=document.getElementById("addsaloon").value;
    $.ajax({
        url:"../../saloon",
        method:"POST",
        data:"salon="+name,
        success:function(data){
            getlistsaloon();
        }
    })
}

function getlistsaloon(){
    var pseudo=document.getElementById("pseudo").innerHTML;
    $.ajax({
        dataType: "json",
        accepts: {
            text: "application/json"
        },
        url:"../../saloon/list",
        method:"GET",
        success:function(data){
            if(data.length==0){
                document.getElementById("saloons").style.display="none";
            }else {
                document.getElementById("saloonsSelect").innerHTML="";
                console.log(data);
                for(var i=0; i<data.length; i++){
                    document.getElementById("saloons").style.display="block";
                    document.getElementById("saloonsSelect").innerHTML+='<a href="saloon.html?saloon='+data[i]+'&username='+pseudo+'" > '+data[i]+' </a> <br>';
                }
            }
        }
    })
}

function getUrlParams(url) {
    var params = {};
    url.substring(1).replace(/[?&]+([^=&]+)=([^&]*)/gi,
        function (str, key, value) {
            params[key] = value;
        });
    return params;
}