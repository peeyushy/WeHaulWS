1. Start HSQLSB server
C:\setups\hsqldb-2.4.1\bin>java -cp ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:C:\setups\DB\erdb\erdb --dbname.0 erdb

2. Start runManagerSwing to access DB
double click - > C:\setup\hsqldb-2.4.1\bin\runManagerSwing

3. Start application from eclipse for now later need java jar to execute

4. Kill tomcat running within spring boot
netstat -ano | find "8080"
taskkill /F /PID 2516

master data setup
1. add admin user
2. add vehicle types
3. add load types
4. add roles

TO-DO
1. Old dates disable in calendar 
2. Picture upload client, user and vehicle
3. Remove native sql queries search for createNativeQuery all these needs to be replaced with JPQL
4. Master data setup on start
5. Bulk data upload for : client, user, vehicle, load
6. book Load page: add google map for pickup & drop location
7. Validation rules on vehicle type & load type combination while registering vehicle. 
8. integrate tracker on booked load/vehicle flow
9. service to auto expire old loads post ldatetime to run in background lets say every 30min
10. integrate sms so incoming can create load entry
11. show available vehicle & load details on mouse hover of each cell at client edit
12. check how search on location would work with google places api as in db we have string stored.
13. Get load search option by load id, load status

Teardown for test data
status column check in appstatus 