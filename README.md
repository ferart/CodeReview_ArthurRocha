# Code Review by Arthur Rocha


This Project points out some errors in code, and give solutions and best practices to follow, also propose a new Architecture for the application and some frameworks to include and why.

#Architecture of the application

I propose the next architecture in order to have a maintainable, expandable and robust project, also to have a readable and reusable code.
The project is separated in tree layers:

0. 	The presentation Layer, has the UI shown to the user, this layer implements the Model-View-Presenter design pattern, 	so we can separate the data representation from the Views that show them (Activities, dialogs, fragments), also the 		Adapter can implement the viewholder patter, wich become easier using butterknife framework.

0. 	The Business Layer contains all the logic, sorting lists of data, perform heavy operations, parse data, etc.

0. 	The Data Access Layer contains the connections to the database, and perform queries to insert, save, delete, update 		some data to the DataBase, or another DataSource (a datasource can be a webservice, a database etc.).  This uses 		the DAO design pattern.
    
    
Files in Github project
-------
* [Source code]
* [PDF file with the guidelines]
* [APK]

    
![alt tag](https://github.com/ferart/CodeReview_ArthurRocha/blob/master/architecture.png)
![alt tag](https://github.com/ferart/CodeReview_ArthurRocha/blob/master/screenshot.png)
