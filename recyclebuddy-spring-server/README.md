## This is mostly copy-paste from my previous Spring project. Stuff will probably different when integrating with Android Studio, but the general project structure should remain the same. Anything marked with an asterisk is something that might change.

### PROJECT STRUCTURE

Here’s a breakdown of the project structure:

### Application Class:
This is where the main method lives, and where you’ll hit run to actually run the code.

### Models:

Each entity is represented by a class within the Models directory. Entities that represent tables are annotated with the @Entity tag. JPA autogenerates the rows and columns provided within the class.

### Repo:

This is what represents the MySQL table itself, to call on in Java. These are needed to actually save to and query the tables, which we can do in our Controllers class. Each entity/table needs its own repo.

### Controller\*:

The controllers are where we define our create/read/update/delete functionality and control navigation between webpages. 
Note: controller might be different for Android Studio integration, but in a standard Spring project this is how it's structured.

### Resources:
All CSS, JS, and HTML files are stored here. These are what make up the frontend of the web application. 

### RUNNING THE PROJECT

Alright, now onto actually running the project. Since there isn't a .jar file as of now, download the code zip file from GitHub. 
Open the project in your favorite IDE. You can also look at files manually and compile via Command Line or Terminal if you prefer to run code this way.

Make sure you have **Maven** installed so that the various dependencies can work.
### Application Properties

First, you’ll need to take a look at main/resources/application.properties. 

These are the important parts:
```
spring.datasource.url=jdbc:mysql://localhost:3306/userdb?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=<your root password here>
```

In the first line, after the "3306/" part of the url, userdb is the name of the database schema you will need to create in MySQL, whether you do it through command line/terminal or via MySQL Workbench. 
I recommend Workbench, because it has UI and it’s easier to see changes to the database when you’re testing the application. 
If for some reason you want to change the name of the DB, change it in application.properties and in the following steps, name it what you wish.

The DB name, username, and password in application.properties MUST MATCH what you have in MySQL.
Whatever root username and password you set upon installing MySQL is what you need to use.

### Start Your MySQL Server
First, start your MySQL Server. 
On Mac, this is how you can do it with Terminal: 
```
sudo /usr/local/mysql/support-files/mysql.server start
```
If you installed MySQL outside of the default location, you will need to adjust the filepath in the above command.

### Setting Up the Database In MySQL Workbench
If you need some images for assistance, check the **MySQL Image Guide PDF**.
There will be annotations referring to these images in this guide, but they're not required if you know what MySQL Workbench looks like.

Here’s how to set the DB up in Workbench.

Click the + icon _(Image 1)_. Unless you changed your username when creating your MySQL info, keep everything as is. Port should remain as 3306. Put cs157afinaldb as the Connection Name (or whatever you choose, just make sure it matches in application.properties).

Set the password to whatever password you set when you first set up MySQL. Your username should be root, unless you changed that at setup. If your screen looks like this _(Image 2)_, hit Store in Keychain to set the password.

Now, click Test Connection. Enter your password. If your server started successfully and your info is all correct, you should see something like this: _(Image 3)_

Click OK, then click OK on the Setup New Connection dialogue. You can now open the DB in MySQL and view its contents, yay!

#### Inside the database, create a new schema called _**userdb**_. This is where our tables will be added, without it you will get errors!!

### Running The Application
Let's head back to our project.

Assuming you're in an IDE, navigate to the Application class. Run the main method. Once it says “Started Application in X seconds”, in the console, you’re good to go!

Right now, there is no front end, so that's where these instructions will end. Normally, you'd check the front end with localhost/8080 but since we're doing android studio stuff, it might be different.
