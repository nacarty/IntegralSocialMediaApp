# IntegralSocialMediaApp
## Exercise in fulfillment of Integral.io job interview

This application is a simple Spring-Boot Web MVC application using Java at the middle tier and HTML/Thymeleaf on the front-end.

The core of the application consists of two Java objects - MediaPoster and Post. There is a service class that dynamically 
instantiates three (3) MediaPosters and assigns a few posts to each MediaPoster. The code also assigns one of the MediaPosters
a follower of the other two. 

Given the limited functionality expected for this application, the user does not yet have the capapbility to create or delete 
MediaPosters, create or delete posts, or make a MediaPoster follow or unfollow others.These are obvious limitations which can 
be overcome in the next iteration of the Software Development Life Cycle.

As to the mechanics of the application, the following features were critical to its functioning as required:
1. Each post created receives an automatically-generated id based on the system time. This makes each post unique
2. The Post class implements the Comparable<> interface and defines the compareTo method to enable efficient sorting
3. PriorityQueues<> were used as the data structures to store the posts as this data structure automatically sorts its data
4. For each MediaPoster, a list of followers as wells as a list of following were maintained. This facilitated efficient 
   cross-referencing to ensure that the system remains consistent
5. ArrayLists were used to maintain the lists described in 4 above. This data structure is ideal for maintaining unsorted data

## How To Use The App
1. Download the code from the maven project and build it in your local IDE
2. Run the application using: $mvn spring-boot:run
3. Open your browser to http://localhost:8080/socialmedia/ and perform any of the following requests:
-  http://localhost:8080/socialmedia/own?name=Matt   (Substitute 'Matt' in this request with Jill or Jack)
-  http://localhost:8080/socialmedia/following?follower=Matt&followee=Jill   (Substitute any name in this request with Jill, Jack or Matt)
-  http://localhost:8080/socialmedia/aggregated?name=Matt   (Substitute 'Matt' in this request with Jill or Jack)

Nigel Carty
August 24, 2021
