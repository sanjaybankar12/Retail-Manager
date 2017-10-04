# Retail-Manager

Requirements :

    Spring Boot Version = 2.0.0,
    JDK Version = 1.7 or later,
    gradle version = 4.2 (optional)

First you can clone this project from github by using command as :
>git clone https://github.com/sanjaybankar12/Retail-Manager.git

or download as a zip file and unzip it.

After cloning or unzipping it, Open Git Bash if not available then you can use our normal command prompt otherwise download Git Bash from link given below : 
>https://git-scm.com/download/

Go to the project root folder by Git Bash or normal command prompt, then you have to use gradle wrapper to build your project for that you have to use command on Git Bash as :
>./gradlew build

and for normal command prompt you have to use as(not prefix './') :
>gradlew build

Now after this command build directory is created with lots of sub-directories which mainly contains following directories as :
      
     1) classes = compile classes
     2) reports = test reports details
     3) libs = jar files of project

Finally, you have to run project by using command on Git Bash as : 
>./gradlew run

and for normal command prompt you have to use as(not prefix './') :
>gradlew run

Use Following URL to add shops :
> http://localhost:3000/shops

This example shows that new shop is added : 

![alt text](https://github.com/sanjaybankar12/Retail-Manager/blob/master/new_shop.png)

This example shows that old shop is updated : 

![alt text](https://github.com/sanjaybankar12/Retail-Manager/blob/master/upd_shop.png)

Use Following URL to get nearest shop :
> http://localhost:3000/nearestshop?customerLatitude=18.5511450&customerLongitude=73.9377253

This example shows that nearest shop for provided latitude & longitude : 

![alt text](https://github.com/sanjaybankar12/Retail-Manager/blob/master/near_shop.png)

