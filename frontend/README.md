# PictureThis

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 15.1.2.


<h2>Dependicies:</h2>

In order for frontend to run properly we need to use npm to install http-server to serve up images uploaded to server since they are uploaded to a local folder. 
Most browsers dont allow for local files to be opend for security reasons

(This isn't the case when this project is deployed. Images are to be uploaded and served by a remote server or a cloud service but this just for demo and we're using a server here as well)

<h3>So how do I set it up?</h3>

1.Open terminal and type npm install -g http-server


2.Go to the image upload folder on your machine, you'll find it under "C:\Users\yourname\Documents\GPIS" and open it in terminal.


3.Now type http-server ./


Now make note of the port number in  the output of  terminal, something kinda like http://localhost:8081 will appear. Now everything in that folder will be okay for browser to serve under that port number.
Example : "img src = "http://localhost:8081/imagename.png"





