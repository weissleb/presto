# Hive Grant Issue

Creating a prepared statement to GRANT privileges, quotedness is not retained, and therefore the prepared statement creation may fail.

*Steps to reproduce:*

1. Run `plugin/trino-hive/src/test/java/io/trino/plugin/hive/HiveQueryRunner.java` to start a server with a Hive catalog.
2. Run `client/trino-cli/target/trino-cli-357-SNAPSHOT-executable.jar` with `--catalog hive --user admin` arguments to start a cli session as admin.
3. Set the user's role to admin via ...
   ```
   SET ROLE admin;
   ```
4. Create a new schema such as ...
   ```
   PREPARE createschemastmt FROM CREATE SCHEMA test_grant;  
   EXECUTE createschemastmt;
   ```
5. Create a new table in that schema where the table name is a reserved word such as ... 
   ```
   PREPARE createtablestmt FROM CREATE TABLE test_grant."case" (k int, v varchar);
   EXECUTE createtablestmt;
   ```
6. Grant privileges on the new table via a SQL command such as ...
   ```
   GRANT SELECT ON test_grant."case" TO ROLE public;
   ```
7. Grant privileges on the new table via a prepared statement such as ... THIS WILL FAIL!
   ```
   PREPARE grantstmt FROM GRANT SELECT ON test_grant."case" TO ROLE public;
   ```

Notice, the last `PREPARE` query will fail with _Formatted query does not parse_.
