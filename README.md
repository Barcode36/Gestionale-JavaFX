# Gestionale-JavaFX
# version 0.5.6 BETA

This software is released "as is" and it is under development. To develop the graphic interface i'm using SceneBuilder for Java FX. 
For any suggestion or to report a bug please contact the developer.
The database runs on postgresql.

Changelog:

0.1.0 ALPHA
Added GestoreOrdini class and Scheduler class.

0.2.0 ALPHA
Added the save on TXT file and loading function.

0.3.0 ALPHA
Added a database to store data and deleted the BufferedReader to load objects from TXT.
There are only 2 tables : Anello and Bracciale. I'll add other tables in next version.

0.3.2 ALPHA
Fixed the bug when you saved data twice and raised an SQLException because the data
was already saved in the database.

0.3.5 ALPHA
Added other tables into database and fixed some bugs.

0.3.7 ALPHA
Added the saving of orders on database. Customers and products are in ArrayList and if you want save customers and products you must
click "Save" by MenuItem in menu bar, while the orders are saved directly on database. I know, this is a "bug" and i will fix it later.

0.3.9 ALPHA
Added a Thread that saves every 5 minutes, added PreparedStatement to query the database, added view of orders in listViewOrdini, added the delete of customers (when you delete a customer the query starts on database and he is deleted directly on db and not in ArrayList), other bugfixes. P.S. The "Save bug" of the previous realease is still there.

0.5.0 BETA
I'm happy to announce BETA phase!!
There are almost all basic features to manipulate orders, customers and objects. The "Save bug" is still there. Added a dialog windows(when you press on X to close the window, opens a window that remind you to save) but there are some bugs on it. I'll update database's tables later cause some bugs.

0.5.5 BETA
The "Save bug" is fixed, now all changes are made directly in the database. Added Fatture in model package, added Fatture tab in view and listViewFatture. Until the end of development i'll backup all database and upload here.

0.5.6 BETA
Starting to add Material design with JFoenix and some bugfix.
