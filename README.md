## Description

Opera app is an application to manage tickets bought on the occasion of opera session by a user.

## Setting

file src/main/resources/`hibernate.cfg.xml` is to be configured under RDBMS in use

## Usage

Guest (unauthorized and non registered) can do the fllowing:
- register 
- login
- get all available performance sessions according to request
- view current stages in Database
***

Authenticated user:
- has the same capabilities as the guest + capability to 
  - purchase tickets
  - add tickets to shoping cart
  - view purchase history
  - complete orders
  - check current shopping cart
***

Admin:
- has the same capabilties of user, additionally can 
  - add new performance sessions
  - manage stages
  - manage performances
  - administer schedule
  - add user/remove user

### Technologies: 
Spring - Core / MVC / Web / Security

MySQL

Hibernate

Apache Maven

Tomcat

JSON
