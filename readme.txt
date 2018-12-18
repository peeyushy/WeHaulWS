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
2. Left/right icon missing in calendar
3. Picture upload client, user and vehicle
4. Remove native sql queries search for createNativeQuery all these needs to be replaced with JPQL
5. Master data setup on start
6. Bulk data upload for : client, user, vehicle, load
7. add/edit Load page: add google map for pickup & drop location
8. Validation rules on vehicle type & load type combination
9. integrate tracker
10. service to auto expire old loads
11. integrate sms so incoming can create load entry
12. show available vehicle & load details on mouse hover of each cell
13. check how search on location would work with google places api as in db we have string stored. 