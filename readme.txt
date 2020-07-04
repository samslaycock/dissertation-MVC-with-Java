
Connecting JavaDB to NetBeans
-------------------------------------
- In NetBeans, go to the Services tab and right-click on Databases -> Java DB and select Properties
- Set the Database Location to the "Derby" folder included in the zip

Configuring NetBeans to dissdb
-------------------------------------
- In NetBeans, go to the Services tab and right-click on Databases and select New Connection
- Ensure derby jar files are in the Project libraries (already added so should work, though may need importing again)
- Leave Username and Password blank and make sure JDBC url is "jdbc:derby://localhost:1527/dissdb"

Running Java DB
-------------------------------------
- In NetBeans, go to the Services tab and right-click on Databases -> Java DB and select Start Server


Any difficulties, email at samslaycock@gmail.com or 23253444@edgehill.ac.uk