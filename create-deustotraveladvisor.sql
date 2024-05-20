/* DELETE 'deustoTravelAdvisor_user' database*/
DROP SCHEMA IF EXISTS deustotraveladvisor;
/* DELETE USER 'deustoTravelAdvisor_user' AT LOCAL SERVER*/
DROP USER IF EXISTS 'deustotraveladvisor_user'@'localhost';

/* CREATE ''deustoTravelAdvisordb' DATABASE */
CREATE SCHEMA IF NOT EXISTS deustotraveladvisor;
/* CREATE THE USER 'strava_user' AT LOCAL SERVER WITH PASSWORD 'strava_user' */
CREATE USER IF NOT EXISTS 'deustotraveladvisor_user'@'localhost' IDENTIFIED BY 'password';
/* GRANT FULL ACCESS TO THE DATABASE 'strava' FOR THE USER 'strava_user' AT LOCAL SERVER*/
GRANT ALL PRIVILEGES ON deustotraveladvisor.* TO 'deustotraveladvisor_user'@'localhost';
FLUSH PRIVILEGES;