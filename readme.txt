1. Start HSQLSB server
C:\setups\hsqldb-2.4.1\bin>java -cp ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:C:\setups\DB\whdb\whdb --dbname.0 whdb

2. Start runManagerSwing to access DB
double click - > C:\setup\hsqldb-2.4.1\bin\runManagerSwing

3. Kill tomcat running within spring boot
netstat -ano | find "8080"
taskkill /F /PID 2516

master data setup
1. add admin user
2. add vehicle types
3. add load types
4. add roles

TO-DO
1. Old dates disable in calendar 
2. Master data setup on startup
3. Bulk data upload for : client, user, requirement . clients are done through selanium
4. Validation rules on vehicle type & load type combination while registering requirement. 
5. integrate sms so incoming quotes can update requirement.

Teardown for test data
status column check in appstatus 