During my 4th year in Computer Sciences at the University of Lyon, Titouan Chary and I have programmed a chat application. 

## Maven 
To run the project, type the command: <br>
*mvn install*

## Spring 
The project is working with the framework Spring. 

## static html vs jsp
The project has two versions a dynamic and a static version, both are available by /index.html

## RestFull Application
The application follows the REST aspects : <br>
/user			POST<br>
/user/list		GET<br>
/user/{id}		GET<br>
/user/{id}		PATCH<br>
/user/{id}/saloons	GET<br>
/user/delete/{idUser}	DELETE XXX <br>
/message/{salon]/{idMessage} UPDATE<br>
/message/{salon]/{idMessage}  GET <br>
/message/{salon]/{idMessage}  DELETE<br>
/saloon				POST<br>
/saloon/list			GET<br>
/saloon/messages		GET<br>
/saloon/message 		POST<br>
/saloon/{salon}/messages/{index} GET<br>
/saloon/{idSaloon}		Delete <br>
