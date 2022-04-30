# BookStoreApi

* I implemented the Event Sourcing Pattern pattern to solve the stock consistency issue. 
* I created Stock Event Bus, transactions related to a book such as stock increase or stock decrease are collected here.
* Each day at 4 am, scheduled task update the stock of each book and delete data in event bus 

# Tech 
* Spring Security, Docker, PostgreSQL, Redis, Swagger , Junit

# End-Points 

![end points](https://user-images.githubusercontent.com/57045507/163176140-1524f85f-fc8a-4286-833e-bde78d4b83ed.PNG)
