1. Start HSQLSB server
C:\setup\hsqldb-2.4.1\bin>java -cp ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:C:\setup\DB\erdb\erdb --dbname.0 erdb

2. Start runManagerSwing to access DB
double click - > C:\setup\hsqldb-2.4.1\bin\runManagerSwing

3. Start application from eclipse for now later need java jar to execute

4. Kill tomcat running within spring boot
netstat -ano | find "8080"
taskkill /F /PID 2516