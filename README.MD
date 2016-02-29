# Hair Salon

##### Epicodus exercise using Java and Postgres, 2/26/16

##### Brad Pizzimenti

## Description
This exercise allowed for work with Java and Postgres databases. The criteria were to create a hair salon database containing "clients" and "stylists". Stylists were to have a one-to-many relationship with clients necessitating a stylist_id column within the clients table.

## Setup

Clone this repository:
```
$ cd ~/Desktop
$ git clone https://github.com/pizzimenti/java15
$ cd java15
```

Open terminal and run Postgres:
```
$ postgres
```

Open a new tab in terminal and create the `best-restaurants` database:
```
$ psql
$ CREATE DATABASE hair_salon;
$ psql hair_salon < hair_salon.sql
```

Navigate back to the directory where this repository has been cloned and run gradle:
```
$ gradle run
```

## Legal

Copyright (c) 2015 ***Brad Pizzimenti***
