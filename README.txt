Fantasy Sports
---------------

Setup
------
The project was build in NetBeans and is configured for Maven.
The POM file lists all of the dependencies.  It is easiest to
import into NetBeans and "Build with Dependencies" from there.

The project is tested and works with JRE 1.7.  Some of the 
jsps use HTML5, so do not run in IE.  Development was tested in
Chrome, though Firefox is also likely okay.

Persistence is set up through the persistence.xml file.  The 
application was developed and tested with derby as a backend
DB.

Prior to starting the application, run the commands listed in
FFDBScema file.  The application will not work without that 
being done prior to startup.  After the tables are created, the 
application should be ready to use.  By default, there is NO data
in any of the tables.

Use
----
The application is fairly self-explanatory to use and navigate.
The only point I would mention is that you should create a user
named 'admin' first.  The 'admin' user has access to special menu
options, 'add players' and 'set player scores'.  Without those actions,
the application is useless for any other users.