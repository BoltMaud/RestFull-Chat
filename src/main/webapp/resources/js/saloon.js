
//---------------------------------------------------------------------------------
setInterval(function(){getMessages();},5000);
//---------------------------------------------------------------------------------
function sendMessage(){
    var url = window.location.href;
    var params = getUrlParams(url);

    var salon= document.getElementById("salon").value;
    var pseudo=document.getElementById("pseudo").value;
    var message=document.getElementById("message").value;
    $.ajax({
        dataType: "json",
        accepts: {
            text: "application/json"
        },
        url:"../../saloon/"+salon +"/message",
        method:"POST",
        data:{
            pseudo:pseudo,
            salon :salon,
            message:message
        },
        statusCode: {
            202: function () {
                getMessages(true);
                document.getElementById("message").value="";
            }
        }
    })
}
//---------------------------------------------------------------------------------
function deleteMessage() {
    var id=document.getElementById("idLast").value;
    var salon= document.getElementById("salon").value;
    $.ajax({
        dataType: "json",
        accepts: {
            text: "application/json"
        },
        url:"../../message/"+salon +"/"+id,
        method:"DELETE",
        statusCode: {
            202: function () {
                getMessages(false);
            },
            403:function () {
                alert("This is not the last message !");
            }
        }
    })
}
//---------------------------------------------------------------------------------
function onLoad(){
    getMessages(false);

};
//---------------------------------------------------------------------------------
//test is true if getMessage came from sendMessage : it's keep the last id to know if we can delete or not
function getMessages(test){
    var url = window.location.href;
    var params = getUrlParams(url);
    document.getElementById("pseudo").setAttribute("value",params["username"]);
    document.getElementById("salon").setAttribute("value",params["saloon"]);
    document.getElementById("saloon").innerHTML=params["saloon"];
    var name= params["saloon"];
    $.ajax({
        dataType: "json",
        accepts: {
            text: "application/json"
        },
        url:"../../saloon/"+name +"/messages",
        method:"GET",
        success:function(data){
            document.getElementById("chat").innerHTML="";
            for(var i=0; i<data.length;i++){
                document.getElementById("chat").innerHTML+="<p>"+data[i]["author"]["name"]+" : "+data[i]["text"]+" </p>";
            }
            if(test) {
                document.getElementById("idLast").value = data[data.length - 1]["id"];
            }
        }
    })
}
//---------------------------------------------------------------------------------
function getUrlParams(url) {
    var params = {};
    url.substring(1).replace(/[?&]+([^=&]+)=([^&]*)/gi,
        function (str, key, value) {
            params[key] = value;
        });
    return params;
}
//-----------------------------------------------------------------------------------
function updateMessage(){
    var id=document.getElementById("idLast").value;
    var salon= document.getElementById("salon").value;
    var message=document.getElementById("message").value;

    $.ajax({
        url:"../../message/"+salon +"/"+id+"?messagetext="+message,
        method:"PATCH",
        statusCode: {
            202: function () {
                getMessages(true);
            },
            403:function () {
                alert("This is not the last message !");
            }
        }
    })
}
//-----------------------------------------------------------------------------
