Programming school is java database application.
The application is based on project Maven and uses mysql connector dependency to provide java connection to mySQL server.
While creating application i used data access object design pattern that 
provide access from java object like Group, User, Solution, Exercise to related
tables and columns in Programming_school database source.
The users passwords in database are stored hashed by BCrypt class - bcrypt dependency.  
For example: user can find exercise, solution by id or find all resolved exercises by specific user.
By using data access object and administration panel user can add, delete, read, update 
all data from console which are constantly updated in the database during program execution.
I would like to develop the application with functions such as:
1.Adding resolved tasks by user from user interface.
2.Adding rating and comments to tasks solutions.
3.Adding rank of the bests users.

