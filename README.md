PictureThis is an implementation of an image-based graphical password full stack 
authentication system. 
The Backend is a SpringBoot application while the frontend is an Angular 15 application.


This web app is meant to add an additional layer of security to your standard username and password combination while also being easier to remember than other text-based verification methods like security questions, etc.

<h3> How it works:</h3>

How PictureThis works is very simple. at sign-up, the user uploads a picture of their selection along with their standard info like their username, email, etc. The picture is then taken and split up into 4 sections. The user is then given an 8x8 grid and each picture chuck is dragged into one of the grid cells, once all pictures are dragged into a cell a serial number is created for the user which is then stored along with their passwords. 

At log-in, the user is presented with their chosen picture along with the grid for them to 
fill in the cells with picture chunks identical to the combo provided at sign-up.


<h3>How to use:</h3>

This web app has not been deployed yet but you can take a look at it by cloning this repo.

1. <h4>Frontend:</h4>:
    1.1.After cloning this repo, open the Frontend folder in your preferred IDE. 
    1.2.Open src folder in terminal and type "npm install" to intstall node_modules.
    1.3.Next, type "ng serve" in terminal and now the frontend of the app should open on
    port 4200.

    
2. <h4>Backend:</h4>:
    2.1.After cloning this repo, open the Backend folder in your preferred IDE. 
    2.2.Navigate to com.picturethis package.
    2.3.Select and open PictureThisApplication and run it. Springboot Server should now be          running on port 8080.
    
    
<h3>That's cool and all, but why?</h3>


Well, this project was my intro to full stack development as a whole and it was such an intresting idea. Plus it is always nice to invent and improve on new ways of user authentication while maintaining accessibility and user-friendliness. 






