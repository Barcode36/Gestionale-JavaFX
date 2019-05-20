# Gestionale-JavaFX
# version 0.3.2 ALPHA

This software is released "as is" and it is under development. To develop the graphic interface i'm using SceneBuilder for Java FX. 
For any suggestion or to report a bug please contact the developer.
The database runs on postgresql.

History

0.1.0 ALPHA
Added GestoreOrdini class and Scheduler class.

0.2.0 ALPHA
Added the save on TXT file and loading function.

0.3.0
Added a database to store data and deleted the BufferedReader to load objects from TXT.
There are only 2 tables : Anello and Bracciale. I'll add other tables in next version.

0.3.2
fixed the bug when you saved data twice and raised an SQLException because the data
was already saved in the database.
