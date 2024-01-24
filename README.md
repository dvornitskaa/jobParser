# Project Job parser

This readme contains the Backend API, written in [Java](https://www.java.com) using the [Spring Framework](https://spring.io).

Application that can scrape job listings from jobs.techstars.com
based on specific job functions. The application should allow me to input the desired work
functions or job categories I am interested in, and then it should automatically crawl the
website to gather relevant job postings for those functions. The scraped data should be
presented in a user-friendly format, showing key details. Additionally, I would like the
application to have an option to filter or sort the results based on different criteria, such as
job location or posting date. This will help me efficiently find and explore job opportunities
tailored to my preferences, ultimately simplifying the job search process and improving my
overall experience on the jobs.techstars.com platform.

## Application Configuration

### Database


To configure communications with a database, the following environment variables are used:

+ `MYSQL_URL` - a MySQL database connection string (ex: `localhost:3306/jobs`)
+ `MYSQL_USERNAME` - a MySQL database username (ex: `admin`)
+ `MYSQL_PASSWORD` - a MySQL database password (ex: `Asmdsn@#$!J`)


## Database dump

File [mysql_dump_1.scv](mysql_dump_1.scv) contains the database dump with 59 job records for IT and Product functions as example.