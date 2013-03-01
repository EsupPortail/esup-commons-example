In order to use example-web-jsf-servlet :
- install maven modules 
  --> mvn install
- go to example-web-jsf-servlet directory
  --> cd example-web-jsf-servlet
-- The first time, initialize the database 
   --> java -jar -Dconf=src/main/resources/properties/config.properties ~/.m2/repository/org/esupportail/example-batch/1.0-SNAPSHOT/example-batch-1.0-SNAPSHOT.jar init-db
-- Earch time, run the application 
   --> mvn jetty:run
