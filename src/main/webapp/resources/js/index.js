//---------------------------------------------------------------
function valideUser(){
    var username=document.getElementById("usernameajax").value;
    console.log(username);
    $.ajax({
        url:"../../user",
        method:"POST",
        data:"username="+username,
        success:function(data){
            window.location = 'connected.html?username=' + username;
        },
        statusCode: {
            409: function () {
                window.location = 'connected.html?username=' + username;
            }
        }


    });
}